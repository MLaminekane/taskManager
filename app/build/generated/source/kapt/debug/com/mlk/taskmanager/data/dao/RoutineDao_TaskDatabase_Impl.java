package com.mlk.taskmanager.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mlk.taskmanager.data.converter.DayOfWeekConverter;
import com.mlk.taskmanager.data.model.Routine;
import com.mlk.taskmanager.data.util.Converters;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.DayOfWeek;
import java.time.LocalTime;
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
public final class RoutineDao_TaskDatabase_Impl implements RoutineDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Routine> __insertionAdapterOfRoutine;

  private final Converters __converters = new Converters();

  private final DayOfWeekConverter __dayOfWeekConverter = new DayOfWeekConverter();

  private final EntityDeletionOrUpdateAdapter<Routine> __deletionAdapterOfRoutine;

  private final EntityDeletionOrUpdateAdapter<Routine> __updateAdapterOfRoutine;

  public RoutineDao_TaskDatabase_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRoutine = new EntityInsertionAdapter<Routine>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `routines` (`id`,`title`,`description`,`time`,`repeatDays`,`isEnabled`,`category`,`isLocationBased`,`latitude`,`longitude`,`locationRadius`,`calendarEventId`,`isSyncedWithCalendar`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Routine entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        final String _tmp = __converters.timeToString(entity.getTime());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final String _tmp_1 = __dayOfWeekConverter.fromDayOfWeekList(entity.getRepeatDays());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final int _tmp_2 = entity.isEnabled() ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        final int _tmp_3 = entity.isLocationBased() ? 1 : 0;
        statement.bindLong(8, _tmp_3);
        if (entity.getLatitude() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(10);
        } else {
          statement.bindDouble(10, entity.getLongitude());
        }
        if (entity.getLocationRadius() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getLocationRadius());
        }
        if (entity.getCalendarEventId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getCalendarEventId());
        }
        final int _tmp_4 = entity.isSyncedWithCalendar() ? 1 : 0;
        statement.bindLong(13, _tmp_4);
      }
    };
    this.__deletionAdapterOfRoutine = new EntityDeletionOrUpdateAdapter<Routine>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `routines` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Routine entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRoutine = new EntityDeletionOrUpdateAdapter<Routine>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `routines` SET `id` = ?,`title` = ?,`description` = ?,`time` = ?,`repeatDays` = ?,`isEnabled` = ?,`category` = ?,`isLocationBased` = ?,`latitude` = ?,`longitude` = ?,`locationRadius` = ?,`calendarEventId` = ?,`isSyncedWithCalendar` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Routine entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        final String _tmp = __converters.timeToString(entity.getTime());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final String _tmp_1 = __dayOfWeekConverter.fromDayOfWeekList(entity.getRepeatDays());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final int _tmp_2 = entity.isEnabled() ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        final int _tmp_3 = entity.isLocationBased() ? 1 : 0;
        statement.bindLong(8, _tmp_3);
        if (entity.getLatitude() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(10);
        } else {
          statement.bindDouble(10, entity.getLongitude());
        }
        if (entity.getLocationRadius() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getLocationRadius());
        }
        if (entity.getCalendarEventId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getCalendarEventId());
        }
        final int _tmp_4 = entity.isSyncedWithCalendar() ? 1 : 0;
        statement.bindLong(13, _tmp_4);
        statement.bindLong(14, entity.getId());
      }
    };
  }

  @Override
  public Object insertRoutine(final Routine routine, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRoutine.insertAndReturnId(routine);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRoutine(final Routine routine, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRoutine.handle(routine);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRoutine(final Routine routine, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRoutine.handle(routine);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Routine>> getAllRoutines() {
    final String _sql = "SELECT * FROM routines ORDER BY time ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"routines"}, new Callable<List<Routine>>() {
      @Override
      @NonNull
      public List<Routine> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsLocationBased = CursorUtil.getColumnIndexOrThrow(_cursor, "isLocationBased");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationRadius = CursorUtil.getColumnIndexOrThrow(_cursor, "locationRadius");
          final int _cursorIndexOfCalendarEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "calendarEventId");
          final int _cursorIndexOfIsSyncedWithCalendar = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedWithCalendar");
          final List<Routine> _result = new ArrayList<Routine>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Routine _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final LocalTime _tmpTime;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTime);
            }
            _tmpTime = __converters.fromTimeString(_tmp);
            final List<DayOfWeek> _tmpRepeatDays;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            _tmpRepeatDays = __dayOfWeekConverter.toDayOfWeekList(_tmp_1);
            final boolean _tmpIsEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_2 != 0;
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsLocationBased;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsLocationBased);
            _tmpIsLocationBased = _tmp_3 != 0;
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final Float _tmpLocationRadius;
            if (_cursor.isNull(_cursorIndexOfLocationRadius)) {
              _tmpLocationRadius = null;
            } else {
              _tmpLocationRadius = _cursor.getFloat(_cursorIndexOfLocationRadius);
            }
            final String _tmpCalendarEventId;
            if (_cursor.isNull(_cursorIndexOfCalendarEventId)) {
              _tmpCalendarEventId = null;
            } else {
              _tmpCalendarEventId = _cursor.getString(_cursorIndexOfCalendarEventId);
            }
            final boolean _tmpIsSyncedWithCalendar;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsSyncedWithCalendar);
            _tmpIsSyncedWithCalendar = _tmp_4 != 0;
            _item = new Routine(_tmpId,_tmpTitle,_tmpDescription,_tmpTime,_tmpRepeatDays,_tmpIsEnabled,_tmpCategory,_tmpIsLocationBased,_tmpLatitude,_tmpLongitude,_tmpLocationRadius,_tmpCalendarEventId,_tmpIsSyncedWithCalendar);
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
  public Flow<List<Routine>> getActiveRoutines() {
    final String _sql = "SELECT * FROM routines WHERE isEnabled = 1 ORDER BY time ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"routines"}, new Callable<List<Routine>>() {
      @Override
      @NonNull
      public List<Routine> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsLocationBased = CursorUtil.getColumnIndexOrThrow(_cursor, "isLocationBased");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationRadius = CursorUtil.getColumnIndexOrThrow(_cursor, "locationRadius");
          final int _cursorIndexOfCalendarEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "calendarEventId");
          final int _cursorIndexOfIsSyncedWithCalendar = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedWithCalendar");
          final List<Routine> _result = new ArrayList<Routine>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Routine _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final LocalTime _tmpTime;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTime);
            }
            _tmpTime = __converters.fromTimeString(_tmp);
            final List<DayOfWeek> _tmpRepeatDays;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            _tmpRepeatDays = __dayOfWeekConverter.toDayOfWeekList(_tmp_1);
            final boolean _tmpIsEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_2 != 0;
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsLocationBased;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsLocationBased);
            _tmpIsLocationBased = _tmp_3 != 0;
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final Float _tmpLocationRadius;
            if (_cursor.isNull(_cursorIndexOfLocationRadius)) {
              _tmpLocationRadius = null;
            } else {
              _tmpLocationRadius = _cursor.getFloat(_cursorIndexOfLocationRadius);
            }
            final String _tmpCalendarEventId;
            if (_cursor.isNull(_cursorIndexOfCalendarEventId)) {
              _tmpCalendarEventId = null;
            } else {
              _tmpCalendarEventId = _cursor.getString(_cursorIndexOfCalendarEventId);
            }
            final boolean _tmpIsSyncedWithCalendar;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsSyncedWithCalendar);
            _tmpIsSyncedWithCalendar = _tmp_4 != 0;
            _item = new Routine(_tmpId,_tmpTitle,_tmpDescription,_tmpTime,_tmpRepeatDays,_tmpIsEnabled,_tmpCategory,_tmpIsLocationBased,_tmpLatitude,_tmpLongitude,_tmpLocationRadius,_tmpCalendarEventId,_tmpIsSyncedWithCalendar);
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
  public Object getRoutineById(final long routineId,
      final Continuation<? super Routine> $completion) {
    final String _sql = "SELECT * FROM routines WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, routineId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Routine>() {
      @Override
      @Nullable
      public Routine call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsLocationBased = CursorUtil.getColumnIndexOrThrow(_cursor, "isLocationBased");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationRadius = CursorUtil.getColumnIndexOrThrow(_cursor, "locationRadius");
          final int _cursorIndexOfCalendarEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "calendarEventId");
          final int _cursorIndexOfIsSyncedWithCalendar = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedWithCalendar");
          final Routine _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final LocalTime _tmpTime;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTime);
            }
            _tmpTime = __converters.fromTimeString(_tmp);
            final List<DayOfWeek> _tmpRepeatDays;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            _tmpRepeatDays = __dayOfWeekConverter.toDayOfWeekList(_tmp_1);
            final boolean _tmpIsEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_2 != 0;
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsLocationBased;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsLocationBased);
            _tmpIsLocationBased = _tmp_3 != 0;
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final Float _tmpLocationRadius;
            if (_cursor.isNull(_cursorIndexOfLocationRadius)) {
              _tmpLocationRadius = null;
            } else {
              _tmpLocationRadius = _cursor.getFloat(_cursorIndexOfLocationRadius);
            }
            final String _tmpCalendarEventId;
            if (_cursor.isNull(_cursorIndexOfCalendarEventId)) {
              _tmpCalendarEventId = null;
            } else {
              _tmpCalendarEventId = _cursor.getString(_cursorIndexOfCalendarEventId);
            }
            final boolean _tmpIsSyncedWithCalendar;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsSyncedWithCalendar);
            _tmpIsSyncedWithCalendar = _tmp_4 != 0;
            _result = new Routine(_tmpId,_tmpTitle,_tmpDescription,_tmpTime,_tmpRepeatDays,_tmpIsEnabled,_tmpCategory,_tmpIsLocationBased,_tmpLatitude,_tmpLongitude,_tmpLocationRadius,_tmpCalendarEventId,_tmpIsSyncedWithCalendar);
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
  public Flow<List<Routine>> getLocationBasedRoutines() {
    final String _sql = "\n"
            + "        SELECT * FROM routines \n"
            + "        WHERE isEnabled = 1 \n"
            + "        AND latitude IS NOT NULL \n"
            + "        AND longitude IS NOT NULL\n"
            + "        ORDER BY time ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"routines"}, new Callable<List<Routine>>() {
      @Override
      @NonNull
      public List<Routine> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsLocationBased = CursorUtil.getColumnIndexOrThrow(_cursor, "isLocationBased");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationRadius = CursorUtil.getColumnIndexOrThrow(_cursor, "locationRadius");
          final int _cursorIndexOfCalendarEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "calendarEventId");
          final int _cursorIndexOfIsSyncedWithCalendar = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedWithCalendar");
          final List<Routine> _result = new ArrayList<Routine>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Routine _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final LocalTime _tmpTime;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTime);
            }
            _tmpTime = __converters.fromTimeString(_tmp);
            final List<DayOfWeek> _tmpRepeatDays;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            _tmpRepeatDays = __dayOfWeekConverter.toDayOfWeekList(_tmp_1);
            final boolean _tmpIsEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_2 != 0;
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsLocationBased;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsLocationBased);
            _tmpIsLocationBased = _tmp_3 != 0;
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final Float _tmpLocationRadius;
            if (_cursor.isNull(_cursorIndexOfLocationRadius)) {
              _tmpLocationRadius = null;
            } else {
              _tmpLocationRadius = _cursor.getFloat(_cursorIndexOfLocationRadius);
            }
            final String _tmpCalendarEventId;
            if (_cursor.isNull(_cursorIndexOfCalendarEventId)) {
              _tmpCalendarEventId = null;
            } else {
              _tmpCalendarEventId = _cursor.getString(_cursorIndexOfCalendarEventId);
            }
            final boolean _tmpIsSyncedWithCalendar;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsSyncedWithCalendar);
            _tmpIsSyncedWithCalendar = _tmp_4 != 0;
            _item = new Routine(_tmpId,_tmpTitle,_tmpDescription,_tmpTime,_tmpRepeatDays,_tmpIsEnabled,_tmpCategory,_tmpIsLocationBased,_tmpLatitude,_tmpLongitude,_tmpLocationRadius,_tmpCalendarEventId,_tmpIsSyncedWithCalendar);
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
  public Flow<List<Routine>> getActiveRoutinesForFiltering() {
    final String _sql = "SELECT * FROM routines WHERE isEnabled = 1 ORDER BY time ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"routines"}, new Callable<List<Routine>>() {
      @Override
      @NonNull
      public List<Routine> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsLocationBased = CursorUtil.getColumnIndexOrThrow(_cursor, "isLocationBased");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationRadius = CursorUtil.getColumnIndexOrThrow(_cursor, "locationRadius");
          final int _cursorIndexOfCalendarEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "calendarEventId");
          final int _cursorIndexOfIsSyncedWithCalendar = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedWithCalendar");
          final List<Routine> _result = new ArrayList<Routine>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Routine _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final LocalTime _tmpTime;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTime);
            }
            _tmpTime = __converters.fromTimeString(_tmp);
            final List<DayOfWeek> _tmpRepeatDays;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            _tmpRepeatDays = __dayOfWeekConverter.toDayOfWeekList(_tmp_1);
            final boolean _tmpIsEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_2 != 0;
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsLocationBased;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsLocationBased);
            _tmpIsLocationBased = _tmp_3 != 0;
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final Float _tmpLocationRadius;
            if (_cursor.isNull(_cursorIndexOfLocationRadius)) {
              _tmpLocationRadius = null;
            } else {
              _tmpLocationRadius = _cursor.getFloat(_cursorIndexOfLocationRadius);
            }
            final String _tmpCalendarEventId;
            if (_cursor.isNull(_cursorIndexOfCalendarEventId)) {
              _tmpCalendarEventId = null;
            } else {
              _tmpCalendarEventId = _cursor.getString(_cursorIndexOfCalendarEventId);
            }
            final boolean _tmpIsSyncedWithCalendar;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsSyncedWithCalendar);
            _tmpIsSyncedWithCalendar = _tmp_4 != 0;
            _item = new Routine(_tmpId,_tmpTitle,_tmpDescription,_tmpTime,_tmpRepeatDays,_tmpIsEnabled,_tmpCategory,_tmpIsLocationBased,_tmpLatitude,_tmpLongitude,_tmpLocationRadius,_tmpCalendarEventId,_tmpIsSyncedWithCalendar);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
