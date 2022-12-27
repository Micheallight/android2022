package com.michaellight.mycalculatorv2

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat

var fieldOfCalculator: String = "0"

class MainActivity : AppCompatActivity() {

	val buttonTextsVertical = arrayOf<Array<String>>(
		arrayOf<String>("C", "⌫", "+/-", "÷"),
		arrayOf<String>("7", "8", "9", "×"),
		arrayOf<String>("4", "5", "6", "-"),
		arrayOf<String>("1", "2", "3", "+"),
		arrayOf<String>("0", "00", ".", "=")
	)

	val buttonTextsHorizontal = arrayOf<Array<String>>(
		arrayOf<String>("7", "8", "9", "÷", "C", "⌫"),
		arrayOf<String>("4", "5", "6", "×", "(", ")"),
		arrayOf<String>("1", "2", "3", "-", "^", "√"),
		arrayOf<String>("0", "00", ".", "+", "+/-", "=")
	)

	fun readAndCompute(inputExpression: String): Double {
		var resultExpression: String = inputExpression.replace('×', '*')
		resultExpression = resultExpression.replace('÷', '/')

		val ex = ExpressionBuilder(resultExpression).build()

		return ex.evaluate()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

//		this.actionBar.hide()

		setContentView(R.layout.activity_main)

		val btnTableVertical : LinearLayout = findViewById<LinearLayout>(R.id.clickTableVertical)
		val btnTableHorizontal : LinearLayout = findViewById<LinearLayout>(R.id.clickTableHorizontal)

		val calculatorText : TextView = findViewById<TextView>(R.id.mainFieldVertical)

		val rowsVertical = Array(5, { LinearLayout(this) })
		val buttonsVertical = Array(5, { Array(4, { TextView(this) }) })

		val rowsHorizontal = Array(4, { LinearLayout(this) })
		val buttonsHorizontal = Array(4, { Array(6, { TextView(this) }) })

		var textOfCalculator: String = ""
		var isNumberLast: Boolean = true
		var wasDotHere: Boolean = false

		val orientation = resources.configuration.orientation
		if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
			calculatorText.text = fieldOfCalculator
		} else {
			calculatorText.text = fieldOfCalculator
		}

		val format = DecimalFormat("0.#####")

