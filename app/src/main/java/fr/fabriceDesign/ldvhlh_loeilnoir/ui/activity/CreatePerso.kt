package fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import butterknife.OnClick
import fr.fabriceDesign.ldvhlh_loeilnoir.R
import fr.fabriceDesign.ldvhlh_loeilnoir.database.CreatePersoViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.Injection
import kotlinx.android.synthetic.main.activity_create_perso.*
import kotlinx.android.synthetic.main.tollbar.*
import timber.log.Timber
import java.security.SecureRandom

class CreatePerso : AppCompatActivity() {

    //FIELDS
    lateinit var name: String
    lateinit var sexe: String
    lateinit var type: String
    private var courage: Int = 0
    private var force: Int = 0
    private var adresse: Int = 0
    private var charisme: Int = 0
    private var intel: Int = 0
    private var fortune: Int = 0
    private var vie: Int = 30
    private lateinit var persoViewModel : CreatePersoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_perso)
        ButterKnife.bind(this)

        toolbar.title = "Création du personnage"
        setSupportActionBar(toolbar)

        val factory = Injection.provideViewModelFactory()
        persoViewModel = ViewModelProvider(this, factory).get(CreatePersoViewModel::class.java)

        de_depart.setOnClickListener { checkValues()}

    }

    private fun checkValues() {
        name = text_name.text.toString()

       when {
           radio_aventurier.isChecked -> type = radio_aventurier.text.toString()
           radio_guerrier.isChecked -> type = radio_guerrier.text.toString()
           radio_nain.isChecked -> {
               type = radio_nain.text.toString()
               vie = 35
           }
       }
        when{
            radio_men.isChecked -> sexe = "Homme"
            radio_women.isChecked -> sexe = "Femme"
        }
        val perso = Personnage(0, name, sexe, type, courage, force, intel, charisme, adresse, vie ,0 ,0, fortune)
        persoViewModel.createPerso(perso)

    }

    @OnClick(R.id.de_courage, R.id.de_adresse, R.id.de_charisme, R.id.de_force, R.id.de_intel, R.id.de_fortune, R.id.type_button)
    fun buttonClickFields(view: View){
        when(view.id){
            R.id.de_courage ->{
                courage = defineFields()
                setPersoFields(edit_courage, de_courage, courage)
            }
            R.id.de_adresse -> {
                adresse = defineFields()
                setPersoFields(edit_adresse, de_adresse, adresse)
            }
            R.id.de_charisme ->  {
                charisme = defineFields()
                setPersoFields(edit_charisme, de_charisme, charisme)
            }
            R.id.de_force ->  {
                force = defineFields()
                setPersoFields(edit_force, de_force, force)
            }
            R.id.de_intel ->  {
                intel = defineFields()
                setPersoFields(edit_intel, de_intel, intel)
            }
            R.id.de_fortune ->  {
                fortune = defineFortune()
                setPersoFields(edit_fortune, de_fortune, fortune)
            }
            R.id.type_button -> {
                radio_group.visibility = View.VISIBLE
                checkPersoFields(force, courage, adresse)
            }
        }
    }

    private fun defineFields() : Int {
        val rand = SecureRandom()
        return (rand.nextInt(6) + 1) + 7
    }

    private fun defineFortune() : Int {
        val rand = SecureRandom()
        return ((rand.nextInt(6) + 1) + 6) * 10
    }

    private fun setPersoFields(text : TextView, button : ImageButton, number : Int){
        text.text = number.toString()
        button.visibility = View.GONE
    }

    private fun checkPersoFields(force : Int, courage : Int, adresse : Int){
        if (force >= 12 && courage >= 12){
            radio_guerrier.visibility = View.VISIBLE
        }
        if (force >= 12 && adresse >= 12){
            radio_nain.visibility = View.VISIBLE
        }
    }
}