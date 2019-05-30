package com.br.testeame.productscreen;

import com.br.testeame.Util;
import com.br.testeame.api.Endpoint;
import com.br.testeame.api.RetrofitConfiguration;
import com.br.testeame.api.model.Product;
import com.br.testeame.api.model.ProductResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter implements ProductContract.Presenter {

    private ProductContract.View view;
    private Product product;
    private Endpoint endpoint;


    @Override
    public void onStart() {

        if(this.product != null){
            this.view.setUrlImage(product.getUrlImage());
            this.view.setDescriptProduct(product.getDescription());
            this.view.setNameProduct(product.getName());
            this.view.setValueOrigin(Util.Companion.stringMoney(product.getPriceOf()));
            this.view.setValueOff(Util.Companion.stringMoney(product.getPriceOff()));
        }

        endpoint =  RetrofitConfiguration.Companion.getRetrofitInstance().create(Endpoint.class);


    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void init(@NotNull ProductContract.View view) {

        this.view = view;

    }

    @Override
    public void initProduct(@NotNull Product product) {
        this.product = product;
    }

    @Override
    public void reservedProduct() {

        this.view.showProgressBar();
        endpoint.postProduct(product.getId()).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                view.hiddenProgressBar();
                view.showDialogSucess();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

                view.hiddenProgressBar();
                view.showErroMsg(t.getMessage());

            }
        });
    }
}
