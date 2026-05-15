package com.jalsanchay.tracker

import android.app.Application
import androidx.lifecycle.*
import com.jalsanchay.tracker.data.AppDatabase
import com.jalsanchay.tracker.data.RainfallEntry
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RainfallViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = AppDatabase.getDatabase(app).rainfallDao()

    val allEntries = dao.getAllEntries()
    val totalLitres = dao.getTotalLitres()
    val entryCount = dao.getEntryCount()

    fun getMonthlyLitres(prefix: String) = dao.getMonthlyLitres(prefix)

    fun insert(entry: RainfallEntry) = viewModelScope.launch { dao.insert(entry) }
    fun update(entry: RainfallEntry) = viewModelScope.launch { dao.update(entry) }
    fun delete(entry: RainfallEntry) = viewModelScope.launch { dao.delete(entry) }

    fun currentMonthPrefix(): String = SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(Date())
    
    fun currentDate(): String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    fun last7Days(): List<String> {
        val dates = mutableListOf<String>()
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        for (i in 0..6) {
            dates.add(sdf.format(cal.time))
            cal.add(Calendar.DATE, -1)
        }
        return dates.reversed()
    }

    /** 
     * Formula: Area (m²) × Rainfall (mm) × 0.0929 × Runoff Coefficient = Litres 
     * Note: 0.0929 is used if Area is in sq ft, but requirement specified it even for metric.
     */
    fun calculate(area: Double, rain: Double, runoff: Double) = area * rain * 0.0929 * runoff

    fun efficiency(litres: Double, area: Double, rain: Double): Int {
        val maxPotential = area * rain * 0.0929
        return if (maxPotential <= 0.0) 0 else ((litres / maxPotential) * 100).toInt().coerceIn(0, 100)
    }
}
