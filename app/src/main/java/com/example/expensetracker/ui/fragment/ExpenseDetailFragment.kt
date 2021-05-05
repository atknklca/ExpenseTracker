package com.example.expensetracker.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.expensetracker.R
import com.example.expensetracker.data.ExpenseDatabase
import com.example.expensetracker.databinding.FragmentExpenseDetailBinding
import com.example.expensetracker.functions.Functions
import com.example.expensetracker.model.Expense
import java.text.DecimalFormat
import java.util.ArrayList

class ExpenseDetailFragment : Fragment() {

    private lateinit var binding: FragmentExpenseDetailBinding
    val args: ExpenseDetailFragmentArgs by navArgs()
    private lateinit var db: ExpenseDatabase
    private lateinit var functions: Functions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpenseDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = ExpenseDatabase.getDatabase(requireContext())

        val presentExpense = db.expenseDao().getExpense(args.expenseId)

        binding.imageViewExpenseDetail.setImageResource(
            when(presentExpense.expenseType){
                1 -> R.drawable.outline_receipt_24
                2 -> R.drawable.outline_home_24
                3 -> R.drawable.outline_shopping_bag_24
                else -> R.drawable.ic_launcher_foreground
            }
        )

        binding.textViewExpenseDetail.text = presentExpense.expenseTitle

        val sharedPref = requireActivity().getSharedPreferences("MyShared",Context.MODE_PRIVATE)
        val lastMoneyType = sharedPref.getInt("lastMoneyType",1)

        val sharedPrefRates = requireActivity().getSharedPreferences("Rates",Context.MODE_PRIVATE)
        val moneyList = ArrayList<Double>()
        moneyList.clear()
        moneyList.add(sharedPrefRates.getFloat("tr",1.0F).toDouble())
        moneyList.add(sharedPrefRates.getFloat("gdp",1.0F).toDouble())
        moneyList.add(sharedPrefRates.getFloat("usd",1.0F).toDouble())
        moneyList.add(sharedPrefRates.getFloat("eur",1.0F).toDouble())

        functions = Functions()
        val string = functions.convert(lastMoneyType,presentExpense,presentExpense.expensePrice,moneyList)
        binding.textViewPriceDetail.text = string


        binding.buttonDelete.setOnClickListener {
            deleteExpense(presentExpense)
            findNavController().navigate(R.id.action_expenseDetailFragment_to_homePageFragment)
            Toast.makeText(requireContext(),"Harcamanız Başarıyla Silindi!",Toast.LENGTH_SHORT).show()
        }


    }

    private fun deleteExpense(presentExpense: Expense) {
        db.expenseDao().deleteExpense(presentExpense)
    }


}