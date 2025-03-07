package com.akshitha.flagquizgame.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akshitha.flagquizgame.R
import com.akshitha.flagquizgame.databinding.FragmentResultBinding

class FragmentResult : Fragment() {

    private lateinit var fragementResultBinding: FragmentResultBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragementResultBinding = FragmentResultBinding.inflate(inflater, container, false)

        fragementResultBinding.btnNewQuiz.setOnClickListener {  }

        fragementResultBinding.btnExit.setOnClickListener {  }

        return fragementResultBinding.root
    }
}