package com.example.tmdb;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tmdb.Model.Info;
import com.example.tmdb.Model.Result;
import com.example.tmdb.MovieAdapter.MovieAdapter;
import com.example.tmdb.R;
import com.example.tmdb.Service.GetMoviesData;
import com.example.tmdb.Service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    public ArrayList<Result> movielist;
    MovieAdapter movieAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        loadMovies();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadMovies();

            }
        });

    }

    private void loadMovies() {



            GetMoviesData getMoviesData = RetrofitInstance.getService();


            Call<Info> call = getMoviesData.getPopularMovies(getString(R.string.api_key));

                call.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {

                    Info info = response.body();


                    if (info!=null && info.getResults()!=null) {

                        movielist = (ArrayList<Result>) info.getResults();

                        showRecyclerview();
                        swipeRefreshLayout.setRefreshing(false);


                    }


                }

                @Override
                public void onFailure(Call<Info> call, Throwable t) {

                }
            });











    }

    private void showRecyclerview() {


        recyclerView = (RecyclerView) findViewById(R.id.rv_Movies);
        movieAdapter = new MovieAdapter(this, movielist);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();




    }
}