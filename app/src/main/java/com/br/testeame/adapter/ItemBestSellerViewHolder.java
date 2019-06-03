package com.br.testeame.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.testeame.R;
import com.br.testeame.Util;
import com.br.testeame.api.model.Product;
import com.squareup.picasso.Picasso;

public class ItemBestSellerViewHolder extends RecyclerView.ViewHolder {

    private ImageView imagemProduct;
    private TextView txtDescriptionProduct;
    private ImageView imageOpen;
    private TextView txtProductOriginal;
    private TextView txtValueDiscount;
    public ConstraintLayout contenerItemBest;

    public ItemBestSellerViewHolder(@NonNull View itemView) {
        super(itemView);

        imagemProduct = itemView.findViewById(R.id.imagem_product);
        txtDescriptionProduct = itemView.findViewById(R.id.txt_description_product);
        imageOpen = itemView.findViewById(R.id.image_open);
        txtProductOriginal = itemView.findViewById(R.id.txt_product_original);
        contenerItemBest = itemView.findViewById(R.id.contener_item_best);
        txtValueDiscount = itemView.findViewById(R.id.txt_value_discount);

    }

    public void build(Product product) {
        Picasso.get()
                .load(product.getUrlImage())
                .placeholder(itemView.getResources().getDrawable(R.drawable.logo_sobre))
                .error(itemView.getResources().getDrawable(R.drawable.logo_sobre))
                .into(imagemProduct);
        txtDescriptionProduct.setText(Html.fromHtml(product.getName()));
        if(String.valueOf(product.getPriceOf()) != null && String.valueOf(product.getPriceOf()).length() > 0) {
            txtProductOriginal.setText(Util.Companion.stringMoney(product.getPriceOf()));
        }
        if(String.valueOf(product.getPriceOff()) != null && String.valueOf(product.getPriceOff()).length() > 0) {
            txtValueDiscount.setText(Util.Companion.stringMoney(product.getPriceOff()));
        }
    }
}
