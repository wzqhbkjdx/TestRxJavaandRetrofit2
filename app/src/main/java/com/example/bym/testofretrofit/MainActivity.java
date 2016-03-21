package com.example.bym.testofretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bt_getData)
    protected Button bt_getData;
    @Bind(R.id.bt_cancel)
    protected Button bt_cancel;
    @Bind(R.id.bt_clear)
    protected Button bt_clear;
    @Bind(R.id.bt_to_another)
    protected Button bt_to_another;
    @Bind(R.id.result_TV)
    protected TextView tv_result;

    private MySubscriber mySubscriber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mySubscriber = new MySubscriber();

        bt_getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mySubscriber.isUnsubscribed()) {
                    mySubscriber.unsubscribe();
                    Toast.makeText(MainActivity.this,"请求被取消",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_result.setText("");
            }
        });

        bt_to_another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getResult() {
//        Toast.makeText(MainActivity.this,"获取数据测试",Toast.LENGTH_SHORT).show();
        HttpMethods.getInstance().getTopMovie(mySubscriber ,0 ,10);

    }

    private class MySubscriber extends Subscriber<List<Subject>> {

        @Override
        public void onCompleted() {
            Toast.makeText(MainActivity.this,"获取数据测试",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Subject> subjects) {
            tv_result.setText(subjects.toString());
        }
    }
}
