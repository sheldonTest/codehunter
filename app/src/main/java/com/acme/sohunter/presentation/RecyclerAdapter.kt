package com.acme.sohunter.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acme.sohunter.R
import com.acme.sohunter.core.domain.Questions
import com.acme.sohunter.presentation.RecyclerAdapter.*

class RecyclerAdapter(private val questions: List<Questions>) : RecyclerView.Adapter<QuestionHolder>() {

      class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

      fun bindItems(questions: Questions) {
          val labels = itemView.findViewById<TextView>(R.id.itemDescription)
          labels.text = questions.title
      }

      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : QuestionHolder {

        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.question_item,parent,false)

        return QuestionHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.bindItems(questions[position])
    }

    override fun getItemCount(): Int = questions.size

}