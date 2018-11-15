package com.example.tire.database;

import android.content.ContentResolver;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.tire.datafactory.TireTableOperator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TireContentProviderTest {
    ContentResolver resolver ;

    @Before
    public void setUp() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.tire", appContext.getPackageName());

        resolver = appContext.getContentResolver();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testdelete(){
        int re = resolver.delete(TireTableOperator.URI,TireTable.ID + "=?",new String[]{"112"});
        System.out.println("re= " + re);
    }

    @Test
    public void testquery(){
        resolver.query(TireTableOperator.URI,null,null,null,null);
    }
}