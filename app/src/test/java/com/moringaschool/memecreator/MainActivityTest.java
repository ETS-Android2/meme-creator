package com.moringaschool.memecreator;

import static org.junit.Assert.*;

import android.content.Intent;
import android.widget.TextView;

import com.moringaschool.memecreator.ui.MainActivity;
import com.moringaschool.memecreator.ui.CreatedMemeActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;


@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void textViewDisplaysCorrectValue() {
        TextView textView = activity.findViewById(R.id.welcomeText);
        assertEquals("MEME CREATOR!", textView.getText().toString());
    }

    @Test
    public void secondActivityGetsStarted() {
        activity.findViewById(R.id.button).performClick();
        Intent intent = new Intent(activity, CreatedMemeActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent expectedIntent = shadowActivity.getNextStartedActivity();
        assertTrue(intent.filterEquals(expectedIntent));
    }

}