package com.br.testeame.homescreen

import com.br.testeame.BasePresenter
import com.br.testeame.BaseView
import com.br.testeame.api.model.BannerResponse
import com.br.testeame.api.model.Category
import com.br.testeame.api.model.Product
import java.util.ArrayList

interface HomeContract {

    interface View : BaseView {

        fun setupViewPager(bannerArrayList: BannerResponse)

        fun setupCategory(lisCategory : ArrayList<Category>)

        fun setupProductBestSeller(listProduct: ArrayList<Product>)
    }

    interface Presenter : BasePresenter {

        fun init(view: View)

    }
}