package com.michaellight.navigationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

class fragment2 : Fragment() {
	lateinit var navController: NavController
	lateinit var viewModel: NavigationViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		viewModel = ViewModelProvider(requireActivity()).get(NavigationViewModel::class.java)
		return inflater.inflate(R.layout.fragment2, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val btn1 = requireActivity().findViewById<Button>(R.id.f2_btn1)
		val btn2 = requireActivity().findViewById<Button>(R.id.f2_btn2)
		navController = (activity as MainActivity).navController

		val etName = requireActivity().findViewById<EditText>(R.id.f2_et_name)
		val etSName = requireActivity().findViewById<EditText>(R.id.f2_et_sname)
		val etAge = requireActivity().findViewById<EditText>(R.id.f2_et_age)

		btn1.setOnClickListener {
			viewModel.livePerson.value = Person(etName.text.toString(), etSName.text.toString(), etAge.text.toString().toInt())
			navController.popBackStack(R.id.fragment1, false)
		}
		btn2.setOnClickListener {
			navController.navigate(R.id.fragment3)
		}
	}
}