package com.example.emoji_prediction;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<Home> activityRule =
            new ActivityScenarioRule<>(Home.class);

    @Test
    public void testMainScreenDisplayed() {
        onView(withId(R.id.btnEmojiPrediction)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSearchEmoji)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonClicks() {
        onView(withId(R.id.btnEmojiPrediction)).perform(click());
        // Check if the EmojiPrediction activity is launched
        onView(withId(R.id.edtEnter)).check(matches(isDisplayed()));

        // Go back to MainActivity
        pressBack();

        onView(withId(R.id.btnSearchEmoji)).perform(click());
        // Check if the SearchEmoji activity is launched
        onView(withId(R.id.edtEnter)).check(matches(isDisplayed()));
}
}
