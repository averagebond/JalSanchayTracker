package com.jalsanchay.tracker;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0011J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\bJ\u001e\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011J\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00062\u0006\u0010 \u001a\u00020\u0018J\u000e\u0010!\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\bJ\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00180\u0007J\u000e\u0010#\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\nR\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\n\u00a8\u0006$"}, d2 = {"Lcom/jalsanchay/tracker/RainfallViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "(Landroid/app/Application;)V", "allEntries", "Landroidx/lifecycle/LiveData;", "", "Lcom/jalsanchay/tracker/data/RainfallEntry;", "getAllEntries", "()Landroidx/lifecycle/LiveData;", "dao", "Lcom/jalsanchay/tracker/data/RainfallDao;", "entryCount", "", "getEntryCount", "totalLitres", "", "getTotalLitres", "calculate", "area", "rain", "runoff", "currentDate", "", "currentMonthPrefix", "delete", "Lkotlinx/coroutines/Job;", "entry", "efficiency", "litres", "getMonthlyLitres", "prefix", "insert", "last7Days", "update", "app_debug"})
public final class RainfallViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.jalsanchay.tracker.data.RainfallDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.jalsanchay.tracker.data.RainfallEntry>> allEntries = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalLitres = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> entryCount = null;
    
    public RainfallViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.jalsanchay.tracker.data.RainfallEntry>> getAllEntries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalLitres() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getEntryCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getMonthlyLitres(@org.jetbrains.annotations.NotNull()
    java.lang.String prefix) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insert(@org.jetbrains.annotations.NotNull()
    com.jalsanchay.tracker.data.RainfallEntry entry) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job update(@org.jetbrains.annotations.NotNull()
    com.jalsanchay.tracker.data.RainfallEntry entry) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job delete(@org.jetbrains.annotations.NotNull()
    com.jalsanchay.tracker.data.RainfallEntry entry) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentMonthPrefix() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> last7Days() {
        return null;
    }
    
    /**
     * Formula: Area (m²) × Rainfall (mm) × 0.0929 × Runoff Coefficient = Litres 
     * Note: 0.0929 is used if Area is in sq ft, but requirement specified it even for metric.
     */
    public final double calculate(double area, double rain, double runoff) {
        return 0.0;
    }
    
    public final int efficiency(double litres, double area, double rain) {
        return 0;
    }
}