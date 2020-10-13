package com.acme.sohunter.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acme.sohunter.R
import com.acme.sohunter.core.domain.Questions
import com.acme.sohunter.presentation.RecyclerAdapter.QuestionHolder


class RecyclerAdapter(context: Context,private val questions: List<Questions>) : RecyclerView.Adapter<QuestionHolder>() {

    private val context = context

      class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

      fun bindItems(questions: Questions) {
          val labels = itemView.findViewById<TextView>(R.id.itemDescription)
          labels.text = questions.title
      }

      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : QuestionHolder {

        val inflatedView = LayoutInflater.from(parent.context).inflate(
            R.layout.question_item,
            parent,
            false
        )

        return QuestionHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.bindItems(questions[position])
        holder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(questions[position].link))
            this.context.startActivity(browserIntent)
        }
    }

    override fun getItemCount(): Int = questions.size

}