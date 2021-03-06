package fr.fabriceDesign.ldvhlh_loeilnoir.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.app.App
import fr.fabriceDesign.ldvhlh_loeilnoir.database.PersonnageRepository
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by fabricedesign at 21/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.database
 */

class CreatePersoViewModel @Inject constructor() : ViewModel() {

    private var executor: Executor = Executors.newSingleThreadExecutor()
    private val persoRepository : PersonnageRepository

    init {
        val personnageDao = App.database.personnageDao()
        persoRepository =
            PersonnageRepository(
                personnageDao
            )
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
            persoRepository.updatePerso(personnage)
        }
    }

    fun deletePerso(personnage: Personnage){
        executor.execute {
            persoRepository.deletePerso(personnage)
        }
    }
}