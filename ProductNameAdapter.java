package com.example.antop.recylerview.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antop.recylerview.ModelArray;
import com.example.antop.recylerview.ModelSecond;
import com.example.antop.recylerview.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by antop on 1/26/2017.
 */
public class ProductNameAdapter extends RecyclerView.Adapter<ProductNameAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelArray> arrayArrayList;

    public ProductNameAdapter(Context context, ArrayList<ModelArray> arrays) {
        this.context = context;
        this.arrayArrayList = arrays;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_product, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rvViewProduct.setLayoutManager(linearLayoutManager);
        holder.txtName.setText(arrayArrayList.get(position).getStrName());
        holder.txtLast.setText(arrayArrayList.get(position).getStrLast());
        Picasso.with(context)
                .load(arrayArrayList.get(position).getImage())
                .resize(250, 250)
                .into(holder.imageName);

        ProductImageAdapter productImageAdapter = new ProductImageAdapter(context, arrayArrayList.get(position).getStringArrayList());
        holder.rvViewProduct.setAdapter(productImageAdapter);
    }

    @Override
    public int getItemCount() {
        return arrayArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLast, txtName;
        private ImageView imageName;
        private RecyclerView rvViewProduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtLast = (TextView) itemView.findViewById(R.id.txtLast);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            imageName = (ImageView) itemView.findViewById(R.id.imageName);
            rvViewProduct = (RecyclerView) itemView.findViewById(R.id.rvViewProduct);
        }
    }

}
