package com.example.wrcsearchfilter.ui.main;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrcsearchfilter.R;

public class FirebaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_title;
    public TextView txt_comment;

    public FirebaseRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_comment = (TextView) itemView.findViewById(R.id.txt_content);
        txt_title = (TextView) itemView.findViewById(R.id.txt_title);
    }
}
