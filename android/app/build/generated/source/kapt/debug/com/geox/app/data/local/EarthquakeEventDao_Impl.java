package com.geox.app.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.geox.app.data.model.EarthquakeEvent;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
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
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EarthquakeEventDao_Impl implements EarthquakeEventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EarthquakeEvent> __insertionAdapterOfEarthquakeEvent;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public EarthquakeEventDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEarthquakeEvent = new EntityInsertionAdapter<EarthquakeEvent>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `earthquake_events` (`id`,`time`,`latitude`,`longitude`,`magnitude`,`depth`,`place`,`url`,`source`,`alert`,`magType`,`gap`,`dmin`,`rms`,`net`,`nst`,`updated`,`detail`,`felt`,`cdi`,`mmi`,`alertLevel`,`status`,`tsunami`,`sig`,`code`,`ids`,`sources`,`types`,`ncd`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EarthquakeEvent entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        statement.bindLong(2, entity.getTime());
        statement.bindDouble(3, entity.getLatitude());
        statement.bindDouble(4, entity.getLongitude());
        statement.bindDouble(5, entity.getMagnitude());
        statement.bindDouble(6, entity.getDepth());
        if (entity.getPlace() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getPlace());
        }
        if (entity.getUrl() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getUrl());
        }
        if (entity.getSource() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getSource());
        }
        if (entity.getAlert() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getAlert());
        }
        if (entity.getMagType() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getMagType());
        }
        if (entity.getGap() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getGap());
        }
        if (entity.getDmin() == null) {
          statement.bindNull(13);
        } else {
          statement.bindDouble(13, entity.getDmin());
        }
        if (entity.getRms() == null) {
          statement.bindNull(14);
        } else {
          statement.bindDouble(14, entity.getRms());
        }
        if (entity.getNet() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getNet());
        }
        if (entity.getNst() == null) {
          statement.bindNull(16);
        } else {
          statement.bindLong(16, entity.getNst());
        }
        if (entity.getUpdated() == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.getUpdated());
        }
        if (entity.getDetail() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getDetail());
        }
        if (entity.getFelt() == null) {
          statement.bindNull(19);
        } else {
          statement.bindLong(19, entity.getFelt());
        }
        if (entity.getCdi() == null) {
          statement.bindNull(20);
        } else {
          statement.bindDouble(20, entity.getCdi());
        }
        if (entity.getMmi() == null) {
          statement.bindNull(21);
        } else {
          statement.bindDouble(21, entity.getMmi());
        }
        if (entity.getAlertLevel() == null) {
          statement.bindNull(22);
        } else {
          statement.bindString(22, entity.getAlertLevel());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(23);
        } else {
          statement.bindString(23, entity.getStatus());
        }
        if (entity.getTsunami() == null) {
          statement.bindNull(24);
        } else {
          statement.bindLong(24, entity.getTsunami());
        }
        if (entity.getSig() == null) {
          statement.bindNull(25);
        } else {
          statement.bindLong(25, entity.getSig());
        }
        if (entity.getCode() == null) {
          statement.bindNull(26);
        } else {
          statement.bindString(26, entity.getCode());
        }
        if (entity.getIds() == null) {
          statement.bindNull(27);
        } else {
          statement.bindString(27, entity.getIds());
        }
        if (entity.getSources() == null) {
          statement.bindNull(28);
        } else {
          statement.bindString(28, entity.getSources());
        }
        if (entity.getTypes() == null) {
          statement.bindNull(29);
        } else {
          statement.bindString(29, entity.getTypes());
        }
        if (entity.getNcd() == null) {
          statement.bindNull(30);
        } else {
          statement.bindLong(30, entity.getNcd());
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM earthquake_events";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<EarthquakeEvent> events,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEarthquakeEvent.insert(events);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<EarthquakeEvent>> observeAll() {
    final String _sql = "SELECT * FROM earthquake_events ORDER BY time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"earthquake_events"}, new Callable<List<EarthquakeEvent>>() {
      @Override
      @NonNull
      public List<EarthquakeEvent> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfMagnitude = CursorUtil.getColumnIndexOrThrow(_cursor, "magnitude");
          final int _cursorIndexOfDepth = CursorUtil.getColumnIndexOrThrow(_cursor, "depth");
          final int _cursorIndexOfPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "place");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfAlert = CursorUtil.getColumnIndexOrThrow(_cursor, "alert");
          final int _cursorIndexOfMagType = CursorUtil.getColumnIndexOrThrow(_cursor, "magType");
          final int _cursorIndexOfGap = CursorUtil.getColumnIndexOrThrow(_cursor, "gap");
          final int _cursorIndexOfDmin = CursorUtil.getColumnIndexOrThrow(_cursor, "dmin");
          final int _cursorIndexOfRms = CursorUtil.getColumnIndexOrThrow(_cursor, "rms");
          final int _cursorIndexOfNet = CursorUtil.getColumnIndexOrThrow(_cursor, "net");
          final int _cursorIndexOfNst = CursorUtil.getColumnIndexOrThrow(_cursor, "nst");
          final int _cursorIndexOfUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "updated");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFelt = CursorUtil.getColumnIndexOrThrow(_cursor, "felt");
          final int _cursorIndexOfCdi = CursorUtil.getColumnIndexOrThrow(_cursor, "cdi");
          final int _cursorIndexOfMmi = CursorUtil.getColumnIndexOrThrow(_cursor, "mmi");
          final int _cursorIndexOfAlertLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "alertLevel");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTsunami = CursorUtil.getColumnIndexOrThrow(_cursor, "tsunami");
          final int _cursorIndexOfSig = CursorUtil.getColumnIndexOrThrow(_cursor, "sig");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "code");
          final int _cursorIndexOfIds = CursorUtil.getColumnIndexOrThrow(_cursor, "ids");
          final int _cursorIndexOfSources = CursorUtil.getColumnIndexOrThrow(_cursor, "sources");
          final int _cursorIndexOfTypes = CursorUtil.getColumnIndexOrThrow(_cursor, "types");
          final int _cursorIndexOfNcd = CursorUtil.getColumnIndexOrThrow(_cursor, "ncd");
          final List<EarthquakeEvent> _result = new ArrayList<EarthquakeEvent>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EarthquakeEvent _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final double _tmpMagnitude;
            _tmpMagnitude = _cursor.getDouble(_cursorIndexOfMagnitude);
            final double _tmpDepth;
            _tmpDepth = _cursor.getDouble(_cursorIndexOfDepth);
            final String _tmpPlace;
            if (_cursor.isNull(_cursorIndexOfPlace)) {
              _tmpPlace = null;
            } else {
              _tmpPlace = _cursor.getString(_cursorIndexOfPlace);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpAlert;
            if (_cursor.isNull(_cursorIndexOfAlert)) {
              _tmpAlert = null;
            } else {
              _tmpAlert = _cursor.getString(_cursorIndexOfAlert);
            }
            final String _tmpMagType;
            if (_cursor.isNull(_cursorIndexOfMagType)) {
              _tmpMagType = null;
            } else {
              _tmpMagType = _cursor.getString(_cursorIndexOfMagType);
            }
            final Double _tmpGap;
            if (_cursor.isNull(_cursorIndexOfGap)) {
              _tmpGap = null;
            } else {
              _tmpGap = _cursor.getDouble(_cursorIndexOfGap);
            }
            final Double _tmpDmin;
            if (_cursor.isNull(_cursorIndexOfDmin)) {
              _tmpDmin = null;
            } else {
              _tmpDmin = _cursor.getDouble(_cursorIndexOfDmin);
            }
            final Double _tmpRms;
            if (_cursor.isNull(_cursorIndexOfRms)) {
              _tmpRms = null;
            } else {
              _tmpRms = _cursor.getDouble(_cursorIndexOfRms);
            }
            final String _tmpNet;
            if (_cursor.isNull(_cursorIndexOfNet)) {
              _tmpNet = null;
            } else {
              _tmpNet = _cursor.getString(_cursorIndexOfNet);
            }
            final Integer _tmpNst;
            if (_cursor.isNull(_cursorIndexOfNst)) {
              _tmpNst = null;
            } else {
              _tmpNst = _cursor.getInt(_cursorIndexOfNst);
            }
            final Long _tmpUpdated;
            if (_cursor.isNull(_cursorIndexOfUpdated)) {
              _tmpUpdated = null;
            } else {
              _tmpUpdated = _cursor.getLong(_cursorIndexOfUpdated);
            }
            final String _tmpDetail;
            if (_cursor.isNull(_cursorIndexOfDetail)) {
              _tmpDetail = null;
            } else {
              _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            }
            final Integer _tmpFelt;
            if (_cursor.isNull(_cursorIndexOfFelt)) {
              _tmpFelt = null;
            } else {
              _tmpFelt = _cursor.getInt(_cursorIndexOfFelt);
            }
            final Double _tmpCdi;
            if (_cursor.isNull(_cursorIndexOfCdi)) {
              _tmpCdi = null;
            } else {
              _tmpCdi = _cursor.getDouble(_cursorIndexOfCdi);
            }
            final Double _tmpMmi;
            if (_cursor.isNull(_cursorIndexOfMmi)) {
              _tmpMmi = null;
            } else {
              _tmpMmi = _cursor.getDouble(_cursorIndexOfMmi);
            }
            final String _tmpAlertLevel;
            if (_cursor.isNull(_cursorIndexOfAlertLevel)) {
              _tmpAlertLevel = null;
            } else {
              _tmpAlertLevel = _cursor.getString(_cursorIndexOfAlertLevel);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final Integer _tmpTsunami;
            if (_cursor.isNull(_cursorIndexOfTsunami)) {
              _tmpTsunami = null;
            } else {
              _tmpTsunami = _cursor.getInt(_cursorIndexOfTsunami);
            }
            final Integer _tmpSig;
            if (_cursor.isNull(_cursorIndexOfSig)) {
              _tmpSig = null;
            } else {
              _tmpSig = _cursor.getInt(_cursorIndexOfSig);
            }
            final String _tmpCode;
            if (_cursor.isNull(_cursorIndexOfCode)) {
              _tmpCode = null;
            } else {
              _tmpCode = _cursor.getString(_cursorIndexOfCode);
            }
            final String _tmpIds;
            if (_cursor.isNull(_cursorIndexOfIds)) {
              _tmpIds = null;
            } else {
              _tmpIds = _cursor.getString(_cursorIndexOfIds);
            }
            final String _tmpSources;
            if (_cursor.isNull(_cursorIndexOfSources)) {
              _tmpSources = null;
            } else {
              _tmpSources = _cursor.getString(_cursorIndexOfSources);
            }
            final String _tmpTypes;
            if (_cursor.isNull(_cursorIndexOfTypes)) {
              _tmpTypes = null;
            } else {
              _tmpTypes = _cursor.getString(_cursorIndexOfTypes);
            }
            final Integer _tmpNcd;
            if (_cursor.isNull(_cursorIndexOfNcd)) {
              _tmpNcd = null;
            } else {
              _tmpNcd = _cursor.getInt(_cursorIndexOfNcd);
            }
            _item = new EarthquakeEvent(_tmpId,_tmpTime,_tmpLatitude,_tmpLongitude,_tmpMagnitude,_tmpDepth,_tmpPlace,_tmpUrl,_tmpSource,_tmpAlert,_tmpMagType,_tmpGap,_tmpDmin,_tmpRms,_tmpNet,_tmpNst,_tmpUpdated,_tmpDetail,_tmpFelt,_tmpCdi,_tmpMmi,_tmpAlertLevel,_tmpStatus,_tmpTsunami,_tmpSig,_tmpCode,_tmpIds,_tmpSources,_tmpTypes,_tmpNcd);
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
  public Object getById(final String id, final Continuation<? super EarthquakeEvent> $completion) {
    final String _sql = "SELECT * FROM earthquake_events WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EarthquakeEvent>() {
      @Override
      @Nullable
      public EarthquakeEvent call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfMagnitude = CursorUtil.getColumnIndexOrThrow(_cursor, "magnitude");
          final int _cursorIndexOfDepth = CursorUtil.getColumnIndexOrThrow(_cursor, "depth");
          final int _cursorIndexOfPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "place");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfAlert = CursorUtil.getColumnIndexOrThrow(_cursor, "alert");
          final int _cursorIndexOfMagType = CursorUtil.getColumnIndexOrThrow(_cursor, "magType");
          final int _cursorIndexOfGap = CursorUtil.getColumnIndexOrThrow(_cursor, "gap");
          final int _cursorIndexOfDmin = CursorUtil.getColumnIndexOrThrow(_cursor, "dmin");
          final int _cursorIndexOfRms = CursorUtil.getColumnIndexOrThrow(_cursor, "rms");
          final int _cursorIndexOfNet = CursorUtil.getColumnIndexOrThrow(_cursor, "net");
          final int _cursorIndexOfNst = CursorUtil.getColumnIndexOrThrow(_cursor, "nst");
          final int _cursorIndexOfUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "updated");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfFelt = CursorUtil.getColumnIndexOrThrow(_cursor, "felt");
          final int _cursorIndexOfCdi = CursorUtil.getColumnIndexOrThrow(_cursor, "cdi");
          final int _cursorIndexOfMmi = CursorUtil.getColumnIndexOrThrow(_cursor, "mmi");
          final int _cursorIndexOfAlertLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "alertLevel");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTsunami = CursorUtil.getColumnIndexOrThrow(_cursor, "tsunami");
          final int _cursorIndexOfSig = CursorUtil.getColumnIndexOrThrow(_cursor, "sig");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "code");
          final int _cursorIndexOfIds = CursorUtil.getColumnIndexOrThrow(_cursor, "ids");
          final int _cursorIndexOfSources = CursorUtil.getColumnIndexOrThrow(_cursor, "sources");
          final int _cursorIndexOfTypes = CursorUtil.getColumnIndexOrThrow(_cursor, "types");
          final int _cursorIndexOfNcd = CursorUtil.getColumnIndexOrThrow(_cursor, "ncd");
          final EarthquakeEvent _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final double _tmpMagnitude;
            _tmpMagnitude = _cursor.getDouble(_cursorIndexOfMagnitude);
            final double _tmpDepth;
            _tmpDepth = _cursor.getDouble(_cursorIndexOfDepth);
            final String _tmpPlace;
            if (_cursor.isNull(_cursorIndexOfPlace)) {
              _tmpPlace = null;
            } else {
              _tmpPlace = _cursor.getString(_cursorIndexOfPlace);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpAlert;
            if (_cursor.isNull(_cursorIndexOfAlert)) {
              _tmpAlert = null;
            } else {
              _tmpAlert = _cursor.getString(_cursorIndexOfAlert);
            }
            final String _tmpMagType;
            if (_cursor.isNull(_cursorIndexOfMagType)) {
              _tmpMagType = null;
            } else {
              _tmpMagType = _cursor.getString(_cursorIndexOfMagType);
            }
            final Double _tmpGap;
            if (_cursor.isNull(_cursorIndexOfGap)) {
              _tmpGap = null;
            } else {
              _tmpGap = _cursor.getDouble(_cursorIndexOfGap);
            }
            final Double _tmpDmin;
            if (_cursor.isNull(_cursorIndexOfDmin)) {
              _tmpDmin = null;
            } else {
              _tmpDmin = _cursor.getDouble(_cursorIndexOfDmin);
            }
            final Double _tmpRms;
            if (_cursor.isNull(_cursorIndexOfRms)) {
              _tmpRms = null;
            } else {
              _tmpRms = _cursor.getDouble(_cursorIndexOfRms);
            }
            final String _tmpNet;
            if (_cursor.isNull(_cursorIndexOfNet)) {
              _tmpNet = null;
            } else {
              _tmpNet = _cursor.getString(_cursorIndexOfNet);
            }
            final Integer _tmpNst;
            if (_cursor.isNull(_cursorIndexOfNst)) {
              _tmpNst = null;
            } else {
              _tmpNst = _cursor.getInt(_cursorIndexOfNst);
            }
            final Long _tmpUpdated;
            if (_cursor.isNull(_cursorIndexOfUpdated)) {
              _tmpUpdated = null;
            } else {
              _tmpUpdated = _cursor.getLong(_cursorIndexOfUpdated);
            }
            final String _tmpDetail;
            if (_cursor.isNull(_cursorIndexOfDetail)) {
              _tmpDetail = null;
            } else {
              _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            }
            final Integer _tmpFelt;
            if (_cursor.isNull(_cursorIndexOfFelt)) {
              _tmpFelt = null;
            } else {
              _tmpFelt = _cursor.getInt(_cursorIndexOfFelt);
            }
            final Double _tmpCdi;
            if (_cursor.isNull(_cursorIndexOfCdi)) {
              _tmpCdi = null;
            } else {
              _tmpCdi = _cursor.getDouble(_cursorIndexOfCdi);
            }
            final Double _tmpMmi;
            if (_cursor.isNull(_cursorIndexOfMmi)) {
              _tmpMmi = null;
            } else {
              _tmpMmi = _cursor.getDouble(_cursorIndexOfMmi);
            }
            final String _tmpAlertLevel;
            if (_cursor.isNull(_cursorIndexOfAlertLevel)) {
              _tmpAlertLevel = null;
            } else {
              _tmpAlertLevel = _cursor.getString(_cursorIndexOfAlertLevel);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final Integer _tmpTsunami;
            if (_cursor.isNull(_cursorIndexOfTsunami)) {
              _tmpTsunami = null;
            } else {
              _tmpTsunami = _cursor.getInt(_cursorIndexOfTsunami);
            }
            final Integer _tmpSig;
            if (_cursor.isNull(_cursorIndexOfSig)) {
              _tmpSig = null;
            } else {
              _tmpSig = _cursor.getInt(_cursorIndexOfSig);
            }
            final String _tmpCode;
            if (_cursor.isNull(_cursorIndexOfCode)) {
              _tmpCode = null;
            } else {
              _tmpCode = _cursor.getString(_cursorIndexOfCode);
            }
            final String _tmpIds;
            if (_cursor.isNull(_cursorIndexOfIds)) {
              _tmpIds = null;
            } else {
              _tmpIds = _cursor.getString(_cursorIndexOfIds);
            }
            final String _tmpSources;
            if (_cursor.isNull(_cursorIndexOfSources)) {
              _tmpSources = null;
            } else {
              _tmpSources = _cursor.getString(_cursorIndexOfSources);
            }
            final String _tmpTypes;
            if (_cursor.isNull(_cursorIndexOfTypes)) {
              _tmpTypes = null;
            } else {
              _tmpTypes = _cursor.getString(_cursorIndexOfTypes);
            }
            final Integer _tmpNcd;
            if (_cursor.isNull(_cursorIndexOfNcd)) {
              _tmpNcd = null;
            } else {
              _tmpNcd = _cursor.getInt(_cursorIndexOfNcd);
            }
            _result = new EarthquakeEvent(_tmpId,_tmpTime,_tmpLatitude,_tmpLongitude,_tmpMagnitude,_tmpDepth,_tmpPlace,_tmpUrl,_tmpSource,_tmpAlert,_tmpMagType,_tmpGap,_tmpDmin,_tmpRms,_tmpNet,_tmpNst,_tmpUpdated,_tmpDetail,_tmpFelt,_tmpCdi,_tmpMmi,_tmpAlertLevel,_tmpStatus,_tmpTsunami,_tmpSig,_tmpCode,_tmpIds,_tmpSources,_tmpTypes,_tmpNcd);
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

  @Override
  public Object getCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM earthquake_events";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
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
