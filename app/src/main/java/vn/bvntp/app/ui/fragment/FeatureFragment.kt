package vn.bvntp.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import vn.bvntp.app.R
import vn.bvntp.app.databinding.FragmentFeatureBinding
import vn.bvntp.app.helper.ClickHandler

class FeatureFragment : Fragment() {

//    val features = arrayOf(
//        Feature("Tờ điều trị", "@drawable/baseline_article_24"),
//        Feature("Hồ sơ bệnh án", "@drawable/baseline_assessment_24")
//    )

//    private var _binding: FragmentFeatureBinding? = null
//    private val binding get() = _binding!!

    private var _binding: FragmentFeatureBinding? = null
    private val binding get() = _binding!!


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
                    view.findNavController().navigate(R.id.action_featureFragment_to_toDieuTriFragment)
                }
            }

            getString(R.string.ho_so_benh_an) -> {
                ClickHandler.AnimateButtonOnClick(binding.cardViewHoSoBenhAn){
                    view.findNavController().navigate(R.id.action_featureFragment_to_hoSoBenhAnFragment)
                }
            }
        }
    }
}