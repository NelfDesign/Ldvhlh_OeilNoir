package fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.game

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Page
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity.Game.Companion.PAGE_NUMBER
import javax.inject.Inject

class GameViewModel @Inject constructor() : ViewModel() {

    fun setNumberPage(number : Int){
        PAGE_NUMBER = number
    }
    fun getNumberPage() : Int {
        return PAGE_NUMBER
    }

    fun parseJson(json : String) : MutableList<Page>{
        val pagesLivres = mutableListOf<Page>()
        //permet de connaitre le type de retour de la liste
        val listType = object : TypeToken<ArrayList<Page>>() {}.type
        //on crée un tableau de pages
        val pages= Gson().fromJson<ArrayList<Page>>(json, listType)
        for (page in pages) {
            pagesLivres.add(page)
        }
        return pagesLivres
    }

}