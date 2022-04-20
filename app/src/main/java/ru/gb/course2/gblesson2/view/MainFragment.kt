package ru.gb.course2.gblesson2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.gb.course2.gblesson2.R
import ru.gb.course2.gblesson2.data.AppState
import ru.gb.course2.gblesson2.data.Weather
import ru.gb.course2.gblesson2.databinding.FragmentMainBinding
import ru.gb.course2.gblesson2.hide
import ru.gb.course2.gblesson2.show
import ru.gb.course2.gblesson2.showSnackBar
import ru.gb.course2.gblesson2.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemClick(weather: Weather) {
            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.container,
                DetailFragment().also { fragment ->
                    fragment.arguments = Bundle().also { bundle ->
                        bundle.putParcelable(
                            DetailFragment.BUNDLE_EXTRA,
                            weather
                        )
                    }
                }
            )?.addToBackStack("")?.commit()
        }
    })
    private var isDataSetRus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
        viewModel.liveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherFromLocalSourceRus()
    }

    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun changeWeatherDataSet() {
        if (isDataSetRus) {
            viewModel.getWeatherFromLocalSourceWorld()
            // binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getWeatherFromLocalSourceRus()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
        isDataSetRus = !isDataSetRus
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.hide()
                adapter.setWeather(appState.weatherData)
            }
            is AppState.Loading -> {
                binding.mainFragmentLoadingLayout.show()
            }
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.hide()
                binding.mainFragmentFAB.showSnackBar(
                    text = getString(R.string.error),
                    actionText = getString(R.string.reload),
                    action = { viewModel.getWeatherFromLocalSourceRus() }
                )
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemClick(weather: Weather)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
