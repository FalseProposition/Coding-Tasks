package com.example.shinelon.notebook.MySQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlite extends SQLiteOpenHelper {
    private static MySqlite mysqlite=null;
    private static final int DB_VERSION = 1;
    public MySqlite(Context context) {
        super(context, "Note.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Note (_id integer primary key,title nvarchar(30),content nvarchar, if_finished nchar(2), note_rank nchar(4),create_time nvarchar,finish_time nvarchar)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
