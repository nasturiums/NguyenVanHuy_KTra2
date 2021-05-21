package com.example.nguyenvanhuy_ktra2bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nguyenvanhuy_ktra2bai2.model.Course;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    EditText edtName, edtDate;
    Button btnGetDate, btnUpdate, btnCancel;
    Spinner spinnerChuyenNganh;
    CheckBox cbActivate;
    int mYear, mMonth, mDay;
    Button btnDelete;
    SQLiteCourseHelper sqLiteCourseHelper = new SQLiteCourseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        btnDelete = findViewById(R.id.btn_delete_course);
        edtName = findViewById(R.id.edt_update_name);
        edtDate = findViewById(R.id.edt_update_date);
        cbActivate = findViewById(R.id.cb_update_isActivated);
        btnUpdate = findViewById(R.id.btn_update_course);
        btnGetDate = findViewById(R.id.btn_update_getDate);
        btnCancel = findViewById(R.id.btn_cancel_update);
        spinnerChuyenNganh = findViewById(R.id.spinner_update_chuyennganh);

        String[] listChuyenNganh = {"Tiếng anh", "cntt", "Kinh tế", "truyền thông"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(UpdateActivity.this, android.R.layout.simple_spinner_item, listChuyenNganh);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerChuyenNganh.setAdapter(arrayAdapter);

        Intent intent = getIntent();
        Course course = (Course) intent.getSerializableExtra("course_update");
        edtName.setText(course.getName());
        edtDate.setText(course.getDate());
        cbActivate.setChecked(course.isActivated());
        if (course.isActivated()) {
            btnDelete.setEnabled(false);
        }

        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog =  new DatePickerDialog(UpdateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtDate.setText(mDay + "/" + (month + 1) + "/" + mYear);
                            }
                        }, mYear, mMonth, mDay);

                dialog.show();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String date = edtDate.getText().toString();
                boolean activated;
                if (cbActivate.isChecked()) {
                    activated = true;
                } else {
                    activated = false;
                }
                String chuyenNganh = spinnerChuyenNganh.getSelectedItem().toString();
                Course course = new Course(name, date, chuyenNganh, activated);
                sqLiteCourseHelper.updateCourse(course);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteCourseHelper.deleteCourse(course.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}