package com.example.cs4092_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LecturersFromXML l = new LecturersFromXML(this);

        Log.d(TAG, Arrays.toString(l.getLecturers()));

        RecyclerView recyclerView = findViewById(R.id.lecturers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LecturerAdapter adapter = new LecturerAdapter(l.getLecturers());
        recyclerView.setAdapter(adapter);
    }
}