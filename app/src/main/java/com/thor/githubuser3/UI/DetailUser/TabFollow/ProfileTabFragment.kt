package com.thor.githubuser3.UI.DetailUser.TabFollow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.thor.githubuser3.R
import com.thor.githubuser3.UI.DetailUser.DetailUserFragmentDirections
import com.thor.githubuser3.UI.UserAdapter
import com.thor.githubuser3.Utils.viewBinding
import com.thor.githubuser3.databinding.FragmentFollowerBinding
import org.koin.android.ext.android.inject

class ProfileTabFragment : Fragment(R.layout.fragment_follower) {

    private val binding by viewBinding(FragmentFollowerBinding::bind)

    private val viewModel: ProfileTabViewModel by inject()

    private val tabkey by lazy {
        requireArguments().getInt(TAB_KEY, 0)
    }

    private val username by lazy {
        requireArguments().getString(USERNAME_KEY, "")
    }

    private val adapter = UserAdapter {
        findNavController().navigate(DetailUserFragmentDirections.actionDetailUserFragmentSelf(it))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.list(tabkey, username)
        binding.swiperefresh.setOnRefreshListener { viewModel.list(tabkey, username) }

        binding.rvFollower.adapter = adapter
        binding.rvFollower.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.state.observe(viewLifecycleOwner, observerState)
    }

    private val observerState = Observer<ProfileTabState> {
//        binding.swiperefresh.isRefreshing = (it == ProfileTabState.OnLoading)
        when (it) {
            ProfileTabState.OnLoading -> {}
            is ProfileTabState.OnSuccess -> {
                adapter.setList(it.list)
            }
            is ProfileTabState.OnError -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val TAB_KEY = "tabb"
        const val USERNAME_KEY = "usernamekey"
    }

}
