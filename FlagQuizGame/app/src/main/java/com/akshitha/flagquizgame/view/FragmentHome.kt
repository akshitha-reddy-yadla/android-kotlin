package com.akshitha.flagquizgame.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akshitha.flagquizgame.R
import com.akshitha.flagquizgame.databinding.FragmentHomeBinding

class FragmentHome : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        fragmentHomeBinding.btnStart.setOnClickListener {

        }

        return fragmentHomeBinding.root
    }

}