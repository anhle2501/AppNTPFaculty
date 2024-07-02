package vn.bvntp.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.bvntp.app.R
import vn.bvntp.app.databinding.FragmentToDieuTriBinding

class ToDieuTriFragment : Fragment() {

    private var _binding: FragmentToDieuTriBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_to_dieu_tri, container, false)
//        val navigation = view.findNavController();



        return view
    }


}