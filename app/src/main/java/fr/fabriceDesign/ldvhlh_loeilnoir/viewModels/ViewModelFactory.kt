package fr.fabriceDesign.ldvhlh_loeilnoir.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.feuille_perso.FeuillePersoViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.game.GameViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.pages.PagesViewModel
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Created by fabricedesign at 21/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.viewModels
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
}