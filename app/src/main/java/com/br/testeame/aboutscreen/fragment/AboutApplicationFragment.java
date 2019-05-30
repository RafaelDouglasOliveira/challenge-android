package com.br.testeame.aboutscreen.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br.testeame.R;
import com.br.testeame.aboutscreen.AboutApplicationContract;
import com.br.testeame.aboutscreen.AboutApplicationPresenter;

import org.jetbrains.annotations.NotNull;

public class AboutApplicationFragment extends Fragment implements AboutApplicationContract.View {

    AboutApplicationPresenter presenter;

    TextView txtNameDev;
    TextView txtData;

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showErroMsg(String message) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hiddenProgressBar() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_application, container, false);
        init();
        initView(view);
        return view;
    }

    private void init() {

        presenter = new AboutApplicationPresenter();
        presenter.init(this);

    }

    private void initView(View view) {

        txtNameDev = view.findViewById(R.id.txt_name_dev);
        txtData = view.findViewById(R.id.txt_data);
        presenter.onStart();

    }

    @Override
    public void setNameDev(@NotNull String name) {

        txtNameDev.setText(name);
    }

    @Override
    public void setDataNow(@NotNull String data) {
        txtData.setText(data);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
    }
}
