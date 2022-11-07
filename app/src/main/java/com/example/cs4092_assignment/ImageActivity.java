package com.example.cs4092_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.CircularPropagation;
import android.transition.SidePropagation;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
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

        enterTransition.excludeTarget(android.R.id.statusBarBackground, true);
        enterTransition.excludeTarget(android.R.id.navigationBarBackground, true);

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
            // set an exit transition
            Transition exitTransition = new Slide(Gravity.RIGHT)
                    .setDuration(500);

            exitTransition.excludeTarget(android.R.id.statusBarBackground, true);
            exitTransition.excludeTarget(android.R.id.navigationBarBackground, true);

            CircularPropagation exitPropagation = new CircularPropagation();
            exitPropagation.setPropagationSpeed(10);
            exitTransition.setPropagation(exitPropagation);
            exitTransition.setInterpolator(new DecelerateInterpolator());

            ImageActivity.this.getWindow().setExitTransition(exitTransition);
            ImageActivity.this.getWindow().setSharedElementEnterTransition(new ChangeTransform());

            // add the lecturer
            intent.putExtra("lecturer", l);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    ImageActivity.this,
                    Pair.create(findViewById(R.id.desc_title), "desc_title"),
                    Pair.create(detailsView, "desc"),
                    Pair.create(nameView, "name"),
                    Pair.create(button, "button")
            );

            ImageActivity.this.startActivity(intent, options.toBundle());
        });
    }
}