package com.br.testeame.homescreen.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.testeame.R;
import com.br.testeame.api.model.Banner;
import com.br.testeame.api.model.BannerResponse;
import com.br.testeame.api.model.Category;
import com.br.testeame.api.model.Product;
import com.br.testeame.categoryscreen.CategoryActivity;
import com.br.testeame.homescreen.HomeContract;
import com.br.testeame.homescreen.HomePresenter;
import com.br.testeame.adapter.BestSellerAdapter;
import com.br.testeame.adapter.CategoryAdapter;
import com.br.testeame.adapter.ViewPageBannerAdapter;
import com.br.testeame.productscreen.ProductActivity;
import com.rd.PageIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeContract.View ,
        CategoryAdapter.OnItemClickListener,
        BestSellerAdapter.OnItemClickListener {


    ProgressBar progressBar;
    HomePresenter homePresenter;
    ViewPager viewPagerBanner;
    ViewPageBannerAdapter viewPageBannerAdapter;
    PageIndicatorView pageIndicatorView;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    BestSellerAdapter bestSellerAdapter;
    RecyclerView rvBestSeller;
    TextView txtProduct;
    TextView txtCategory;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        initView(view);
        return view;
    }

    private void initView(View view) {

        viewPagerBanner = view.findViewById(R.id.view_pager_banner);
        viewPageBannerAdapter = new ViewPageBannerAdapter(getActivity().getSupportFragmentManager());
        pageIndicatorView = view.findViewById(R.id.pageIndicatorView);
        recyclerView =  view.findViewById(R.id.recyclerView);
        rvBestSeller = view.findViewById(R.id.rv_best_seller);
        progressBar = view.findViewById(R.id.progressBar);
        txtProduct = view.findViewById(R.id.txt_product);
        txtCategory = view.findViewById(R.id.txtCategory);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManagerTwo
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvBestSeller.setLayoutManager(layoutManagerTwo);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        homePresenter.detachView();
    }

    @Override
    public void setupCategory(@NotNull ArrayList<Category> lisCategory) {

        categoryAdapter = new CategoryAdapter(lisCategory,this);
        recyclerView.setAdapter(categoryAdapter);
        txtCategory.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

    }

    private void init() {

        homePresenter = new HomePresenter();
        homePresenter.init(this);
        homePresenter.onStart();
    }

    @Override
    public void onItemClick(Category category) {

        startActivity(prepareIntentCategory(category));
    }

    private Intent prepareIntentCategory(Category category) {
        Intent intent = new  Intent(getActivity(), CategoryActivity.class);
        intent.putExtra(CategoryActivity.EXTRA_CATEGORY_ACTIVITY,category);
        return  intent;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showErroMsg(String message) {

        Snackbar.make(getActivity().findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {

        if(progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hiddenProgressBar() {

        if(progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

    }



    @Override
    public void setupViewPager(BannerResponse bannerArrayList) {


        for (Banner banner : bannerArrayList.getBannerResponse()) {
            Bundle args = new Bundle();
            args.putParcelable("banner", banner);
            BannerFragment bannerFragment = new BannerFragment();
            bannerFragment.setArguments(args);
            viewPageBannerAdapter.addFragment(bannerFragment);

        }

        viewPagerBanner.setAdapter(viewPageBannerAdapter);

        pageIndicatorView.setCount(viewPageBannerAdapter.getCount());

        viewPagerBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelection(position);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void setupProductBestSeller(@NotNull ArrayList<Product> listProduct) {

        bestSellerAdapter = new BestSellerAdapter(listProduct,this);
        rvBestSeller.setAdapter(bestSellerAdapter);
        txtProduct.setVisibility(View.VISIBLE);
        rvBestSeller.setVisibility(View.VISIBLE);

    }

    @Override
    public void onItemClick(Product product) {

        startActivity(getIntentProduct(product));

    }

    private Intent getIntentProduct(Product product) {
        Intent intent = new  Intent(getActivity(), ProductActivity.class);
        intent.putExtra(ProductActivity.EXTRA_PRODUCT_ACTIVITY,product);
        return  intent;

    }
}
