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
import androidx.fragment.app.activityViewModels
import com.thebyte.boggle.R
import java.io.InputStream

class Fragment2 : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var newGameButton: Button
    private val dictionary: MutableCollection<String> = mutableListOf()

    private var alreadySubmitted: MutableCollection<String> = mutableListOf()
    private var score: Int = 0

    private fun getScoreStr(str: String): String {
        val vowelCount = str.count{c: Char ->  c in setOf('a','e','i','o','u') }
        val specialConsonantCount = str.count{c: Char ->  c in setOf('s','z','p','x','q') }
        if (str.length < 4){
            Toast.makeText(activity, "Words should be at least 4 characters long", Toast.LENGTH_SHORT).show()
            score -= 10
        }
        else if (vowelCount < 2){
            Toast.makeText(activity, "At least 2 vowels should be used", Toast.LENGTH_SHORT).show()
            score -= 10
        }
        else if(alreadySubmitted.contains(str)){
            Toast.makeText(activity, "Word already submitted", Toast.LENGTH_SHORT).show()
            score -= 10
        }
        else if (!dictionary.contains(str)){
            Toast.makeText(activity, "This is not a word in english", Toast.LENGTH_SHORT).show()
            score -= 10
        }
        else{
            alreadySubmitted.add(str)
            score += vowelCount*5
            score += (str.length-vowelCount)
            if (specialConsonantCount > 0) {
                score += vowelCount*5
                score += (str.length-vowelCount)
                Toast.makeText(activity, "Bravo! Double points!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(activity, "Bravo!", Toast.LENGTH_SHORT).show()
            }
        }
        return "Score: $score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val inputStream: InputStream = getResources().assets.open("words.txt")
        inputStream.bufferedReader().forEachLine{ dictionary.add(it.lowercase()) }

        newGameButton = requireView().findViewById(R.id.new_game_button)
        newGameButton.setOnClickListener {
            sharedViewModel.newGameClick.value = true
            score = 0
            alreadySubmitted = mutableListOf()
            val textView: TextView = requireView().findViewById<View>(R.id.score_text) as TextView
            textView.text = "Score: 0"
        }

        sharedViewModel.typedText.observe(viewLifecycleOwner) {str ->
            val textView: TextView = requireView().findViewById<View>(R.id.score_text) as TextView
            textView.text = getScoreStr(str)
        }
    }

}