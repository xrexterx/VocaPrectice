package com.xrexter.vocaprectice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlin.concurrent.thread

class FragmentEditWord: Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_edit_word, container, false)
		val rvEditWordList = view.findViewById<RecyclerView>(R.id.rv_word_list)
		val rvEditWordListAdapter = RecyclerViewEditWordAdapter(requireContext())
		rvEditWordList.adapter = rvEditWordListAdapter

		return view
	}

	override fun onResume() {
		super.onResume()
		refreshRecyclerView()
	}

	private fun refreshRecyclerView() {
		val rvEditWordList = view?.findViewById<RecyclerView>(R.id.rv_word_list)
		val rvAdapter = RecyclerViewEditWordAdapter(requireContext())
		rvEditWordList?.adapter = rvAdapter

		thread {
			val userWordDatabase =
				Room.databaseBuilder(requireContext(), UserWordDatabase::class.java, "db").build()
			val words = userWordDatabase.userWordInterface().getAll()

			val handler = Handler(Looper.getMainLooper())
			handler.postDelayed({
				rvAdapter.datas.clear()
				rvAdapter.notifyDataSetChanged()
				for (item in words) {
					rvAdapter.datas.add(
						RecyclerViewEditWordDataSet(
							item.english.toString(),
							item.korean.toString(),
							item.corrects.toString(),
							item.wrongs.toString()
						)
					)
					rvAdapter.notifyItemInserted(item.idx + 1)
				}
			}, 0)
		}
	}
}