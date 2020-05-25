package fr.fabriceDesign.ldvhlh_loeilnoir.database

import androidx.lifecycle.LiveData
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage

/**
 * Created by fabricedesign at 21/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.database
 */
class PersonnageRepository(private val personnageDao: PersonnageDao) {

    var long : Long = 0

    val personnages = personnageDao.getAllPersonnage()

    fun getPersoById(persoId : Long) : LiveData<Personnage> = personnageDao.getPersonnage(persoId)

    fun getPersoByName(persoName : String) : LiveData<Personnage> = personnageDao.getPersonnageByName(persoName)

    fun createPerso(personnage: Personnage) : Long {
        long = personnageDao.createProperty(personnage)
        return long
    }

    fun updateProperty(personnage: Personnage) {
        personnageDao.updateProperty(personnage)
    }

    fun deleteProperty(personnage: Personnage) {
        personnageDao.deleteProperty(personnage)
    }
}