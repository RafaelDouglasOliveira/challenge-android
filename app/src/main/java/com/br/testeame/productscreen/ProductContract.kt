package com.br.testeame.productscreen

import com.br.testeame.BasePresenter
import com.br.testeame.BaseView
import com.br.testeame.api.model.Product

interface ProductContract {

    interface View : BaseView {
        fun setUrlImage(url : String)
        fun setNameProduct(nameProduct: String)
        fun setDescriptProduct(description : String)
        fun setValueOrigin(value : String)
        fun setValueOff(value : String)
        fun showDialogSucess()
    }

    interface Presenter : BasePresenter{
        fun init(view: View)
        fun initProduct(product: Product)
        fun reservedProduct()
    }
}