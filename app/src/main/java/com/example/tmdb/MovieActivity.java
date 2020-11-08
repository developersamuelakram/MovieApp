package com.example.tmdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tmdb.Model.Result;
import com.example.tmdb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    String moviename, movieposter, moviedescription;

    ImageView imagePoster;
    TextView movietitle, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


            Intent intent  = getIntent();


            moviename = intent.getStringExtra("moviename");
            movieposter = intent.getStringExtra("movieposter");
            moviedescription = intent.getStringExtra("moviedescription");


            movietitle = findViewById(R.id.movieName_activity);
            description = findViewById(R.id.movieinfo);
            imagePoster = findViewById(R.id.poster);


                String imagepath = "https://image.tmdb.org/t/p/w500" + movieposter;


                movietitle.setText(moviename);
                description.setText(moviedescription);




                    Picasso.get().load(imagepath).into(imagePoster);

                    Toast.makeText(this, imagepath, Toast.LENGTH_SHORT).show();







    }

}