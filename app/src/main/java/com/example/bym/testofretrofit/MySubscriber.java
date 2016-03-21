package com.example.bym.testofretrofit;

import android.app.Activity;
import android.content.Context;

import rx.Subscriber;

/**
 * Created by bym on 16/3/21.
 */
public class MySubscriber<T> extends Subscriber<T> implements OnCancelListener {

    private SubscriberOnNextListener onNextListener;
    private Activity activity;
    private Context context;

    public MySubscriber() {


    }




    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }


}
