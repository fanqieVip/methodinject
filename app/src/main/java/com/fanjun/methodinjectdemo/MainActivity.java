package com.fanjun.methodinjectdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanjun.methodinject.annotation.Inject;
import com.fanjun.methodinject.annotation.InjectClass;

@InjectClass
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Inject(TestInject.class)
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click Me! 我不会被拦截", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Inject(Test2Inject.class)
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click Me! 匿名内部类方法被拦截了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Inject(Test2Inject.class)
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "click Me! 外部类方法被拦截了", Toast.LENGTH_SHORT).show();
    }
}
