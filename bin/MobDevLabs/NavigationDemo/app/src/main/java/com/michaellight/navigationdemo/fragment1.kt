package com.michaellight.navigationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

class fragment1 : Fragment() {
	lateinit var navController: NavController
	lateinit var viewModel: NavigationViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		viewModel = ViewModelProvider(requireActivity()).get(NavigationViewModel::class.java)
		return inflater.inflate(R.layout.fragment1, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val btn1 = requireActivity().findViewById<Button>(R.id.f1_btn1)
		val btn2 = requireActivity().findViewById<Button>(R.id.f1_btn2)
		val btn3 = requireActivity().findViewById<Button>(R.id.f1_btn3)
		val tvPerson = requireActivity().findViewById<EditText>(R.id.f1_tv_person)

		navController = (activity as MainActivity).navController

		viewModel.livePerson.observeForever {
			tvPerson.text = it.toString()
		}


		btn1.setOnClickListener {
			navController.navigate(R.id.action_fragment1_to_fragment2)
		}
		btn2.setOnClickListener {
			navController.navigate(R.id.fragment3)
		}

		btn3.setOnClickListener {
			viewModel.person?.let {
				tvPerson.text = it.toString()
			}
		}
	}
}