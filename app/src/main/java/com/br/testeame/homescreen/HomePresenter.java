package com.br.testeame.homescreen;

import com.br.testeame.api.Endpoint;
import com.br.testeame.api.RetrofitConfiguration;
import com.br.testeame.api.model.BannerResponse;
import com.br.testeame.api.model.CategoryResponse;
import com.br.testeame.api.model.ProductsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
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

        endpoint.getBanner().enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                view.setupViewPager(response.body());
                view.hiddenProgressBar();

            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {

                view.hiddenProgressBar();
                view.showErroMsg(t.getMessage());
            }
        });

    }


    private void infoCategory() {

        endpoint.getCategory().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                view.setupCategory(response.body().getCategoryResponse());
                view.hiddenProgressBar();

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                view.showErroMsg(t.getMessage());
                view.hiddenProgressBar();

            }
        });
    }

    private void initBestSeller() {

        endpoint.getTopSellingProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {

                view.setupProductBestSeller(response.body().getProductsResponse());
                view.hiddenProgressBar();

            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                view.showErroMsg(t.getMessage());
                view.hiddenProgressBar();
            }
        });
    }
}
