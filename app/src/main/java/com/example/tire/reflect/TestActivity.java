package com.example.tire.reflect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        re1();
    }

    private void re1(){
        try {
            Class clazz1 = Class.forName("com.example.tire.reflect.User");
            User user = (User) clazz1.newInstance();
            System.out.println(user.toString());
            Constructor constructor = clazz1.getConstructor(String.class);
            User user1 = (User) constructor.newInstance("ref-con-name");
            System.out.println("user1= " + user1.outInfo());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
