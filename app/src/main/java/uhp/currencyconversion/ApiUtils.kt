package uhp.currencyconversion

class ApiUtils {

    companion object {

        private val baseUrl: String = "http://hnbex.eu/api/v1/"

        fun getRateService() : RateService{

            return RetrofitClient.getClient(baseUrl).create(RateService::class.java)

        }

    }

}