package com.example.emoji_prediction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    Button btnEmojiPrediction, btnSearchEmoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnEmojiPrediction = findViewById(R.id.btnEmojiPrediction);
        btnSearchEmoji = findViewById(R.id.btnSearchEmoji);

        btnEmojiPrediction.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, EmojiPrediction.class);
            intent.putExtra("source", "home");
            startActivity(intent);
        });

        btnSearchEmoji.setOnClickListener(v -> startActivity(new Intent(Home.this, SearchEmoji.class)));

    }
}
