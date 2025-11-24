package com.geox.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile EarthquakeEventDao _earthquakeEventDao;

  private volatile DisasterEventDao _disasterEventDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `earthquake_events` (`id` TEXT NOT NULL, `time` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `magnitude` REAL NOT NULL, `depth` REAL NOT NULL, `place` TEXT NOT NULL, `url` TEXT NOT NULL, `source` TEXT NOT NULL, `alert` TEXT, `magType` TEXT, `gap` REAL, `dmin` REAL, `rms` REAL, `net` TEXT, `nst` INTEGER, `updated` INTEGER, `detail` TEXT, `felt` INTEGER, `cdi` REAL, `mmi` REAL, `alertLevel` TEXT, `status` TEXT, `tsunami` INTEGER, `sig` INTEGER, `code` TEXT, `ids` TEXT, `sources` TEXT, `types` TEXT, `ncd` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `disaster_events` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT, `category` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `date` INTEGER NOT NULL, `url` TEXT, `source` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fdf8350c8ef49ea2c535bbcb3ca3f373')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `earthquake_events`");
        db.execSQL("DROP TABLE IF EXISTS `disaster_events`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsEarthquakeEvents = new HashMap<String, TableInfo.Column>(30);
        _columnsEarthquakeEvents.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("magnitude", new TableInfo.Column("magnitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("depth", new TableInfo.Column("depth", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("place", new TableInfo.Column("place", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("alert", new TableInfo.Column("alert", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("magType", new TableInfo.Column("magType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("gap", new TableInfo.Column("gap", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("dmin", new TableInfo.Column("dmin", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("rms", new TableInfo.Column("rms", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("net", new TableInfo.Column("net", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("nst", new TableInfo.Column("nst", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("updated", new TableInfo.Column("updated", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("detail", new TableInfo.Column("detail", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("felt", new TableInfo.Column("felt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("cdi", new TableInfo.Column("cdi", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("mmi", new TableInfo.Column("mmi", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("alertLevel", new TableInfo.Column("alertLevel", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("tsunami", new TableInfo.Column("tsunami", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("sig", new TableInfo.Column("sig", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("code", new TableInfo.Column("code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("ids", new TableInfo.Column("ids", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("sources", new TableInfo.Column("sources", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("types", new TableInfo.Column("types", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEarthquakeEvents.put("ncd", new TableInfo.Column("ncd", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEarthquakeEvents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEarthquakeEvents = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEarthquakeEvents = new TableInfo("earthquake_events", _columnsEarthquakeEvents, _foreignKeysEarthquakeEvents, _indicesEarthquakeEvents);
        final TableInfo _existingEarthquakeEvents = TableInfo.read(db, "earthquake_events");
        if (!_infoEarthquakeEvents.equals(_existingEarthquakeEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "earthquake_events(com.geox.app.data.model.EarthquakeEvent).\n"
                  + " Expected:\n" + _infoEarthquakeEvents + "\n"
                  + " Found:\n" + _existingEarthquakeEvents);
        }
        final HashMap<String, TableInfo.Column> _columnsDisasterEvents = new HashMap<String, TableInfo.Column>(9);
        _columnsDisasterEvents.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisasterEvents.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisasterEvents.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisasterEvents.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisasterEvents.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisasterEvents.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisasterEvents.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisasterEvents.put("url", new TableInfo.Column("url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisasterEvents.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDisasterEvents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDisasterEvents = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDisasterEvents = new TableInfo("disaster_events", _columnsDisasterEvents, _foreignKeysDisasterEvents, _indicesDisasterEvents);
        final TableInfo _existingDisasterEvents = TableInfo.read(db, "disaster_events");
        if (!_infoDisasterEvents.equals(_existingDisasterEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "disaster_events(com.geox.app.data.model.DisasterEvent).\n"
                  + " Expected:\n" + _infoDisasterEvents + "\n"
                  + " Found:\n" + _existingDisasterEvents);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fdf8350c8ef49ea2c535bbcb3ca3f373", "cfd9c2095c739094b18fc9f82610efbc");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "earthquake_events","disaster_events");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `earthquake_events`");
      _db.execSQL("DELETE FROM `disaster_events`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(EarthquakeEventDao.class, EarthquakeEventDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DisasterEventDao.class, DisasterEventDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public EarthquakeEventDao earthquakeEventDao() {
    if (_earthquakeEventDao != null) {
      return _earthquakeEventDao;
    } else {
      synchronized(this) {
        if(_earthquakeEventDao == null) {
          _earthquakeEventDao = new EarthquakeEventDao_Impl(this);
        }
        return _earthquakeEventDao;
      }
    }
  }

  @Override
  public DisasterEventDao disasterEventDao() {
    if (_disasterEventDao != null) {
      return _disasterEventDao;
    } else {
      synchronized(this) {
        if(_disasterEventDao == null) {
          _disasterEventDao = new DisasterEventDao_Impl(this);
        }
        return _disasterEventDao;
      }
    }
  }
}
