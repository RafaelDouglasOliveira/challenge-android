package com.br.testeame

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.br.testeame.api.Endpoint
import com.br.testeame.api.model.BannerResponse
import com.br.testeame.homescreen.HomeContract
import com.br.testeame.homescreen.HomePresenter
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4::class)
class HomeFragmentPresenterTest {

    lateinit var view :HomeContract.View
    lateinit var presenter: HomePresenter
    lateinit var context: Context
    lateinit var endpoint : Endpoint


    @Before
    fun setup() {
        context = InstrumentationRegistry.getTargetContext()

        view = mock(HomeContract.View::class.java)
        presenter = HomePresenter()
        presenter.init(view)
        endpoint  = mock()

    }


    @Test
    fun testAttach() {

        assertNotNull(presenter.view)
    }

    @Test
    fun testDetach() {

        presenter.detachView()
        assertNull(presenter.view)
    }


    @Test
    fun returnBanner() {

        val bannerResponse = loadBanner()

        whenever(endpoint.getBanner()).thenReturn(Observable.just(bannerResponse))

    }



    private fun loadBanner() : BannerResponse? {

        val banners = context.assets?.open("banner.json")?.bufferedReader().use {

            it?.readText()
        }

        var gson = Gson()
        return gson.fromJson(banners, BannerResponse::class.java)
    }

}



