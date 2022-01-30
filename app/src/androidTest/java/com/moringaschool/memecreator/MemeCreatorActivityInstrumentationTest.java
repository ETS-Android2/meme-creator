package com.moringaschool.memecreator;

import static androidx.test.espresso.Espresso.onView;
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
}