package br.com.intelbras

import br.com.intelbras.api.IntelbrasAPI
import br.com.intelbras.api.util.PokemonListDeserializer
import br.com.intelbras.model.Pokemon
import br.com.intelbras.util.RestServiceTestHelper
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import junit.framework.Assert
import junit.framework.TestCase
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Modifier
import java.lang.reflect.Type

class IntelbrasAPITest {
    private var server: MockWebServer? = null
    private var routeService: IntelbrasAPI? = null
    @Before
    fun setUp() {
        System.setProperty("javax.net.ssl.trustStore", "NONE")
        val listType: Type = object : TypeToken<MutableList<Pokemon>>() {}.type
        server = MockWebServer()
        try {
            server!!.start()
            val gson = GsonBuilder().serializeNulls()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .registerTypeAdapter(listType,  PokemonListDeserializer())
                .excludeFieldsWithoutExposeAnnotation().create()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(server!!.url("/").toString()).build()
            routeService = retrofit.create(IntelbrasAPI::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        server!!.shutdown()
    }

    @Test
    @Throws(Exception::class)
    fun testGetPokemon() {
        val fileName = "server_pokemon.json"
        server!!.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    RestServiceTestHelper.getStringFromFile(
                        this.javaClass.classLoader,
                        fileName
                    )
                )
        )
        val booleanServerResponse: Response<MutableList<Pokemon>?> =
            routeService!!.getPokemon(100, 0).execute()
        val request1 = server!!.takeRequest()
        val body = booleanServerResponse.body()
        Assert.assertNotNull(booleanServerResponse)
        TestCase.assertTrue(booleanServerResponse.isSuccessful)
        TestCase.assertEquals("GET", request1.method)
        TestCase.assertEquals(
            "/pokemon?limit=100&offset=0",
            request1.path
        )
        TestCase.assertEquals(body?.size, 100)
        for (pokemon in body!!) {
            TestCase.assertTrue(pokemon.name!= null)
        }
    }
}