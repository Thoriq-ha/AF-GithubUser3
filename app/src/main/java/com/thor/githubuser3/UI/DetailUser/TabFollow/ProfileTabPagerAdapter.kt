package com.thor.githubuser3.UI.DetailUser.TabFollow

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileTabPagerAdapter(private val username: String, fragmentManager: FragmentManager, lifecycle: Lifecycle):  FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = ProfileTabFragment()
        fragment.arguments = Bundle().apply {
            putInt(ProfileTabFragment.TAB_KEY, position)
            putString(ProfileTabFragment.USERNAME_KEY, username)
        }
        return fragment
    }

}