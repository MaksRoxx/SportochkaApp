package com.example.sportochka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sportochka.databinding.FragmentRegistrationBinding
import com.example.sportochka.viewmodels.RegisterViewModel

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel: RegisterViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            signUpButton.setOnClickListener {
                if (emailAddress.text.isNotBlank() && password.text.isNotBlank() && confPassword.text.isNotBlank() && username.text.isNotBlank()) {
                viewModel.registerWithBasicAuth(username.text.toString(), password.text.toString())
            } else {
                Toast.makeText(requireContext(), "Wrong entered", Toast.LENGTH_SHORT).show()
            }
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }
            redirectLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }
        }
    }

//    private fun basicRegistr(): String {
//        binding.apply {
//            if (emailAddress.text.isNotBlank() && password.text.isNotBlank() && confPassword.text.isNotBlank() && username.text.isNotBlank()) {
//                val bas = okhttp3.Credentials.basic(username.text.toString(), password.text.toString())
//                return bas
//            } else {
//                Toast.makeText(requireContext(), "Wrong entered", Toast.LENGTH_SHORT).show()
//                return ""
//            }
//        }
//    }
}