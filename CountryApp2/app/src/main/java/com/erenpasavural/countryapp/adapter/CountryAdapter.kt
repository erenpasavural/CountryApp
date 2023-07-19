package com.erenpasavural.countryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.erenpasavural.countryapp.FeedFragmentDirections
import com.erenpasavural.countryapp.databinding.ItemCountryBinding
import com.erenpasavural.countryapp.model.Country
import com.erenpasavural.countryapp.util.downloadUrl

class CountryAdapter (val countryList:ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder (val binding: ItemCountryBinding) :RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
       return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.binding.name.text = countryList[position].countryName
        holder.binding.region.text = countryList[position].countryRegion

        holder.itemView.setOnClickListener {
            val country = countryList[position]
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment().setCountryUuid(country.countryId)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.imageView.downloadUrl(countryList[position].countryFlag)


    }

    fun updateCountryList (newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()


    }
}