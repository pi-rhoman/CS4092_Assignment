package com.example.cs4092_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Lecturer l = (Lecturer) getIntent()
                .getExtras()
                .getSerializable("lecturer");

        TextView nameView = findViewById(R.id.name);
        ImageView imageView = findViewById(R.id.image);
        TextView detailsView = findViewById(R.id.desc);

        nameView.setText(l.getName());
        imageView.setImageResource(l.getImage());
        detailsView.setText(Html.fromHtml("Lecturer in the department of <b>" +
                l.getDepartment() +
                "</b> carries out research in <b>" +
                l.getField() + "</b>"));
    }
}