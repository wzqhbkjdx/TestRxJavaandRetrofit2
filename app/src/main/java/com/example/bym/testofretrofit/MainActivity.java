package com.example.bym.testofretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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





        bt_getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        mySubscriber = new MySubscriber(); //每次建立连接必须新建一个Subscriber
        HttpMethods.getInstance().getTopMovie(mySubscriber ,0 ,10);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mySubscriber != null) {
            if(!mySubscriber.isUnsubscribed()){
                mySubscriber.unsubscribe();
                Toast.makeText(MainActivity.this,"解除绑定OnBackPressed",Toast.LENGTH_SHORT).show();
                Log.i("MainActivity","解除绑定OnBackPressed");
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mySubscriber != null) {
            if(!mySubscriber.isUnsubscribed()){
                mySubscriber.unsubscribe();
                Toast.makeText(MainActivity.this,"解除绑定OnDestroy",Toast.LENGTH_SHORT).show();
                Log.i("MainActivity","解除绑定OnDestroy");
            }
        }
    }

    private class MySubscriber extends Subscriber<List<Subject>> {

        @Override
        public void onCompleted() {
            Toast.makeText(MainActivity.this,"获取数据测试",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(MainActivity.this,"获取数据出现问题",Toast.LENGTH_SHORT).show();
            tv_result.setText(e.toString());
        }

        @Override
        public void onNext(List<Subject> subjects) {
            Log.i("MainActivity","还是获取到了数据");
            tv_result.setText(subjects.toString());
        }
    }
}
