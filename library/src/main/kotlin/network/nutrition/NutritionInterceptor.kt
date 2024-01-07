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
        val requestBuilder = chain.request().newBuilder()
        when (source) {
            NutritionSource.FOOD -> {
                requestBuilder
                    .addHeader(APP_ID, EDAMAM_RECIPE_ID)
                    .addHeader(APP_KEY, EDAMAM_RECIPE_KEY)
            }
            NutritionSource.NUTRITION -> {
                requestBuilder
                    .addHeader(APP_ID, EDAMAM_NUTRITION_ID)
                    .addHeader(APP_KEY, EDAMAM_NUTRITION_KEY)
            }
            NutritionSource.RECIPE -> {
                requestBuilder
                    .addHeader(APP_ID, EDAMAM_FOOD_ID)
                    .addHeader(APP_KEY, EDAMAM_FOOD_KEY)
            }
        }
        return requestBuilder.build()
    }
}