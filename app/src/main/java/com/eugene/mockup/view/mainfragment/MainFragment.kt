package com.eugene.mockup.view.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eugene.mockup.R
import com.eugene.mockup.databinding.MainFragmentBinding
import com.eugene.mockup.model.Valute
import com.eugene.mockup.view.listfragment.ListFragment
import com.eugene.mockup.viewModel.AppState
import com.eugene.mockup.viewModel.MainViewModel

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null;
    private val binding get () = _binding!!
    private lateinit var viewModel: MainViewModel
    private var allValutes: List<Valute>? = null

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
        viewModel.getValuteFromServer()

        onClickButtonsChangeValutes();
    }

    private fun updateData(appState: AppState) {
        when(appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE;

                allValutes = appState.data;

                var rub: Valute? = null;
                var usd: Valute? = null;

                appState.data?.forEach {
                    if (it.charCode.equals("RUB")) rub = it
                    if (it.charCode.equals("USD")) run { usd = it }
                }

                binding.charCode1Tv.text = usd?.charCode
                binding.charCode2Tv.text = rub?.charCode

                binding.value1Et.setText(usd?.nominal)
                binding.value2Et.setText(usd?.value)

            }
            is AppState.Loading ->  { binding.loadingLayout.visibility = View.VISIBLE; }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE;
                Toast.makeText(context, appState.error.message, Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun onClickButtonsChangeValutes() {
        binding.changeValute1Button.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, ListFragment.newInstance(allValutes))
                .addToBackStack("main_frag")
                .commitAllowingStateLoss()
        }

        binding.changeValute2Button.setOnClickListener {
            parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, ListFragment.newInstance(allValutes))
                    .addToBackStack("main_frag")
                    .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }
}