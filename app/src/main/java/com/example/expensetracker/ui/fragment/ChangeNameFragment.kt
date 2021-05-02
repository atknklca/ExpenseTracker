package com.example.expensetracker.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentChangeNameBinding

class ChangeNameFragment : Fragment() {

    private lateinit var binding: FragmentChangeNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChangeNameBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var gender : Int? = 3

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, id ->
            gender = when(id){
                R.id.radioButtonErkek -> { 1 }
                R.id.radioButtonKadin -> { 2 }
                R.id.radioButtonBelirsiz -> { 3 }
                else -> { -1 }
            }
        }

        binding.buttonSaveName.setOnClickListener {
            if (binding.inputName.editText?.text.toString().isNotEmpty() && binding.radioGroup.checkedRadioButtonId != -1){

                val name = binding.inputName.editText?.text.toString()
                val sharedPref = requireActivity().getSharedPreferences("Name", Context.MODE_PRIVATE)

                val editor = sharedPref.edit()
                editor.putString("name", name)
                editor.putInt("gender",gender!!)
                editor.apply()

                findNavController().navigate(R.id.action_changeNameFragment2_to_homePageFragment)
            }else{
                Toast.makeText(requireContext(),"Lütfen Boş Alan Bırakmayınız!", Toast.LENGTH_LONG).show()
            }
        }
    }

}