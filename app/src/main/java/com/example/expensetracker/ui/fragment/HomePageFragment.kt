package com.example.expensetracker.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentFirstScreenBinding
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

    binding.layoutMerhaba.setOnClickListener{
        findNavController().navigate(R.id.action_homePageFragment_to_changeNameFragment2)
    }
    }


}