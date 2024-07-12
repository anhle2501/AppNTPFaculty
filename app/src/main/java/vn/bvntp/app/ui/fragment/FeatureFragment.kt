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
import androidx.navigation.findNavController
import vn.bvntp.app.App
import vn.bvntp.app.R
import vn.bvntp.app.databinding.FragmentFeatureBinding
import vn.bvntp.app.helper.ClickHandler
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModel
import vn.bvntp.app.viewmodel.ThongTinBenhNhanViewModel

class FeatureFragment : Fragment() {

//    val features = arrayOf(
//        Feature("Tờ điều trị", "@drawable/baseline_article_24"),
//        Feature("Hồ sơ bệnh án", "@drawable/baseline_assessment_24")
//    )

//    private var _binding: FragmentFeatureBinding? = null
//    private val binding get() = _binding!!

    private var _binding: FragmentFeatureBinding? = null
    private val binding get() = _binding!!

    private lateinit var hsbaViewModel: HoSoBenhAnViewModel
    private lateinit var thongTinBenhNhanViewModel: ThongTinBenhNhanViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        _binding = FragmentFeatureBinding.inflate(inflater, container, false)
//        val view = binding.root
//
//        val featureAdapter = FeatureItemAdapter()
//        binding.featuresList.adapter = featureAdapter
//        featureAdapter.data = features

        _binding = FragmentFeatureBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.navigateTo = this
        binding.lifecycleOwner = viewLifecycleOwner
        val appContainer = (requireActivity().applicationContext as App).container

        // get maBenhNhan
        hsbaViewModel = ViewModelProvider(requireActivity(), appContainer.hsbaViewModelFactory).get(
            HoSoBenhAnViewModel::class.java)
        thongTinBenhNhanViewModel =  ViewModelProvider(requireActivity(), appContainer.thongTinBenhNhanFactory).get(
            ThongTinBenhNhanViewModel::class.java)

        binding.thongTinBenhNhanViewModel = thongTinBenhNhanViewModel
//        binding.hsbaViewModel = hsbaViewModel




        thongTinBenhNhanViewModel.thongTinBenhNhan.observe(viewLifecycleOwner, Observer {
//            binding.tvHoTen.text = it.HOTEN
            Log.d("hsbaViewModel feature", hsbaViewModel.maBenhNhan.value.toString())
        })




        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun goToFeature(code: String){
        val view = binding.root
        when(code) {
            getString(R.string.to_dieu_tri) -> {
                ClickHandler.AnimateButtonOnClick(binding.cardViewToDieuTri){
                    if (hsbaViewModel.maBenhNhan.value != "")
                        view.findNavController().navigate(R.id.action_featureFragment_to_danhSachToDieuTriFragment)
                    else
                        Toast.makeText(context, "Vui lòng nhập mã bệnh nhân", Toast.LENGTH_LONG).show()
                }
            }

            getString(R.string.ho_so_benh_an) -> {
                ClickHandler.AnimateButtonOnClick(binding.cardViewHoSoBenhAn){
                    if (hsbaViewModel.maBenhNhan.value != "")
                        view.findNavController().navigate(R.id.action_featureFragment_to_hoSoBenhAnFragment)
                    else
                        Toast.makeText(context, "Vui lòng nhập mã bệnh nhân", Toast.LENGTH_LONG).show()


                }
            }
        }
    }
}