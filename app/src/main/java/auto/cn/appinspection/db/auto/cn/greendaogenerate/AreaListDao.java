package auto.cn.greendaogenerate;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import auto.cn.greendaogenerate.AreaList;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "AREA_LIST".
*/
public class AreaListDao extends AbstractDao<AreaList, Long> {

    public static final String TABLENAME = "AREA_LIST";

    /**
     * Properties of entity AreaList.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PL_AREA_ID = new Property(1, Integer.class, "PL_AREA_ID", false, "PL__AREA__ID");
        public final static Property PL_AREA_NAME = new Property(2, String.class, "PL_AREA_NAME", false, "PL__AREA__NAME");
        public final static Property PL_AREA_LABEL = new Property(3, String.class, "PL_AREA_LABEL", false, "PL__AREA__LABEL");
        public final static Property PL_AREA_CREATE_ID = new Property(4, String.class, "PL_AREA_CREATE_ID", false, "PL__AREA__CREATE__ID");
        public final static Property PL_AREA_CREATE_DATE = new Property(5, String.class, "PL_AREA_CREATE_DATE", false, "PL__AREA__CREATE__DATE");
        public final static Property Valid_Flag = new Property(6, Integer.class, "Valid_Flag", false, "VALID__FLAG");
        public final static Property PlanId = new Property(7, String.class, "PlanId", false, "PLAN_ID");
        public final static Property AREA_NORNAL = new Property(8, String.class, "AREA_NORNAL", false, "AREA__NORNAL");
        public final static Property AREA_FINISH = new Property(9, Boolean.class, "AREA_FINISH", false, "AREA__FINISH");
        public final static Property Fk_plan = new Property(10, Long.class, "fk_plan", false, "FK_PLAN");
    };

    private DaoSession daoSession;

    private Query<AreaList> planList_AreasQuery;

    public AreaListDao(DaoConfig config) {
        super(config);
    }
    
    public AreaListDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"AREA_LIST\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PL__AREA__ID\" INTEGER," + // 1: PL_AREA_ID
                "\"PL__AREA__NAME\" TEXT," + // 2: PL_AREA_NAME
                "\"PL__AREA__LABEL\" TEXT," + // 3: PL_AREA_LABEL
                "\"PL__AREA__CREATE__ID\" TEXT," + // 4: PL_AREA_CREATE_ID
                "\"PL__AREA__CREATE__DATE\" TEXT," + // 5: PL_AREA_CREATE_DATE
                "\"VALID__FLAG\" INTEGER," + // 6: Valid_Flag
                "\"PLAN_ID\" TEXT," + // 7: PlanId
                "\"AREA__NORNAL\" TEXT," + // 8: AREA_NORNAL
                "\"AREA__FINISH\" INTEGER," + // 9: AREA_FINISH
                "\"FK_PLAN\" INTEGER);"); // 10: fk_plan
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"AREA_LIST\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, AreaList entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer PL_AREA_ID = entity.getPL_AREA_ID();
        if (PL_AREA_ID != null) {
            stmt.bindLong(2, PL_AREA_ID);
        }
 
        String PL_AREA_NAME = entity.getPL_AREA_NAME();
        if (PL_AREA_NAME != null) {
            stmt.bindString(3, PL_AREA_NAME);
        }
 
        String PL_AREA_LABEL = entity.getPL_AREA_LABEL();
        if (PL_AREA_LABEL != null) {
            stmt.bindString(4, PL_AREA_LABEL);
        }
 
        String PL_AREA_CREATE_ID = entity.getPL_AREA_CREATE_ID();
        if (PL_AREA_CREATE_ID != null) {
            stmt.bindString(5, PL_AREA_CREATE_ID);
        }
 
        String PL_AREA_CREATE_DATE = entity.getPL_AREA_CREATE_DATE();
        if (PL_AREA_CREATE_DATE != null) {
            stmt.bindString(6, PL_AREA_CREATE_DATE);
        }
 
        Integer Valid_Flag = entity.getValid_Flag();
        if (Valid_Flag != null) {
            stmt.bindLong(7, Valid_Flag);
        }
 
        String PlanId = entity.getPlanId();
        if (PlanId != null) {
            stmt.bindString(8, PlanId);
        }
 
        String AREA_NORNAL = entity.getAREA_NORNAL();
        if (AREA_NORNAL != null) {
            stmt.bindString(9, AREA_NORNAL);
        }
 
        Boolean AREA_FINISH = entity.getAREA_FINISH();
        if (AREA_FINISH != null) {
            stmt.bindLong(10, AREA_FINISH ? 1L: 0L);
        }
 
        Long fk_plan = entity.getFk_plan();
        if (fk_plan != null) {
            stmt.bindLong(11, fk_plan);
        }
    }

    @Override
    protected void attachEntity(AreaList entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public AreaList readEntity(Cursor cursor, int offset) {
        AreaList entity = new AreaList( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // PL_AREA_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // PL_AREA_NAME
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // PL_AREA_LABEL
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // PL_AREA_CREATE_ID
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // PL_AREA_CREATE_DATE
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // Valid_Flag
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // PlanId
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // AREA_NORNAL
            cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0, // AREA_FINISH
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10) // fk_plan
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, AreaList entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPL_AREA_ID(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setPL_AREA_NAME(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPL_AREA_LABEL(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPL_AREA_CREATE_ID(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPL_AREA_CREATE_DATE(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setValid_Flag(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setPlanId(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setAREA_NORNAL(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAREA_FINISH(cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0);
        entity.setFk_plan(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(AreaList entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(AreaList entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "areas" to-many relationship of PlanList. */
    public List<AreaList> _queryPlanList_Areas(Long fk_plan) {
        synchronized (this) {
            if (planList_AreasQuery == null) {
                QueryBuilder<AreaList> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Fk_plan.eq(null));
                planList_AreasQuery = queryBuilder.build();
            }
        }
        Query<AreaList> query = planList_AreasQuery.forCurrentThread();
        query.setParameter(0, fk_plan);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getPlanListDao().getAllColumns());
            builder.append(" FROM AREA_LIST T");
            builder.append(" LEFT JOIN PLAN_LIST T0 ON T.\"FK_PLAN\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected AreaList loadCurrentDeep(Cursor cursor, boolean lock) {
        AreaList entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        PlanList planList = loadCurrentOther(daoSession.getPlanListDao(), cursor, offset);
        entity.setPlanList(planList);

        return entity;    
    }

    public AreaList loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<AreaList> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<AreaList> list = new ArrayList<AreaList>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<AreaList> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<AreaList> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
