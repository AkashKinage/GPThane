package com.example.gpthane.Student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gpthane.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {

    public myadapter(@NonNull @NotNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myadapter.myviewholder holder, int position, @NonNull @NotNull model model) {
        holder.title.setText(model.getNoticeTitle());
        Glide.with(holder.img.getContext()).load(model.getImageUri()).into(holder.img);
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView title;

        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.textView);
        }
    }
}
