package com.example.antop.retrofitdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by antop on 2/13/2017.
 */
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<String> arrayList;
    public MainActivityAdapter(Context context, ArrayList<String> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public MainActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.view_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainActivityAdapter.MyViewHolder holder, int position) {
        holder.txtName.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtName=(TextView)itemView.findViewById(R.id.txtName);
        }
    }
}
