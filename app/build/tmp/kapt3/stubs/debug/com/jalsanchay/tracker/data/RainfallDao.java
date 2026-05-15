package com.jalsanchay.tracker.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\bH\'J\u0018\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\b2\u0006\u0010\u0012\u001a\u00020\fH\'J\u0010\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\bH\'J\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0016"}, d2 = {"Lcom/jalsanchay/tracker/data/RainfallDao;", "", "delete", "", "entry", "Lcom/jalsanchay/tracker/data/RainfallEntry;", "(Lcom/jalsanchay/tracker/data/RainfallEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllEntries", "Landroidx/lifecycle/LiveData;", "", "getEntryByDate", "date", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEntryCount", "", "getMonthlyLitres", "", "prefix", "getTotalLitres", "insert", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface RainfallDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.jalsanchay.tracker.data.RainfallEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.jalsanchay.tracker.data.RainfallEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.jalsanchay.tracker.data.RainfallEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Storing dates as YYYY-MM-DD allows simple string sorting for chronological order.
     */
    @androidx.room.Query(value = "SELECT * FROM rainfall_entries ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.jalsanchay.tracker.data.RainfallEntry>> getAllEntries();
    
    @androidx.room.Query(value = "SELECT SUM(litresCollected) FROM rainfall_entries")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTotalLitres();
    
    /**
     * With YYYY-MM-DD storage, filtering by 'YYYY-MM%' prefix correctly grabs the whole month.
     */
    @androidx.room.Query(value = "SELECT SUM(litresCollected) FROM rainfall_entries WHERE date LIKE :prefix || \'%\'")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getMonthlyLitres(@org.jetbrains.annotations.NotNull()
    java.lang.String prefix);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM rainfall_entries")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getEntryCount();
    
    @androidx.room.Query(value = "SELECT * FROM rainfall_entries WHERE date = :date LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEntryByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.jalsanchay.tracker.data.RainfallEntry> $completion);
}