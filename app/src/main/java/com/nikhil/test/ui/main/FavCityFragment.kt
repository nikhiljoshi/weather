package com.nikhil.test.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nikhil.test.R
import com.nikhil.test.databinding.FragmentListFavBinding
import com.nikhil.test.ui.adapter.FavCityListAdapter
import com.nikhil.test.utils.EventObserver
import com.nikhil.test.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 *  Fragment for showing Favourite city info
 */
@AndroidEntryPoint
class FavCityFragment : Fragment() {

    @Inject
    lateinit var weatherListViewModel: WeatherViewModel

    lateinit var favCityListAdapter: FavCityListAdapter

    private var fragmentListFavBinding: FragmentListFavBinding? = null


    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = fragmentListFavBinding!!


    //Inflated layout here
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentListFavBinding = FragmentListFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
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

        favCityListAdapter = FavCityListAdapter()
        binding.resultsRecyclerView.setHasFixedSize(true)
        binding.resultsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.resultsRecyclerView.adapter = favCityListAdapter
        weatherListViewModel.getBookMarks()

    }


    fun setupData() {
        weatherListViewModel.message.observe(viewLifecycleOwner, EventObserver {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        })

        weatherListViewModel.favCityMutableLiveData.observe(
            viewLifecycleOwner,
            Observer {
                val cities = weatherListViewModel.favCityMutableLiveData.value
                if (cities != null) {
                    favCityListAdapter.setData(cities)
                    binding.swipeContainer.setRefreshing(false);
                }
                favCityListAdapter.notifyDataSetChanged()
            })


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Navigation
        findNavController().navigate(R.id.nav_main_fragment, arguments)
        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        fragmentListFavBinding = null
    }
}
