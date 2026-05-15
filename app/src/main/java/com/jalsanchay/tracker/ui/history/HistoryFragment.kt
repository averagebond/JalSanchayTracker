package com.jalsanchay.tracker.ui.history

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jalsanchay.tracker.R
import com.jalsanchay.tracker.RainfallViewModel
import com.jalsanchay.tracker.data.RainfallEntry
import com.jalsanchay.tracker.databinding.FragmentHistoryBinding
import com.jalsanchay.tracker.databinding.DialogAddEditEntryBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val vm: RainfallViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val adapter = HistoryAdapter(
            onEdit = { showAddEditDialog(it) },
            onDelete = { showDeleteConfirmation(it) }
        )
        
        binding.rvHistory.adapter = adapter
        vm.allEntries.observe(viewLifecycleOwner) { adapter.submitList(it) }

        binding.fabAdd.setOnClickListener {
            showAddEditDialog(null)
        }
    }

    private fun showAddEditDialog(entry: RainfallEntry?) {
        val dialogBinding = DialogAddEditEntryBinding.inflate(layoutInflater)
        
        val pref = requireActivity().getSharedPreferences("setup", 0)
        val defaultArea = pref.getFloat("area", 120f).toDouble()
        val defaultTank = pref.getFloat("tank", 5000f).toDouble()

        if (entry != null) {
            // BUG 1 FIX: Convert stored YYYY-MM-DD to DD/MM/YYYY for the EditText
            dialogBinding.etDate.setText(toDisplayDate(entry.date))
            dialogBinding.etRainfall.setText(entry.rainfallMm.toString())
            dialogBinding.etArea.setText(entry.areaM2.toString())
            dialogBinding.etRunoff.setText(entry.runoffCoeff.toString())
            dialogBinding.etTank.setText(entry.tankCapacity.toString())
        } else {
            // Default to today in DD/MM/YYYY
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            dialogBinding.etDate.setText(sdf.format(Date()))
            dialogBinding.etArea.setText(defaultArea.toString())
            dialogBinding.etRunoff.setText("0.8")
            dialogBinding.etTank.setText(defaultTank.toString())
        }

        AlertDialog.Builder(requireContext())
            .setTitle(if (entry == null) "Log Rainfall" else "Edit Entry")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                val displayDateStr = dialogBinding.etDate.text.toString()
                // BUG 1 FIX: Convert DD/MM/YYYY from UI to YYYY-MM-DD for DB storage
                val storedDateStr = toStoredDate(displayDateStr)
                
                val rain = dialogBinding.etRainfall.text.toString().toDoubleOrNull()
                val area = dialogBinding.etArea.text.toString().toDoubleOrNull()
                val runoff = dialogBinding.etRunoff.text.toString().toDoubleOrNull()
                val tank = dialogBinding.etTank.text.toString().toDoubleOrNull()

                if (storedDateStr.isNotEmpty() && rain != null && area != null && runoff != null && tank != null) {
                    val collected = vm.calculate(area, rain, runoff)
                    val newEntry = if (entry == null) {
                        RainfallEntry(
                            date = storedDateStr,
                            rainfallMm = rain,
                            areaM2 = area,
                            runoffCoeff = runoff,
                            litresCollected = collected,
                            tankCapacity = tank
                        )
                    } else {
                        entry.copy(
                            date = storedDateStr,
                            rainfallMm = rain,
                            areaM2 = area,
                            runoffCoeff = runoff,
                            litresCollected = collected,
                            tankCapacity = tank
                        )
                    }
                    
                    if (entry == null) vm.insert(newEntry) else vm.update(newEntry)
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Please enter valid values (Date: DD/MM/YYYY)", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun toStoredDate(displayDate: String): String {
        return try {
            val parser = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = parser.parse(displayDate)
            if (date != null) formatter.format(date) else ""
        } catch (e: Exception) { "" }
    }

    private fun toDisplayDate(storedDate: String): String {
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = parser.parse(storedDate)
            if (date != null) formatter.format(date) else storedDate
        } catch (e: Exception) { storedDate }
    }

    private fun showDeleteConfirmation(entry: RainfallEntry) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Entry")
            .setMessage("Are you sure you want to delete this log?")
            .setPositiveButton("Delete") { _, _ ->
                vm.delete(entry)
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
