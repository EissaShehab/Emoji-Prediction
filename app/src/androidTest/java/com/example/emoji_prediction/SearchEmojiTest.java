package com.example.emoji_prediction;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SearchEmojiTest {

    @Rule
    public ActivityScenarioRule<SearchEmoji> activityRule =
            new ActivityScenarioRule<>(SearchEmoji.class);

    @Test
    public void testSearchEmoji() {
        onView(withId(R.id.spincategory)).perform(click());
        onView(withText("Smileys & Emotion")).perform(click()); // Adjust based on your spinner values
        onView(withId(R.id.edtEnter)).perform(typeText("happy"));
        onView(withId(R.id.btnSearchEmoji)).perform(click());
        // Add expected emoji based on your JSON data
        onView(withId(R.id.tvEmoji)).check(matches(withText("😀")));
}
}
