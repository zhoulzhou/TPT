package com.example.tire.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import com.example.tire.R;

public class TestMainActivity extends AppCompatActivity{
    TextView mText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main_layout);

        mText = (TextView) findViewById(R.id.time_c);
        setText2(1.5);
    }

    private void setText1(double time){
        String content = "1.2 h 行驶时间";
        String str1 = Double.toString(time);
        String str2 = "   h";
        int str1Length =str1.length();
        String str = str1 + str2;
        int strLenght =str.length();
        SpannableStringBuilder sb = new SpannableStringBuilder(str);
        AbsoluteSizeSpan as = new AbsoluteSizeSpan(30,true);
        sb.setSpan(as , 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new AbsoluteSizeSpan(30,true), 0, str1Length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new AbsoluteSizeSpan(10,true), str1Length, strLenght, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mText.setText(sb);
    }

    private void setText2(double d){
        String src1 = Double.toString(d);
        String src2 = "  h";
        int style1 = R.style.style_black;
        int style2 = R.style.style_red;
        SpannableStringBuilder ssb = getSpannableString(this,src1,src2,style1,style2);
        mText.setText(ssb);
    }

    public SpannableStringBuilder getSpannableString(Context context, String src1, String src2,
                                                     int style1, int style2){
        SpannableStringBuilder ssb = null;
        String src = src1 + src2;
        int length1 = src1.length();
        int lengthAll = src.length();
        if(context != null){
            ssb = new SpannableStringBuilder(src);
            if(length1 != 0){
                ssb.setSpan(new TextAppearanceSpan(context,style1),0,length1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if(lengthAll != 0){
                ssb.setSpan(new TextAppearanceSpan(context,style2),length1,lengthAll,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return ssb;
    }

    private void testCheckValue(){
        checkValue("Bodywork.getAuto>69 DB>0","work.getAutesstessggeeedfooooooosf");
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
