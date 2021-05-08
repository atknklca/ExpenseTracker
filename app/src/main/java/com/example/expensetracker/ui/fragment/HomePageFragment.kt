package com.example.expensetracker.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.R
import com.example.expensetracker.data.ExpenseDatabase
import com.example.expensetracker.databinding.FragmentHomePageBinding
import com.example.expensetracker.functions.Functions
import com.example.expensetracker.model.Expense
import com.example.expensetracker.ui.adapter.ExpenseAdapter
import com.example.expensetracker.util.Constants.base
import com.example.expensetracker.viewmodel.CurrencyViewModel
import java.text.DecimalFormat

class HomePageFragment : Fragment() {

    private lateinit var binding : FragmentHomePageBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var adapter: ExpenseAdapter
    private lateinit var db: ExpenseDatabase
    private lateinit var expenseList: List<Expense>
    private lateinit var moneyList: ArrayList<Double>
    private lateinit var functions: Functions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater,container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = ExpenseDatabase.getDatabase(requireContext())
        expenseList = db.expenseDao().getAllExpense()
        moneyList = ArrayList()

        sharedPref = requireActivity().getSharedPreferences("MyShared", Context.MODE_PRIVATE)
        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)

        jobPlanning(sharedPref,expenseList,moneyList)


        binding.layoutMerhaba.setOnClickListener{
        findNavController().navigate(R.id.action_homePageFragment_to_changeNameFragment2)
        }

        binding.buttonEkle.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_addExpenseFragment)
        }

        binding.buttonTl.setOnClickListener{
            changeColor(it as Button)
            changeLastClicked(1)
            getRates(moneyList)
            loadAdapter(1,moneyList)
            showTotalPrice(1)
        }

        binding.buttonSterlin.setOnClickListener{
            changeColor(it as Button)
            changeLastClicked(2)
            getRates(moneyList)
            loadAdapter(2,moneyList)
            showTotalPrice(2)
        }

        binding.buttonEuro.setOnClickListener{
            changeColor(it as Button)
            changeLastClicked(3)
            getRates(moneyList)
            loadAdapter(3,moneyList)
            showTotalPrice(3)
        }

        binding.buttonDolar.setOnClickListener{
            changeColor(it as Button)
            changeLastClicked(4)
            getRates(moneyList)
            loadAdapter(4,moneyList)
            showTotalPrice(4)
        }
    }


    private fun jobPlanning(sharedPref: SharedPreferences, expenseList: List<Expense>, moneyList: ArrayList<Double>) {

        checkedName(sharedPref)
        checkedMoneyType(sharedPref)
        val lastMoneyType = sharedPref.getInt("lastMoneyType",1)

        //  internet bağlantısı kontrolü
        val connectivityManager=requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        val isConnected:Boolean = ((networkInfo!=null) && networkInfo.isConnected)

        if(isConnected){

            getNetworkRates(currencyViewModel)
            getRates(moneyList)
            loadAdapter(lastMoneyType,moneyList)
            showTotalPrice(lastMoneyType)

        }else{

            Toast.makeText(requireActivity(),"İnternet Hizmetiniz Kullanılamıyor. Güncel Kur Değerleri İçin İnternet Erişimi Sağlayınız!",Toast.LENGTH_LONG).show()
            getRates(moneyList)
            loadAdapter(lastMoneyType,moneyList)
            showTotalPrice(lastMoneyType)

        }

    }

    private fun getRates(moneyList: ArrayList<Double>) {

        sharedPref = requireActivity().getSharedPreferences("Rates",Context.MODE_PRIVATE)

        moneyList.clear()
            moneyList.add(sharedPref.getFloat("tr",1.0F).toDouble())
        moneyList.add(sharedPref.getFloat("gdp",1.0F).toDouble())
        moneyList.add(sharedPref.getFloat("usd",1.0F).toDouble())
        moneyList.add(1.0)
    }


    private fun loadAdapter(moneyType: Int, moneyList: ArrayList<Double>){

        adapter = ExpenseAdapter(moneyType,moneyList)
        adapter.setData(expenseList)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

    }

    @SuppressLint("SetTextI18n")
    private fun showTotalPrice(moneyType: Int){

       val sum : Double

        getRates(moneyList)
        functions = Functions()
        sum = functions.totalPrice(moneyType,moneyList,expenseList)

        binding.textViewHarcamaTutari.text = DecimalFormat("##.#").format(sum) + "₺"
        when(moneyType){
            1->binding.textViewHarcamaTutari.text = DecimalFormat("##.#").format(sum) + "₺"
            2 -> binding.textViewHarcamaTutari.text =  DecimalFormat("##.#").format(sum) +"£"
            3 -> binding.textViewHarcamaTutari.text = DecimalFormat("##.#").format(sum) +"€"
            4-> binding.textViewHarcamaTutari.text =  DecimalFormat("##.#").format(sum) +"$"
        }
    }

    // apiden gelen kur değerleri (euro bazlı) çekilir ve kaydedilir
    private fun getNetworkRates(currencyViewModel: CurrencyViewModel) {
        currencyViewModel.getRates(base,this.requireContext()).observe(viewLifecycleOwner,{
            val tr = it.rates.TRY
            val gbp = it.rates.GBP
            val usd = it.rates.USD

            val sharedPrefRates = requireActivity().getSharedPreferences("Rates", Context.MODE_PRIVATE)
            val editor = sharedPrefRates.edit()
            editor.putFloat("euro", 1.0F)
            editor.putFloat("tr", tr)
            editor.putFloat("gbp", gbp)
            editor.putFloat("usd", usd)
            editor.apply()
        })
    }

    private fun checkedMoneyType(sharedPref: SharedPreferences) {

        val lastMoneyType = sharedPref.getInt("lastMoneyType",1)

        when(lastMoneyType){
            1 -> binding.buttonTl.setTextColor(ContextCompat.getColor(requireContext(),R.color.primaryColor))
            2 -> binding.buttonSterlin.setTextColor(ContextCompat.getColor(requireContext(),R.color.primaryColor))
            3 -> binding.buttonEuro.setTextColor(ContextCompat.getColor(requireContext(),R.color.primaryColor))
            4 -> binding.buttonDolar.setTextColor(ContextCompat.getColor(requireContext(),R.color.primaryColor))
            else -> binding.buttonTl.setTextColor(ContextCompat.getColor(requireContext(),R.color.primaryColor))
        }

    }

    private fun changeLastClicked(moneyType: Int) {
        sharedPref = requireActivity().getSharedPreferences("MyShared",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("lastMoneyType", moneyType)
        editor.apply()

    }

    private fun changeColor(button: Button) {
        binding.buttonTl.setTextColor(ContextCompat.getColor(requireContext(),R.color.dark_grey))
        binding.buttonDolar.setTextColor(ContextCompat.getColor(requireContext(),R.color.dark_grey))
        binding.buttonEuro.setTextColor(ContextCompat.getColor(requireContext(),R.color.dark_grey))
        binding.buttonSterlin.setTextColor(ContextCompat.getColor(requireContext(),R.color.dark_grey))
        button.setTextColor(ContextCompat.getColor(requireContext(),R.color.primaryColor))

    }

    @SuppressLint("SetTextI18n")
    private fun checkedName(sharedPref: SharedPreferences?) {

        val name = sharedPref?.getString("name","Lütfen Tıklayınız")

        when(sharedPref?.getInt("gender", 3)){
            1 -> binding.textViewKullaniciIsmi.text = "$name Bey"
            2 -> binding.textViewKullaniciIsmi.text = "$name Hanım"
            3 -> binding.textViewKullaniciIsmi.text = "$name"

        }

    }


}