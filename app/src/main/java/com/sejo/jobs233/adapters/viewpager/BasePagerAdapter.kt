package com.sejo.jobs233.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sejo.jobs233.models.TabFragment

class BasePagerAdapter(fm: FragmentManager, behaviour: Int, tabFragments: List<TabFragment>) :
    FragmentPagerAdapter(fm, behaviour) {

    val fragments = tabFragments

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position].fragment

    override fun getPageTitle(position: Int): CharSequence = fragments[position].title
}