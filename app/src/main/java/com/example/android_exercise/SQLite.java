package com.example.android_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLite extends AppCompatActivity {

    EditText edtMa, edtTieuDe, edtTen;
    Button btnSave, btnDel, btnUpdate, btnExit, btnSelect;
    GridView gvDisplay;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        edtMa = (EditText)findViewById(R.id.edtMa);
        edtTieuDe= (EditText)findViewById(R.id.edtTieuDe);
        edtTen = (EditText)findViewById(R.id.edtTen);
        btnDel = (Button)findViewById(R.id.btnDel);
        btnSelect = (Button)findViewById(R.id.btnSelect);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnExit = (Button)findViewById(R.id.btnExit);
        btnSave = (Button)findViewById(R.id.btnSave);
        gvDisplay = (GridView)findViewById(R.id.gvDisplay);
        dbHelper = new DBHelper(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setId(Integer.parseInt(edtMa.getText().toString()));
                book.setTitle(edtTieuDe.getText().toString());
                book.setAuthor(edtTen.getText().toString());

                if(dbHelper.InsertBook(book)){
                    loadData();
                    Toast.makeText(SQLite.this, "Ok", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SQLite.this, "Not Ok", Toast.LENGTH_SHORT).show();
                }
            }

        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                ArrayList<Book> bookList = new ArrayList<>();
                bookList = dbHelper.getAllBook();
                for(Book b : bookList){
                    list.add(b.getId() + "");
                    list.add(b.getTitle());
                    list.add(b.getAuthor());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(SQLite.this, android.R.layout.simple_list_item_1, list);
                gvDisplay.setAdapter(adapter);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.deleteBook(Integer.parseInt(edtMa.getText().toString()))){
                    loadData();
                    Toast.makeText(SQLite.this, "Ok", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SQLite.this, "Not Ok", Toast.LENGTH_SHORT).show();
                }
            }
        });
        gvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i == 0){
                    int id = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                    Book b = dbHelper.getBook(id);
                    edtMa.setText(b.getId()+ "");
                    edtTen.setText(b.getAuthor());
                    edtTieuDe.setText(b.getTitle());
                }
//                else{
//                    String selectdItem = adapterView.getItemAtPosition(i).toString();
//                    edtMa.setText(i+"");
//
//                }

            }
        });
    }
    private void loadData(){
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();
        bookList = dbHelper.getAllBook();
        for(Book b : bookList){
            list.add(b.getId() + "");
            list.add(b.getTitle());
            list.add(b.getAuthor());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SQLite.this, android.R.layout.simple_list_item_1, list);
        gvDisplay.setAdapter(adapter);
    }
}
