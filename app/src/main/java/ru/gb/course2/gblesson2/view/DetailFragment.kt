package ru.gb.course2.gblesson2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.gb.course2.gblesson2.R
import ru.gb.course2.gblesson2.data.Weather
import ru.gb.course2.gblesson2.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(BUNDLE_EXTRA)?.let { weather ->
            with(binding) {
                cityName.text = weather.city.city
                cityCoordinates.text = String.format(
                    getString(R.string.city_coordinates),
                    weather.city.lat.toString(),
                    weather.city.lon.toString()
                )
                temperatureValue.text = weather.temperature.toString()
                feelsLikeValue.text = weather.feelsLike.toString()
            }
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"
    }
}
