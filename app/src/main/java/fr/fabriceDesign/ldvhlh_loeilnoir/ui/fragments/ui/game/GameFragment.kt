package fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.game

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import butterknife.OnClick
import fr.fabriceDesign.ldvhlh_loeilnoir.R
import fr.fabriceDesign.ldvhlh_loeilnoir.dagger.DaggerComponents
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Choix
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Jet
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Page
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity.Game.Companion.NAME_PERSO
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity.Game.Companion.PAGE_NUMBER
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.CreatePersoViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*
import timber.log.Timber
import java.security.SecureRandom
import javax.inject.Inject

class GameFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var gameViewModel: GameViewModel
    private lateinit var persoViewModel: CreatePersoViewModel
    lateinit var pagesLivres: MutableList<Page>
    lateinit var page: Page
    lateinit var perso: Personnage
    lateinit var jet: ArrayList<Jet>
    lateinit var choix: ArrayList<Choix>
    private var result: Int = 0
    private var property = 0
    private var resultKeep = 0
    private var jetProperty = ""
    private var numberResultOk = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_game, container, false)
        factory = DaggerComponents.create().createFactory()
        gameViewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)
        persoViewModel = ViewModelProvider(this, factory).get(CreatePersoViewModel::class.java)
        ButterKnife.bind(this, root)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val json = resources.openRawResource(R.raw.book).bufferedReader().use { it.readText() }
        gameViewModel.perso.observe(
            viewLifecycleOwner,
            Observer { personnage -> getPerso(personnage) })
        pagesLivres = gameViewModel.parseJson(json)

        if (page.number != 228){
            goToPage(PAGE_NUMBER)
        }else{
            Toast.makeText(requireContext(), "228", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getPerso(personnage: Personnage?) {
        perso = personnage!!
    }


    private fun goToPage(number: Int) {
        for (p in pagesLivres) {
            if (number == p.number) {
                page = p
                createPage(p)
                Timber.d("page = $p")
            } else {
                continue
            }
        }
    }

    private fun createPage(p: Page) {
        relative_Partie.numero_page.text = p.number.toString()
        relative_Partie.textPage.text = p.text
        relative_Partie.textPage.movementMethod = ScrollingMovementMethod()

        if (p.ennemi != null) {
            bouton_combat.visibility = View.VISIBLE
            bouton_combat.text = getString(R.string.combat)
        } else {
            bouton_combat.visibility = View.GONE
        }

        layout_bouton.removeAllViews()
        checkIfJet(p)

        PAGE_NUMBER = p.number
    }

    @OnClick(R.id.de_jet, R.id.de_jet_6, R.id.bouton_combat)
    fun diceJet(v: View) {
        when (v.id) {
            R.id.de_jet -> {
                when (page.number) {
                    29 ->{
                        resultDoubleJet(perso, jet)
                        updateButton()
                    }
                    109 ->{
                        resultDoubleJet(perso, jet)
                        updateButton2()
                    }
                    224 -> {
                        resultDoubleJet(perso, jet)
                        updateButton3()
                    }
                    else -> {
                        resultDiceJetProperty(perso, jet[0])
                        updateUi(result, property, jetProperty, text_result)
                    }
                }
            }
            R.id.de_jet_6 -> {
                when (page.number) {
                    41, 120 -> resultDiceLucky(choix)
                    91 -> {
                        resultLifeDiceJet(2)
                        persoViewModel.updatePerso(perso)
                    }
                    95 -> resultLucky(choix)
                    182 -> {
                        resultLifeDiceJet(1)
                        persoViewModel.updatePerso(perso)
                    }
                    215 -> resultLucky20(choix)
                    168 -> resultLuckyPair(choix)
                    else -> {
                        resultDiceJetLucky(choix, jet)
                        updateChoice()
                    }
                }
            }
            R.id.bouton_combat -> combat()
        }
    }

    private fun updateButton3() {
        if (numberResultOk != 0){
            createButton(choix[1].text, choix[1].number!!)
        }else{
            createButton(choix[0].text, choix[0].number!!)
        }
    }

    private fun updateButton2() {
        if (numberResultOk == 2){
            createButton(choix[1].text, choix[1].number!!)
            createButton(choix[2].text, choix[2].number!!)
        }else{
            createButton(choix[0].text, choix[0].number!!)
        }
    }

    private fun resultDoubleJet(perso: Personnage, jet: ArrayList<Jet>) {
        if (jet.size > 1) {
            resultDiceJetProperty(perso, jet[0])
           numberResultOk = updateDouble(result, property, jetProperty,text_result)

            resultDiceJetProperty(perso, jet[1])
           numberResultOk += updateDouble(result, property, jetProperty,text_result2)
        }
    }

    private fun updateButton(){
        if (numberResultOk == 2){
            createButton(choix[0].text, choix[0].number!!)
        }else{
            createButton(choix[1].text, choix[1].number!!)
        }
    }

    private fun updateChoice() {
        de_jet_6.visibility = View.GONE
        layout_bouton.visibility = View.VISIBLE
        text_result.visibility = View.VISIBLE
        text_result.text = gameViewModel.updateChoice(result, "Vous devez vous rendre au paragraphe $result.")
        createButton(choix[0].text, choix[0].number!!)
    }

    private fun updateChoiceDice(text : String) {
        de_jet_6.visibility = View.GONE
        de_jet.visibility = View.GONE
        layout_bouton.visibility = View.VISIBLE
        text_result.visibility = View.VISIBLE
        text_result.text = gameViewModel.updateChoice(result, text)
    }

    private fun combat() {
        /*   for (e in p.ennemi!!){
               //database.createEnnemi(e)
           }

           val intent = Intent(this, Combat::class.java)
           intent.putExtra("perso", persoPartie)
           intent.putExtra("number", numberPage)
           startActivity(intent)*/
        Toast.makeText(requireContext(), "Combat clicked", Toast.LENGTH_SHORT).show()
    }

    private fun resultDiceJetProperty(personnage: Personnage, jet: Jet) {
        when (jet.type) {
            "adresse" -> {
                jetProperty = "adresse"
                result = gameViewModel.diceJet20()
                property = personnage.adresse
                resultKeep = resultPropertyModif(jet, result)
            }
            "intel" -> {
                jetProperty = "intelligence"
                result = gameViewModel.diceJet20()
                property = personnage.intel
                resultKeep = resultPropertyModif(jet, result)
            }
            "force" -> {
                jetProperty = "force"
                result = gameViewModel.diceJet20()
                property = personnage.force
                resultKeep = resultPropertyModif(jet, result)
            }
        }
    }

    private fun resultPropertyModif(jet: Jet, result: Int): Int {

        if (jet.moins != null && jet.moins != 0) {
            resultKeep = result - jet.moins!!
        } else if (jet.plus != null && jet.plus != 0) {
            resultKeep = result + jet.plus!!
        } else {
            resultKeep = result
        }

        return resultKeep
    }

    private fun updateUi(result: Int, property: Int, jetProperty: String, textResult: TextView) {
        if (resultKeep <= property) {
            de_jet.visibility = View.GONE
            layout_bouton.visibility = View.VISIBLE
            textResult.visibility = View.VISIBLE
            textResult.text = gameViewModel.updateUi(jetProperty, property, result, resultKeep, "Vous avez réussi le test!!")
            createButton(choix[0].text, choix[0].number!!)
        } else {
            de_jet.visibility = View.GONE
            layout_bouton.visibility = View.VISIBLE
            textResult.visibility = View.VISIBLE
            textResult.text = gameViewModel.updateUi(jetProperty, property, result, resultKeep, "Vous n'avez pas réussi le test.")
            for (c in 1 until choix.size) {
                createButton(choix[c].text, choix[c].number!!)
            }
        }
    }

    private fun updateDouble(result: Int, property: Int, jetProperty: String, textResult: TextView) : Int {
        var resultDouble = 0
        if (resultKeep <= property) {
            de_jet.visibility = View.GONE
            layout_bouton.visibility = View.VISIBLE
            textResult.visibility = View.VISIBLE
            textResult.text = gameViewModel.updateUi(jetProperty, property, result, resultKeep, "Vous avez réussi le test!!")
            resultDouble = 1
        } else {
            de_jet.visibility = View.GONE
            layout_bouton.visibility = View.VISIBLE
            textResult.visibility = View.VISIBLE
            textResult.text = gameViewModel.updateUi(jetProperty, property, result, resultKeep, "Vous n'avez pas réussi le test.")
            resultDouble = 0
        }
        return resultDouble
    }

   private fun resultDiceJetLucky(choix: ArrayList<Choix>, jet: ArrayList<Jet>): Int {

        for (c in choix) {
            if (c.number == 0 && jet[0].multi != null) {
                result = gameViewModel.diceJet6() * jet[0].multi!!
                c.number = result
            }
        }
        return result
    }

   private fun resultDiceLucky(choix: ArrayList<Choix>) {
        result = gameViewModel.diceJet6()
        updateChoiceDice("")
        when (result) {
            1, 2 -> createButton(choix[0].text, choix[0].number!!)
            3, 4 -> createButton(choix[1].text, choix[1].number!!)
            5, 6 -> createButton(choix[2].text, choix[2].number!!)
        }
    }

    private fun resultLifeDiceJet(numberDice : Int){
        val de1 = gameViewModel.diceJet6()
        val de2 = gameViewModel.diceJet6()
        if (numberDice == 2) {
            result = de1 + de2
            updateChoiceDice("Le lancé de dés est $de1 et $de2. \nVous perdez $result points de vie.")
        } else{
            result = de1 - 1
            updateChoiceDice("Vous perdez $result points de vie.")
        }

        perso.vie -= result

        for (c in choix) {
            c.number?.let { createButton(c.text, it) }
        }
    }

    private fun resultLucky(choix: ArrayList<Choix>) {
        result = gameViewModel.diceJet6()
        updateChoiceDice("")
        when (result) {
            1, 2 -> createButton(choix[0].text, choix[0].number!!)
            3, 4, 5 -> createButton(choix[1].text, choix[1].number!!)
            6 -> createButton(choix[2].text, choix[2].number!!)
        }
    }

    private fun resultLuckyPair(choix: ArrayList<Choix>) {
        result = gameViewModel.diceJet6()
        updateChoiceDice("")
        when (result) {
            result % 2 -> createButton(choix[0].text, choix[0].number!!)
            else -> createButton(choix[1].text, choix[1].number!!)
        }
    }

    private fun resultLucky20(choix: ArrayList<Choix>) {
        result = gameViewModel.diceJet20()
        updateChoiceDice("")
        when {
            result < 11 -> createButton(choix[0].text, choix[0].number!!)
            result in 11..16 -> createButton(choix[1].text, choix[1].number!!)
            result >= 17 -> createButton(choix[2].text, choix[2].number!!)
        }
    }

    /**
     * check if there is a dice  jet to do
     */
    private fun checkIfJet(page: Page) {
        choix = page.choix

        if (page.jet != null) {
            jet = page.jet!!
            for (j in page.jet!!) {
                if (j.de == 6) {
                    de_jet_6.visibility = View.VISIBLE
                    layout_bouton.visibility = View.INVISIBLE
                } else {
                    de_jet.visibility = View.VISIBLE
                    layout_bouton.visibility = View.INVISIBLE
                }
            }
        } else {
            for (c in choix) {
                c.number?.let { createButton(c.text, it) }
            }
        }
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
        layoutParams.setMargins(5, 5, 5, 5)
        button.layoutParams = layoutParams
        button.textSize = 12f
        button.setBackgroundResource(R.drawable.border_item)
        button.text = text
        button.setOnClickListener {
            text_result.text = ""
            text_result2.text = ""
            text_result.visibility = View.GONE
            text_result2.visibility = View.GONE
            goToPage(number)
        }
        layout_bouton.addView(button)
    }

}