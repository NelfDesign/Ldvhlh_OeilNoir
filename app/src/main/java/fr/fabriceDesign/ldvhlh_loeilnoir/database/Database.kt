package fr.fabriceDesign.ldvhlh_loeilnoir.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage

/**
 * Created by fabricedesign at 21/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.database
 */
const val DATABASE_NAME = "oeil_noir"

@androidx.room.Database(entities = [Personnage::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    //DAO
    abstract fun personnageDao() : PersonnageDao

    companion object{
        @Volatile
        private var INSTANCE : Database? = null

        fun getDatabase(context : Context) : Database {
            //if the instance is not null return it
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    DATABASE_NAME
                )
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

}