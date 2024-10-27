package com.example.noting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Context;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    //声明控件
    private NoteDatabase dbHelper;

    private Context context = this;
    private FloatingActionButton btn_1;
    final String TAG = "tag";
    TextView tv_1;
    private ListView lv_1;

    private NoteAdapter adapter;
    private List<Note> noteList = new ArrayList<Note>();

    //必须
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //找到控件
        btn_1 = findViewById(R.id.fab_1);
        //tv_1 = findViewById(R.id.tv_1);
        lv_1 = findViewById(R.id.lv_1);
        adapter = new NoteAdapter(getApplicationContext(), noteList);
        refreshListView();
        lv_1.setAdapter(adapter);
        //按加号按钮实现界面跳转，编辑笔记
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    //接收startActivityForResult的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        String edit = data.getStringExtra("input");
//        Log.d(TAG, edit);
//        tv_1.setText(edit);
        String content = data.getStringExtra("content");
        String time = data.getStringExtra("time");
        Note note = new Note(content,time,1);
        CRUD op = new CRUD(context);
        op.open();
        op.addNote(note);
        op.close();
        refreshListView();//每次更改笔记后都刷新主页面
    }
    public void refreshListView(){

        CRUD op = new CRUD(context);
        op.open();
        // set adapter
        if (noteList.size() > 0) noteList.clear();
        noteList.addAll(op.getAllNotes());
        op.close();
        adapter.notifyDataSetChanged();
    }

}