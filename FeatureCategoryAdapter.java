package com.example.antop.ecommerce_app.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.antop.ecommerce_app.Bean.AllFeatureCategory;
import com.example.antop.ecommerce_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by antop on 1/14/2017.
 */
public class FeatureCategoryAdapter extends BaseAdapter {

    private ArrayList<AllFeatureCategory> allFeatureCategories;
    private int listLayout;
    private Context context;
    private LayoutInflater inflater;


    public FeatureCategoryAdapter(Context featureCategoryFragment, int allfeature_item, ArrayList<AllFeatureCategory> feature) {

        this.context = featureCategoryFragment;
        this.listLayout = allfeature_item;
        this.allFeatureCategories = feature;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return allFeatureCategories.size();
    }

    @Override
    public Object getItem(int i) {
        return allFeatureCategories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView text ;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        View v = null;
        v= inflater.inflate(R.layout.allfeature_item , null);

        holder.imageView = (ImageView) v.findViewById(R.id.image);
        holder.text = (TextView)v.findViewById(R.id.text);

        holder.text.setText(allFeatureCategories.get(i).getFeaturesCategoryName());
        Picasso.with(context)
                .load("http://itechnotion.in/ecommerce/FeaturesCategory/" + allFeatureCategories.get(i).getFeaturesCategoryId() +"/"+ allFeatureCategories.get(i).getImage())
                .resize(80 , 80)
                .into(holder.imageView);
        return v;
    }
}
