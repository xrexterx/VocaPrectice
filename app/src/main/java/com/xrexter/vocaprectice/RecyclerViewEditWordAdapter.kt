package com.xrexter.vocaprectice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewEditWordAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerViewEditWordAdapter.ViewHolder> () {
	val datas = mutableListOf<RecyclerViewEditWordDataSet>()

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): RecyclerViewEditWordAdapter.ViewHolder {
		val view = LayoutInflater.from(context).inflate(R.layout.recycler_edit_word_item, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int = datas.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(datas[position])
	}

	inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		private val txtEng: TextView = itemView.findViewById(R.id.tv_rv_eng)
		private val txtKor: TextView = itemView.findViewById(R.id.tv_rv_kor)
		private val txtCorrects: TextView = itemView.findViewById(R.id.tv_rv_correct_count)
		private val txtWrongs: TextView = itemView.findViewById(R.id.tv_rv_wrong_count)

		fun bind(item: RecyclerViewEditWordDataSet) {
			txtEng.text = item.eng
			txtKor.text = item.kor
			txtCorrects.text = item.corrects.toString()
			txtWrongs.text = item.wrongs.toString()
		}
	}
}