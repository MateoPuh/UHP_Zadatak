package uhp.currencyconversion

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

object RatesModel {

    data class Rate(

            @SerializedName("unit_value")
            @Expose
            var unitValue: Integer,

            @SerializedName("currency_code")
            @Expose
            var currencyCode: String,

            @SerializedName("median_rate")
            @Expose
            var medianRate: String,

            @SerializedName("buying_rate")
            @Expose
            var buyingRate: String,

            @SerializedName("selling_rate")
            @Expose
            var sellingRate: String

    )

    var RatesList: MutableList<Rate> = mutableListOf()

}