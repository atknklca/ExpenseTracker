package com.example.expensetracker.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment() {

    private lateinit var binding : FragmentHomePageBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater,container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("Name", Context.MODE_PRIVATE)

        checkedName(sharedPref)
        checkedMoneyType(sharedPref)

        binding.layoutMerhaba.setOnClickListener{
        findNavController().navigate(R.id.action_homePageFragment_to_changeNameFragment2)
        }

        binding.buttonEkle.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_addExpenseFragment)
        }

        binding.buttonTl.setOnClickListener{
            changeColor(it as Button)
            changeLastClicked(sharedPref,1)
        }

        binding.buttonSterlin.setOnClickListener{
            changeColor(it as Button)
            changeLastClicked(sharedPref,2)
        }

        binding.buttonEuro.setOnClickListener{
            changeColor(it as Button)
            changeLastClicked(sharedPref,3)
        }

        binding.buttonDolar.setOnClickListener{
            changeColor(it as Button)
            changeLastClicked(sharedPref,4)
        }
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

    private fun changeLastClicked(sharedPref: SharedPreferences,moneyType: Int) {

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

    private fun checkedName(sharedPref: SharedPreferences?) {

        val name = sharedPref?.getString("name","Lütfen Tıklayınız")

        when(sharedPref?.getInt("gender", 3)){
            1 -> binding.textViewKullaniciIsmi.text = "$name Bey"
            2 -> binding.textViewKullaniciIsmi.text = "$name Hanım"
            3 -> binding.textViewKullaniciIsmi.text = "$name"

        }

    }


}