package vn.bvntp.app.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import vn.bvntp.app.R
import vn.bvntp.app.model.ToDieuTri

class ToDieuTriAdapter:  RecyclerView.Adapter<ToDieuTriAdapter.ToDieuTriViewHolder>() {
    var data = arrayOf<ToDieuTri>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDieuTriViewHolder = ToDieuTriViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ToDieuTriViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, position)
    }
     class ToDieuTriViewHolder(val rootView: MaterialCardView): RecyclerView.ViewHolder(rootView) {
         var isExpanded = false
         val cardview = rootView.findViewById<MaterialCardView>(R.id.material_card_view)
         val buttonExpand = rootView.findViewById<ImageButton>(R.id.expand_button)
         val moreText = rootView.findViewById<LinearLayout>(R.id.expandable_view)
         val lessText = rootView.findViewById<LinearLayout>(R.id.close_view)
        val textView = rootView.findViewById<TextView>(R.id.card_view_ngay)
        companion object {
            fun inflateFrom(parent: ViewGroup): ToDieuTriViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(
                    R.layout.fragment_to_dieu_tri_item, parent, false) as MaterialCardView
                return ToDieuTriViewHolder(view)
            }
        }

         fun handleClick() {

             if (!isExpanded) {
                 lessText.visibility = View.GONE
                 moreText.visibility = View.VISIBLE
                 buttonExpand.setImageResource(R.drawable.baseline_arrow_drop_up_24)
                 isExpanded = true

             } else {
                 lessText.visibility = View.VISIBLE
                 moreText.visibility = View.GONE
                 buttonExpand.setImageResource(R.drawable.baseline_arrow_drop_down_24)
                 isExpanded = false
             }
         }

        fun bind(item: ToDieuTri, position: Int) {
            textView.text = item.BSYLENH

            buttonExpand.setOnClickListener{
                handleClick()
            }

            cardview.setOnClickListener{
                handleClick()
            }


        }


    }
}