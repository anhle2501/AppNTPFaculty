package vn.bvntp.app.ui.fragment

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.bvntp.app.R
import vn.bvntp.app.databinding.FragmentDsHienDienBinding
import vn.bvntp.app.databinding.FragmentToDieuTriItemBinding
import vn.bvntp.app.model.ThongTinHienDien
import vn.bvntp.app.model.ToDieuTri
import java.text.SimpleDateFormat
import java.util.Locale

class DsHienDienAdapter:  RecyclerView.Adapter<DsHienDienAdapter.DsHienDienViewHolder>() {

    var data = ArrayList<ThongTinHienDien>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DsHienDienViewHolder = DsHienDienViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: DsHienDienViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, position)
    }

    fun submitList(it: ArrayList<ToDieuTri>) {

    }

    class DsHienDienViewHolder(val binding: FragmentDsHienDienBinding): RecyclerView.ViewHolder(binding.root) {
        var isExpanded = false
        companion object {
            fun inflateFrom(parent: ViewGroup): DsHienDienViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(
//                    R.layout.fragment_to_dieu_tri_item, parent, false) as MaterialCardView

                val binding = FragmentDsHienDienBinding.inflate(layoutInflater, parent, false)


                return DsHienDienViewHolder(binding)
            }
        }

        fun handleClick() {

//            if (!isExpanded) {
//                binding.closeView.visibility = View.GONE
//                binding.expandableView.visibility = View.VISIBLE
//                binding.expandButton.setImageResource(R.drawable.baseline_arrow_drop_up_24)
//                isExpanded = true
//
//            } else {
//                binding.closeView.visibility = View.VISIBLE
//                binding.expandableView.visibility = View.GONE
//                binding.expandButton.setImageResource(R.drawable.baseline_arrow_drop_down_24)
//                isExpanded = false
//            }
        }

        fun bind(item: ThongTinHienDien, position: Int) {

//            binding.expandButton.setOnClickListener{
//                handleClick()
//            }
//
//            binding.materialCardView.setOnClickListener{
//                handleClick()
//            }
//
//            binding.yLenh.setOnClickListener{
//                handleClick()
//            }
//
//            binding.ngay1.text = formateDate(item.NGAY)
//            binding.bacSi1.text = formateName(item.BSDIENBIEN)
//            binding.dienbien1.text = "Diễn biến: " + Html.fromHtml(item.DIENBIEN)
//
//            binding.ngay.text = formateDate(item.NGAY)
//            binding.bacSi.text = formateName(item.BSDIENBIEN)
//            binding.dienbien.text = "Diễn biến: " + Html.fromHtml(item.DIENBIEN)
//            binding.yLenh.text = "Y lệnh: " + Html.fromHtml(item.YLENH)
        }
        fun formateDate(string: String): String {
            val ldt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            var formattedTime = ""
            try {
                val date = ldt.parse(string)
                val formatter = SimpleDateFormat("HH:mm:ss dd-MM-yyyy")
                formattedTime = formatter.format(date)
                val arrayString = formattedTime.split(" ")
                formattedTime = "Thời gian: " + arrayString[0] + " Ngày: " + arrayString[1]
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return formattedTime
        }

        fun formateName(string: String): String {
            val name = string.split("-")
            val formatted = "Bác sĩ: " + name[0]
            return formatted
        }
    }
}