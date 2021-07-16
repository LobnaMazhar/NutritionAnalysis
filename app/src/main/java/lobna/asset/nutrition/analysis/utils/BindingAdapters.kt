package lobna.asset.nutrition.analysis.utils

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("bind:onBackListener")
    fun onBackPressed(toolbar: Toolbar, lambda: () -> Unit) {
        toolbar.setNavigationOnClickListener {
            lambda.invoke()
        }
    }

    @JvmStatic
    @BindingAdapter("recycler:adapter")
    fun recyclerAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    ) {
        recyclerView.adapter = adapter
    }
}