		for (i in 0..4) {
			rowsVertical[i].layoutParams = LinearLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 0, 1.0f)
			for (j in 0..3) {
				buttonsVertical[i][j].layoutParams =
					LinearLayout.LayoutParams(0, ConstraintLayout.LayoutParams.MATCH_PARENT, 1.0f)
				buttonsVertical[i][j].setTextAppearance(this, R.style.buttonsVerticaltyle)
				buttonsVertical[i][j].text = buttonTextsVertical[i][j]
				if (buttonsVertical[i][j].text.equals("=")) {
					buttonsVertical[i][j].setBackgroundColor(Color.parseColor("#113355"))
				} else {
					buttonsVertical[i][j].setBackgroundColor(Color.parseColor("#000000"))
				}
				val param = buttonsVertical[i][j].layoutParams as ViewGroup.MarginLayoutParams
				param.setMargins(5,0,5,5)

				buttonsVertical[i][j].layoutParams = param
				buttonsVertical[i][j].textAlignment = View.TEXT_ALIGNMENT_CENTER
				buttonsVertical[i][j].setGravity(Gravity.CENTER_VERTICAL)
				buttonsVertical[i][j].setTextSize(30.0f)

				when(buttonsVertical[i][j].text.toString()) {
					in "0".."9" ->
						buttonsVertical[i][j].setOnClickListener {
							if (calculatorText.text.toString() == "0") {
								if (buttonsVertical[i][j].text.toString() != "00") {
									calculatorText.text = buttonsVertical[i][j].text.toString()
									isNumberLast = true
									fieldOfCalculator = calculatorText.text.toString()
								}
							} else {
								if (
									calculatorText.text.toString().last().toString() == "+" ||
									calculatorText.text.toString().last().toString() == "-" ||
									calculatorText.text.toString().last().toString() == "×" ||
									calculatorText.text.toString().last().toString() == "÷"
								) {
									wasDotHere = false
								}
								calculatorText.text = calculatorText.text.toString() + buttonsVertical[i][j].text.toString()
								isNumberLast = true
								fieldOfCalculator = calculatorText.text.toString()
							}
						}
					"C" ->
						buttonsVertical[i][j].setOnClickListener {
							calculatorText.text = "0"
							isNumberLast = true
							wasDotHere = false
							fieldOfCalculator = calculatorText.text.toString()
						}
					"⌫" ->
						buttonsVertical[i][j].setOnClickListener {
							if (calculatorText.text.toString() != "0") {
								if (
									calculatorText.text.toString().last().toString() == "+" ||
									calculatorText.text.toString().last().toString() == "-" ||
									calculatorText.text.toString().last().toString() == "×" ||
									calculatorText.text.toString().last().toString() == "÷"
								) {
									isNumberLast = !isNumberLast
									wasDotHere = !wasDotHere
								}
								calculatorText.text = calculatorText.text.dropLast(1)
								if (calculatorText.text.toString().last().toString() == ".") {
									calculatorText.text = calculatorText.text.dropLast(1)
									wasDotHere = false
									fieldOfCalculator = calculatorText.text.toString()
								}
							}
						}
					"." ->
						buttonsVertical[i][j].setOnClickListener {
							if (!wasDotHere && isNumberLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsVertical[i][j].text.toString()
								wasDotHere = true
								isNumberLast = false
								fieldOfCalculator = calculatorText.text.toString()
							}
						}
					"+" ->
						buttonsVertical[i][j].setOnClickListener {
							if (isNumberLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsVertical[i][j].text.toString()
								isNumberLast = false
//								wasDotHere = false
								fieldOfCalculator = calculatorText.text.toString()
							}
						}
					"-" ->
						buttonsVertical[i][j].setOnClickListener {
							if (
								isNumberLast ||
								calculatorText.text.toString().last().toString() == "+" ||
								calculatorText.text.toString().last().toString() == "×" ||
								calculatorText.text.toString().last().toString() == "÷"
							) {
								if (calculatorText.text.toString().last().toString() == "-") {
									isNumberLast = false
								}
								calculatorText.text =
									calculatorText.text.toString() + buttonsVertical[i][j].text.toString()
//								wasDotHere = false
								fieldOfCalculator = calculatorText.text.toString()
							}
						}
					"×" ->
						buttonsVertical[i][j].setOnClickListener {
							if (isNumberLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsVertical[i][j].text.toString()
								isNumberLast = false
//								wasDotHere = false
								fieldOfCalculator = calculatorText.text.toString()
							}
						}
					"÷" ->
						buttonsVertical[i][j].setOnClickListener {
							if (isNumberLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsVertical[i][j].text.toString()
								isNumberLast = false
//								wasDotHere = false
								fieldOfCalculator = calculatorText.text.toString()
							}
						}
					"=" ->
						buttonsVertical[i][j].setOnClickListener {
							textOfCalculator = calculatorText.text.toString()

//							calculatorText.text = textOfCalculator

							calculatorText.text = format.format(readAndCompute(textOfCalculator))
//							calculatorText.text = readAndCompute(textOfCalculator).toString()

//							calculatorText.text = NumberFormat.getInstance().format(readAndCompute(textOfCalculator)).toString()

//								NumberFormat.getInstance().format().toString()
							fieldOfCalculator = calculatorText.text.toString()
						}
				}
				rowsVertical[i].addView(buttonsVertical[i][j])
			}
			btnTableVertical.addView(rowsVertical[i])
		}

		var isBracketOpened: Boolean = false
		var isPowerLast: Boolean = false

		for (i in 0..3) {
			rowsHorizontal[i].layoutParams = LinearLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 0, 1.0f)
			for (j in 0..5) {
				buttonsHorizontal[i][j].layoutParams =
					LinearLayout.LayoutParams(0, ConstraintLayout.LayoutParams.MATCH_PARENT, 1.0f)
				buttonsHorizontal[i][j].setTextAppearance(this, R.style.buttonsVerticaltyle)
				buttonsHorizontal[i][j].text = buttonTextsHorizontal    [i][j]
				if (buttonsHorizontal[i][j].text.equals("=")) {
					buttonsHorizontal[i][j].setBackgroundColor(Color.parseColor("#113355"))
				} else {
					buttonsHorizontal[i][j].setBackgroundColor(Color.parseColor("#000000"))
				}
//				buttonsVertical[i][j].layoutParams.setMargins(1, 1, 1, 1) // = LinearLayout.LayoutParams.

				val param = buttonsHorizontal[i][j].layoutParams as ViewGroup.MarginLayoutParams
				param.setMargins(2,0,2,5)

				buttonsHorizontal[i][j].layoutParams = param
				buttonsHorizontal[i][j].textAlignment = View.TEXT_ALIGNMENT_CENTER
				buttonsHorizontal[i][j].setGravity(Gravity.CENTER_VERTICAL)
				buttonsHorizontal[i][j].setTextSize(20.0f)

				when(buttonsHorizontal[i][j].text.toString()) {
					in "0".."9" ->
						buttonsHorizontal[i][j].setOnClickListener {
							if (calculatorText.text.toString() == "0") {
								if (buttonsHorizontal[i][j].text.toString() != "00") {
									calculatorText.text = buttonsHorizontal[i][j].text.toString()
									isNumberLast = true
									isPowerLast = false
								}
							} else {
								if (
									calculatorText.text.toString().last().toString() == "+" ||
									calculatorText.text.toString().last().toString() == "-" ||
									calculatorText.text.toString().last().toString() == "×" ||
									calculatorText.text.toString().last().toString() == "÷"
								) {
									wasDotHere = false
								}
								calculatorText.text = calculatorText.text.toString() + buttonsHorizontal[i][j].text.toString()
								isNumberLast = true
								isPowerLast = false
								fieldOfCalculator = calculatorText.text.toString()
							}
						}
					"C" ->
						buttonsHorizontal[i][j].setOnClickListener {
							calculatorText.text = "0"
							isNumberLast = true
							wasDotHere = false
							fieldOfCalculator = calculatorText.text.toString()
						}
					"⌫" ->
						buttonsHorizontal[i][j].setOnClickListener {
							if (calculatorText.text.toString() != "0") {
								if (
									calculatorText.text.toString().last().toString() == "+" ||
									calculatorText.text.toString().last().toString() == "-" ||
									calculatorText.text.toString().last().toString() == "×" ||
									calculatorText.text.toString().last().toString() == "÷"
								) {
									isNumberLast = !isNumberLast
									wasDotHere = !wasDotHere
								}
								calculatorText.text = calculatorText.text.dropLast(1)
								if (calculatorText.text.toString().last().toString() == ".") {
									calculatorText.text = calculatorText.text.dropLast(1)
									isPowerLast = false
									wasDotHere = false
								}
							}
							fieldOfCalculator = calculatorText.text.toString()
						}
					"." ->
						buttonsHorizontal[i][j].setOnClickListener {
							if (!wasDotHere && isNumberLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsHorizontal[i][j].text.toString()
								wasDotHere = true
								isNumberLast = false
								isPowerLast = false
							}
							fieldOfCalculator = calculatorText.text.toString()
						}
					"+" ->
						buttonsHorizontal[i][j].setOnClickListener {
							if (isNumberLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsHorizontal[i][j].text.toString()
								isNumberLast = false
								isPowerLast = false
//								wasDotHere = false
							}
							fieldOfCalculator = calculatorText.text.toString()
						}
					"-" ->
						buttonsHorizontal[i][j].setOnClickListener {
							if (
								isNumberLast ||
								calculatorText.text.toString().last().toString() == "+" ||
								calculatorText.text.toString().last().toString() == "×" ||
								calculatorText.text.toString().last().toString() == "÷"
							) {
								if (calculatorText.text.toString().last().toString() == "-") {
									isNumberLast = false
								}
								calculatorText.text =
									calculatorText.text.toString() + buttonsHorizontal[i][j].text.toString()
//								wasDotHere = false
								isPowerLast = false
							}
							fieldOfCalculator = calculatorText.text.toString()
						}
					"×" ->
						buttonsHorizontal[i][j].setOnClickListener {
							if (isNumberLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsHorizontal[i][j].text.toString()
								isNumberLast = false
//								wasDotHere = false
							}
							fieldOfCalculator = calculatorText.text.toString()
						}
					"÷" ->
						buttonsHorizontal[i][j].setOnClickListener {
							if (isNumberLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsHorizontal[i][j].text.toString()
								isNumberLast = false
//								wasDotHere = false
								isPowerLast = false
							}
							fieldOfCalculator = calculatorText.text.toString()
						}
					"(" ->
						buttonsHorizontal[i][j].setOnClickListener {
							if (!isNumberLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsHorizontal[i][j].text.toString()
								isNumberLast = false
								wasDotHere = false
								isBracketOpened = true
								isPowerLast = false
							}
							fieldOfCalculator = calculatorText.text.toString()
						}
					")" ->
						buttonsHorizontal[i][j].setOnClickListener {
							calculatorText.text =
								calculatorText.text.toString() + buttonsHorizontal[i][j].text.toString()
							isNumberLast = false
							wasDotHere = false
							isBracketOpened = false
							isPowerLast = false
							fieldOfCalculator = calculatorText.text.toString()
						}
					"^" ->
						buttonsHorizontal[i][j].setOnClickListener {
							if (!isPowerLast) {
								calculatorText.text =
									calculatorText.text.toString() + buttonsHorizontal[i][j].text.toString()
								isNumberLast = false
								wasDotHere = false
								isPowerLast = true
							}
							fieldOfCalculator = calculatorText.text.toString()
						}
					"=" ->
						buttonsHorizontal[i][j].setOnClickListener {
							textOfCalculator = calculatorText.text.toString()

//							calculatorText.text = textOfCalculator

							calculatorText.text = format.format(readAndCompute(textOfCalculator))
//							calculatorText.text = readAndCompute(textOfCalculator).toString()

//							calculatorText.text = NumberFormat.getInstance().format(readAndCompute(textOfCalculator)).toString()

//								NumberFormat.getInstance().format().toString()
							fieldOfCalculator = calculatorText.text.toString()
						}
				}
				rowsHorizontal[i].addView(buttonsHorizontal[i][j])
			}
			btnTableHorizontal.addView(rowsHorizontal[i])
		}
	}
}

//var btnTableVertical : LinearLayout = findViewById<LinearLayout>(R.id.clickTable)
//
//var newBtn = arrayOf<Button>(Button(this), Button(this), Button(this)) // Button(this)
//newBtn[0].layoutParams =
//ConstraintLayout.LayoutParams(100, 100)
//newBtn[0].text = "0"
//newBtn[1].layoutParams =
//ConstraintLayout.LayoutParams(100, 100)
//newBtn[1].text = "0"
//newBtn[2].layoutParams =
//ConstraintLayout.LayoutParams(100, 100)
//newBtn[2].text = "0"
//
//btnTableVertical.addView(newBtn[0])
//btnTableVertical.addView(newBtn[1])
//btnTableVertical.addView(newBtn[2])