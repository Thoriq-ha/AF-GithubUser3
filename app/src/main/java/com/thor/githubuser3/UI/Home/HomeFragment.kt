package com.thor.githubuser3.UI.Home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.thor.githubuser3.Data.Preference.DataStore.DataStoreThemeUI
import com.thor.githubuser3.Data.Preference.DataStore.UIMode
import com.thor.githubuser3.R
import com.thor.githubuser3.UI.UserAdapter
import com.thor.githubuser3.Utils.viewBinding
import com.thor.githubuser3.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.system.exitProcess


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by inject()

    private val themeUI: DataStoreThemeUI by inject()

    private val adapter = UserAdapter {
//        findNavController().navigate(HomeFragmentDirections.toProfileFragment(it))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.searchview.setOnQueryTextListener(onQueryTextListener)

        binding.swiperefresh.setOnRefreshListener { viewModel.refresh() }

        binding.rvUser.adapter = adapter
        viewModel.stateList.observe(viewLifecycleOwner, observerStateList)

        themeUI.uiModeFlow.asLiveData().observe(viewLifecycleOwner) {
            setCheckedMode(it)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dark_mode -> {
                themeUI.uiModeFlow.asLiveData().observe(viewLifecycleOwner) {
                    if (it == UIMode.LIGHT) {
                        lifecycleScope.launch {
                            themeUI.setThemeMode(UIMode.DARK)
                        }
                    } else {
                        lifecycleScope.launch {
                            themeUI.setThemeMode(UIMode.LIGHT)
                        }
                    }
                }
                true
            }
            R.id.menu1 -> {
                lifecycleScope.launch {
                    themeUI.setThemeMode(UIMode.LIGHT)
                }
                true
            }
            R.id.menu2 -> {
//                this@MainActivity.finish()
                exitProcess(0)
            }
            else -> true
        }
    }

    private val observerStateList = Observer<HomeState> {
        binding.swiperefresh.isRefreshing = (it == HomeState.OnLoading)
        when (it) {
            is HomeState.OnSuccess -> {
                adapter.setList(it.list)
            }
            is HomeState.OnError -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
            HomeState.OnLoading -> {}
        }
    }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            viewModel.search(query ?: "")
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            viewModel.search(newText ?: "")
            return false
        }
    }

    private fun setCheckedMode(uiMode: UIMode) {
        when (uiMode) {
            UIMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            UIMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

}