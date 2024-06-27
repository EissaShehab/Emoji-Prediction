package com.example.emoji_prediction;

import com.google.gson.annotations.SerializedName;


public class EmojiData {

    public EmojiData(String category, String tags, String emoji, String aliases) {
        this.category = category;
        this.tags = tags;
        this.emoji = emoji;
        this.aliases = aliases;
    }

    @SerializedName("category")
    private String category;
    @SerializedName("tags")
    private String tags;
    @SerializedName("emoji")
    private String emoji;



    @SerializedName("aliases")
    private String aliases;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }
}
