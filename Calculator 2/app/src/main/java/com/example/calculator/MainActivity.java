package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numbers;
    private TextView results;
    private ImageView one,two,three,four,five,six,seven,eight,nine,zero,cls,add,sub,mul,div;
    private int a,b,c;
    private boolean isfirst = true,isAdd = false,isSub = false,isMul = false,isDiv = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        cls = findViewById(R.id.cls);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        numbers = findViewById(R.id.numbers);
        results = findViewById(R.id.results);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a+=1;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                    a*=10;
                }
                else{
                    b+=1;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                    b*=10;
                }
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a+=2;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                    a*=10;
                }
                else{
                    b+=2;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                    b*=10;
                }
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a+=3;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                    a*=10;
                }
                else{
                    b+=3;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                    b*=10;
                }
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a+=4;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                    a*=10;
                }
                else{
                    b+=4;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                    b*=10;
                }
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a+=5;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                    a*=10;
                }
                else{
                    b+=5;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                    b*=10;
                }
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a+=6;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                    a*=10;
                }
                else{
                    b+=6;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                    b*=10;
                }
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a+=7;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                    a*=10;
                }
                else{
                    b+=7;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                    b*=10;
                }
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a+=8;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                    a*=10;
                }
                else{
                    b+=8;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                    b*=10;
                }
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a+=9;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                    a*=10;
                }
                else{
                    b+=9;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                    b*=10;
                }
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfirst){
                    a*=10;
                    String st = String.valueOf(a);
                    numbers.setText(st);
                }
                else{
                    b*=10;
                    String st = String.valueOf(b);
                    numbers.setText(st);
                    if(isAdd){
                        long sum = a+b;
                        String s = String.valueOf(sum);
                        results.setText(s);
                    }
                    else if(isSub){
                        long diff = a-b;
                        String s = String.valueOf(diff);
                        results.setText(s);
                    }
                    else if(isMul){
                        long prod = a*b;
                        String s = String.valueOf(prod);
                        results.setText(s);
                    }
                    else if(isDiv){
                        long divis = a-b;
                        String s = String.valueOf(divis);
                        results.setText(s);
                    }
                }
            }
        });
        cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results.setText("");
                numbers.setText("");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isfirst = false;
                isAdd = true;
                a /= 10;
                long sum = a;
                String s = String.valueOf(sum);
                results.setText(s);
                numbers.setText("");
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isfirst = false;
                isSub = true;
                a /= 10;
                long sub = a;
                String s = String.valueOf(sub);
                results.setText(s);
                numbers.setText("");
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isfirst = false;
                isMul = true;
                a /= 10;
                long mul = a;
                String s = String.valueOf(mul);
                results.setText(s);
                numbers.setText("");
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isfirst = false;
                isDiv = true;
                a /= 10;
                long div = a;
                String s = String.valueOf(div);
                results.setText(s);
                numbers.setText("");
            }
        });
    }
}
