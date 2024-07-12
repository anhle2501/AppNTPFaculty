package vn.bvntp.app.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vn.bvntp.app.App
import vn.bvntp.app.databinding.FragmentDanhSachToDieuTriBinding
import vn.bvntp.app.model.ToDieuTri
import vn.bvntp.app.model.ToDieuTriRequest
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModel
import vn.bvntp.app.viewmodel.ToDieuTriViewModel

class DanhSachToDieuTriFragment : Fragment() {


    private var _binding: FragmentDanhSachToDieuTriBinding ? = null
    private val binding get() = _binding!!

    private val toDieuTri = ToDieuTri(
        "1",
        "1",
        "1",
        "1",
        "1986-04-08 12:30",
        "1",
        "1",
        "1",
        2,
        "1",
        "2",
        5,
        "1",
        "1",
        "1",
        "111111111111111111",
        "1",
        "1",
        "1",
        1f,
        "1",
        1,
        1,
        "1",
        "1",
        "1",
        "1",
        true,
        1,
        1f,
        1f,
        1f,
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        false,
        false
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDanhSachToDieuTriBinding.inflate(inflater, container, false)
        val view = binding.root


        val appContainer = (requireActivity().applicationContext as App).container
        // provide modelView
        val dsToDieuTri = ViewModelProvider(requireActivity(), appContainer.toDieuTriFactory).get(
            ToDieuTriViewModel::class.java)
        // get maBenhNhan
        val hsbaViewModel = ViewModelProvider(requireActivity(), appContainer.hsbaViewModelFactory).get(
            HoSoBenhAnViewModel::class.java)
        // binding viewmodel
        binding.toDieuTriViewModel = dsToDieuTri

        hsbaViewModel.maBenhNhan.value?.let { Log.d("hsbaViewModel", it) }

        // for recycler view
        val adapter = ToDieuTriAdapter()

        // context
        val context = requireContext()
        hsbaViewModel.modelLichSuVaoVienView2(context, {})


        // Observe isLoading LiveData
        dsToDieuTri.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            // Update UI, e.g., show or hide a progress bar
            if (isLoading) {
                // Show progress bar
                binding.loadingIcon.visibility = View.VISIBLE
                binding.danhSachToDieuTri.visibility = View.GONE
            } else {
                // Hide progress bar
                binding.loadingIcon.visibility = View.GONE
                binding.danhSachToDieuTri.visibility = View.VISIBLE
            }
        })

        dsToDieuTri.progress.observe(viewLifecycleOwner, Observer { progress ->
            binding.loadingIcon.progress = progress
        })


        hsbaViewModel.maBenhNhan.observe(viewLifecycleOwner, Observer {
            if (it.length == 8){
                hsbaViewModel.updateMaBenhNhan(it)
                hsbaViewModel.modelLichSuVaoVienView2(context, {})
            }
        })

        hsbaViewModel.lichSuDieuTri.observe(viewLifecycleOwner, Observer {
            if ( it.size > 0) {
                val toDieuTriRequest = ToDieuTriRequest(it.get(0))
                dsToDieuTri.fectchData(context, toDieuTriRequest){
                        it -> if (it) Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show() else  Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show()
                }
            }
        })

        dsToDieuTri.danhSachToDieuTri.observe(viewLifecycleOwner) {
            it?.let {
                adapter.data = it
            }
        }

        binding.danhSachToDieuTri.adapter = adapter


        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}