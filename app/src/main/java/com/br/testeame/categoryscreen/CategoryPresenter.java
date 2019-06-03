package com.br.testeame.categoryscreen;

import com.br.testeame.Util;
import com.br.testeame.api.Endpoint;
import com.br.testeame.api.RetrofitConfiguration;
import com.br.testeame.api.model.Category;
import com.br.testeame.api.model.ProductsResponse;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private Category category;
    private Endpoint endpoint;

    @Override
    public void setCategory(@NotNull Category category) {
        this.category = category;
    }

    @Override
    public void init(@NotNull CategoryContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        setTitle();
        endpoint =  RetrofitConfiguration.Companion.getRetrofitInstance().create(Endpoint.class);
        this.view.showProgressBar();
        searcheCategory();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    private void setTitle() {
        if(category!= null && category.getDescription() != null) {
            this.view.setTitleToolbar(category.getDescription());
        } else {
            this.view.setTitleToolbar("Categorias");
        }
    }

    private void searcheCategory() {


            endpoint.getCategoryProducts(category.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ProductsResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ProductsResponse productsResponse) {
                            view.hiddenProgressBar();
                            view.setupProduct(productsResponse.getProductsResponse());
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
