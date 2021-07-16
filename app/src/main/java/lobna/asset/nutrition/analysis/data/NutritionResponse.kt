package lobna.asset.nutrition.analysis.data

sealed class NutritionResponse {
    data class ErrorResponse(val code: Int, val message: String) : NutritionResponse()
    data class ExceptionResponse(val message: String?) : NutritionResponse()
    data class DataResponse<T>(val data: T) : NutritionResponse()
}