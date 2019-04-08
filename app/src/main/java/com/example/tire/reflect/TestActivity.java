package com.example.tire.reflect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

            Constructor[] constructors = clazz1.getConstructors();
            for (int i=0; i<constructors.length; i++){
                Class[] parameterTypes = constructors[i].getParameterTypes();
                System.out.println("第" + i + " 个构造函数");
//                if(parameterTypes.length == 0){
//                    System.out.println("parameterTypes.length == 0");
//                    continue;
//                }
                for (int j=0; j<parameterTypes.length; j++){
                    System.out.println(parameterTypes[j].getName() + ",");
                }
            }

            User user2 = (User) clazz1.newInstance();
            Field field = clazz1.getDeclaredField("age");
            field.setAccessible(true);
            field.setInt(user2,333);
            System.out.println("user2= " + user2.outInfo());

            User user3 = (User) clazz1.newInstance();
            Method method = clazz1.getMethod("output");
            method.invoke(user3);

            User user4 = (User) clazz1.newInstance();
            Method method1 = clazz1.getMethod("setName",String.class);
            method1.invoke(user4,"xiaoming");
            System.out.println("user4= " + user4.outInfo());

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
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
