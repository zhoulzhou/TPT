package com.example.tire.application;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TirePressureDetectionActivityTest extends ActivityInstrumentationTestCase2<TirePressureDetectionActivity> {

    private TirePressureDetectionActivity tirePressureDetectionActivity;
    public TirePressureDetectionActivityTest(Class<TirePressureDetectionActivity> activityClass) {
        super(activityClass);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tirePressureDetectionActivity = getActivity();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPrecondition(){
        assertNotNull("activity is not null",tirePressureDetectionActivity);
    }
}