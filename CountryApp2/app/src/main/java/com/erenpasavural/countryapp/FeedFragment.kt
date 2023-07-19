package com.erenpasavural.countryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.erenpasavural.countryapp.adapter.CountryAdapter
import com.erenpasavural.countryapp.databinding.FragmentFeedBinding
import com.erenpasavural.countryapp.model.Country
import com.erenpasavural.countryapp.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel:FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater,container,false)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.countryList.layoutManager = LinearLayoutManager(context)
        binding.countryList.adapter = countryAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {

            binding.countryLoading.visibility = View.VISIBLE

            binding.countryList.visibility = View.GONE
            binding.countryError.visibility = View.GONE
            viewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()












        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

   private fun observeLiveData() {

        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->

            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { countryError ->

            countryError?.let {
                if(it) {
                    binding.countryError.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE
                } else {
                    binding.countryError.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { countryLoading ->

            countryLoading?.let {
                if(it){
                    binding.countryLoading.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE
                    binding.countryError.visibility = View.GONE
                } else {
                    binding.countryLoading.visibility = View.GONE
                }
            }




        })
    }
}