package com.example.bym.testofretrofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bym on 16/3/21.
 */
public class SecondActivity extends Activity {
    @Bind(R.id.bt_get)
    protected Button bt_get;
    @Bind(R.id.bt_clear_data)
    protected Button bt_clear_data;
    @Bind(R.id.movie_result)
    protected TextView movie_result;

    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        ButterKnife.bind(this);

        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                movie_result.setText(subjects.toString());
            }
        };

        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovie();
            }
        });

        bt_clear_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie_result.setText("");
            }
        });

    }

    private void getMovie() {
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext, SecondActivity.this), 0, 10);
    }
}
