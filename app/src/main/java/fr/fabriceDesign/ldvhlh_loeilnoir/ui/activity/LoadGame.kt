package fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.fabriceDesign.ldvhlh_loeilnoir.R
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.adapter.LoadGameAdapter
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.CreatePersoViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.Injection
import kotlinx.android.synthetic.main.activity_load_game.*
import kotlinx.android.synthetic.main.tollbar.*

class LoadGame : AppCompatActivity(), LoadGameAdapter.LoadGameAdapterListener{

    //FIELDS
    private lateinit var createPersoViewModel: CreatePersoViewModel
    private lateinit var adapterList : LoadGameAdapter
    private var listPerso : MutableList<Personnage> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_game)

        toolbar.title = "Parties sauvegardées"
        setSupportActionBar(toolbar)

        val factory = Injection.provideViewModelFactory()
        createPersoViewModel = ViewModelProvider(this, factory).get(CreatePersoViewModel::class.java)
        createPersoViewModel.persos.observe(this, Observer { list -> observeList(list) })

        adapterList = LoadGameAdapter(listPerso, this)
        recycler_perso.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterList
        }

    }

    private fun observeList(list : List<Personnage>) {
        listPerso.clear()
        listPerso.addAll(list)
        adapterList.notifyDataSetChanged()
    }

    override fun onPersoSelected(perso : Personnage) {
        val intent = Intent(this, Game::class.java)
        intent.putExtra("name", perso.name)
        startActivity(intent)
    }

    override fun delete(perso: Personnage) {
        createPersoViewModel.deletePerso(perso)
    }
}
