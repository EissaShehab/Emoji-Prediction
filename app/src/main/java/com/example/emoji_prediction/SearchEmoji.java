package com.example.emoji_prediction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SearchEmoji extends AppCompatActivity {
    private List<EmojiData> emojiDataList;
    private Spinner spincategory;
    private Button btnSearchEmoji;
    private TextView tvEmoji;
    private EditText edtEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_emoji);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.SearchEmoji), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        spincategory = findViewById(R.id.spincategory);
        btnSearchEmoji = findViewById(R.id.btnSearchEmoji);
        tvEmoji = findViewById(R.id.tvEmoji);
        edtEnter = findViewById(R.id.edtEnter);

        // Load emoji data from JSON file
        loadEmojiData();

        // Set click listener for search button
        btnSearchEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchEmoji();
            }
        });
    }

    private void loadEmojiData() {
        emojiDataList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("Emoji's.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStream.close();

            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                EmojiData emojiData = new EmojiData(
                        jsonObject.getString("category"),
                        jsonObject.getString("tags"),
                        jsonObject.getString("emoji"),
                        jsonObject.getString("aliases")

                );
                emojiDataList.add(emojiData);
            }
        } catch (Exception e) {
            Log.e("SearchEmoji", "Error loading emoji data: " + e.getMessage());
        }
    }

    private void searchEmoji() {
        String selectedCategory = spincategory.getSelectedItem().toString();
        String searchText = edtEnter.getText().toString().trim().toLowerCase();
        for (EmojiData emojiData : emojiDataList) {
            if(emojiData.getCategory().equalsIgnoreCase(selectedCategory) && emojiData.getTags().contains(searchText)) {
                tvEmoji.setText(emojiData.getEmoji());
                return;
            } else if (emojiData.getCategory().equalsIgnoreCase(selectedCategory) && emojiData.getAliases().contains(searchText)) {
                tvEmoji.setText(emojiData.getEmoji());
                return;
            }


        }
        // If emoji is not found, clear the TextView
        tvEmoji.setText("");
    }
}