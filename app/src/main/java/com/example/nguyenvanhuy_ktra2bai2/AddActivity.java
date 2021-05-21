package com.example.nguyenvanhuy_ktra2bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class AddActivity extends AppCompatActivity {

    EditText edtName, edtDate;
    Button btnGetDate, btnAdd, btnCancel;
    Spinner spinnerChuyenNganh;
    CheckBox cbIsActivated;
    SQLiteCourseHelper sqLiteCourseHelper = new SQLiteCourseHelper(this);
    int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtName = findViewById(R.id.edt_add_name);
        edtDate = findViewById(R.id.edt_add_date);
        btnGetDate = findViewById(R.id.btn_add_getDate);
        btnAdd = findViewById(R.id.btn_add_course);
        btnCancel = findViewById(R.id.btn_cancel_add);
        spinnerChuyenNganh = findViewById(R.id.spinner_chuyennganh);
        cbIsActivated = findViewById(R.id.cb_isActivated);

        String[] listChuyenNganh = {"Tiếng anh", "cntt", "Kinh tế", "truyền thông"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(AddActivity.this, android.R.layout.simple_spinner_item, listChuyenNganh);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerChuyenNganh.setAdapter(arrayAdapter);

        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog =  new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtDate.setText(mDay + "/" + (month + 1) + "/" + mYear);
                            }
                        }, mYear, mMonth, mDay);

                dialog.show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String date = edtDate.getText().toString();
                boolean activated;
                if (cbIsActivated.isChecked()) {
                    activated = true;
                } else {
                    activated = false;
                }
                String chuyenNganh = (String) spinnerChuyenNganh.getSelectedItem();
                Course course = new Course(name, date, chuyenNganh, activated);
                sqLiteCourseHelper.addCourse(course);
                finish();
            }
        });
    }
}