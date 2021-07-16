package lobna.asset.nutrition.analysis.network

import com.google.gson.JsonObject
import lobna.asset.nutrition.analysis.BuildConfig
import lobna.asset.nutrition.analysis.data.IngredientAnalysis
import lobna.asset.nutrition.analysis.data.TotalNutrition
import retrofit2.Response
import retrofit2.http.*

interface NutritionApiInterface {

    @GET("nutrition-data")
    suspend fun nutritionData(
        @Query("ingr") ingredients: String,
        @Query("app_id") appId: String = BuildConfig.NUTRITION_APP_ID,
        @Query("app_key") appKey: String = BuildConfig.NUTRITION_APP_KEY
    ): Response<IngredientAnalysis>

    @POST("nutrition-details")
    @Headers("Content-Type: application/json")
    suspend fun nutritionDetails(
        @Body data: JsonObject,
        @Query("app_id") appId: String = BuildConfig.NUTRITION_APP_ID,
        @Query("app_key") appKey: String = BuildConfig.NUTRITION_APP_KEY
    ): Response<TotalNutrition>
}