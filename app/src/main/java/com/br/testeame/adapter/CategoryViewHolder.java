package com.br.testeame.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.testeame.R;
import com.br.testeame.api.model.Category;
import com.squareup.picasso.Picasso;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageCategory;
    private TextView txtDescriptionCategory;
    public ConstraintLayout contentCategory;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        imageCategory = itemView.findViewById(R.id.image_category);
        txtDescriptionCategory = itemView.findViewById(R.id.txt_description_category);
        contentCategory = itemView.findViewById(R.id.content_category);
    }

    public void bind(Category category) {

        txtDescriptionCategory.setText(category.getDescription().trim());
        Picasso.get().load(category.getUrlImage()).into(imageCategory);

    }
}
