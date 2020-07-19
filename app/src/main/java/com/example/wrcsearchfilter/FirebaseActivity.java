package com.example.wrcsearchfilter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrcsearchfilter.ressource.api.PostII;
import com.example.wrcsearchfilter.ui.main.FirebaseRecyclerViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseActivity extends AppCompatActivity {
    EditText Edit_Title,Edit_content;
    Button btn_post;
    RecyclerView recyclerview;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseRecyclerOptions<PostII> options;
    FirebaseRecyclerAdapter<PostII, FirebaseRecyclerViewHolder> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        Edit_content = (EditText) findViewById(R.id.edit_Content);
        Edit_Title = (EditText) findViewById(R.id.edit_Title);
        btn_post = (Button) findViewById(R.id.btn_post);
        recyclerview = (RecyclerView) findViewById(R.id.Recycler_charts);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("EMDT_FIREBASE");

        btn_post.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                displaycomment();
                postcomment();
            }

            private void postcomment() {

                String title = Edit_Title.getText().toString();
                String content = Edit_content.getText().toString();

                PostII post = new PostII(title,content);
                databaseReference.push().setValue(post);
                adapter.notifyDataSetChanged();
            }

            private void displaycomment() {

                options =
                        new FirebaseRecyclerOptions.Builder<PostII>()
                                .setQuery(databaseReference,PostII.class)
                                .build();

                adapter =
                        new FirebaseRecyclerAdapter<PostII, FirebaseRecyclerViewHolder>(options) {

                            @Override
                            protected void onBindViewHolder(@NonNull FirebaseRecyclerViewHolder holder, int position, @NonNull PostII model) {
                                holder.txt_title.setText(model.getTitle());
                                holder.txt_comment.setText(model.getContent());
                            }

                            @NonNull
                            @Override
                            public FirebaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                View itemView = LayoutInflater.from(getBaseContext()).inflate(R.layout.postii_item, parent, false);
                                return new FirebaseRecyclerViewHolder(itemView);
                            }
                        };
                adapter.startListening();
                recyclerview.setAdapter(adapter);
            }
        });

    }

}
