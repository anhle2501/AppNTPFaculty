package vn.bvntp.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.bvntp.app.R

/**
 * A simple [Fragment] subclass.
 * Use the [PatientInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PatientInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_tin_benh_nhan, container, false)
    }


}