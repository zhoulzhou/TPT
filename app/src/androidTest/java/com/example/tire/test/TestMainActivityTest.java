package com.example.tire.test;

import android.test.ActivityInstrumentationTestCase2;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestMainActivityTest extends ActivityInstrumentationTestCase2<TestMainActivity> {

    private TestMasinActivity testMainActivity;

    public TestMainActivityTest() {
        super(TestMainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        testMainActivity = getActivity();
    }

    @Test
    public void testcheckValue() {
        ArrayList<String> arrayList = new ArrayList<>(80);
        arrayList.add("tate");

        for(int i=0; i<arrayList.size();i++){
            String source = arrayList.get(i);
            String target = source;
            testMainActivity.checkValue(source,target);
        }

    }
}