package com.example.sportochka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView

class MapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bot = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bot.visibility = View.VISIBLE
        return inflater.inflate(R.layout.fragment_map, container, false)
    }
}