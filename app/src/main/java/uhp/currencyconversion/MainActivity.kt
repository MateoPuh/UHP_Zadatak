package uhp.currencyconversion

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var spinner1: Spinner
    private lateinit var input: EditText
    private lateinit var spinner2: Spinner
    private lateinit var output1: TextView
    private lateinit var output2: TextView
    private lateinit var button: Button
    private var currencyCodeList: MutableList<String> = mutableListOf()
    private var spinnerAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner1 = findViewById(R.id.spinner1)
        input = findViewById(R.id.input)
        spinner2 = findViewById(R.id.spinner2)
        output1 = findViewById(R.id.output1)
        output2 = findViewById(R.id.output2)
        button = findViewById(R.id.button)

        button.setOnClickListener{

            val inputStr = input.text.toString()
            var inCurrency = spinner1.selectedItem.toString()
            var outCurrency = spinner2.selectedItem.toString()

            var inRate: Double = 1.0
            var outRate: Double = 1.0

            if (spinner1.selectedItem == null || spinner2.selectedItem == null){

                Toast.makeText(this@MainActivity, "Please select currency", Toast.LENGTH_LONG).show()

            } else {

                inCurrency = spinner1.selectedItem.toString()
                outCurrency = spinner2.selectedItem.toString()

                val result: Double

                for (rate in RatesModel.RatesList){

                    if (rate.currencyCode == inCurrency){
                        inRate = rate.buyingRate.toDouble()
                    }
                    if (rate.currencyCode == outCurrency){
                        outRate = rate.sellingRate.toDouble()
                    }

                }

                result = inputStr.toDouble() * inRate / outRate

                var resultStr = String.format("%.6f", result)

                output1.text = resultStr.toString()

                output2.text = inputStr + inCurrency + " = " + resultStr.toString() + outCurrency

            }

        }

        val service = ApiUtils.getRateService()
        val call: retrofit2.Call<MutableList<RatesModel.Rate>> = service.getRates()

        call.enqueue(object: retrofit2.Callback<MutableList<RatesModel.Rate>>{

            override fun onResponse(call: Call<MutableList<RatesModel.Rate>>, response: Response<MutableList<RatesModel.Rate>>) {

                RatesModel.RatesList = response.body()!!

                for (rate in RatesModel.RatesList){

                    currencyCodeList.add(rate.currencyCode)

                }

                spinnerAdapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_item, currencyCodeList)
                spinner1.adapter = spinnerAdapter
                spinner2.adapter = spinnerAdapter


            }

            override fun onFailure(call: Call<MutableList<RatesModel.Rate>>?, t: Throwable?) {

                Toast.makeText(this@MainActivity, "Pogreska", Toast.LENGTH_LONG).show()

            }

        })

    }
}
