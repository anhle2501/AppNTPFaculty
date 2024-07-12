package vn.bvntp.app.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vn.bvntp.app.App
import vn.bvntp.app.R
import vn.bvntp.app.ui.activity.PdfViewer
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HoSoBenhAnFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HoSoBenhAnFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val appContainer = (requireActivity().applicationContext as App).container
        val hsbaViewModel = ViewModelProvider(requireActivity(), appContainer.hsbaViewModelFactory).get(
            HoSoBenhAnViewModel::class.java)

        val context = requireContext()
        hsbaViewModel.modelHoSoBenhAnView(
            context
        ) {
            val intent = Intent(
                context, PdfViewer::class.java
            )
            intent.putExtra(
                "fileUri", hsbaViewModel.getTemp()
            )
            context.startActivity(intent)
        }

        return inflater.inflate(R.layout.fragment_ho_so_benh_an, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HoSoBenhAnFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HoSoBenhAnFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}