package com.xrexter.vocaprectice

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private var totalCount: Int): FragmentStateAdapter(fragmentActivity) {

	override fun getItemCount(): Int {
		return totalCount
	}

	override fun createFragment(position: Int): Fragment {
		return when (position) {
			0 -> FragmentAddWord()
			1 -> FragmentEditWord()
			2 -> FragmentTestWord()
			else -> FragmentAddWord()
		}
	}


}