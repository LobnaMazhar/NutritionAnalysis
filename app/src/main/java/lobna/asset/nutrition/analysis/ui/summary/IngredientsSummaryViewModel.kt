package lobna.asset.nutrition.analysis.ui.summary

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import lobna.asset.nutrition.analysis.data.IngredientAnalysis
import lobna.asset.nutrition.analysis.ui.total.TotalNutritionActivity
import lobna.asset.nutrition.analysis.utils.IntentClass
import lobna.asset.nutrition.analysis.utils.SingleLiveEvent

class IngredientsSummaryViewModel(val data: Bundle?) : ViewModel() {

    val onBackEvent = SingleLiveEvent<Boolean>()

    val onBackClicked: () -> Unit = { onBackEvent.postValue(true) }

    private val items = data?.getParcelableArrayList<IngredientAnalysis>("items")
            as? List<IngredientAnalysis> ?: emptyList()
    val summaryAdapter = SummaryAdapter(items)

    fun total(view: View) {
        val bundle = bundleOf(Pair("ingredients", data?.getString("ingredients")))
        IntentClass.goToActivity(view.context, TotalNutritionActivity::class.java, bundle)
    }
}