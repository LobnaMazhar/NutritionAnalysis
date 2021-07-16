package lobna.asset.nutrition.analysis.repository

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lobna.asset.nutrition.analysis.data.NutritionResponse
import lobna.asset.nutrition.analysis.network.MyRetrofitClient
import lobna.asset.nutrition.analysis.network.NutritionApiInterface

object NutritionRepository : NutritionInterface {

    private var nutritionApi: NutritionApiInterface =
        MyRetrofitClient.createService(NutritionApiInterface::class.java)

    override suspend fun analyzeIngredients(ingredients: String): NutritionResponse {
        return try {
            val response = nutritionApi.nutritionData(ingredients)

            if (response.isSuccessful) {
                NutritionResponse.DataResponse(response.body())
            } else {
                NutritionResponse.ErrorResponse(response.code(), response.message())
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { e.printStackTrace() }
            NutritionResponse.ExceptionResponse(e.message)
        }
    }

    override suspend fun totalNutrition(
        title: String, ingredients: List<String>
    ): NutritionResponse {
        return try {
            val data = JsonObject()
            data.addProperty("title", title)
            val jsonArray = JsonArray()
            ingredients.forEach { jsonArray.add(it) }
            data.add("ingr", jsonArray)

            val response = nutritionApi.nutritionDetails(data)

            if (response.isSuccessful) {
                NutritionResponse.DataResponse(response.body())
            } else {
                NutritionResponse.ErrorResponse(response.code(), response.message())
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { e.printStackTrace() }
            NutritionResponse.ExceptionResponse(e.message)
        }
    }
}