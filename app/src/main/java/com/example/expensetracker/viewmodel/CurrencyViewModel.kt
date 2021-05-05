package com.example.expensetracker.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.model.CurrencyResponse
import com.example.expensetracker.model.Rates
import com.example.expensetracker.repository.CurrencyRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class CurrencyViewModel(application: Application): AndroidViewModel(application) {

    private val lastRates: MutableLiveData<CurrencyResponse> = MutableLiveData()
    private val repository by lazy { CurrencyRepository() }
    private lateinit var sharedPref :SharedPreferences

    fun getRates(base: String, context: Context): MutableLiveData<CurrencyResponse>{

        sharedPref = context.getSharedPreferences("Rates",Context.MODE_PRIVATE)


        viewModelScope.launch {
            repository.getRates(base).enqueue(object : retrofit2.Callback<CurrencyResponse>{
                override fun onResponse(
                    call: Call<CurrencyResponse>,
                    response: Response<CurrencyResponse>
                ){
                    if (response.isSuccessful){
                        saveLatestRates(response)
                        lastRates.value = response.body()
                    }
                }

                override fun onFailure(call: Call<CurrencyResponse>, T: Throwable){
                    getDefaultRates(base)
                }
            })
        }
        return lastRates
    }

    //Api'den gelen son verilerin saklanması.(euro bazlı)
    private fun saveLatestRates(response:Response<CurrencyResponse> ) {
        val editor = sharedPref.edit()
        editor.putFloat("euro", 1.0F)
        editor.putFloat("tr", response.body()!!.rates.TRY)
        editor.putFloat("gdp", response.body()!!.rates.GBP)
        editor.putFloat("usd", response.body()!!.rates.USD)
        editor.apply()
    }

    //Uygulamanın ilk açılışı da dahil olmak üzere internet bağlantısı gerçekleşmemişse ya da veri api'den gelmemişse.
    // Senaryo 1 : İlk açılışta internet bağlantısı yoksa veriler benim default olarak girdiğim sonuçlardan elde edilir.
    // Senaryo 2: Eğer ilk açılışta herhangi bir bağlantı sağlanmışsa veriler api'den temin edilir ve son güncel kur saklanır.
    private fun getDefaultRates(base: String){
        val eur = 1.0F
        val usd = sharedPref.getFloat("usd", 1.0F) //veri yoksa defalult değer Constants'da
        val tr = sharedPref.getFloat("tr", 1.0F)
        val gbp = sharedPref.getFloat("gbp", 1.0F)
        lastRates.value = CurrencyResponse(base, Rates(tr,gbp,eur,usd))
    }

}

