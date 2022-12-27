package com.testapp.gridcalc
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.testapp.gridcalc.R
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val zeroButton: Button
        zeroButton = findViewById(R.id.zeroButton)
        zeroButton.setOnClickListener { setNumbers("0") }

        val oneButton: Button
        oneButton = findViewById(R.id.oneButton)
        oneButton.setOnClickListener { setNumbers("1") }

        val twoButton: Button
        twoButton = findViewById(R.id.twoButton)
        twoButton.setOnClickListener { setNumbers("2") }

        val threeButton: Button
        threeButton = findViewById(R.id.threeButton)
        threeButton.setOnClickListener { setNumbers("3") }

        val fourButton: Button
        fourButton = findViewById(R.id.fourButton)
        fourButton.setOnClickListener { setNumbers("4") }

        val fiveButton: Button
        fiveButton = findViewById(R.id.fiveButton)
        fiveButton.setOnClickListener { setNumbers("5") }

        val sixButton: Button
        sixButton = findViewById(R.id.sixButton)
        sixButton.setOnClickListener { setNumbers("6") }

        val sevenButton: Button
        sevenButton = findViewById(R.id.sevenButton)
        sevenButton.setOnClickListener { setNumbers("7") }
        val eightButton: Button
        eightButton = findViewById(R.id.eightButton)
        eightButton.setOnClickListener { setNumbers("8") }

        val nineButton: Button
        nineButton = findViewById(R.id.nineButton)
        nineButton.setOnClickListener { setNumbers("9") }

        val plusButton: Button
        plusButton = findViewById(R.id.plusButton)
        plusButton.setOnClickListener { setNumbers("+") }

        val minusButton: Button
        minusButton = findViewById(R.id.minusButton)
        minusButton.setOnClickListener { setNumbers("-") }

        val multiplyButton: Button
        multiplyButton = findViewById(R.id.multiplyButton)
        multiplyButton.setOnClickListener { setNumbers("*") }

        val divisionButton: Button
        divisionButton = findViewById(R.id.divisionButton)
        divisionButton.setOnClickListener { setNumbers("/") }

        val bracketOneButton: Button
        bracketOneButton = findViewById(R.id.bracketOneButton)
        bracketOneButton.setOnClickListener { setNumbers("(") }

        val bracketTwoButton: Button
        bracketTwoButton = findViewById(R.id.bracketTwoButton)
        bracketTwoButton.setOnClickListener { setNumbers(")") }

        val clearAllButton: Button
        clearAllButton = findViewById(R.id.clearAllButton)

        var firstString: TextView
        var resultString: TextView
        firstString = findViewById(R.id.firstString)
        resultString = findViewById(R.id.resultString)
        clearAllButton.setOnClickListener {
            firstString.text = ""
            resultString.text = ""
        }

        val deleteButton: Button
        deleteButton = findViewById(R.id.deleteButton)
        deleteButton.setOnClickListener {
            var helper = firstString.text.toString()
            if(helper.isNotEmpty() && resultString.text.toString().isEmpty())
            {
                firstString.text = helper.substring(0, helper.length-1)
            }
            else if(resultString.text.toString().isNotEmpty())
            {
                helper = resultString.text.toString()
                firstString.text = helper.substring(0, helper.length-1)
            }
            resultString.text = "";
            if(helper.isEmpty() && resultString.text.isEmpty()){
                Toast.makeText(applicationContext, "Input field already empty!", Toast.LENGTH_SHORT).show()
            }
        }

        val equalButton: Button
        equalButton = findViewById(R.id.equalButton)
        equalButton.setOnClickListener{
            try {
                val eb = ExpressionBuilder(firstString.text.toString()).build()
                val rez = eb.evaluate()
                val int = rez.toLong()
                if(rez == int.toDouble())
                    resultString.text = int.toString()
                else
                    resultString.text = rez.toString()
            } catch (e:Exception) {
                Log.d("Error", "${e.message}")
            }
        }
    }

    fun setNumbers(str: String) {
        var firstString: TextView
        firstString = findViewById(R.id.firstString)
        var resultString: TextView
        resultString = findViewById(R.id.resultString)
        if(resultString.text != "")
        {
            firstString.text = resultString.text
            resultString.text = ""
        }
        firstString.append(str)
    }

}