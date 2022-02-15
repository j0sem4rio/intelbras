package br.com.intelbras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.intelbras.view.activity.pokemon.PokemonActivity
import com.facebook.stetho.Stetho

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this@MainActivity, PokemonActivity::class.java))
        finish()
    }
}