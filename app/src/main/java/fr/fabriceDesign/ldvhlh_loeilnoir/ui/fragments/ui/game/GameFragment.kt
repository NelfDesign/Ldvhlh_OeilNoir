package fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.game

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.fabriceDesign.ldvhlh_loeilnoir.R
import fr.fabriceDesign.ldvhlh_loeilnoir.dagger.DaggerComponents
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Page
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*
import timber.log.Timber
import javax.inject.Inject

class GameFragment : Fragment() {

    @Inject
    lateinit var factory : ViewModelProvider.Factory
    private lateinit var gameViewModel: GameViewModel
    lateinit var pagesLivres : MutableList<Page>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_game, container, false)
        factory = DaggerComponents.create().createFactory()
        gameViewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val json = resources.openRawResource(R.raw.book).bufferedReader().use { it.readText() }
        pagesLivres = gameViewModel.parseJson(json)

        goToPage(gameViewModel.getNumberPage())
    }


    private fun goToPage(number: Int) {
        for (p in pagesLivres) {
            if (number == p.number) {
                createPage(p)
                Timber.d("page = $p")
            } else {
                continue
            }
        }
    }

    private fun createPage(p : Page) {
        relative_Partie.numero_page.text = p.number.toString()
        relative_Partie.textPage.text = p.text
        relative_Partie.textPage.setMovementMethod(ScrollingMovementMethod())

        if (p.ennemi != null){
            bouton_combat.visibility = View.VISIBLE
            bouton_combat.text = "Combat"
        }else{
            bouton_combat.visibility = View.GONE
        }

       /* bouton_combat.setOnClickListener(){
            for (e in p.ennemi!!){
                //database.createEnnemi(e)
            }

            val intent = Intent(this, Combat::class.java)
            intent.putExtra("perso", persoPartie)
            intent.putExtra("number", numberPage)
            startActivity(intent)
        }
*/
        layout_bouton.removeAllViews()
        val choix = p.choix
        for (c in choix){
            createButton(c.text, c.number)
        }
        gameViewModel.setNumberPage(p.number)
    }

    /**
     * Create button dynamically
     */
    private fun createButton(text: String?, number: Int) {
        val button = Button(requireContext())
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.setMargins(5,5,5,5)
        button.layoutParams = layoutParams
        button.textSize = 12f
        button.setBackgroundResource(R.drawable.border_item)
        button.text = text
        button.setOnClickListener{
            goToPage(number)
        }
        layout_bouton.addView(button)
    }

}