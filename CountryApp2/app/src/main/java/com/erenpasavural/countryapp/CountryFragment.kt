package com.erenpasavural.countryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.Navigation
import com.erenpasavural.countryapp.databinding.FragmentCountryBinding
import com.erenpasavural.countryapp.util.downloadUrl
import com.erenpasavural.countryapp.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private var countryUuid =  0
    private lateinit var viewModel : CountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCountryBinding.inflate(inflater,container,false)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)



        observeLiveData()









        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeLiveData() {

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { countryDetails ->

            binding.countryName.text = countryDetails.countryName
            binding.countryCapital.text = countryDetails.countryCapital
            binding.countryCurrency.text = countryDetails.countryCurrency
            binding.countryLanguage.text = countryDetails.countryLanguage
            binding.countryRegion.text = countryDetails.countryRegion
            binding.countryImage.downloadUrl(countryDetails.countryFlag)

        })
    }
}