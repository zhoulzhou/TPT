package com.example.tire.view;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityTestCase;
import android.test.ActivityUnitTestCase;

import com.example.tire.R;
import com.example.tire.application.TirePressureDetectionActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TirePressureDetectionFragmentTest {
    @Rule
    public ActivityTestRule<TirePressureDetectionActivity> mActivityRule =
            new ActivityTestRule<>(TirePressureDetectionActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFragment() throws Exception{
        ViewInteraction car_bg = onView(withId(R.id.car_bg));
        car_bg.check(doesNotExist());
        car_bg.check(matches(isDisplayed()));
        car_bg.perform(click());
    }
}