package lobna.asset.nutrition.analysis.repository

import lobna.asset.nutrition.analysis.data.NutritionResponse

interface NutritionInterface {

    suspend fun analyzeIngredients(ingredients: String): NutritionResponse
    suspend fun totalNutrition(title: String, ingredients: List<String>): NutritionResponse
}