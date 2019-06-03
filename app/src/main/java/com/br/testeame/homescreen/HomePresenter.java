package com.br.testeame.homescreen;

import android.util.Log;

import com.br.testeame.api.Endpoint;
import com.br.testeame.api.RetrofitConfiguration;
import com.br.testeame.api.model.BannerResponse;
import com.br.testeame.api.model.CategoryResponse;
import com.br.testeame.api.model.ProductsResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomePresenter implements HomeContract.Presenter {

    public HomeContract.View view;
    private Endpoint endpoint;

    @Override
    public void onStart() {

       endpoint =  RetrofitConfiguration.Companion.getRetrofitInstance().create(Endpoint.class);
       this.view.showProgressBar();
       infoBannerHome();
       infoCategory();
       initBestSeller();

    }

    @Override
    public void detachView() {

        this.view = null;
    }

    @Override
    public void init(HomeContract.View view) {

        this.view = view;
    }

    private void infoBannerHome() {

        endpoint.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BannerResponse bannerResponse) {
                        setListBanner(bannerResponse);

                    }

                    @Override
                    public void onError(Throwable e) {

                        if(view != null) {
                            view.hiddenProgressBar();
                            Log.i("retorno internet", e.getMessage());
                            view.showErroMsg(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        view.hiddenProgressBar();

                    }
                });


    }


    private void infoCategory() {

           endpoint.getCategory()
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Observer<CategoryResponse>() {
                       @Override
                       public void onSubscribe(Disposable d) {

                       }

                       @Override
                       public void onNext(CategoryResponse categoryResponse) {
                           view.setupCategory(categoryResponse.getCategoryResponse());
                           view.hiddenProgressBar();
                       }

                       @Override
                       public void onError(Throwable e) {
                           view.hiddenProgressBar();
                           Log.i("retorno internet", e.getMessage());
                           view.showErroMsg(e.getMessage());

                       }

                       @Override
                       public void onComplete() {

                       }
                   });
        }


    private void initBestSeller() {

            endpoint.getTopSellingProducts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ProductsResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ProductsResponse productsResponse) {
                            view.setupProductBestSeller(productsResponse.getProductsResponse());
                            view.hiddenProgressBar();
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showErroMsg(e.getMessage());
                            view.hiddenProgressBar();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }


    public void setListBanner(BannerResponse response) {
        if(view != null) {
            this.view.setupViewPager(response);
            this.view.hiddenProgressBar();
        }
    }
}
