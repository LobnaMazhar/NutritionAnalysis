package lobna.asset.nutrition.analysis.network

import com.google.gson.GsonBuilder
import lobna.asset.nutrition.analysis.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitClient {

    const val BASE_URL = "https://api.edamam.com/api/"
    val BASE_PARAMS = mapOf(
        Pair("app_id", BuildConfig.NUTRITION_APP_ID),
        Pair("app_key", BuildConfig.NUTRITION_APP_KEY)
    )

    internal var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}