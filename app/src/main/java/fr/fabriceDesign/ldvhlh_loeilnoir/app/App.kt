package fr.fabriceDesign.ldvhlh_loeilnoir.app

import android.app.Application
import android.content.Context
import fr.fabriceDesign.ldvhlh_loeilnoir.dagger.DaggerComponents
import fr.fabriceDesign.ldvhlh_loeilnoir.database.Database
import timber.log.Timber

/**
 * Created by fabricedesign at 14/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir
 */
class App : Application() {

    companion object{
        lateinit var appContext : Context
        lateinit var database: Database
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appContext = applicationContext
        database = Database.getDatabase(appContext)

    }
}