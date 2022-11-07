package com.example.cs4092_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Transition enterTransition = new Slide(Gravity.END)
                .setDuration(500);
        enterTransition.setPropagation(null);
        getWindow().setEnterTransition(enterTransition);

        Lecturer l = (Lecturer) getIntent()
                .getExtras()
                .getSerializable("lecturer");

        TextView nameView = findViewById(R.id.name);
        ImageView imageView = findViewById(R.id.profile_pic);
        TextView detailsView = findViewById(R.id.desc);

        nameView.setText(l.getName());
        imageView.setImageResource(l.getImage());
        detailsView.setText(Html.fromHtml("Lecturer in the department of <b>" +
                l.getDepartment() +
                "</b> carries out research in <b>" +
                l.getField() + "</b>"));

        Button button = findViewById(R.id.more_details);
        button.setOnClickListener(v -> {
            Intent intent=new Intent(ImageActivity.this, DetailsActivity.class);
            intent.putExtra("lecturer", l);
            startActivity(intent);
        });
    }
}