package fr.fabriceDesign.ldvhlh_loeilnoir.database

import androidx.lifecycle.LiveData
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import javax.inject.Inject

/**
 * Created by fabricedesign at 21/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.database
 */
class PersonnageRepository @Inject constructor(private val personnageDao: PersonnageDao) {

    var long : Long = 0

    val personnages = personnageDao.getAllPersonnage()

    fun getPersoById(persoId : Long) : LiveData<Personnage> = personnageDao.getPersonnage(persoId)

    fun getPersoByName(persoName : String) : LiveData<Personnage> = personnageDao.getPersonnageByName(persoName)

    fun createPerso(personnage: Personnage) : Long {
        long = personnageDao.createPerso(personnage)
        return long
    }

    fun updatePerso(personnage: Personnage) {
        personnageDao.updatePerso(personnage)
    }

    fun deletePerso(personnage: Personnage) {
        personnageDao.deletePerso(personnage)
    }
}