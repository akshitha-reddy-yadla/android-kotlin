package com.akshitha.flagquizgame.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akshitha.flagquizgame.R
import com.akshitha.flagquizgame.databinding.FragmentQuizBinding

class FragmentQuiz : Fragment() {

    private lateinit var fragmentQuizBinding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentQuizBinding = FragmentQuizBinding.inflate(inflater, container, false)

        fragmentQuizBinding.btnA.setOnClickListener {  }

        fragmentQuizBinding.btnB.setOnClickListener {  }

        fragmentQuizBinding.btnC.setOnClickListener {  }

        fragmentQuizBinding.btnD.setOnClickListener {  }

        fragmentQuizBinding.btnNext.setOnClickListener {  }
        
        return fragmentQuizBinding.root;
    }
}