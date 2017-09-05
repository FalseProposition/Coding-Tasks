package com.example.shinelon.notebook;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shinelon.notebook.MySQLite.MySqlite;

import static com.example.shinelon.notebook.MainActivity.newpage;
import static com.example.shinelon.notebook.MainActivity.message;

public class NoteActivity extends AppCompatActivity {
    EditText create_time;
    EditText finish_time;
    EditText note_title;
    EditText note;
    EditText if_finish;
    EditText note_rank;
    Button btn_finish;
    Button btn_delete;
    Button back;

    int id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notelayout);
        create_time= (EditText) findViewById(R.id.create_time);
        finish_time=(EditText)findViewById(R.id.finish_time);
        note= (EditText) findViewById(R.id.note);
        note_title= (EditText) findViewById(R.id.note_title);
        if_finish= (EditText) findViewById(R.id.if_finish);
        note_rank= (EditText) findViewById(R.id.note_rank);
        btn_finish= (Button) findViewById(R.id.btn_finish);
        btn_delete= (Button) findViewById(R.id.btn_delete);
        back= (Button) findViewById(R.id.back);

        MySqlite mysqlite=new MySqlite(this);
        final SQLiteDatabase db=mysqlite.getWritableDatabase();

        final ContentValues contentvalues=new ContentValues();
        contentvalues.put("create_time", String.valueOf(create_time.getText()));
        contentvalues.put("finish_time", String.valueOf(finish_time.getText()));
        contentvalues.put("if_finished", String.valueOf(if_finish.getText()));
        contentvalues.put("note_rank", String.valueOf(note_rank.getText()));
        contentvalues.put("title", String.valueOf(note_title.getText()));
        contentvalues.put("content", String.valueOf(note.getText()));

        //通过传入的message判断是新建还是已有的
        if(message == 1) {
            contentvalues.put("_id", newpage);
            btn_finish.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    db.insert("Note",null,contentvalues);
                }
            });
           btn_delete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    String sql="DELETE FROM Note WHERE _id ="+newpage;
                    db.execSQL(sql);
                    Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        else if(message==-1){
            Intent intent=getIntent();
            Bundle bundle=intent.getExtras();
            id=bundle.getInt("id");
            Cursor cursor=db.rawQuery("select * from Note where _id="+id,null);
            if(cursor.moveToFirst()) {
                note_title.setText(cursor.getString(cursor.getColumnIndex("title")));
                note.setText(cursor.getString(cursor.getColumnIndex("content")));
                if_finish.setText(cursor.getString(cursor.getColumnIndex("if_finished")));
                note_rank.setText(cursor.getString(cursor.getColumnIndex("note_rank")));
                create_time.setText(cursor.getString(cursor.getColumnIndex("create_time")));
                finish_time.setText(cursor.getString(cursor.getColumnIndex("finish_time")));
            }
            contentvalues.put("_id", id);
            btn_finish.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    db.update("Note",contentvalues,"_id="+id,null);
                }
            });
            btn_delete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    String sql="DELETE FROM Note WHERE _id ="+id;
                    db.execSQL(sql);
                    Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            //待完善——返回时先保存再跳转
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
