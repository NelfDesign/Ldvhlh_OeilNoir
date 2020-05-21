package fr.fabriceDesign.ldvhlh_loeilnoir.viewModels

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by fabricedesign at 21/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.viewModels
 */
class Injection {

    companion object{

        private fun provideExecutor() : Executor {
            return Executors.newSingleThreadExecutor()
        }

        fun provideViewModelFactory() : ViewModelFactory{
            val executor = provideExecutor()
            return ViewModelFactory(executor)
        }

    }
}