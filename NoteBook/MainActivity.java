package com.example.shinelon.notebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.example.shinelon.notebook.MySQLite.MySqlite;

/**
 * 问题：
 * listview为空白，没有显示内容
 * 点击已创建的笔记时也没有内容
 * 待完善：
 * 使用recyclerview
 * 添加spinner，来选择优先级和是否完成
 * 长按item弹出对话框，确认删除
 * 优先级排序
 * 分析页面
 * 创建时间不用自己输入
 * “返回”按钮与“完成”相结合
 */

public class MainActivity extends AppCompatActivity {
    ListView listview;
    Button new_create;
    Button rank_sort;
    Button analyse;

    //以newpage作为新建的id
    static int newpage=0;
    static int message=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySqlite mysqlite;
        SQLiteDatabase db;
        mysqlite=new MySqlite(this);
        db=mysqlite.getReadableDatabase();

        listview= (ListView) findViewById(R.id.listview);
        new_create= (Button) findViewById(R.id.new_create);
        rank_sort= (Button) findViewById(R.id.rank_sort);
        analyse= (Button) findViewById(R.id.analyse);


        //给ListView设置适配器
        Cursor mycursor=db.query("Note", null, null, null, null, null, null);
        mycursor.moveToFirst();
        final SimpleCursorAdapter MyAdapter = new SimpleCursorAdapter(MainActivity.this, R.layout.itemlayout, mycursor,
                new String[]{"title", "if_finished","note_rank"}, new int[]{R.id.item_title, R.id.item_finish,R.id.item_rank}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listview.setAdapter(MyAdapter);

        //点击item跳转到相应页面
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                message=-1;
                Cursor cursor = MyAdapter.getCursor();
                cursor.moveToPosition(i);
                int item_id = cursor.getInt(cursor.getColumnIndex("_id"));
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("id",item_id);
                startActivity(intent);
            }
        });

        //新建笔记
        new_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newpage++;
                message=1;
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
