package com.thor.githubuser3.UI.Page.DetailUser.TabFollow

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FollowTabPagerAdapter(
    private val username: String,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowTabFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowTabFragment.TAB_KEY, position)
            putString(FollowTabFragment.USERNAME_KEY, username)
        }
        return fragment
    }

}