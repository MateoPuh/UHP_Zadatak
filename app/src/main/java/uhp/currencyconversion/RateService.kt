package uhp.currencyconversion


import retrofit2.http.GET


interface RateService {

    @GET("rates/daily/")
    fun getRates(): retrofit2.Call<MutableList<RatesModel.Rate>>

}