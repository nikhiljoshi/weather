package com.nikhil.test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nikhil.test.R
import com.nikhil.test.databinding.FragmentListMainBinding
import com.nikhil.test.ui.adapter.WeatherInfoListAdapter
import com.nikhil.test.utils.EventObserver
import com.nikhil.test.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 *  Fragment for showing weather info
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var weatherListViewModel: WeatherViewModel

    lateinit var weatherInfoListAdapter: WeatherInfoListAdapter

    private var fragmentMainBinding: FragmentListMainBinding? = null


    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = fragmentMainBinding!!


    //Inflated layout here
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentListMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().setTitle(R.string.app_name)
        setupAdapter()
        setupData()
    }

    private fun setupAdapter() {
        binding.swipeContainer.setOnRefreshListener {
            binding.swipeContainer.setRefreshing(false);
        }
        binding.swipeContainer.setRefreshing(true);
        // Configure the refreshing colors
        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);

        weatherInfoListAdapter = WeatherInfoListAdapter()
        binding.resultsRecyclerView.setHasFixedSize(true)
        binding.resultsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.resultsRecyclerView.adapter = weatherInfoListAdapter
    }


    fun setupData() {
        weatherListViewModel.message.observe(viewLifecycleOwner, EventObserver {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        })

        weatherListViewModel.weatherMutableLiveData.observe(
            viewLifecycleOwner,
            Observer {
                val cityWeather = weatherListViewModel.weatherMutableLiveData.value
                if (cityWeather != null) {

                    weatherInfoListAdapter.setData(listOf(cityWeather))
                    binding.swipeContainer.setRefreshing(false);
                }
                weatherInfoListAdapter.notifyDataSetChanged()
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }
}
