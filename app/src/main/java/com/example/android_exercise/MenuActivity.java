package com.example.android_exercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn:
                Toast.makeText(this, "Xem danh sach", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnsv:
                Toast.makeText(this, "Xem danh sach sinh vien", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnlh:
                Toast.makeText(this, "Xem danh sach lop hoc", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mninds:
                Toast.makeText(this, "In danh sach lop hoc", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnsuads:
                Toast.makeText(this, "Sua danh sach lop hoc", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnxoads:
                Toast.makeText(this, "Xoa danh sach lop hoc", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
