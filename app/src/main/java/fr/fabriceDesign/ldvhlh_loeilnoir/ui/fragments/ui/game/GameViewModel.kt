package fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.fabriceDesign.ldvhlh_loeilnoir.app.App
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Page
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity.Game.Companion.NAME_PERSO
import java.security.SecureRandom
import javax.inject.Inject

class GameViewModel @Inject constructor() : ViewModel() {

    var perso: LiveData<Personnage> = App.database.personnageDao().getPersonnageByName(NAME_PERSO!!)

    fun parseJson(json: String): MutableList<Page> {
        val pagesLivres = mutableListOf<Page>()
        //permet de connaitre le type de retour de la liste
        val listType = object : TypeToken<ArrayList<Page>>() {}.type
        //on crée un tableau de pages
        val pages = Gson().fromJson<ArrayList<Page>>(json, listType)
        for (page in pages) {
            pagesLivres.add(page)
        }
        return pagesLivres
    }

    fun updateUi(capa: String, capaPerso: Int, result: Int, resultKeep: Int, texte: String): String {
        val text = StringBuilder()
        text.append("Votre $capa = $capaPerso.")
        text.append("\nRésultat du lancé de dé : $result")
        text.append("\nRésultat du dé après modification : $resultKeep")
        text.append("\n$texte")
        return text.toString()
    }

    fun updateChoice(result: Int, texte: String): String {
        val text = StringBuilder()
        text.append("\nRésultat du lancé de dé : $result")
        text.append("\n$texte")
        return text.toString()
    }


    fun diceJet6(): Int {
        val rand = SecureRandom()
        return rand.nextInt(6) + 1
    }

    fun diceJet20(): Int {
        val rand = SecureRandom()
        return rand.nextInt(20) + 1
    }

}