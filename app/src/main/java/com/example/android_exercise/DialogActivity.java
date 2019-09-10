package com.example.android_exercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    TextView tv_kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        AlertDialog.Builder mydialog = new AlertDialog.Builder(this);
            mydialog.setIcon(R.drawable.a);
            tv_kq = (TextView)findViewById(R.id.textView1);
            mydialog.setIcon(R.drawable.a);
            mydialog.setTitle("ckon mau ik");
            final CharSequence[] items= {"do","vang","cam"};
            final  boolean[] arraycheck = {false,true,false};
//        mydialog.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                tv_kq.setText(items[i].toString());
//            }
//        });
            mydialog.setMultiChoiceItems(items, arraycheck, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    arraycheck[i] = b;
                }
            });
            mydialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            mydialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String st = "";
                    for(int j =0; j < items.length;j++)
                        if(arraycheck[j])
                            st += items[j].toString() + "\n";
                    tv_kq.setText(st);
                }
            });


            AlertDialog alertDialog = mydialog.create();
            alertDialog.show();
    }
}

