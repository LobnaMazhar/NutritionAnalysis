package lobna.asset.nutrition.analysis.ui.total

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lobna.asset.nutrition.analysis.databinding.ActivityTotalNutritionBinding

class TotalNutritionActivity : AppCompatActivity() {

    private val totalNutritionViewModel by viewModels<TotalNutritionViewModel> {
        object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TotalNutritionViewModel(intent.getBundleExtra("data"), application) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityTotalNutritionBinding = ActivityTotalNutritionBinding.inflate(layoutInflater)
        setContentView(activityTotalNutritionBinding.root)

        activityTotalNutritionBinding.tnvm = totalNutritionViewModel

        totalNutritionViewModel.onBackEvent.observe(this, { onBackPressed() })
    }
}