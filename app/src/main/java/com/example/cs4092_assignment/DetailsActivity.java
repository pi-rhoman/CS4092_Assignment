package com.example.cs4092_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class DetailsActivity extends AppCompatActivity {

    private Button buttonNext;

    // create and add the objects
    TextView name, desc, bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Slide enterTransition = new Slide(Gravity.BOTTOM);

        enterTransition.excludeTarget(android.R.id.statusBarBackground, true);
        enterTransition.excludeTarget(android.R.id.navigationBarBackground, true);

        DetailsActivity.this.getWindow().setEnterTransition(enterTransition
                .setDuration(500));

        buttonNext =findViewById(R.id.link);

        Lecturer l = (Lecturer) getIntent().getExtras().getSerializable("lecturer");
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailsActivity.this, WebActivity.class);
                intent.putExtra("lecturer", l);
                startActivity(intent);
            }
        });
        name = findViewById(R.id.name);
        name.setText(l.getName());
        desc = findViewById(R.id.desc);
        // todo remove repeating code
        desc.setText(Html.fromHtml("Lecturer in the department of <b>" +
                l.getDepartment() +
                "</b> carries out research in <b>" +
                l.getField() + "</b>"));
        bio = findViewById(R.id.bio);
        bio.setText(l.getBio());

    }
}