//package vn.bvntp.app.ui.fragment
//
////class FeatureItemAdapter: RecyclerView.Adapter<FeatureItemAdapter.FeatureItemViewHolder>() {
////
////    var data = arrayOf<Feature>()
////        set(value) {
////            field = value
////            notifyDataSetChanged()
////        }
////    override fun getItemCount() = data.size
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureItemViewHolder = FeatureItemViewHolder.inflateFrom(parent)
////
////    override fun onBindViewHolder(holder: FeatureItemViewHolder, position: Int) {
////        val item = data[position]
////        holder.bind(item)
////    }
////    class FeatureItemViewHolder(val rootView: CardView): RecyclerView.ViewHolder(rootView) {
////
////        val text = rootView.findViewById<TextView>(R.id.featureName)
////        val icon = rootView.findViewById<ImageView>(R.id.iconFeature)
////        companion object {
////            fun inflateFrom(parent: ViewGroup): FeatureItemViewHolder {
////                val layoutInflater = LayoutInflater.from(parent.context)
////                val view = layoutInflater.inflate(
////                    R.layout.feature_item, parent, false) as CardView
////                return FeatureItemViewHolder(view)
////            }
////        }
////        fun bind(item: Feature) {
////            text.text = item.name
////            icon.setBackgroundResource(R.drawable.baseline_article_24)
////        }
////    }
////}