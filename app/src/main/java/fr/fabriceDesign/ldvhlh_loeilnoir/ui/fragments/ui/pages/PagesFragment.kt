package fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.fabriceDesign.ldvhlh_loeilnoir.R

class PagesFragment : Fragment() {

    private lateinit var notificationsViewModel: PagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(PagesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pages, container, false)


        return root
    }
}
