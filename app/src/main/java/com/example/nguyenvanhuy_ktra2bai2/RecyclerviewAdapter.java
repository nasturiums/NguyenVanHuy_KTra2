package com.example.nguyenvanhuy_ktra2bai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenvanhuy_ktra2bai2.model.Course;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.CourseHolder> {

    Context context;
    ArrayList<Course> list = new ArrayList<>();

    public void setList (ArrayList<Course> list) {
        this.list = list;
    }
    public RecyclerviewAdapter(Context context) {
        this.context = context;
    }

    public RecyclerviewAdapter(Context context, ArrayList<Course> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_cardview, null);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        Course course = list.get(position);

        holder.txtIdName.setText(course.getName() + " - " + course.getId());
        holder.txtDate.setText(course.getDate());
        holder.txtChuyenNganh.setText(course.getChuyenNganh());
        if(course.isActivated()) {
            holder.txtCheck.setText("is Activated");
        } else {
            holder.txtCheck.setText("not Activated yet");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("course_update", course);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class CourseHolder extends RecyclerView.ViewHolder {
        TextView txtIdName, txtDate, txtChuyenNganh, txtCheck;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            txtIdName = itemView.findViewById(R.id.txt_idname);
            txtDate = itemView.findViewById(R.id.txt_chuyenNganh);
            txtCheck = itemView.findViewById(R.id.txt_check);
            txtChuyenNganh = itemView.findViewById(R.id.txt_chuyenNganh);
        }
    }
}
