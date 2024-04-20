package com.example.sportochka

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class GreetingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_greeting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(requireActivity().mainLooper).postDelayed({
            if (true) {
                findNavController().navigate(R.id.action_greetingFragment_to_authFragment)
            } else {
                findNavController().navigate(R.id.action_greetingFragment_to_qrFragment)
            }
        }, 3000L)
    }
}