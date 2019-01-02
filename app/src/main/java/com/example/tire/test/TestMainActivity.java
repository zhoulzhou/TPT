package com.example.tire.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class TestMainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkValue("Bodywork.getAutoType>69 DB>0","Bodywork.getAutoTypesstessggeeedfooooooosf");
        checkValue("Bodywork.getAutoType>69 DB>0","Bodywork.getAutoType kkj");
        checkValue("Bodywork.getAutoType","Bodywork.getAuto");
    }

    int resId;
    StringBuffer showBuffer = new StringBuffer();
    public void checkValue(String source, String target){
        if(source.contains(target)){
            resId = getResources().getIdentifier(target.replace(".", "_").toLowerCase(), "string", "com.example.tire");
            showBuffer.append(getResources().getString(resId) + "->" + source);
        }

        System.out.println("checkValue = " + showBuffer.toString());
    }
}
