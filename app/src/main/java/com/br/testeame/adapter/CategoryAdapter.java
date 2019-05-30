package com.br.testeame.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.br.testeame.R;
import com.br.testeame.api.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private ArrayList<Category> categoryArrayList;

    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(Category category);
    }

    public CategoryAdapter(ArrayList<Category> categoryArrayList , OnItemClickListener listener) {
        this.categoryArrayList = categoryArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new CategoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup,false ));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int position) {

        final Category category = categoryArrayList.get(position);
        categoryViewHolder.bind(category);
        categoryViewHolder.contentCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }


}
