package com.example.expensetracker.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.databinding.RecyclerViewRowBinding
import com.example.expensetracker.functions.Functions
import com.example.expensetracker.model.Expense
import com.example.expensetracker.ui.fragment.HomePageFragmentDirections
import java.util.ArrayList

class ExpenseAdapter (private val moneyType: Int, private val list: ArrayList<Double>): RecyclerView.Adapter<ExpenseAdapter.myViewHolder>() {

    private var expenseList = emptyList<Expense>()
    private var totalPrice: Float = 0.0F
    private lateinit var functions: Functions

    class myViewHolder(val itemBinding: RecyclerViewRowBinding): RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val binding = RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context))

        return myViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val presentItem = expenseList[position]
        val presentPrice = presentItem.expensePrice

        holder.itemBinding.textViewTitle.text = presentItem.expenseTitle

        when(presentItem.expenseType){
            1 -> holder.itemBinding.imageViewExpense.setImageResource(R.drawable.outline_receipt_24)
            2 -> holder.itemBinding.imageViewExpense.setImageResource(R.drawable.outline_home_24)
            3 -> holder.itemBinding.imageViewExpense.setImageResource(R.drawable.outline_shopping_bag_24)
        }

        functions = Functions()
        val string = functions.convert(moneyType,presentItem,presentPrice,list)
        holder.itemBinding.textViewPrice.text = string

        holder.itemBinding.itemCardView.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToExpenseDetailFragment(presentItem.expenseId)
            Navigation.findNavController(it).navigate(action)
        }

        //println(string)

        /*
        when(moneyType){

            1 ->{   //tl olarak kaydedilmiş fiyatların dönüşümleri
                when(presentItem.currencyType){ //dönüşümü yapılacak olan para birimine göre hesaplamalar

                    1->{    //tl - dönüşüm yapılmaz
                        convertPrice(0,0,presentPrice,holder)
                        // holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(presentPrice)} TL"
                    }
                    2->{    //sterlin to try
                        convertPrice(1,0,presentPrice,holder)
                        //  val result = (1.0/list[1])*list[0]*presentPrice.toDouble()
                        //  holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} TL"
                    }
                    3->{    //usd to try
                        convertPrice(2,0,presentPrice,holder)
                        // val result = (1/list[2])*list[0]*presentPrice.toDouble()
                        // holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} TL"
                    }
                    4->{    //eur to try
                        convertPrice(3,0,presentPrice,holder)
                        // val result = (1/list[3])*list[0]*presentPrice.toDouble()
                        //  holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} TL"
                    }
                }
            }
            2 -> {
                when(presentItem.currencyType){
                    1->{
                        convertPrice(0,1,presentPrice,holder)
                    }
                    2->{    //sterlin to try
                        convertPrice(1,1,presentPrice,holder)
                    }
                    3->{    //usd to try
                        convertPrice(2,1,presentPrice,holder)
                    }
                    4->{    //eur to try
                        convertPrice(3,1,presentPrice,holder)
                    }
                }}
            3 ->{
                when(presentItem.currencyType){
                    1->{    //tl - dönüşüm yapılmaz
                        convertPrice(0,2,presentPrice,holder)
                    }
                    2->{    //sterlin to try
                        convertPrice(1,2,presentPrice,holder)
                    }
                    3->{    //usd to try
                        convertPrice(2,2,presentPrice,holder)
                    }
                    4->{    //eur to try
                        convertPrice(3,2,presentPrice,holder)
                    }
                }
            }
            4 ->{
                when(presentItem.currencyType){
                    1->{    //tl - dönüşüm yapılmaz
                        convertPrice(0,3,presentPrice,holder)
                    }
                    2->{    //sterlin to try
                        convertPrice(1,3,presentPrice,holder)
                    }
                    3->{    //usd to try
                        convertPrice(2,3,presentPrice,holder)
                    }
                    4->{    //eur to try
                        convertPrice(3,3,presentPrice,holder)
                    }
                }
            }
        }*/
    }


/*
    @SuppressLint("SetTextI18n")
    private fun convertPrice(x: Int, y: Int, presentPrice: Int, holder: myViewHolder) {
        val result = (1/list[x])*list[y]*presentPrice.toDouble()
        when(y){
            0 -> holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} ₺"
            1 -> holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} £"
            2 -> holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} €"
            3 -> holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} $"
        }

        totalPrice += result.toFloat()

        val sharedPref = mContext.getSharedPreferences("MyShared",Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.putFloat("totalPrice",totalPrice)
        editor.apply()

        println("Burası Adapter"+totalPrice)
    }
*/

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun setData(expense: List<Expense>){
        this.expenseList = expense
        notifyDataSetChanged()
    }

}

