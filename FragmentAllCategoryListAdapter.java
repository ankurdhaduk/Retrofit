package com.example.antop.ecommerce_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antop.ecommerce_app.Bean.AllCategoryList;
import com.example.antop.ecommerce_app.Bean.Constant;
import com.example.antop.ecommerce_app.Fragment.FeatureCategoryAdapter;
import com.example.antop.ecommerce_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by antop on 1/14/2017.
 */
public class AllCategoryListAdapter extends BaseAdapter {

    ArrayList<AllCategoryList> allCategoryList;
    int allLayout;
    Context context;
    LayoutInflater inflater;

    public AllCategoryListAdapter(Context context, int allcategory_item, ArrayList<AllCategoryList> allCategory) {

        this.context = context;
        this.allLayout = allcategory_item;
        this.allCategoryList = allCategory;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return allCategoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return allCategoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder {

        ImageView imageView ;
        TextView textView ;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        View v = null;
        v = inflater.inflate(R.layout.allcategory_adptr , null );

        holder.imageView = (ImageView) v.findViewById(R.id.image);
        holder.textView = (TextView)v.findViewById(R.id.text1);

        holder.textView.setText(allCategoryList.get(i).getCategoryName());
        Picasso.with(context)
                .load(Constant.allCatogryUrl + allCategoryList.get(i).getCategoryId() + "/" + allCategoryList.get(i).getImage())
                .resize(80 , 80)
                .into(holder.imageView);
        return v;
    }
}
