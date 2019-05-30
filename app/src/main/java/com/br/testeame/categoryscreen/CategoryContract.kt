package com.br.testeame.categoryscreen

import com.br.testeame.BasePresenter
import com.br.testeame.BaseView
import com.br.testeame.api.model.Category
import com.br.testeame.api.model.Product
import java.util.ArrayList

interface CategoryContract {

    interface View : BaseView{
        fun setTitleToolbar(title : String)
        fun setupProduct(listProduct: ArrayList<Product>)

    }


    interface Presenter : BasePresenter {
        fun setCategory(category : Category)
        fun init(view: View)
    }


}