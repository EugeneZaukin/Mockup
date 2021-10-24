package com.eugene.mockup.view.listfragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugene.mockup.databinding.ListFragmentBinding
import com.eugene.mockup.model.Valute
import java.util.*


class ListFragment : Fragment() {
    private var _binding: ListFragmentBinding? = null;
    private val binding get () = _binding!!
    private var parcelableArrayList: List<Valute>? = null;

    companion object {
        private const val KEY_LIST = "key_list"

        @JvmStatic
        fun newInstance(list: List<Valute>?) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(KEY_LIST, ArrayList<Parcelable>(list))
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parcelableArrayList = arguments?.getParcelableArrayList<Valute>(KEY_LIST)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myItemClickListener = object : ListFragmentAdapter.MyItemClickListener {
            override fun onMyItemClick(list: List<Valute>?, position: Int) {
                parentFragmentManager.popBackStack("main_frag", 1)
            }

        }

        binding.recyclerViewList.setHasFixedSize(true)
        val adapter = ListFragmentAdapter(myItemClickListener,parcelableArrayList)
        binding.recyclerViewList.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewList.adapter = adapter
        binding.recyclerViewList.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}