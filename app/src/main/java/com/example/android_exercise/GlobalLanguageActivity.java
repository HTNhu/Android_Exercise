package com.example.android_exercise;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class GlobalLanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        setContentView(R.layout.activity_global_language);
        TextView welcome = (TextView)findViewById(R.id.welcome);
        welcome.setText(getResources().getString(R.string.welcome));
        Button btnChange = (Button)findViewById(R.id.btnChange);


        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showChangeDialog();
            }
        });
    }
    private void showChangeDialog(){
        final String[] lstItems = {"English", "French", "VietNam"} ;
        AlertDialog.Builder builder = new AlertDialog.Builder(GlobalLanguageActivity.this);
        builder.setTitle("Choose Language");

        builder.setSingleChoiceItems(lstItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0: {
                        setLocale("en");
                        recreate();
                        break;
                    }
                    case 1: {
                        setLocale("fr");
                        recreate();
                        break;
                    }
                    case 2: {
                        setLocale("vi");
                        recreate();
                        break;
                    }
                    default:dialogInterface.dismiss();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
        private void setLocale(String language){
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            SharedPreferences.Editor editor = getSharedPreferences("Setting", MODE_PRIVATE ).edit();
            editor.putString("My_lang", language);
//            setLocale(language);
            editor.apply();

    }
    public void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Setting", GlobalLanguageActivity.MODE_PRIVATE);
        String language = preferences.getString("My_lang",null);
        setLocale(language);
    }
}
