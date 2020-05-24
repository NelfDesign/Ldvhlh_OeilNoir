package fr.fabriceDesign.ldvhlh_loeilnoir.database

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage

/**
 * Created by fabricedesign at 21/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.database
 */
@Dao
interface PersonnageDao {

    @Query("SELECT * FROM personnage")
    fun getAllPersonnage() : LiveData<List<Personnage>>

    @Query("SELECT * FROM personnage WHERE id = :personnageId")
    fun getPersonnage(personnageId : Long) : LiveData<Personnage>

    @Query("SELECT * FROM personnage WHERE name = :personnageName")
    fun getPersonnageByName(personnageName : String) : LiveData<Personnage>

    @Insert
    fun createProperty(perso : Personnage) : Long

    @Update
    fun updateProperty(perso: Personnage) : Int

    @Delete
    fun deleteProperty(perso: Personnage)
}