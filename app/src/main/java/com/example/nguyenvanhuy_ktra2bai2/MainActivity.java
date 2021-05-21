package com.example.nguyenvanhuy_ktra2bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.nguyenvanhuy_ktra2bai2.model.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    RecyclerviewAdapter recyclerviewAdapter;
    ArrayList<Course> list = new ArrayList<>();
    SQLiteCourseHelper sqLiteCourseHelper = new SQLiteCourseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcv_course);
        fab = findViewById(R.id.fab_add);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewAdapter = new RecyclerviewAdapter(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.mSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Course> courses = sqLiteCourseHelper.getCourseByName(newText);
                recyclerviewAdapter.setList(courses);
                recyclerView.setAdapter(recyclerviewAdapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    protected void onResume() {
        super.onResume();
        list = sqLiteCourseHelper.getAll();
        recyclerviewAdapter.setList(list);
        recyclerView.setAdapter(recyclerviewAdapter);
    }
}