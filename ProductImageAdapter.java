package com.example.antop.recylerview.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antop.recylerview.CommonActivity;
import com.example.antop.recylerview.ModelArray;
import com.example.antop.recylerview.ModelSecond;
import com.example.antop.recylerview.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by antop on 1/26/2017.
 */
public class ProductImageAdapter extends RecyclerView.Adapter<ProductImageAdapter.ViewHolder> {
    Context context;
    ArrayList<ModelSecond> modelSeconds;

    public ProductImageAdapter(Context context, ArrayList<ModelSecond> stringArrayList) {
        this.context = context;
        this.modelSeconds = stringArrayList;
    }

    @Override
    public ProductImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewhorizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductImageAdapter.ViewHolder holder, final int position) {

            Picasso.with(context)
                    .load(modelSeconds.get(position).getImage())
                    .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent;
                intent = new Intent(context, CommonActivity.class);
                intent.putExtra("url", modelSeconds.get(position).getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelSeconds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageV);
        }
    }
}
