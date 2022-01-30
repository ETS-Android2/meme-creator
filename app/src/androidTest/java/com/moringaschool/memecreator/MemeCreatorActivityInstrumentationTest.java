package com.moringaschool.memecreator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MemeCreatorActivityInstrumentationTest {

    @Rule
    public ActivityScenarioRule<MemeCreatorActivity> activityScenario = new ActivityScenarioRule<MemeCreatorActivity>(MemeCreatorActivity.class);

    @Test
    public void validateTextInput() {
        onView(withId(R.id.memeContent)).perform(typeText("Funny!")).check(matches(withText("Funny!")));
    }

    @Test
    public void memeGetsSentToMemeViewClass() {
        String meme = "Funny!";
        onView(withId(R.id.memeContent)).perform(typeText("Funny!")).perform(closeSoftKeyboard());
        onView(withId(R.id.submittedBy)).perform(typeText("Timothy")).perform(closeSoftKeyboard());
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println(e);
        }
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.viewMemeText)).check(matches(withText(meme)));
    }
}