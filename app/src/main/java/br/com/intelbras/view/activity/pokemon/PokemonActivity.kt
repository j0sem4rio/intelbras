package br.com.intelbras.view.activity.pokemon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.intelbras.R
import br.com.intelbras.model.Pokemon
import br.com.intelbras.view.base.BaseActivity
import br.com.intelbras.view.base.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_pokemon.*


class PokemonActivity : BaseActivity() {

    val TAG = javaClass.simpleName
    lateinit var pokemonAdapter: PokemonAdapter
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    val fuelViewModel = PokemonViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_pokemon)
        initUI()

//        if (NetworkUtil(applicationContext).isNetworkAvailable) {
            openLoading()

            fuelViewModel.mutableLiveData?.observe(this, object: Observer<List<Pokemon>> {

                override fun onChanged(t: List<Pokemon>?) {
                    closeLoading()
                    isLoading = false
                    isLastPage = t!!.isEmpty()
                    pokemonAdapter.addData(t)
                    pokemonAdapter.notifyDataSetChanged()
                }
            })

            fuelViewModel.getPokemons(applicationContext)

//        }else{
//            Toast.makeText(this, "No Network Available", Toast.LENGTH_LONG).show()
//        }
    }

    fun initUI(){

        fuelViewModel.onMessageError?.observe(this, onMessageErrorObserver)
        rvPokemonList.setHasFixedSize(true);
        val layoutManager = LinearLayoutManager(this)
        rvPokemonList.layoutManager = layoutManager
        rvPokemonList.itemAnimator = DefaultItemAnimator()

        rvPokemonList.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        pokemonAdapter = PokemonAdapter(PokemonClick {

            Log.v(TAG, " $it.name")

        })

        rvPokemonList.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getMoreItems()
            }
        })

        pokemonAdapter.isEmptyList.observe(this, emptyListObserver)

        rvPokemonList.adapter = pokemonAdapter

    }

    fun getMoreItems() {
        openLoading()

        fuelViewModel.getPokemons(applicationContext)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val onMessageErrorObserver = Observer<Any> {
//        alert(getString(R.string.dialog_title_attention), getString(R.string.try_again_later))
    }

    private val emptyListObserver = Observer<Boolean> {
        if (it) {
            layoutEmpty.visibility =  View.VISIBLE
        } else{
            layoutEmpty.visibility =  View.GONE
        }
    }

}