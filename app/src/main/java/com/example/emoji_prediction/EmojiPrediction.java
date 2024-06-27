package com.example.emoji_prediction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmojiPrediction extends AppCompatActivity {

    EditText edEnter;
    TextView tvEmoji;
    Button btnPrediction;
    ImageView ivEmoji;

    private DatabaseReference feedbackDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_prediction);

        edEnter = findViewById(R.id.edtEnter);
        tvEmoji = findViewById(R.id.tvEmoji);
        btnPrediction = findViewById(R.id.btnPrediction);
        ivEmoji = findViewById(R.id.ivEmoji);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            mAuth.signInAnonymously().addOnCompleteListener(this, task -> {
                if (!task.isSuccessful()) {
                    Toast.makeText(EmojiPrediction.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnPrediction.setOnClickListener(v -> {
            String inputText = edEnter.getText().toString();
            Python py = Python.getInstance();
            String result = py.getModule("nlp").callAttr("process_text_and_get_sentiment", inputText).toString();

            if (result.equals("happy")) {
                ivEmoji.setVisibility(View.GONE);
                tvEmoji.setText("😀");
            } else if (result.equals("sad")) {
                ivEmoji.setVisibility(View.GONE);
                tvEmoji.setText("😞");
            } else {
                ivEmoji.setVisibility(View.GONE);
                tvEmoji.setText("😐");
            }
        });

        feedbackDatabase = FirebaseDatabase.getInstance().getReference("Feedback");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null) {
            int id = item.getItemId();
            if (id == R.id.action_feedback) {
                showFeedbackDialog();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFeedbackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_feedback, null);
        builder.setView(customLayout);

        EditText edFeedback = customLayout.findViewById(R.id.edFeedback);
        Button btnSubmitFeedback = customLayout.findViewById(R.id.btnSubmitFeedback);

        AlertDialog dialog = builder.create();

        btnSubmitFeedback.setOnClickListener(v -> {
            String feedback = edFeedback.getText().toString().trim();
            if (!feedback.isEmpty()) {
                String id = feedbackDatabase.push().getKey();
                Feedback feedbackEntry = new Feedback(feedback);
                feedbackDatabase.child(id).setValue(feedbackEntry);

                Toast.makeText(EmojiPrediction.this, "Feedback submitted", Toast.LENGTH_SHORT).show();
                edFeedback.setText("");
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Please enter feedback", Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }
}
