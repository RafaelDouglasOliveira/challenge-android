package com.br.testeame.productscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.testeame.R;
import com.br.testeame.api.model.Product;
import com.br.testeame.productscreen.fragment.CustomDialogFragment;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class ProductActivity extends AppCompatActivity implements ProductContract.View {

    public static final String EXTRA_PRODUCT_ACTIVITY = "product";

    TextView txtNameProduct;
    TextView txtProductOriginal;
    ImageView imgProduct;
    TextView txtValueDiscount;
    TextView txtDescription;
    ProductPresenter productPresenter;
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initView();
        init();
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        initListener();
        productPresenter.onStart();

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        txtNameProduct = findViewById(R.id.txt_name_product);
        txtProductOriginal = findViewById(R.id.txt_product_original);
        imgProduct = findViewById(R.id.img_product);
        txtValueDiscount = findViewById(R.id.txt_value_discount);
        txtDescription = findViewById(R.id.txt_description);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        progressBar = findViewById(R.id.progressBar);
        toolbar.setTitle("");

    }

    private void init() {
        productPresenter = new ProductPresenter();
        productPresenter.init(this);
        Product product = getIntent().getParcelableExtra(EXTRA_PRODUCT_ACTIVITY);
        productPresenter.initProduct(product);
    }

    private void initListener() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPresenter.reservedProduct();
            }
        });
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

    @Override
    public void setUrlImage(@NotNull String url) {

        Picasso.get().load(url).into(imgProduct);

    }

    @Override
    public void setNameProduct(@NotNull String nameProduct) {

        txtNameProduct.setText(nameProduct);

    }

    @Override
    public void setDescriptProduct(@NotNull String description) {

        txtDescription.setText(Html.fromHtml(description));

    }

    @Override
    public void setValueOrigin(@NotNull String value) {
        txtProductOriginal.setText(value);
    }

    @Override
    public void setValueOff(@NotNull String value) {

        txtValueDiscount.setText(value);

    }

    @Override
    public void showDialogSucess() {

        DialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(),"dialog");

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

}
