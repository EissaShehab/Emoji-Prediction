package com.example.emoji_prediction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
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
public class EmojiPredictionTest {

    @Rule
    public ActivityScenarioRule<EmojiPrediction> activityRule =
            new ActivityScenarioRule<>(EmojiPrediction.class);

    @Test
    public void testHappyEmojiPrediction() {
        // Type text, close keyboard, and then press the button.
        onView(withId(R.id.edtEnter)).perform(typeText("I am so happy"), closeSoftKeyboard());
        onView(withId(R.id.btnPrediction)).perform(click());

        // Add a short delay to wait for the prediction result.
        try {
            Thread.sleep(2000); // Adjust the duration as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check that the emoji is displayed.
        onView(withId(R.id.tvEmoji)).check(matches(withText("😀")));
    }

    @Test
    public void testSadEmojiPrediction() {
        // Type text, close keyboard, and then press the button.
        onView(withId(R.id.edtEnter)).perform(typeText("I am so sad"), closeSoftKeyboard());
        onView(withId(R.id.btnPrediction)).perform(click());

        // Add a short delay to wait for the prediction result.
        try {
            Thread.sleep(2000); // Adjust the duration as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check that the emoji is displayed.
        onView(withId(R.id.tvEmoji)).check(matches(withText("😞")));
    }

    @Test
    public void testNeutralEmojiPrediction() {
        // Type text, close keyboard, and then press the button.
        onView(withId(R.id.edtEnter)).perform(typeText("I am feeling okay"), closeSoftKeyboard());
        onView(withId(R.id.btnPrediction)).perform(click());

        // Add a short delay to wait for the prediction result.
        try {
            Thread.sleep(2000); // Adjust the duration as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check that the emoji is displayed.
        onView(withId(R.id.tvEmoji)).check(matches(withText("😀")));
    }
}
