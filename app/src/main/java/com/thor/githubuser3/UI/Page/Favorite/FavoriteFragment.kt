package com.thor.githubuser3.UI.Page.Favorite

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thor.githubuser3.R
import com.thor.githubuser3.UI.Page.Home.HomeFragmentDirections
import com.thor.githubuser3.UI.UserAdapter
import com.thor.githubuser3.UI.ViewModel.FavoriteViewModel
import com.thor.githubuser3.UI.ViewModel.ListFavoriteState
import com.thor.githubuser3.Utils.viewBinding
import com.thor.githubuser3.databinding.FragmentFavoriteBinding
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding by viewBinding(FragmentFavoriteBinding::bind)

    private val viewModel: FavoriteViewModel by inject()

    private val adapter = UserAdapter {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailUserFragment(
                it
            )
        )
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

        binding.rvFavorite.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is ListFavoriteState.OnSuccess -> {
                    adapter.setList(it.list)
                }
                is ListFavoriteState.OnError -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                ListFavoriteState.OnLoading -> {

                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                viewModel.deleteAll()
                true
            }
            R.id.menu1 -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
                )
                true
            }
            else -> true
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.list()
    }

}