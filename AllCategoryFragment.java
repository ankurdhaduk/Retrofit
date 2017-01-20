package com.example.antop.ecommerce_app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.antop.ecommerce_app.Adapter.AllCategoryListAdapter;
import com.example.antop.ecommerce_app.Bean.AllCategoryList;
import com.example.antop.ecommerce_app.R;
import com.example.antop.ecommerce_app.Retrofit.RestClient;
import com.example.antop.ecommerce_app.Util.SubCategoryList;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by antop on 1/14/2017.
 */
public class AllCategoryFragment extends Fragment {


    GridView gridView;
    ArrayList<AllCategoryList> allCategory = new ArrayList<AllCategoryList>();

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.allcategory_item, container, false);

        gridView = (GridView) v.findViewById(R.id.gridall);

        RestClient.get().getAllCategoryList(new Callback<ArrayList<AllCategoryList>>() {
            @Override
            public void success(ArrayList<AllCategoryList> allCategoryLists, Response response) {

                for (int i = 0; i < allCategoryLists.size(); i++) {
                    AllCategoryList all = new AllCategoryList();
                    all.setCategoryName(allCategoryLists.get(i).getCategoryName());
                    all.setImage(allCategoryLists.get(i).getImage());
                    all.setCategoryId(allCategoryLists.get(i).getCategoryId());
                    all.setCreatedDate(allCategoryLists.get(i).getCreatedDate());
                    all.setFutureCategory(allCategoryLists.get(i).getFutureCategory());
                    all.setIsDeleted(allCategoryLists.get(i).getIsDeleted());
                    allCategory.add(all);
                }
                AllCategoryListAdapter adapter = new AllCategoryListAdapter(getActivity(), R.layout.allcategory_adptr, allCategory);
                gridView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AllCategoryList allCategoryL = new AllCategoryList();
                Intent intent = new Intent(v.getContext(), SubCategoryList.class);
                allCategoryL = (AllCategoryList) adapterView.getItemAtPosition(i);
                intent.putExtra("ID", allCategoryL.getCategoryId());
                startActivity(intent);

                Toast.makeText(getActivity(), "You Selected :" + allCategory.get(i).getCategoryName(), Toast.LENGTH_SHORT).show();
            }
        });
        return v;

    }


}
