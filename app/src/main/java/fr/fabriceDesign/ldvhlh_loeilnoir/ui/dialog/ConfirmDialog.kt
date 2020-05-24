package fr.fabriceDesign.ldvhlh_loeilnoir.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

/**
 * Created by fabricedesign at 24/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.ui.dialog
 */
class ConfirmDialogFragment : DialogFragment() {

        interface ConfirmDialogListener {
            fun onDialogPositiveClick()
            fun onDialogNegativeClick()
        }

        var listener : ConfirmDialogListener? = null

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(requireActivity())

            builder.setMessage("Votre valeur d'adresse est faible. Souhaitez-vous baisser votre note d'attaque ou de parade?")
                .setPositiveButton("Attaque", { _, _ ->listener?.onDialogPositiveClick()})
                .setNegativeButton("Parade",{ _, _-> listener?.onDialogNegativeClick()})
                .setTitle("Valeur d'adresse basse")

            return builder.create()
        }

}