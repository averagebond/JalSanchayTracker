package com.jalsanchay.tracker.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.jalsanchay.tracker.RainfallViewModel
import com.jalsanchay.tracker.data.RainfallEntry
import com.jalsanchay.tracker.databinding.FragmentDashboardBinding
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val vm: RainfallViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Month label
        val sdf = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        binding.tvMonthLabel.text = "Your conservation performance for ${sdf.format(Date())}"

        // Observe total water (Total water saved card)
        vm.totalLitres.observe(viewLifecycleOwner) { total ->
            val t = total ?: 0.0
            binding.tvTotalWater.text = String.format("%,.0f", t)
            binding.tvProjected.text = String.format("%.1fk L", (t * 1.2) / 1000)
            
            // Update tank progress based on total collected vs tank capacity
            updateTankVisualization(t)
        }

        // Observe monthly total (Daily Avg card)
        vm.getMonthlyLitres(vm.currentMonthPrefix()).observe(viewLifecycleOwner) { monthly ->
            val m = monthly ?: 0.0
            binding.tvDailyAvg.text = String.format("%.0fL", m / 30.0)
        }

        // Observe all entries to build charts
        vm.allEntries.observe(viewLifecycleOwner) { entries ->
            setupBarChart(entries)
            setupLineChart(entries.takeLast(14))
            
            if (entries.isNotEmpty()) {
                val last = entries.first()
                val eff = vm.efficiency(last.litresCollected, last.areaM2, last.rainfallMm)
                binding.tvEfficiency.text = "$eff%"
            } else {
                binding.tvEfficiency.text = "0%"
            }
        }

        // Pre-fill saved config
        val pref = requireActivity().getSharedPreferences("setup", 0)
        binding.etArea.setText(pref.getFloat("area", 120f).toInt().toString())
        binding.etTankCapacity.setText(pref.getFloat("tank", 5000f).toInt().toString())

        // Update config
        binding.btnUpdateConfig.setOnClickListener {
            val area = binding.etArea.text.toString().toDoubleOrNull()
            val tank = binding.etTankCapacity.text.toString().toDoubleOrNull()
            if (area == null || tank == null || area <= 0 || tank <= 0) {
                Toast.makeText(requireContext(), "Enter valid values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            pref.edit()
                .putFloat("area", area.toFloat())
                .putFloat("tank", tank.toFloat())
                .apply()
            
            // Re-update tank visualization with new capacity
            vm.totalLitres.value?.let { updateTankVisualization(it) }
            
            Toast.makeText(requireContext(), "Configuration updated!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateTankVisualization(totalLitres: Double) {
        val pref = requireActivity().getSharedPreferences("setup", 0)
        val tankCapacity = pref.getFloat("tank", 5000f).toDouble()
        
        val percentage = if (tankCapacity > 0) {
            ((totalLitres / tankCapacity) * 100).toInt().coerceIn(0, 100)
        } else 0
        
        binding.tankProgressBar.progress = percentage
        binding.tvTankPercentage.text = "$percentage% full"
    }

    private fun setupBarChart(entries: List<RainfallEntry>) {
        val chart = binding.barChart
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        chart.setDrawGridBackground(false)
        chart.axisRight.isEnabled = false
        chart.axisLeft.apply {
            textColor = Color.parseColor("#6B7280")
            gridColor = Color.parseColor("#E5E7EB")
            axisLineColor = Color.TRANSPARENT
        }

        val last7Dates = vm.last7Days()
        val barEntries = mutableListOf<BarEntry>()
        val labels = mutableListOf<String>()

        val daySdf = SimpleDateFormat("EEE", Locale.getDefault())
        val dateParser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        last7Dates.forEachIndexed { index, dateStr ->
            val entry = entries.find { it.date == dateStr }
            val value = entry?.litresCollected?.toFloat() ?: 0f
            barEntries.add(BarEntry(index.toFloat(), value))
            
            try {
                val date = dateParser.parse(dateStr)
                labels.add(if (date != null) daySdf.format(date) else "")
            } catch (e: Exception) { labels.add("") }
        }

        val ds = BarDataSet(barEntries, "").apply {
            color = Color.parseColor("#1A6B6B")
            setDrawValues(false)
        }
        chart.data = BarData(ds).apply { barWidth = 0.6f }

        chart.xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(labels)
            position = XAxis.XAxisPosition.BOTTOM
            textColor = Color.parseColor("#6B7280")
            gridColor = Color.TRANSPARENT
            axisLineColor = Color.TRANSPARENT
            granularity = 1f
        }
        chart.invalidate()
    }

    private fun setupLineChart(entries: List<RainfallEntry>) {
        val chart = binding.lineChart
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        chart.setDrawGridBackground(false)
        chart.axisRight.isEnabled = false
        chart.axisLeft.apply {
            textColor = Color.parseColor("#6B7280")
            gridColor = Color.parseColor("#E5E7EB")
        }
        chart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textColor = Color.parseColor("#6B7280")
            gridColor = Color.TRANSPARENT
        }

        val lineEntries = if (entries.isEmpty()) {
            (0..7).map { Entry(it.toFloat(), 0f) }
        } else {
            entries.reversed().takeLast(14).mapIndexed { i, e -> Entry(i.toFloat(), e.litresCollected.toFloat()) }
        }

        val ds = LineDataSet(lineEntries, "").apply {
            color = Color.parseColor("#1A6B6B")
            lineWidth = 2f
            setDrawCircles(true)
            circleRadius = 4f
            setCircleColor(Color.parseColor("#1A6B6B"))
            setDrawValues(false)
            setDrawFilled(true)
            fillColor = Color.parseColor("#1A6B6B")
            fillAlpha = 30
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }
        chart.data = LineData(ds)
        chart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
