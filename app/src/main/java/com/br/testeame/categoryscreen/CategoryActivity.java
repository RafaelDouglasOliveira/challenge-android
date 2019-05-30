package com.br.testeame.categoryscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.br.testeame.R;
import com.br.testeame.adapter.BestSellerAdapter;
import com.br.testeame.api.model.Category;
import com.br.testeame.api.model.Product;
import com.br.testeame.productscreen.ProductActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity
        implements CategoryContract.View ,
        BestSellerAdapter.OnItemClickListener{

    public static final String EXTRA_CATEGORY_ACTIVITY = "category";

    CategoryPresenter presenter;
    private Category category;
    private Toolbar toolbar;
    RecyclerView rcCategory;
    BestSellerAdapter bestSellerAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        category = getIntent().getParcelableExtra(EXTRA_CATEGORY_ACTIVITY);

        initView();
        init();

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showErroMsg(String message) {
        Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG).show();

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

    private void initView() {

        rcCategory = findViewById(R.id.rc_category);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressBar);

        LinearLayoutManager layoutManagerTwo
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcCategory.setLayoutManager(layoutManagerTwo);
    }

    private void init() {
        presenter = new CategoryPresenter();
        presenter.init(this);
        presenter.setCategory(category);
        presenter.onStart();
    }

    @Override
    public void setTitleToolbar(@NotNull String title) {
        toolbar.setTitle(title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(getColor(R.color.white));
        } else {
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        }


    }

    @Override
    public void setupProduct(@NotNull ArrayList<Product> listProduct) {

        bestSellerAdapter = new BestSellerAdapter(listProduct,this);
        rcCategory.setAdapter(bestSellerAdapter);

    }

    @Override
    public void onItemClick(Product product) {

        startActivity(getIntentProduct(product));
    }

    private Intent getIntentProduct(Product product) {
        Intent intent = new  Intent(this, ProductActivity.class);
        intent.putExtra(ProductActivity.EXTRA_PRODUCT_ACTIVITY,product);
        return  intent;

    }
}
