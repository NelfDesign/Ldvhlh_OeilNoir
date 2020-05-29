package fr.fabriceDesign.ldvhlh_loeilnoir.dagger

import dagger.Component
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.CreatePersoViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.ViewModelFactory
import javax.inject.Singleton

/**
 * Created by fabricedesign at 26/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.dagger
 */
@Singleton
@Component(modules = [Modules::class, ViewModelsModules::class])
interface Components {

    fun createFactory () : ViewModelFactory

}