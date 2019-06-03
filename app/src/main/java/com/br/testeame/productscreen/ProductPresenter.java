package com.br.testeame.productscreen;

import com.br.testeame.Util;
import com.br.testeame.api.Endpoint;
import com.br.testeame.api.RetrofitConfiguration;
import com.br.testeame.api.model.Product;
import com.br.testeame.api.model.ProductResponse;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductPresenter implements ProductContract.Presenter {

    public ProductContract.View view;
    private Product product;
    private Endpoint endpoint;


    @Override
    public void onStart() {

        if (this.product != null) {
            this.view.setUrlImage(product.getUrlImage());
            this.view.setDescriptProduct(product.getDescription());
            this.view.setNameProduct(product.getName());
            this.view.setValueOrigin(Util.Companion.stringMoney(product.getPriceOf()));
            this.view.setValueOff(Util.Companion.stringMoney(product.getPriceOff()));
        }

        endpoint = RetrofitConfiguration.Companion.getRetrofitInstance().create(Endpoint.class);


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

        endpoint.postProduct(product.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductResponse productResponse) {
                        view.hiddenProgressBar();
                        view.showDialogSucess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hiddenProgressBar();
                        view.showErroMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        }
}

