package com.thor.githubuser3.UI.Favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thor.githubuser3.R
import com.thor.githubuser3.UI.UserAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        with(binding.toolbar) {
//            setNavigationOnClickListener {
//                findNavController().navigateUp()
//            }
//
//            setOnMenuItemClickListener {
//                when (it.itemId) {
//                    R.id.action_truncate -> viewModel.deleteAll()
//                }
//                false
//            }
//        }

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

    override fun onResume() {
        super.onResume()
        viewModel.list()
    }

}