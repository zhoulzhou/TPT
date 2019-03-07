package com.example.tire.annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testAnnotation();
    }

    private void testAnnotation(){
        Person person = new Person();
        Class<Person> c = Person.class;
        //或者下面的方式获取class
//        Class c = Class.forName("com.example.tire.annotation.Person");
        try {
            Method someBody = c.getMethod("someBody", new Class[]{String.class, int.class});
            someBody.invoke(person, new Object[]{"lily", 18});
            iteratorAnnotations(someBody);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            Method empty = c.getMethod("empty",new Class[]{});
            empty.invoke(person, new Object[]{});
            iteratorAnnotations(empty);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void iteratorAnnotations(Method method){

        // 判断 somebody() 方法是否包含MyAnnotation注解
        if(method.isAnnotationPresent(MyAnnotation.class)){
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            String[] values = annotation.value();
            for(String value:values){
                System.out.println("iteratorAnnotations  value= " + value);
            }
        }

        // 获取方法上的所有注解，并打印出来
        Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation:annotations){
            System.out.println("iteratorAnnotations annotation= " + annotation);
        }
    }

}
