package com.example.cs4092_assignment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class LecturerAdapter extends RecyclerView.Adapter<LecturerAdapter.ViewHolder> {
    public static final String TAG = "Adapter";
    private Lecturer[] lecturers;

    /**
     * Provide a reference to the views in the layout
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView departmentTextView;

        private final ImageView profileImageView;
        public TextView getDepartmentTextView() {
            return departmentTextView;
        }

        public ImageView getProfileImageView() {
            return profileImageView;
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public ViewHolder(View view) {
            super(view);
            // todo Define click listener for the ViewHolder's View

            nameTextView = (TextView) view.findViewById(R.id.name);
            profileImageView = (ImageView) view.findViewById(R.id.profile_pic);
            departmentTextView = (TextView) view.findViewById(R.id.department);
        }


    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet Lecturer[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public LecturerAdapter(Lecturer[] dataSet) {
        lecturers = dataSet;
        Log.d(TAG, "dataset = " + Arrays.toString(lecturers));
        Log.d(TAG, "dataset_length = " + lecturers.length);
    }

    /**
     * Create new views (invoked by the layout manager)
      */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lecturer, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     *     Replace the contents of a view (invoked by the layout manager)
      */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "viewholder bound at position = " + position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Lecturer lecturer = lecturers[position];
        viewHolder.getNameTextView().setText(lecturer.getName());
        viewHolder.getProfileImageView().setImageResource(lecturer.getImage());
        viewHolder.getDepartmentTextView().setText(lecturer.getDepartment());

        Log.d(TAG, String.valueOf(lecturer.getImage()));
    }

    /**
     *  @return the size of your dataset (invoked by the layout manager)
      */
    @Override
    public int getItemCount() {
        return lecturers.length;
    }
}