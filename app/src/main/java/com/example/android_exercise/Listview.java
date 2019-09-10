package com.example.android_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Listview extends ListActivity {
        //    String[] students = {
//            "Tung Nui",
//            "Tu Le",
//            "Kiet Le",
//            "Nhu Tran",
//            "Canh Ly",
//            "Tan Pham",
//            "Duy Dubai",
//            "Duy Trinh",
//            "Hieu Tran"
//    };
        String [] students;
        //    Spinner s1 = (Spinner)findViewById(R.id.spinner);
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

//        setContentView((R.layout.activity_main));

            ListView lstView = getListView();
            lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            lstView.setTextFilterEnabled(true);
//        lstView.setBackgroundColor(Color.rgb(140,120,130));
            students = getResources().getStringArray(R.array.students_array);

            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students));
        }
        public void onListItemClick(ListView parent, View v, int position, long id){
            Toast.makeText(this, "You have selectd " + students[position], Toast.LENGTH_SHORT).show();
        }
        public void onClick(View view){
            ListView lstView = getListView();
            String itemsSelected = "Selected items: \n";
            for(int i =0; i < lstView.getCount(); i++){
                if(lstView.isItemChecked(i)){
                    itemsSelected += lstView.getItemAtPosition(i) + "\n";
                }
            }
            Toast.makeText(this, itemsSelected, Toast.LENGTH_SHORT).show();
        }
    }

