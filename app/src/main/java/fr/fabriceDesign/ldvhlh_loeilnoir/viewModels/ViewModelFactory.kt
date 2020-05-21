package fr.fabriceDesign.ldvhlh_loeilnoir.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.fabriceDesign.ldvhlh_loeilnoir.database.CreatePersoViewModel
import java.util.concurrent.Executor

/**
 * Created by fabricedesign at 21/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.viewModels
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val executor : Executor) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(CreatePersoViewModel::class.java) -> return CreatePersoViewModel( executor) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}