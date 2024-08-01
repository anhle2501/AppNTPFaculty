package vn.bvntp.app.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import vn.bvntp.app.App
import vn.bvntp.app.R
import vn.bvntp.app.databinding.FragmentDanhSachToDieuTriBinding
import vn.bvntp.app.databinding.FragmentDsHienDienBinding
import vn.bvntp.app.viewmodel.DsHienDienViewModel
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModel

class DsHienDienFragment : Fragment() {

    private var _binding: FragmentDsHienDienBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = DsHienDienFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDsHienDienBinding.inflate(inflater, container, false)
        val view = binding.root

        val appContainer = (requireActivity().applicationContext as App).container
        val dsHienDienViewModel = ViewModelProvider(requireActivity(), appContainer.dsHienDienFactory).get(
            DsHienDienViewModel::class.java)
        // binding viewmodel
        binding.dsHienDien = dsHienDienViewModel

        dsHienDienViewModel.getDsHienDienViewModel(requireActivity(), 48, {})


        return view
    }
}