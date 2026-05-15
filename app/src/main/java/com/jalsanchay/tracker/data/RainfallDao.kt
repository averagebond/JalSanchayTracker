package com.jalsanchay.tracker.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RainfallDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: RainfallEntry)

    @Update
    suspend fun update(entry: RainfallEntry)

    @Delete
    suspend fun delete(entry: RainfallEntry)

    /**
     * Storing dates as YYYY-MM-DD allows simple string sorting for chronological order.
     */
    @Query("SELECT * FROM rainfall_entries ORDER BY date DESC")
    fun getAllEntries(): LiveData<List<RainfallEntry>>

    @Query("SELECT SUM(litresCollected) FROM rainfall_entries")
    fun getTotalLitres(): LiveData<Double?>

    /**
     * With YYYY-MM-DD storage, filtering by 'YYYY-MM%' prefix correctly grabs the whole month.
     */
    @Query("SELECT SUM(litresCollected) FROM rainfall_entries WHERE date LIKE :prefix || '%'")
    fun getMonthlyLitres(prefix: String): LiveData<Double?>

    @Query("SELECT COUNT(*) FROM rainfall_entries")
    fun getEntryCount(): LiveData<Int>

    @Query("SELECT * FROM rainfall_entries WHERE date = :date LIMIT 1")
    suspend fun getEntryByDate(date: String): RainfallEntry?
}
