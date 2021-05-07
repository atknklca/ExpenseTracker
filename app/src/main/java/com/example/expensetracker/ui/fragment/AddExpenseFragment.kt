package com.example.expensetracker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.data.ExpenseDatabase
import com.example.expensetracker.databinding.FragmentAddExpenseBinding
import com.example.expensetracker.model.Expense
import kotlin.reflect.typeOf

class AddExpenseFragment : Fragment() {

    private lateinit var binding : FragmentAddExpenseBinding
    private lateinit var db: ExpenseDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddExpenseBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = ExpenseDatabase.getDatabase(requireContext())

        var expenseType: Int? = null
        var currencyType : Int? = null

        binding.radioGroupExpense.setOnCheckedChangeListener { radioGroupExpenseType, i ->
            expenseType = when(i){
                R.id.radioButtonReceipt -> 1
                R.id.radioButtonHome -> 2
                else -> 3 // diğer (alışveriş vb.)
            }
        }

        binding.radioGroupCurrency.setOnCheckedChangeListener { radioGroupCurrenyType, i ->
            currencyType = when(i){
                R.id.radioButtonTl -> 1
                R.id.radioButtonSterlin -> 2
                R.id.radioButtonEuro -> 3
                else -> 4 // dolar
            }
        }

        binding.buttonAddExpense.setOnClickListener {
            if (binding.inputExpenseTitle.editText?.text.toString().isNotEmpty() &&
                binding.inputExpensePrice.editText?.text.toString().isNotEmpty()&&
                binding.radioGroupCurrency.checkedRadioButtonId !=1 &&
                binding.radioGroupExpense.checkedRadioButtonId !=1){

                val title = binding.inputExpenseTitle.editText?.text.toString()
                val price = binding.inputExpensePrice.editText?.text.toString().toDouble()

                insertExpense(title,price.toInt(),expenseType!!,currencyType!!)

                findNavController().navigate(R.id.action_addExpenseFragment_to_homePageFragment)
                Toast.makeText(requireContext(),"Harcamanız Başarıyla Kaydedildi!",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Lütfen Boş Alan Bırakmayınız!",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun insertExpense(title: String, price: Int, expenseType: Int, currencyType: Int) {
        val expense = Expense(0,title,price,expenseType,currencyType)
        db.expenseDao().addExpense(expense)
    }
}