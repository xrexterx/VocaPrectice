package com.xrexter.vocaprectice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import kotlin.concurrent.thread

class FragmentAddWord: Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_add_word, container, false)
		val btnAdd = view.findViewById<Button>(R.id.btn_add_word)

		btnAdd.setOnClickListener {
			thread {
				val userWordDatabase = Room.databaseBuilder(requireContext(), UserWordDatabase::class.java, "db").build()
				val userInterface = userWordDatabase.userWordInterface()
				val idx = userInterface.getLastIndex() + 1
				val etEng = view.findViewById<EditText>(R.id.et_add_word_english)
				val etKor = view.findViewById<EditText>(R.id.et_add_word_korean)

				userInterface.insert(
					UserWordSet(idx, etEng.text.toString(), etKor.text.toString(), 0, 0)
				)

				//insert check
				val insertCheck: Int = userInterface.getLastIndex()
				if (idx == insertCheck) {
					etEng.text = null
					etKor.text = null

					val handler = Handler(Looper.getMainLooper())
					handler.postDelayed( { Toast.makeText(activity, "단어 등록 완료", Toast.LENGTH_SHORT).show()}, 0)

				} else {
					Log.d("Add word", "fail")
				}
			}
		}
		return view
	}
}