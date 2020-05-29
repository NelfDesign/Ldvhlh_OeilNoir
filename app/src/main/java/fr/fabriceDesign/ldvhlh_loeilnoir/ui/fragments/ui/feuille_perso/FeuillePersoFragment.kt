package fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.feuille_perso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.fabriceDesign.ldvhlh_loeilnoir.R
import fr.fabriceDesign.ldvhlh_loeilnoir.dagger.DaggerComponents
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity.Game
import fr.fabriceDesign.ldvhlh_loeilnoir.ui.dialog.ConfirmDialogFragment
import kotlinx.android.synthetic.main.fragment_feuille.*
import javax.inject.Inject

class FeuillePersoFragment : Fragment() {

    @Inject
    lateinit var factory : ViewModelProvider.Factory
    private lateinit var feuillePersoViewModel: FeuillePersoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_feuille, container, false)

        factory = DaggerComponents.create().createFactory()
        feuillePersoViewModel = ViewModelProvider(this, factory).get(FeuillePersoViewModel::class.java)
        feuillePersoViewModel.getPersoByName(Game.NAME_PERSO!!).observe(viewLifecycleOwner, Observer { perso -> updateUI(perso) })

        return root
    }

    private fun updateUI(perso: Personnage) {
        text_name.text = perso.name
        sexe_feuille.text = perso.sexe
        text_type.text = perso.type
        text_courage.text = perso.courage.toString()
        text_force.text = perso.force.toString()
        text_adresse.text = perso.adresse.toString()
        text_intel.text = perso.intel.toString()
        text_charisme.text = perso.charisme.toString()
        text_vie.text = perso.vie.toString()
        text_astrale.text = perso.astrale.toString()
        text_fortune.text = perso.fortune.toString()
        text_aventure.text = perso.aventure.toString()
        if (perso.force == 13) text_pi.text = "PI : 1D + 4" else text_pi.text = "PI : 1D + 3"
        if (perso.force == 8) text_pi.text = "PI : 1D + 2" else text_pi.text = "PI : 1D + 3"
        checkAdresse(perso.adresse)
        text_arme.text = getString(R.string.machette)
        text_protection.text = getString(R.string.cuir)
        text_pr.text = "PR : 3"
        setBackgroundImage(perso.type, perso.sexe)
    }

    private fun checkAdresse(adresse: Int) {
        when (adresse) {
            13 -> {
                text_attaque.text = "11"
                text_parade.text = "8"
            }
            8 -> showDialog()
            else -> {
                text_attaque.text = "10"
                text_parade.text = "8"
            }
        }
    }

    private fun showDialog() {
        val fragment = ConfirmDialogFragment()
        fragment.listener = object :
            ConfirmDialogFragment.ConfirmDialogListener {
            override fun onDialogPositiveClick() {
                text_attaque.text = "9"
                text_parade.text = "8"
            }

            override fun onDialogNegativeClick() {
                text_attaque.text = "10"
                text_parade.text = "7"
            }
        }
    }

    private fun setBackgroundImage(type: String, sexe: String) {
        when (type) {
            "Guerrier(e)" -> {
                if (sexe == "Homme") {
                    image_perso.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.guerrier_humain)
                } else {
                    image_perso.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.guerriere_humaine)
                }
            }
            "Nain(e)" -> {
                if (sexe == "Homme") {
                    image_perso.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.nain_guerrier2)
                } else {
                    image_perso.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.naine_guerriere)
                }
            }
            else -> {
                if (sexe == "Homme") {
                    image_perso.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.aventurier)
                } else {
                    image_perso.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.amazone)
                }
            }
        }
    }
}
