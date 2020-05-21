package fr.fabriceDesign.ldvhlh_loeilnoir.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.app.App
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import java.util.concurrent.Executor

/**
 * Created by fabricedesign at 21/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.database
 */
class CreatePersoViewModel(private val executor: Executor) : ViewModel() {

    private val persoRepository : PersonnageRepository

    init {
        val personnageDao = App.database.personnageDao()
        persoRepository = PersonnageRepository(personnageDao)
    }

    var persos : LiveData<List<Personnage>> = persoRepository.personnages

    fun getPersoById(persoId : Long): LiveData<Personnage> =
        persoRepository.getPersoById(persoId)

    fun createPerso(personnage: Personnage) {
        executor.execute {
            val long = persoRepository.createPerso(personnage)
        }
    }

    fun updatePerso(personnage: Personnage) {
        executor.execute {
            persoRepository.updateProperty(personnage)
        }
    }
}