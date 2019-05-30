package com.br.testeame;

import android.content.Context;

public interface BaseView {

    Context getContext();
    void showErroMsg(String message);
    void showProgressBar();
    void hiddenProgressBar();
}
