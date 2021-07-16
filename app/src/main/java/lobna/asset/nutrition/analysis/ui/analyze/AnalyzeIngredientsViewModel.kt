package lobna.asset.nutrition.analysis.ui.analyze

import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lobna.asset.nutrition.analysis.R
import lobna.asset.nutrition.analysis.data.IngredientAnalysis
import lobna.asset.nutrition.analysis.data.NutritionResponse
import lobna.asset.nutrition.analysis.repository.NutritionRepository
import lobna.asset.nutrition.analysis.ui.summary.IngredientsSummaryActivity
import lobna.asset.nutrition.analysis.utils.IntentClass
import lobna.asset.nutrition.analysis.utils.Utilities

class AnalyzeIngredientsViewModel : ViewModel() {

    val ingredientsTextObservable = ObservableField<String>()
    val ingredientsErrorObservable = ObservableField<String>()

    fun analyze(view: View) {
        Utilities.hideKeyboard(view)

        ingredientsErrorObservable.set(null)

        val ingredients = ingredientsTextObservable.get()

        if (ingredients.isNullOrBlank()) {
            ingredientsErrorObservable.set(view.context.getString(R.string.field_required))
        } else {
            val loading = Utilities.showLoading(view.context)

            val ingredientsList = ingredients.lines()
            val ingredientsAnalysisList = arrayListOf<IngredientAnalysis>()

            var error = false
            viewModelScope.launch(Dispatchers.IO) {
                ingredientsList.forEach { ingredient ->
                    if (ingredient.isNotBlank()) {
                        val response = NutritionRepository.analyzeIngredients(ingredient.trim())

                        when (response) {
                            is NutritionResponse.ErrorResponse -> {
                                error = true
                                ingredientsErrorObservable.set(response.message)
                                return@forEach
                            }
                            is NutritionResponse.ExceptionResponse -> {
                                error = true
                                ingredientsErrorObservable.set(response.message)
                                return@forEach
                            }
                            is NutritionResponse.DataResponse<*> ->
                                (response.data as? IngredientAnalysis)?.run {
                                    val x = ingredient.split(' ', limit = 3)
                                    if (x.size < 3) {
                                        error = true
                                        ingredientsErrorObservable.set("Failed to calculate nutrition for some ingredients")
                                        return@forEach
                                    } else {
                                        qty = x[0].trim().toInt()
                                        unit = x[1].trim()
                                        food = x[2].trim()
                                        ingredientsAnalysisList.add(this)
                                    }
                                }
                        }
                    }
                }

                withContext(Dispatchers.Main) {
                    Utilities.dismissLoading(loading)
                    if (!error && ingredientsAnalysisList.isNotEmpty()) {
                        val bundle = bundleOf(
                            Pair("ingredients", ingredients),
                            Pair("items", ingredientsAnalysisList)
                        )
                        IntentClass.goToActivity(
                            view.context, IngredientsSummaryActivity::class.java, bundle
                        )
                    }
                }
            }
        }
    }
}