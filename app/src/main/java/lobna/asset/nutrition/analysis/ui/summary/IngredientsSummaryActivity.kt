package lobna.asset.nutrition.analysis.ui.summary

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lobna.asset.nutrition.analysis.databinding.ActivityIngredientsSummaryBinding

class IngredientsSummaryActivity : AppCompatActivity() {

    private val ingredientsSummaryViewModel by viewModels<IngredientsSummaryViewModel> {
        object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return IngredientsSummaryViewModel(intent.getBundleExtra("data")) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityIngredientsSummaryBinding =
            ActivityIngredientsSummaryBinding.inflate(layoutInflater)
        setContentView(activityIngredientsSummaryBinding.root)

        activityIngredientsSummaryBinding.isvm = ingredientsSummaryViewModel

        ingredientsSummaryViewModel.onBackEvent.observe(this, { onBackPressed() })
    }
}