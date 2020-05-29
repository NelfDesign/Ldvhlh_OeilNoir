package fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.feuille_perso

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.app.App
import fr.fabriceDesign.ldvhlh_loeilnoir.database.PersonnageRepository
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import javax.inject.Inject

class FeuillePersoViewModel @Inject constructor(): ViewModel() {

    private val persoRepository : PersonnageRepository

    init {
        val personnageDao = App.database.personnageDao()
        persoRepository = PersonnageRepository(personnageDao)
    }

    fun getPersoByName(persoName : String): LiveData<Personnage> = persoRepository.getPersoByName(persoName)
}