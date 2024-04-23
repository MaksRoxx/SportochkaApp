package com.example.sportochka

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sportochka.databinding.FragmentQrBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class QrFragment : Fragment() {
    private lateinit var binding: FragmentQrBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bot = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bot.visibility = View.VISIBLE
        binding = FragmentQrBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val barkodeEncode = BarcodeEncoder()
            val bitmap: Bitmap = barkodeEncode
                .encodeBitmap("Bob", BarcodeFormat.QR_CODE, 750, 750)
            binding.imageView.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.button.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Наведите камеру для сканирования qr кода")
            options.setOrientationLocked(false)
            barcodeLauncher.launch(options)
        }
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                requireContext(),
                "Scanned: friend added",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}