import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.br.testeame.api.Endpoint
import com.br.testeame.api.model.Category
import com.br.testeame.api.model.Product
import com.br.testeame.productscreen.ProductContract
import com.br.testeame.productscreen.ProductPresenter
import com.nhaarman.mockito_kotlin.mock
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class ProductPresenterTest {

    lateinit var view: ProductContract.View
    lateinit var presenter: ProductPresenter
    lateinit var context: Context
    lateinit var endpoint: Endpoint
    lateinit var product: Product

    @Before
    fun setup() {
        context = InstrumentationRegistry.getTargetContext()

        view = Mockito.mock(ProductContract.View::class.java)
        presenter = ProductPresenter()
        presenter.init(view)
        endpoint = mock()
        product = loadProduct()
        presenter.initProduct(product)

    }

    @Test
    fun testAttach() {

        TestCase.assertNotNull(presenter.view)
    }

    @Test
    fun testDetach() {

        presenter.detachView()
        TestCase.assertNull(presenter.view)
    }

    private fun loadProduct(): Product {
        val url = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
        val urlProduct = "https://images-submarino.b2w.io/produtos/01/00/item/129125/0/129125074_1G.png"
        val description = "Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!"


        var category = Category(1, "Games", url)


        return Product(1, "teste", urlProduct, description, 399F, 199F, category)


    }

}