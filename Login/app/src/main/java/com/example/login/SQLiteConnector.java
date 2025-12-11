package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteConnector extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    public SQLiteConnector(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE =
                "CREATE TABLE " + TABLE_USER + " (" +
                        COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USER_NAME + " TEXT, " +
                        COLUMN_USER_EMAIL + " TEXT, " +
                        COLUMN_USER_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }


    // =============== ADD USER ===================

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword()); // password đã hash

        db.insert(TABLE_USER, null, values);
        db.close();
    }


    // =============== CHECK EMAIL ===================

    public boolean checkUser(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER,
                new String[]{COLUMN_USER_ID},
                COLUMN_USER_EMAIL + " = ?",
                new String[]{email},
                null, null, null);

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();
        return exists;
    }

    // =============== CHECK LOGIN (username + hash password) ===================

    public boolean checkUser(String username, String password) {

        String hashedPassword = HashUtil.sha256(password);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER,
                new String[]{COLUMN_USER_ID},
                COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?",
                new String[]{username, hashedPassword},
                null, null, null);

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();
        return exists;
    }


    // =============== GET EMAIL BY USERNAME ===================

    public String getEmail(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER,
                new String[]{COLUMN_USER_EMAIL},
                COLUMN_USER_NAME + " = ?",
                new String[]{username},
                null, null, null);

        String email = null;

        if (cursor.moveToFirst()) {
            email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_EMAIL));
        }

        cursor.close();
        db.close();
        return email;
    }
}
