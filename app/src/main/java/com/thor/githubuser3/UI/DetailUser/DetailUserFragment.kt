package com.thor.githubuser3.UI.DetailUser

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.thor.githubuser3.R
import com.thor.githubuser3.UI.DetailUser.TabFollow.ProfileTabPagerAdapter
import com.thor.githubuser3.UI.Home.HomeState
import com.thor.githubuser3.Utils.viewBinding
import com.thor.githubuser3.databinding.FragmentDetailUserBinding
import org.koin.android.ext.android.inject

class DetailUserFragment : Fragment(R.layout.fragment_detail_user) {

    private val binding by viewBinding(FragmentDetailUserBinding::bind)

    private val user by lazy {
        DetailUserFragmentArgs.fromBundle(requireArguments()).user
    }

    lateinit var menuDetail: Menu
    private val viewModel: DetailUserViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menuDetail = menu
        inflater.inflate(R.menu.user_detail_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite_active -> {
                item.isVisible = false
                menuDetail.findItem(R.id.favorite_nonactive).isVisible = true
                viewModel.delete(user.username)
                true
            }
            R.id.favorite_nonactive -> {
                item.isVisible = false
                menuDetail.findItem(R.id.favorite_active).isVisible = true
                viewModel.favorite(user)
                true
            }
            else -> true
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(DetailUserFragmentDirections.actionDetailUserFragmentToHomeFragment3())
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.root.context)
            .load(user.avatar)
            .fitCenter()
            .into(binding.circleImageView)

        binding.swiperefresh.setOnRefreshListener { viewModel.refresh(user.username) }
        binding.viewPager.adapter =
            ProfileTabPagerAdapter(user.username, childFragmentManager, lifecycle)

        TabLayoutMediator(
            binding.tabs,
            binding.viewPager
        ) { tab, position ->
            tab.text = when (position) {
                0 -> "Follower"
                else -> "Following"
            }
        }.attach()

        with(user.username) {
            viewModel.detail(this)
            viewModel.find(this)
        }

        viewModel.state.observe(viewLifecycleOwner, stateObserver)
        viewModel.favoriteState.observe(viewLifecycleOwner, favoriteObserver)
    }

    private val stateObserver = Observer<ProfileState> {
        binding.swiperefresh.isRefreshing = (it == ProfileState.OnLoading)
        binding.root.visibility = if (it == ProfileState.OnLoading) {
            View.GONE
        } else {
            View.VISIBLE
        }

        when (it) {
            ProfileState.OnLoading -> {
            }
            is ProfileState.OnSuccess -> {
                val name = "Name      : ${it.user.name}"
                val location = "Location  : ${it.user.location}"
                val company = "Company   : ${it.user.company}"

                binding.tvFollowers.text = it.user.followers.toString()
                binding.tvFollowing.text = it.user.following.toString()
                binding.tvRepository.text = it.user.publicRepos.toString()
                binding.tvName.text = name
                binding.tvLocation.text =
                    if (it.user.location.isNullOrBlank()) "Location is empty" else location
                binding.tvCompanyDetail.text =
                    if (it.user.company.isNullOrBlank()) "Company is empty" else company
            }
            is ProfileState.OnError -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                binding.tvName.text = "-"
                binding.tvLocation.text = "-"
            }
        }
    }

    private val favoriteObserver = Observer<FavoriteState> {
        when (it) {
            FavoriteState.NotFound -> {
                menuDetail.findItem(R.id.favorite_nonactive).isVisible = true
                menuDetail.findItem(R.id.favorite_active).isVisible = false
            }
            FavoriteState.OnSaved -> {
                menuDetail.findItem(R.id.favorite_active).isVisible = true
                menuDetail.findItem(R.id.favorite_nonactive).isVisible = false
            }
            is FavoriteState.OnError -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}