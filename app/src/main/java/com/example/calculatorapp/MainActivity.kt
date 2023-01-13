package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ActionMenuView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var Displaytext: TextView? = null
    var lastDigit: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Displaytext = findViewById(R.id.displaytext)

        }
    fun Numberclick(view: View){
        Displaytext?.append((view as Button).text)
        lastDigit = true
        lastDot = false
    }
    fun Onclear(view: View){
        Displaytext?.text=""
    }
    fun Ondecimalpoint(){
        if(lastDigit && !lastDot){
            Displaytext?.append(".")
            lastDigit = true
            lastDot = false
        }

    }
    fun Onoperator(view: View){
        Displaytext?.text?.let {
            if(lastDigit && !isOperatoradded(it.toString())){
                Displaytext?.append((view as Button).text)
                lastDigit =false
                lastDot = false
            }
        }
    }

    fun Onequals(view: View){
        if(lastDigit){
            var lastvalue = Displaytext?.text.toString()
            var prefix = ""
            try {
                if (lastvalue.startsWith("-")){
                    prefix = "-"
                    lastvalue = lastvalue.substring(1)
                }
                if(lastvalue.contains("-")) {
                    var splitvalue = lastvalue.split("-")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    Displaytext?.text = removeZeroAfterResult((one.toDouble() - two.toDouble()).toString())
                }else if(lastvalue.contains("+")){
                    var splitvalue = lastvalue.split("+")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+ one
                    }
                    Displaytext?.text = removeZeroAfterResult((one.toDouble() + two.toDouble()).toString())
                }else if(lastvalue.contains("*")){
                    var splitvalue = lastvalue.split("*")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+ one
                    }
                    Displaytext?.text = removeZeroAfterResult((one.toDouble() * two.toDouble()).toString())
                }else if(lastvalue.contains("/")){
                    var splitvalue = lastvalue.split("/")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+ one
                    }
                    Displaytext?.text = removeZeroAfterResult((one.toDouble() / two.toDouble()).toString())
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterResult(result:String):String {
        var final = result
        if(final.contains(".0")){
            final = result.substring(0,result.length-2)
        }
        return final
    }


    private fun isOperatoradded(value: String): Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("+")
                    ||value.contains("-")
                    ||value.contains("*")
        }
    }
}