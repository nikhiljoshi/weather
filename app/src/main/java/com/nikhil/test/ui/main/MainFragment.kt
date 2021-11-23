package com.nikhil.test.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.nikhil.test.R
import com.nikhil.test.databinding.FragmentListMainBinding
import com.nikhil.test.utils.EventObserver
import com.nikhil.test.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


/**
 *  Fragment for showing weather info
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var weatherListViewModel: WeatherViewModel

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
        setUI()
        setupData()
    }

    private fun setUI() {
        binding.swipeContainer.setOnRefreshListener {
            binding.swipeContainer.setRefreshing(false);
        }

        // Configure the refreshing colors
        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                //clear the focus so that keyBoard won't pop-up upon service call results
                binding.searchView.clearFocus()
                query?.let {
                    Timber.e(query)
                    weatherListViewModel.getWeather(query)
                    binding.swipeContainer.setRefreshing(true);
                }

                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
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

                  //  weatherInfoListAdapter.setData(listOf(cityWeather))
                    binding.cityLayout.name.text = cityWeather.name
                    binding.cityLayout.maxMin.text = cityWeather.main.temp_min.toInt().toString()
                    binding.cityLayout.temp.text = cityWeather.main.temp.toInt().toString()
                    binding.cityLayout.description.text = cityWeather.weather.get(0).description
                    binding.cityLayout.maxMin.text = cityWeather.main.humidity.toString()
                    binding.cityLayout.addBookmarkButton.visibility = View.VISIBLE
                    binding.swipeContainer.setRefreshing(false);
                }
            })

        binding.cityLayout.addBookmarkButton.setOnClickListener{
            val cityWeather = weatherListViewModel.weatherMutableLiveData.value

            if (cityWeather != null) {
                weatherListViewModel.bookmarkLocation(cityWeather.name,cityWeather.id,cityWeather.main.temp)
            }
            binding.cityLayout.addBookmarkButton.visibility = View.GONE
            binding.cityLayout.removeBookmarkButton.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Navigation
        findNavController().navigate(R.id.nav_fav_fragment, arguments)
        return true
    }

}
