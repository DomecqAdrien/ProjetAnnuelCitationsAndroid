package com.example.wrcsearchfilter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrcsearchfilter.ressource.api.Post;

import java.util.ArrayList;
import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.HolderView> {

    public List<Item> getProductlist() {
        return productlist;
    }

    public Context getContext() {
        return context;
    }

    private List<Item> productlist;
    private Context context;

    public MainActivityAdapter(List<Item> productlist, Context context) {
        this.productlist = productlist;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.customitem,parent,false);
        return new HolderView(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, final int position) {

        holder.V_text.setText(productlist.get(position).getName());
        holder.V_image.setImageResource(productlist.get(position).getPhoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CitationsActivity.class);
              //intent.putExtra("Nom",productlist.get(position).getName());
              //intent.putExtra("Nom",productlist.get(position).getPhoto());
                context.startActivity(intent);
                Toast.makeText(context, "click on " + productlist.get(position).getName(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }

    public void setfilter(List<Item> listitem){
        productlist = new ArrayList<>();
        productlist.addAll(listitem);
        notifyDataSetChanged();
    }
    class HolderView extends RecyclerView.ViewHolder{

        ImageView V_image;
        TextView V_text;

        HolderView (View itemview){
            super(itemview);
            V_image= (ImageView) itemview.findViewById(R.id.product_image);
            V_text= (TextView) itemview.findViewById(R.id.product_title);
        }

    }
}
