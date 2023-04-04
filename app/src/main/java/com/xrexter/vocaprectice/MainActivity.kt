package com.xrexter.vocaprectice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.xrexter.vocaprectice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setupViewPager()
		setupTabLayout()

	}

	private fun setupTabLayout() {
		val tabTitle = listOf("Add", "Edit", "Test")

		TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
			tab.text = tabTitle[position]
		}.attach()

	}

	private fun setupViewPager() {
		val adapter = ViewPagerAdapter(this, 3)
		binding.viewPager.adapter = adapter

	}
}