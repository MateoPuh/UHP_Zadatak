package uhp.currencyconversion

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        fun getClient(baseUrl: String) : Retrofit{

            return retrofit2.Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        }

    }

}