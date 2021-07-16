package lobna.asset.nutrition.analysis.ui.total

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lobna.asset.nutrition.analysis.data.NutritionResponse
import lobna.asset.nutrition.analysis.data.TotalNutrition
import lobna.asset.nutrition.analysis.repository.NutritionRepository
import lobna.asset.nutrition.analysis.utils.SingleLiveEvent
import kotlin.math.roundToInt

class TotalNutritionViewModel(data: Bundle?, application: Application) : AndroidViewModel(
    application
) {

    val caloriesObservable = ObservableField<String>()
    val fatObservable = ObservableField<String>()
    val cholesterolObservable = ObservableField<String>()
    val sodiumObservable = ObservableField<String>()
    val carbohydrateObservable = ObservableField<String>()
    val proteinObservable = ObservableField<String>()
    val vitaminObservable = ObservableField<String>()
    val calciumObservable = ObservableField<String>()
    val ironObservable = ObservableField<String>()
    val potassiumObservable = ObservableField<String>()

    val onBackEvent = SingleLiveEvent<Boolean>()

    val onBackClicked: () -> Unit = { onBackEvent.postValue(true) }

    init {
        data?.getString("ingredients")?.lines()?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val response = NutritionRepository.totalNutrition("", it)

                when (response) {
                    is NutritionResponse.ErrorResponse ->
                        Toast.makeText(application, response.message, Toast.LENGTH_LONG).show()
                    is NutritionResponse.ExceptionResponse ->
                        Toast.makeText(application, response.message, Toast.LENGTH_LONG).show()
                    is NutritionResponse.DataResponse<*> ->
                        (response.data as? TotalNutrition)?.run {
                            caloriesObservable.set(calories.toString())
                            fatObservable.set(
                                String.format("%.1f", totalNutrients.FAT.quantity) +
                                        " ${totalNutrients.FAT.unit}"
                            )
                            cholesterolObservable.set("${totalNutrients.CHOLE.quantity.roundToInt()} ${totalNutrients.CHOLE.unit}")
                            sodiumObservable.set("${totalNutrients.NA.quantity.roundToInt()} ${totalNutrients.NA.unit}")
                            carbohydrateObservable.set(
                                String.format("%.1f", totalNutrients.CHOCDF.quantity) +
                                        " ${totalNutrients.CHOCDF.unit}"
                            )
                            proteinObservable.set(
                                String.format("%.1f", totalNutrients.PROCNT.quantity) +
                                        " ${totalNutrients.PROCNT.unit}"
                            )
                            vitaminObservable.set("${totalNutrients.VITD.quantity.roundToInt()} ${totalNutrients.VITD.unit}")
                            calciumObservable.set(
                                String.format("%.1f", totalNutrients.CA.quantity) +
                                        " ${totalNutrients.CA.unit}"
                            )
                            ironObservable.set(
                                String.format("%.1f", totalNutrients.FE.quantity) +
                                        " ${totalNutrients.FE.unit}"
                            )
                            potassiumObservable.set(
                                String.format("%.1f", totalNutrients.K.quantity) +
                                        " ${totalNutrients.K.unit}"
                            )
                        }
                }
            }
        }
    }
}