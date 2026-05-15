package com.jalsanchay.tracker.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RainfallDao_Impl implements RainfallDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RainfallEntry> __insertionAdapterOfRainfallEntry;

  private final EntityDeletionOrUpdateAdapter<RainfallEntry> __deletionAdapterOfRainfallEntry;

  private final EntityDeletionOrUpdateAdapter<RainfallEntry> __updateAdapterOfRainfallEntry;

  public RainfallDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRainfallEntry = new EntityInsertionAdapter<RainfallEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `rainfall_entries` (`id`,`date`,`rainfallMm`,`areaM2`,`runoffCoeff`,`litresCollected`,`tankCapacity`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RainfallEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getDate() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDate());
        }
        statement.bindDouble(3, entity.getRainfallMm());
        statement.bindDouble(4, entity.getAreaM2());
        statement.bindDouble(5, entity.getRunoffCoeff());
        statement.bindDouble(6, entity.getLitresCollected());
        statement.bindDouble(7, entity.getTankCapacity());
      }
    };
    this.__deletionAdapterOfRainfallEntry = new EntityDeletionOrUpdateAdapter<RainfallEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `rainfall_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RainfallEntry entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRainfallEntry = new EntityDeletionOrUpdateAdapter<RainfallEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `rainfall_entries` SET `id` = ?,`date` = ?,`rainfallMm` = ?,`areaM2` = ?,`runoffCoeff` = ?,`litresCollected` = ?,`tankCapacity` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RainfallEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getDate() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDate());
        }
        statement.bindDouble(3, entity.getRainfallMm());
        statement.bindDouble(4, entity.getAreaM2());
        statement.bindDouble(5, entity.getRunoffCoeff());
        statement.bindDouble(6, entity.getLitresCollected());
        statement.bindDouble(7, entity.getTankCapacity());
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final RainfallEntry entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRainfallEntry.insert(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final RainfallEntry entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRainfallEntry.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final RainfallEntry entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRainfallEntry.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<RainfallEntry>> getAllEntries() {
    final String _sql = "SELECT * FROM rainfall_entries ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"rainfall_entries"}, false, new Callable<List<RainfallEntry>>() {
      @Override
      @Nullable
      public List<RainfallEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfRainfallMm = CursorUtil.getColumnIndexOrThrow(_cursor, "rainfallMm");
          final int _cursorIndexOfAreaM2 = CursorUtil.getColumnIndexOrThrow(_cursor, "areaM2");
          final int _cursorIndexOfRunoffCoeff = CursorUtil.getColumnIndexOrThrow(_cursor, "runoffCoeff");
          final int _cursorIndexOfLitresCollected = CursorUtil.getColumnIndexOrThrow(_cursor, "litresCollected");
          final int _cursorIndexOfTankCapacity = CursorUtil.getColumnIndexOrThrow(_cursor, "tankCapacity");
          final List<RainfallEntry> _result = new ArrayList<RainfallEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RainfallEntry _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final double _tmpRainfallMm;
            _tmpRainfallMm = _cursor.getDouble(_cursorIndexOfRainfallMm);
            final double _tmpAreaM2;
            _tmpAreaM2 = _cursor.getDouble(_cursorIndexOfAreaM2);
            final double _tmpRunoffCoeff;
            _tmpRunoffCoeff = _cursor.getDouble(_cursorIndexOfRunoffCoeff);
            final double _tmpLitresCollected;
            _tmpLitresCollected = _cursor.getDouble(_cursorIndexOfLitresCollected);
            final double _tmpTankCapacity;
            _tmpTankCapacity = _cursor.getDouble(_cursorIndexOfTankCapacity);
            _item = new RainfallEntry(_tmpId,_tmpDate,_tmpRainfallMm,_tmpAreaM2,_tmpRunoffCoeff,_tmpLitresCollected,_tmpTankCapacity);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getTotalLitres() {
    final String _sql = "SELECT SUM(litresCollected) FROM rainfall_entries";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"rainfall_entries"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getMonthlyLitres(final String prefix) {
    final String _sql = "SELECT SUM(litresCollected) FROM rainfall_entries WHERE date LIKE ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (prefix == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, prefix);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"rainfall_entries"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Integer> getEntryCount() {
    final String _sql = "SELECT COUNT(*) FROM rainfall_entries";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"rainfall_entries"}, false, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getEntryByDate(final String date,
      final Continuation<? super RainfallEntry> $completion) {
    final String _sql = "SELECT * FROM rainfall_entries WHERE date = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RainfallEntry>() {
      @Override
      @Nullable
      public RainfallEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfRainfallMm = CursorUtil.getColumnIndexOrThrow(_cursor, "rainfallMm");
          final int _cursorIndexOfAreaM2 = CursorUtil.getColumnIndexOrThrow(_cursor, "areaM2");
          final int _cursorIndexOfRunoffCoeff = CursorUtil.getColumnIndexOrThrow(_cursor, "runoffCoeff");
          final int _cursorIndexOfLitresCollected = CursorUtil.getColumnIndexOrThrow(_cursor, "litresCollected");
          final int _cursorIndexOfTankCapacity = CursorUtil.getColumnIndexOrThrow(_cursor, "tankCapacity");
          final RainfallEntry _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final double _tmpRainfallMm;
            _tmpRainfallMm = _cursor.getDouble(_cursorIndexOfRainfallMm);
            final double _tmpAreaM2;
            _tmpAreaM2 = _cursor.getDouble(_cursorIndexOfAreaM2);
            final double _tmpRunoffCoeff;
            _tmpRunoffCoeff = _cursor.getDouble(_cursorIndexOfRunoffCoeff);
            final double _tmpLitresCollected;
            _tmpLitresCollected = _cursor.getDouble(_cursorIndexOfLitresCollected);
            final double _tmpTankCapacity;
            _tmpTankCapacity = _cursor.getDouble(_cursorIndexOfTankCapacity);
            _result = new RainfallEntry(_tmpId,_tmpDate,_tmpRainfallMm,_tmpAreaM2,_tmpRunoffCoeff,_tmpLitresCollected,_tmpTankCapacity);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
