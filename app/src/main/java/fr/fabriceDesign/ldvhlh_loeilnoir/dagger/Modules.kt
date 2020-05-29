package fr.fabriceDesign.ldvhlh_loeilnoir.dagger

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.ViewModelFactory

/**
 * Created by fabricedesign at 26/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.dagger
 */
@Module
abstract class Modules {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}