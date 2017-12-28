package me.xiba.startlearnmvvm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import me.xiba.startlearnmvvm.difftype.activity.DiffTypeActivity;
import me.xiba.startlearnmvvm.movie.activity.DoubanMovieActivity;
import me.xiba.startlearnmvvm.movie.activity.MovieDetailActivity;
import me.xiba.startlearnmvvm.movie.activity.MovieTopListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mDoubanMovieTV;
    private TextView mDoubanTopMovieTV;
    private TextView mDoubanMovieFanghuaTV;
    private TextView mDiffTypeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDoubanMovieTV = findViewById(R.id.doubanMovieTV);
        mDoubanTopMovieTV = findViewById(R.id.doubanMovieListTV);
        mDoubanMovieFanghuaTV = findViewById(R.id.douban_movie_fanghua_TV);
        mDiffTypeTV = findViewById(R.id.diffTypeTV);

        mDoubanMovieTV.setOnClickListener(this);
        mDoubanTopMovieTV.setOnClickListener(this);
        mDoubanMovieFanghuaTV.setOnClickListener(this);
        mDiffTypeTV.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Class c = null;
        switch (v.getId()) {

            case R.id.doubanMovieTV:
                c = DoubanMovieActivity.class;
                break;

            case R.id.doubanMovieListTV:
                c = MovieTopListActivity.class;
                break;

            case R.id.douban_movie_fanghua_TV:
                c = MovieDetailActivity.class;
                break;

            case R.id.diffTypeTV:
                c = DiffTypeActivity.class;
                break;
        }
        startActivity(new Intent(this, c));
    }
}
