package com.example.tmdb.MovieAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdb.MovieActivity;
import com.example.tmdb.Model.Result;
import com.example.tmdb.R;


import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyHolder> {


    ArrayList<Result> resultArrayList;
    Context context;


    public MovieAdapter(Context context, ArrayList<Result> resultArrayList) {
        this.resultArrayList = resultArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moviestyle, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Result result = resultArrayList.get(position);

        String imagePath="https://image.tmdb.org/t/p/w500"+resultArrayList.get(position).getPosterPath();


        holder.moviesName.setText(result.getOriginalTitle());

        Glide.with(context).load(imagePath).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView moviesName;
        ImageView imageView;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            moviesName = itemView.findViewById(R.id.movieName);
            imageView = itemView.findViewById(R.id.moviePhoto);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {


            int positionofmovie = getAdapterPosition();

            if (positionofmovie!= RecyclerView.NO_POSITION) {


                Result result = resultArrayList.get(positionofmovie);

                String imagepath =  result.getPosterPath();


                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("moviename", result.getOriginalTitle());
                intent.putExtra("movieposter", imagepath);
                intent.putExtra("moviedescription", result.getOverview());
                context.startActivity(intent);

            }
        }
    }
}

