package fr.fabriceDesign.ldvhlh_loeilnoir.dagger

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.feuille_perso.FeuillePersoViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.game.GameViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.CreatePersoViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.ViewModelFactory
import kotlin.reflect.KClass

/**
 * Created by fabricedesign at 28/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.dagger
 */
@Module
abstract class ViewModelsModules {

    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(CreatePersoViewModel::class)
    internal abstract fun createPersoViewModel(viewModel: CreatePersoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(FeuillePersoViewModel::class)
    abstract fun feuillePersoViewModel(viewModel: FeuillePersoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(GameViewModel::class)
    abstract fun gameViewModel(viewModel: GameViewModel): ViewModel

}