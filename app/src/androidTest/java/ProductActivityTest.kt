import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.br.testeame.R
import com.br.testeame.api.model.Category
import com.br.testeame.api.model.Product
import com.br.testeame.productscreen.ProductActivity
import com.br.testeame.productscreen.ProductContract
import com.br.testeame.productscreen.ProductPresenter
import com.nhaarman.mockito_kotlin.mock
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest

class ProductActivityTest {

    val description = "Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!"

    lateinit var server: MockWebServer
    lateinit var context: Context
    lateinit var presenter: ProductPresenter
    lateinit var view : ProductContract.View


    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<ProductActivity> =
            object : ActivityTestRule<ProductActivity>(ProductActivity::class.java) {

                override fun getActivityIntent(): Intent {
                    val intent = Intent()

                    val url = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
                    val urlProduct = "https://images-submarino.b2w.io/produtos/01/00/item/129125/0/129125074_1G.png"


                    var category = Category(1, "Games", url)


                    val product = Product(1, "teste", urlProduct, description, 399F, 199F, category)

                    intent.putExtra("product", product)

                    return intent
                }
            }

    @Before
    internal fun setUp() {


        server = MockWebServer()
        server.start()
        context = InstrumentationRegistry.getTargetContext()
        presenter = mock()
        view = mock()
        presenter.init(view)

    }

    @After
    internal fun tearDown() {
        server.shutdown()
    }



        @Test
    fun testNameProduct() {
        onView(withId(R.id.txt_name_product)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_name_product)).check(matches(withText("teste")))

    }


    @Test
    fun testValue() {
        onView(withId(R.id.txt_product_original)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_value_discount)).check(matches(isDisplayed()))
    }

    @Test
    fun clickAdd() {

        onView(withId(R.id.floatingActionButton)).perform(scrollTo(),click())

        val product = context.assets?.open("post_produto.json")?.bufferedReader().use {

            it?.readText()
        }

        server.enqueue(MockResponse().setBody(product))

    }



}