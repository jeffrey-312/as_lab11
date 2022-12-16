package com.example.as_lab11;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText ed_book, ed_price;
    private Button btn_query,btn_insert,btn_update,btn_delete;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String > items=new ArrayList<>();
    private SQLiteDatabase dbrw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_book=findViewById(R.id.ed_book);
        ed_price=findViewById(R.id.ed_price);
        btn_query=findViewById(R.id.btn_query);
        btn_insert=findViewById(R.id.btn_insert);
        btn_update=findViewById(R.id.btn_update);
        btn_delete=findViewById(R.id.btn_delete);
        listView=findViewById(R.id.listView);

        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        dbrw=new MyDBHelper(this).getWritableDatabase();

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c;
                if (ed_book.length()<1)
                    c=dbrw.rawQuery("SELECT * FROM myTable",null);
                else
                    c=dbrw.rawQuery("SELECT * FROM myTable WHERE book LIKE'"+ed_book.getText().toString()+"'",null);
                c.moveToFirst();
                items.clear();
                Toast.makeText(MainActivity.this,"共有"+c.getCount()+"筆資料",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroy(){
        super.onDestroy();

        dbrw.close();
    }
}
