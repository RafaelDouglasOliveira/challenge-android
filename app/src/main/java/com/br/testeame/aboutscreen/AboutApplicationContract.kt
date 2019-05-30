package com.br.testeame.aboutscreen

import com.br.testeame.BasePresenter
import com.br.testeame.BaseView

interface AboutApplicationContract {

    interface View : BaseView {

        fun setNameDev(name : String)
        fun setDataNow(data : String)
    }


    interface Presenter : BasePresenter {
        fun init(view: AboutApplicationContract.View)
    }

}