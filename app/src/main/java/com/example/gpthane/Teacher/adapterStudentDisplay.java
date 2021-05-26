package com.example.gpthane.Teacher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gpthane.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class adapterStudentDisplay extends FirebaseRecyclerAdapter<modelStudentDisplay, adapterStudentDisplay.myviewholder>{

    public adapterStudentDisplay(@NonNull @NotNull FirebaseRecyclerOptions<modelStudentDisplay> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull adapterStudentDisplay.myviewholder holder, int position, @NonNull @NotNull modelStudentDisplay model) {
        holder.studName.setText(model.getName());
        holder.studEnrollment.setText(model.getEnrollmentno());
        holder.studEmail.setText(model.getEmail());
        holder.studPhone.setText(model.getPhone());
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_display, parent, false);
        return new myviewholder(view);

    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView studName, studEnrollment, studEmail, studPhone;

        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            studName = itemView.findViewById(R.id.studName);
            studEnrollment = itemView.findViewById(R.id.studEnrollment);
            studEmail = itemView.findViewById(R.id.studEmail);
            studPhone = itemView.findViewById(R.id.studPhone);
        }
    }
}
