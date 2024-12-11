package com.example.schedulerapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulerapp.R
import com.example.schedulerapp.model.ScheduleItem

class ScheduleAdapter(private val items: MutableList<ScheduleItem>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val checkDone: CheckBox = view.findViewById(R.id.checkDone)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvTitle.text = item.title
        holder.tvDate.text = item.date
        holder.tvTime.text = item.time
        holder.checkDone.isChecked = item.done

        holder.checkDone.setOnCheckedChangeListener { _, isChecked ->
            item.done = isChecked
        }

        holder.btnDelete.setOnClickListener {
            removeItem(position)
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: ScheduleItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun removeItem(position: Int) {
        if (position in items.indices) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
