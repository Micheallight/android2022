package com.michaellight.navigationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController

class fragment3 : Fragment() {
	lateinit var navController: NavController

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment3, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val btn1 = requireActivity().findViewById<Button>(R.id.f3_btn1)
		val btn2 = requireActivity().findViewById<Button>(R.id.f3_btn2)
		navController = (activity as MainActivity).navController

		btn1.setOnClickListener {
			navController.navigate(R.id.fragment1)
		}
		btn2.setOnClickListener {
			navController.navigate(R.id.fragment2)
		}
	}
}