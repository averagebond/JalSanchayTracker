package com.jalsanchay.tracker.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jalsanchay.tracker.data.RainfallEntry
import com.jalsanchay.tracker.databinding.ItemHistoryBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(
    private val onEdit: (RainfallEntry) -> Unit,
    private val onDelete: (RainfallEntry) -> Unit
) : ListAdapter<RainfallEntry, HistoryAdapter.VH>(DIFF) {

    inner class VH(private val b: ItemHistoryBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(entry: RainfallEntry) {
            // BUG 1 FIX: Convert stored YYYY-MM-DD to DD/MM/YYYY for display
            b.tvDate.text = displayDate(entry.date)
            b.tvRainfall.text = "Rainfall: ${entry.rainfallMm} mm"
            b.tvLitres.text = String.format("%.1f L", entry.litresCollected)
            
            b.btnEdit.setOnClickListener { onEdit(entry) }
            b.btnDelete.setOnClickListener { onDelete(entry) }
        }
    }

    /**
     * Converts internal DB format (YYYY-MM-DD) to UI format (DD/MM/YYYY)
     */
    private fun displayDate(stored: String): String {
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = parser.parse(stored)
            if (date != null) formatter.format(date) else stored
        } catch (e: Exception) {
            stored
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<RainfallEntry>() {
            override fun areItemsTheSame(a: RainfallEntry, b: RainfallEntry) = a.id == b.id
            override fun areContentsTheSame(a: RainfallEntry, b: RainfallEntry) = a == b
        }
    }
}
