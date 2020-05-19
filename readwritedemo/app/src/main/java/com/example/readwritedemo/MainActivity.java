package com.example.readwritedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
TextView textView;
Button button;
String file;
String get;
boolean isMarked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = "obvioustest.txt";
        button = findViewById(R.id.show);
        textView = findViewById(R.id.text);
        get = "";
        try {
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(file, Context.MODE_PRIVATE));
            writer.write("This is a test");
            writer.close();
        }catch (IOException e){
            Log.d("IOException", "oof");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(file)));
                    Integer i = new Integer(reader.read());
                    if((i != null)){
                        get = reader.readLine();
                        textView.setText(get);
                        button.setBackgroundColor(Color.GREEN);
                        isMarked = reader.markSupported();
                        extraMarked(isMarked);
                        Log.d("REEE", reader.read() + "");
                        reader.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void extraMarked(Boolean isMarked){
        if(isMarked){
            Log.d("extraMarked", "Is markable");
        }else{
            Log.d("extraMarked", "Is not markable");
        }
    }

}
