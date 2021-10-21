package com.eugene.mockup.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.eugene.mockup.viewModel.AppState
import com.eugene.mockup.databinding.MainFragmentBinding
import com.eugene.mockup.viewModel.MainViewModel

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null;
    private val binding get () = _binding!!
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
       _binding = MainFragmentBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java);
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { updateData(it) });
    }

    private fun updateData(appState: AppState) {
        when(appState) {
            is AppState.Success -> {
                var data = appState.data;
                binding.loadingLayout.visibility = View.GONE;

                binding.valute1Tv.text = data.toString();
            }
            is AppState.Loading ->  { binding.loadingLayout.visibility = View.VISIBLE; }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE;
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }

}