package com.michaellight.mycalculatorv1

class Button (_value: String) {
	val valueOfButton: String  = _value
	var text: String = "0"

	constructor(_value: String, _text: String) : this(_value) {
		if (_value == "/") {
			text = "÷"
		} else if (_value == "/") {
			text = "×"
		} else if (_value == "back") {
			text = "⌫"
		} else {
			text = _value
		}
	}
}

//class Person(_name: String){
//	val name: String = _name
//	var age: Int = 0
//
//	constructor(_name: String, _age: Int) : this(_name){
//		age = _age
//	}
//}