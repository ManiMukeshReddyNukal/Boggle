package com.thebyte.boggle.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.thebyte.boggle.R

class Fragment1 : Fragment() {

    companion object {
        fun newInstance() = Fragment1()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var button10: Button
    private lateinit var button11: Button
    private lateinit var button12: Button
    private lateinit var button13: Button
    private lateinit var button14: Button
    private lateinit var button15: Button
    private lateinit var button16: Button
    private lateinit var clearButton: Button
    private lateinit var submitButton: Button
    private var lastButton: Int = -1
    private var curText: String = ""
    private var posToChar = arrayOf("S","T","N","G","E","I","A","E","D","R","L","S","S","E","P","O")
    private var alreadyClicked: MutableList<Int> = mutableListOf()

    private fun ifValidButtonPress(butNum: Int, lastBut: Int): Boolean{
        if (lastBut == -1) return true
        val possibilities: MutableList<Int> = mutableListOf()
        possibilities.addAll(listOf(lastBut-4, lastBut+4))
        if (lastBut % 4 == 0){
            possibilities.addAll(listOf(lastBut-1, lastBut-4-1, lastBut+4-1))
        }
        if (lastBut % 4 == 1){
            possibilities.addAll(listOf(lastBut+1, lastBut-4+1, lastBut+4+1))
        }
        if (lastBut % 4 == 2 || lastBut % 4 == 3){
            possibilities.addAll(listOf(lastBut-1, lastBut-4-1, lastBut+4-1, lastBut+1, lastBut-4+1, lastBut+4+1))
        }
        return possibilities.contains(butNum) && !alreadyClicked.contains(butNum)
    }

    private fun handleTextButtons(view: View){
        val butNum = (view.resources.getResourceName(view.id)).split("button").last().toInt()
        if (ifValidButtonPress(butNum, lastButton)){
            lastButton = butNum
            alreadyClicked.add(butNum)
            curText += posToChar[butNum-1]
            (view as Button).setBackgroundColor(0xFF009200.toInt())
            val textView: TextView = requireView().findViewById<View>(R.id.current_word) as TextView
            textView.text = curText
        }
        else{
            Toast.makeText(
                activity,
                "This is not a valid letter selection",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun handleClearButton(view: View){
        lastButton = -1
        curText = ""
        alreadyClicked = mutableListOf()
        val textView: TextView = requireView().findViewById<View>(R.id.current_word) as TextView
        textView.text = curText
        (requireView().findViewById<View>(R.id.button1) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button2) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button3) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button4) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button5) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button6) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button7) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button8) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button9) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button10) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button11) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button12) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button13) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button14) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button15) as Button).setBackgroundColor(0xFF7CFC00.toInt())
        (requireView().findViewById<View>(R.id.button16) as Button).setBackgroundColor(0xFF7CFC00.toInt())
    }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        button1 = requireView().findViewById(R.id.button1)
        button2 = requireView().findViewById(R.id.button2)
        button3 = requireView().findViewById(R.id.button3)
        button4 = requireView().findViewById(R.id.button4)
        button5 = requireView().findViewById(R.id.button5)
        button6 = requireView().findViewById(R.id.button6)
        button7 = requireView().findViewById(R.id.button7)
        button8 = requireView().findViewById(R.id.button8)
        button9 = requireView().findViewById(R.id.button9)
        button10 = requireView().findViewById(R.id.button10)
        button11 = requireView().findViewById(R.id.button11)
        button12 = requireView().findViewById(R.id.button12)
        button13 = requireView().findViewById(R.id.button13)
        button14 = requireView().findViewById(R.id.button14)
        button15 = requireView().findViewById(R.id.button15)
        button16 = requireView().findViewById(R.id.button16)

        button1.setOnClickListener { handleTextButtons(button1) }
        button2.setOnClickListener { handleTextButtons(button2) }
        button3.setOnClickListener { handleTextButtons(button3) }
        button4.setOnClickListener { handleTextButtons(button4) }
        button5.setOnClickListener { handleTextButtons(button5) }
        button6.setOnClickListener { handleTextButtons(button6) }
        button7.setOnClickListener { handleTextButtons(button7) }
        button8.setOnClickListener { handleTextButtons(button8) }
        button9.setOnClickListener { handleTextButtons(button9) }
        button10.setOnClickListener { handleTextButtons(button10) }
        button11.setOnClickListener { handleTextButtons(button11) }
        button12.setOnClickListener { handleTextButtons(button12) }
        button13.setOnClickListener { handleTextButtons(button13) }
        button14.setOnClickListener { handleTextButtons(button14) }
        button15.setOnClickListener { handleTextButtons(button15) }
        button16.setOnClickListener { handleTextButtons(button16) }

        clearButton = requireView().findViewById(R.id.clear)
        clearButton.setOnClickListener { handleClearButton(clearButton) }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

}