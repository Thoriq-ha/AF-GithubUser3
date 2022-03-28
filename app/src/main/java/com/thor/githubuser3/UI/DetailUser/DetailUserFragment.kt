package com.thor.githubuser3.UI.DetailUser

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.thor.githubuser3.R
import com.thor.githubuser3.Utils.viewBinding
import com.thor.githubuser3.databinding.FragmentDetailUserBinding
import org.koin.android.ext.android.inject

class DetailUserFragment : Fragment(R.layout.fragment_detail_user) {

    private val binding by viewBinding(FragmentDetailUserBinding::bind)

    private val user by lazy {
        DetailUserFragmentArgs.fromBundle(requireArguments()).user
    }


    private val viewModel: DetailUserViewModel by inject()

//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        setHasOptionsMenu(true)
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.favorite_active -> {
//                item.isVisible = false
//                true
//            }
//            R.id.favorite_nonactive -> {
//                item.isVisible = false
//                item.
//                true
//            }
//            else -> true
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        binding.toolbar.setNavigationOnClickListener {
//            findNavController().navigateUp()
//        }

//        with(binding.tvRepository) {
//            text = user.htmlUrl
//            setOnClickListener {
//                CustomTabsIntent.Builder().build()
//                    .launchUrl(requireContext(), Uri.parse(user.htmlUrl))
//            }
//        }

        Glide.with(binding.root.context)
            .load(user.avatar)
            .fitCenter()
            .into(binding.circleImageView)

//        binding.containerContent.viewPager.adapter =
//            ProfileTabPagerAdapter(user.username, childFragmentManager, lifecycle)

//        TabLayoutMediator(
//            binding.containerContent.tabLayout,
//            binding.containerContent.viewPager
//        ) { tab, position ->
//            tab.text = when (position) {
//                0 -> "Follower"
//                else -> "Following"
//            }
//        }.attach()

        with(user.username) {
            viewModel.detail(this)
            viewModel.find(this)
        }

        viewModel.state.observe(viewLifecycleOwner, stateObserver)
        viewModel.favoriteState.observe(viewLifecycleOwner, favoriteObserver)
//    }
    }

    private val stateObserver = Observer<ProfileState> {
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
                viewModel.favorite(user)
            }
            FavoriteState.OnSaved -> {
                viewModel.delete(user.username)
            }
            is FavoriteState.OnError -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        }
    }


}