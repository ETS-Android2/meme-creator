package com.moringaschool.memecreator;

import static org.junit.Assert.*;

import android.widget.TextView;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


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

}