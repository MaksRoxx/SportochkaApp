package com.example.sportochka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.sportochka.databinding.FragmentMapBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yandex.mapkit.MapKitFactory

class MapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bot = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bot.visibility = View.VISIBLE
        setApiKey(savedInstanceState)
        MapKitFactory.initialize(requireContext())
        binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    private fun setApiKey(savedInstanceState: Bundle?) {
        val haveApiKey = savedInstanceState?.getBoolean("haveApiKey")
            ?: false // При первом запуске приложения всегда false
        if (!haveApiKey) {
            MapKitFactory.setApiKey(MAPKIT_API_KEY) // API-ключ должен быть задан единожды перед инициализацией MapKitFactory
        }
    }

    // Если Activity уничтожается (например, при нехватке памяти или при повороте экрана) - сохраняем информацию, что API-ключ уже был получен ранее
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("haveApiKey", true)
    }

    // Отображаем карты перед моментом, когда активити с картой станет видимой пользователю:
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    // Останавливаем обработку карты, когда активити с картой становится невидимым для пользователя:
    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    companion object {
        const val MAPKIT_API_KEY = "6ef8d0c5-2284-480e-aac8-1282e21b2c6b"
    }
}