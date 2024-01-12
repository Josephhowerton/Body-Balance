package network.nutrition

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation

class NutritionInterceptor : Interceptor {
    private val registration: MutableMap<Int, Nutrition> = mutableMapOf()

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val annotation = findAnnotation(request)
        if (annotation != null) {
            request = buildRequest(chain, annotation.source)
        }
        return chain.proceed(request)
    }

    private fun findAnnotation(request: Request): Nutrition? {
        val key = request.url.hashCode()
        return registration[key] ?: request.tag(Invocation::class.java)
            ?.method()
            ?.annotations
            ?.filterIsInstance<Nutrition>()
            ?.firstOrNull()
            ?.also { registration[key] = it }
    }

    private fun buildRequest(chain: Interceptor.Chain, source: NutritionSource): Request {
        val urlBuilder = chain.request().url.newBuilder()
        when (source) {
            NutritionSource.RECIPE -> {
                urlBuilder
                    .addQueryParameter(APP_ID, EDAMAM_RECIPE_ID)
                    .addQueryParameter(APP_KEY, EDAMAM_RECIPE_KEY)
            }
            NutritionSource.NUTRITION -> {
                urlBuilder
                    .addQueryParameter(APP_ID, EDAMAM_NUTRITION_ID)
                    .addQueryParameter(APP_KEY, EDAMAM_NUTRITION_KEY)
            }
            NutritionSource.FOOD -> {
                urlBuilder
                    .addQueryParameter(APP_ID, EDAMAM_FOOD_ID)
                    .addQueryParameter(APP_KEY, EDAMAM_FOOD_KEY)
            }
        }
        return chain.request().newBuilder().url(urlBuilder.build()).build()
    }


}