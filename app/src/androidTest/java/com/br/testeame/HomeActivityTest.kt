package com.br.testeame

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.br.testeame.api.Endpoint
import com.br.testeame.api.model.BannerResponse
import com.br.testeame.api.model.CategoryResponse
import com.br.testeame.api.model.ProductsResponse
import com.br.testeame.homescreen.HomeActivity
import com.br.testeame.homescreen.HomePresenter
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {


    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(HomeActivity::class.java)

    lateinit var homePresenter: HomePresenter

    lateinit var context: Context

    lateinit var mockServer: MockWebServer

    @Mock
    lateinit var service: Endpoint

    @Before
    internal fun setUp() {

        MockitoAnnotations.initMocks(this)
        mockServer = MockWebServer()
        context = InstrumentationRegistry.getTargetContext()
        service = mock()
        mockServer.start()

        homePresenter = HomePresenter()


        val category = context.assets?.open("category_response.json")?.bufferedReader().use {
            it?.readText()
        }

        val topSellingProducts = context.assets?.open("produtos_mais_vendidos.json")?.bufferedReader().use {
            it?.readText()
        }

        val banners = context.assets?.open("banner.json")?.bufferedReader().use {

            it?.readText()
        }

        var gson = Gson()

        val categoryResponse = gson.fromJson(category, CategoryResponse::class.java)
        val topSellingProductsResponse = gson.fromJson(topSellingProducts, ProductsResponse::class.java)
        val balanceModel = gson.fromJson(banners, BannerResponse::class.java)
        whenever(service.getBanner()).thenReturn(Observable.just(balanceModel))
        whenever(service.getCategory()).thenReturn(Observable.just(categoryResponse))
        whenever(service.getTopSellingProducts()).thenReturn(Observable.just(topSellingProductsResponse))

    }

    @Test
    fun openHome() {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

}