package lobna.asset.nutrition.analysis.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lobna.asset.nutrition.analysis.R
import lobna.asset.nutrition.analysis.data.IngredientAnalysis
import lobna.asset.nutrition.analysis.databinding.ItemSummaryBinding

class SummaryAdapter(private val items: List<IngredientAnalysis>) :
    RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val itemSummaryBinding: ItemSummaryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_summary, parent, false
        )
        return SummaryViewHolder(itemSummaryBinding)
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SummaryViewHolder(var itemSummaryBinding: ItemSummaryBinding) :
        RecyclerView.ViewHolder(itemSummaryBinding.root) {

        fun bind(item: IngredientAnalysis) {
            itemSummaryBinding.item = item
        }
    }
}