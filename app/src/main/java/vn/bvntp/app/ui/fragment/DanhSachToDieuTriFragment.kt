package vn.bvntp.app.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import vn.bvntp.app.databinding.FragmentDanhSachToDieuTriBinding
import vn.bvntp.app.model.ToDieuTri
import java.time.LocalDateTime

class DanhSachToDieuTriFragment : Fragment() {


    private var _binding: FragmentDanhSachToDieuTriBinding ? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    private val toDieuTri = ToDieuTri(
        "1",
        "1",
        "1",
        "1",
        LocalDateTime.now(),
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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDanhSachToDieuTriBinding.inflate(inflater, container, false)
        val view = binding.root
        val adapter = ToDieuTriAdapter()


//        Log.d("todieutri", toDieuTri.toString())
//        Log.d("todieutri", toDieuTri.BSYLENH.toString())
//        val text = TextView(context)
//        text.text = " afdjfdlkfjkasdfjalkjfsdflkasdjflksdjfjasdlkfjasdlkfjasdlkfjlkasdjflksdjfljasl 11111111111"
//        binding.root.addView(text)
        adapter.data = arrayOf(toDieuTri, toDieuTri, toDieuTri, toDieuTri,toDieuTri,toDieuTri,toDieuTri)
        binding.danhSachToDieuTri.adapter = adapter
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}