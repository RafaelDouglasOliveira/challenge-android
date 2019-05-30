package com.br.testeame.aboutscreen;


import com.br.testeame.R;
import com.br.testeame.Util;

import org.jetbrains.annotations.NotNull;

public class AboutApplicationPresenter implements AboutApplicationContract.Presenter {


    private AboutApplicationContract.View view;


    @Override
    public void onStart() {
        setNameDev();
        setDataNoe();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void init(@NotNull AboutApplicationContract.View view) {
        this.view = view;
    }

    private void setNameDev() {

        String name = this.view.getContext().getString(R.string.name_dev);
        this.view.setNameDev(name);
    }

    private void setDataNoe() {

        String data = Util.Companion.dataNow();
        this.view.setDataNow(data);
    }
}
