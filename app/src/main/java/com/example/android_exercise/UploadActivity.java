package com.example.android_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        final EditText et_filename = (EditText)findViewById(R.id.edfilename);
        final EditText et_content = (EditText)findViewById(R.id.ed_content);

        Button bt_moi = (Button)findViewById(R.id.btnNew);
        bt_moi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_filename.setText("");
                et_content.setText("");
            }
        });

        final ArrayList<String> filenamelist = new ArrayList<>();
        filenamelist.add("hello");

        final Spinner sp_filename = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter =  new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,filenamelist);
        sp_filename.setAdapter(adapter);
        sp_filename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                et_filename.setText(filenamelist.get(i).toString());
                et_content.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button bt_save = (Button)findViewById(R.id.btnSave);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = et_filename.getText().toString();
                filenamelist.add(filename);
                try{
                    FileOutputStream fout = openFileOutput(filename, Context.MODE_PRIVATE);
                    fout.write(et_content.getText().toString().getBytes());
                    fout.close();
                }catch (Exception e){
                    Toast.makeText(UploadActivity.this, "Lỗi lưu file", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button bt_open = (Button)findViewById(R.id.btnOpen);
        bt_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = et_filename.getText().toString();
                StringBuffer buffer = new StringBuffer();
                String line = null;
                try{
                    FileInputStream fin = openFileInput(filename);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                    while ((line=br.readLine())!=null)
                        buffer.append(line).append("\n");
                        et_content.setText(buffer.toString());
                }catch (Exception e){
                    Toast.makeText(UploadActivity.this, "Lỗi nè", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
