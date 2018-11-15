package com.example.tire.datafactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TireTableOperatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetValue(){
        Map<String,Float> map = new HashMap<>(16);
        map = TireTableOperator.getInstance().getValue();
        for (Map.Entry<String,Float> entry : map.entrySet()){
            System.out.println("key= " + entry.getKey() + "  value= " + entry.getValue());
        }
    }

    @Test
    public void testInsert(){
        TireTableOperator.getInstance().insert();
    }

    @Test
    public void testgenerateIncreaseNumber(){
        float f= TireTableOperator.getInstance().generateIncreaseNumber();
        System.out.println("f= " + f);
        assertEquals(1.0, f,0);
    }

}