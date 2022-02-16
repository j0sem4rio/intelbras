package br.com.intelbras.view.activity.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.intelbras.R
import br.com.intelbras.databinding.ActivityPokemonBinding
import br.com.intelbras.databinding.ActivityPokemonDetailsBinding
import br.com.intelbras.model.Pokemon
import br.com.intelbras.model.PokemonItem
import br.com.intelbras.view.base.BaseActivity
import com.squareup.picasso.Picasso


class PokemonDetailsActivity : BaseActivity() {

    val TAG = javaClass.simpleName
    val pokemonDetailsViewModel= PokemonDetailsViewModel()
    private lateinit var binding: ActivityPokemonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        initUI()
    }

    private fun initUI() {
        pokemonDetailsViewModel.onMessageError.observe(this, onMessageErrorObserver)
        pokemonDetailsViewModel.pokemon.observe(this, renderPokemon)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_details)
        val id = intent.getSerializableExtra("URL") as? String
//        binding.gasStation = gasStations



        pokemonDetailsViewModel.getDetailPokemon(this,  id!!.toInt())

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
        alert(getString(R.string.title_attention), getString(R.string.try_again_later))
    }

    //observers
    private val renderPokemon = Observer<PokemonItem> {
        if(it != null ){
            binding.item = it
            Picasso.get().load(it.sprites?.other?.home?.front_default)
                .placeholder(R.drawable.ic_a4a72105d37447734c2b1f36c1049d07)
                .into( binding.imageView)
        }

    }

}
