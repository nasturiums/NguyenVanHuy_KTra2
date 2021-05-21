package com.example.nguyenvanhuy_ktra2bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import com.example.nguyenvanhuy_ktra2bai2.model.Course;

import java.util.ArrayList;

public class SQLiteCourseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HocOnline.db";
    public static final int DATABASE_VERSION = 1;
    public SQLiteCourseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE khoahoc (" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "name text," +
                "dateStarted text," +
                "chuyenNganh text," +
                "activate BOOLEAN)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put("name",course.getName());
        values.put("dateStarted", course.getDate());
        values.put("chuyenNganh", course.getChuyenNganh());
        values.put("activate", course.isActivated());

        SQLiteDatabase database = getWritableDatabase();
        return database.insert("khoahoc", null, values);
    }

    public ArrayList<Course> getAll() {
        ArrayList<Course> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("khoahoc", null, null, null,
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                String chuyenNganh = cursor.getString(3);
                boolean activate = cursor.getInt(4) == 1;
                list.add(new Course(id, name, date, chuyenNganh, activate));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int updateCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put("name", course.getName());
        values.put("dateStarted", course.getDate());
        values.put("chuyenNganh", course.getChuyenNganh());
        values.put("activate", course.isActivated());
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(course.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("khoahoc",
                values, whereClause, whereArgs);
    }

    public int deleteCourse(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("khoahoc",
                whereClause, whereArgs);
    }

    public ArrayList<Course> getCourseByName(String name) {
        ArrayList<Course> list = new ArrayList<>();
        String whereClause = "name LIKE ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("khoahoc", null, whereClause,
                whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String courseName = cursor.getString(1);
            String courseDate = cursor.getString(2);
            String chuyenNganh = cursor.getString(3);
            boolean activate = cursor.getInt(4) == 1;
            list.add(new Course(id, courseName, courseDate, chuyenNganh, activate));
        }
        return list;
    }
}
