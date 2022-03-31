package ru.gb.course2.gblesson2.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.gb.course2.gblesson2.R
import ru.gb.course2.gblesson2.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<AppState> {
            renderData(it)
        }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.load()
        binding.message.text = "Text"
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.isVisible = true
            }
            is AppState.Loading -> {
                binding.loadingLayout.isVisible = true
                binding.mainView.isVisible = false
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.isVisible = true
                Snackbar.make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("reload") { viewModel.load() }
                    .show()
            }
        }
        Toast.makeText(requireContext(), "data", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}