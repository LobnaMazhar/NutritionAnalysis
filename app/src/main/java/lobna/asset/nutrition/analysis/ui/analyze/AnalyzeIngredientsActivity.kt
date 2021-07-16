package lobna.asset.nutrition.analysis.ui.analyze

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import lobna.asset.nutrition.analysis.databinding.ActivityAnalyzeIngredientsBinding

class AnalyzeIngredientsActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<AnalyzeIngredientsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityAnalyzeIngredientsBinding =
            ActivityAnalyzeIngredientsBinding.inflate(layoutInflater)
        setContentView(activityAnalyzeIngredientsBinding.root)

        activityAnalyzeIngredientsBinding.mvm = mainViewModel
    }
}