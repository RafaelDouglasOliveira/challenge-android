package com.br.testeame.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.testeame.R;
import com.br.testeame.api.model.Product;

import java.util.ArrayList;

public class BestSellerAdapter extends RecyclerView.Adapter<ItemBestSellerViewHolder> {


    private ArrayList<Product> productArrayList;

    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public BestSellerAdapter(ArrayList<Product> productArrayList, OnItemClickListener listener) {
        this.productArrayList = productArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemBestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new ItemBestSellerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_best_seller, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemBestSellerViewHolder itemBestSellerViewHolder, int position) {

        final Product product = productArrayList.get(position);
        itemBestSellerViewHolder.build(product);
        itemBestSellerViewHolder.contenerItemBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }
}
