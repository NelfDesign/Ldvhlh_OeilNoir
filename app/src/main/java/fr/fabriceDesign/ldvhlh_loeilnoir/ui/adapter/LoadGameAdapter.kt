package fr.fabriceDesign.ldvhlh_loeilnoir.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.fabriceDesign.ldvhlh_loeilnoir.R
import fr.fabriceDesign.ldvhlh_loeilnoir.app.App
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import kotlinx.android.synthetic.main.item_perso.view.*

/**
 * Created by fabricedesign at 25/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.ui.adapter
 */
class LoadGameAdapter(private val persos : List<Personnage>, private val listener : LoadGameAdapterListener) : RecyclerView.Adapter<LoadGameAdapter.LoadGameViewHolder>(), View.OnClickListener {

    interface LoadGameAdapterListener {
        fun onPersoSelected(perso : Personnage)
        fun delete(perso: Personnage)
    }

    class LoadGameViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val cardView = item.cardView_item!!
        val text_name = item.name_item!!
        val text_vie = item.text_vie!!
        val text_type = item.type_item!!
        val deleteButton = item.delete_button!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : LoadGameViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_perso, parent, false)
        return LoadGameViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: LoadGameViewHolder, position: Int) {
        val perso = persos[position]

        with(holder){
            cardView.setOnClickListener(this@LoadGameAdapter)
            cardView.tag = perso
            text_name.text = perso.name
            text_type.text = perso.type
            text_vie.text = App.appContext.getString(R.string.text_energie, perso.vie.toString())
            deleteButton.setOnClickListener { listener.delete(perso) }
        }

    }

    override fun getItemCount(): Int = persos.size

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cardView_item -> listener.onPersoSelected(v.tag as Personnage)
        }
    }

}