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

import auto.cn.greendaogenerate.ContentList;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CONTENT_LIST".
*/
public class ContentListDao extends AbstractDao<ContentList, Long> {

    public static final String TABLENAME = "CONTENT_LIST";

    /**
     * Properties of entity ContentList.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CONTENT_ID = new Property(1, String.class, "CONTENT_ID", false, "CONTENT__ID");
        public final static Property CONTENT_PL_BZ_ID = new Property(2, String.class, "CONTENT_PL_BZ_ID", false, "CONTENT__PL__BZ__ID");
        public final static Property CONTENT_PART_ID = new Property(3, String.class, "CONTENT_PART_ID", false, "CONTENT__PART__ID");
        public final static Property CONTENT_ITEM_ID = new Property(4, String.class, "CONTENT_ITEM_ID", false, "CONTENT__ITEM__ID");
        public final static Property CONTENT_NAME = new Property(5, String.class, "CONTENT_NAME", false, "CONTENT__NAME");
        public final static Property CONTENT_CONTENT_TYPE = new Property(6, String.class, "CONTENT_CONTENT_TYPE", false, "CONTENT__CONTENT__TYPE");
        public final static Property CONTENT_SORT = new Property(7, Integer.class, "CONTENT_SORT", false, "CONTENT__SORT");
        public final static Property CONTENT_WAY = new Property(8, String.class, "CONTENT_WAY", false, "CONTENT__WAY");
        public final static Property CONTENT_IS_USE = new Property(9, Integer.class, "CONTENT_IS_USE", false, "CONTENT__IS__USE");
        public final static Property CONTENT_STANDARD = new Property(10, String.class, "CONTENT_STANDARD", false, "CONTENT__STANDARD");
        public final static Property CONTENT_IS_PHOTO = new Property(11, Integer.class, "CONTENT_IS_PHOTO", false, "CONTENT__IS__PHOTO");
        public final static Property CONTENT_IS_PHOTO_EXCEPTION = new Property(12, Integer.class, "CONTENT_IS_PHOTO_EXCEPTION", false, "CONTENT__IS__PHOTO__EXCEPTION");
        public final static Property CONTENT_CREATE_DATE = new Property(13, String.class, "CONTENT_CREATE_DATE", false, "CONTENT__CREATE__DATE");
        public final static Property CONTENT_ALARM_H1 = new Property(14, String.class, "CONTENT_ALARM_H1", false, "CONTENT__ALARM__H1");
        public final static Property CONTENT_ALARM_H2 = new Property(15, String.class, "CONTENT_ALARM_H2", false, "CONTENT__ALARM__H2");
        public final static Property CONTENT_ALARM_STYLE = new Property(16, String.class, "CONTENT_ALARM_STYLE", false, "CONTENT__ALARM__STYLE");
        public final static Property Valid_Flag = new Property(17, String.class, "Valid_Flag", false, "VALID__FLAG");
        public final static Property CODE_NAME = new Property(18, String.class, "CODE_NAME", false, "CODE__NAME");
        public final static Property TEMP_VALUE = new Property(19, String.class, "TEMP_VALUE", false, "TEMP__VALUE");
        public final static Property PHOTO_PATH = new Property(20, String.class, "PHOTO_PATH", false, "PHOTO__PATH");
        public final static Property CONTENT_FINISH = new Property(21, Boolean.class, "CONTENT_FINISH", false, "CONTENT__FINISH");
        public final static Property CONTENT_STATUS = new Property(22, String.class, "CONTENT_STATUS", false, "CONTENT__STATUS");
        public final static Property Fk_item = new Property(23, Long.class, "fk_item", false, "FK_ITEM");
    };

    private DaoSession daoSession;

    private Query<ContentList> itemList_ContentsQuery;

    public ContentListDao(DaoConfig config) {
        super(config);
    }
    
    public ContentListDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CONTENT_LIST\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CONTENT__ID\" TEXT," + // 1: CONTENT_ID
                "\"CONTENT__PL__BZ__ID\" TEXT," + // 2: CONTENT_PL_BZ_ID
                "\"CONTENT__PART__ID\" TEXT," + // 3: CONTENT_PART_ID
                "\"CONTENT__ITEM__ID\" TEXT," + // 4: CONTENT_ITEM_ID
                "\"CONTENT__NAME\" TEXT," + // 5: CONTENT_NAME
                "\"CONTENT__CONTENT__TYPE\" TEXT," + // 6: CONTENT_CONTENT_TYPE
                "\"CONTENT__SORT\" INTEGER," + // 7: CONTENT_SORT
                "\"CONTENT__WAY\" TEXT," + // 8: CONTENT_WAY
                "\"CONTENT__IS__USE\" INTEGER," + // 9: CONTENT_IS_USE
                "\"CONTENT__STANDARD\" TEXT," + // 10: CONTENT_STANDARD
                "\"CONTENT__IS__PHOTO\" INTEGER," + // 11: CONTENT_IS_PHOTO
                "\"CONTENT__IS__PHOTO__EXCEPTION\" INTEGER," + // 12: CONTENT_IS_PHOTO_EXCEPTION
                "\"CONTENT__CREATE__DATE\" TEXT," + // 13: CONTENT_CREATE_DATE
                "\"CONTENT__ALARM__H1\" TEXT," + // 14: CONTENT_ALARM_H1
                "\"CONTENT__ALARM__H2\" TEXT," + // 15: CONTENT_ALARM_H2
                "\"CONTENT__ALARM__STYLE\" TEXT," + // 16: CONTENT_ALARM_STYLE
                "\"VALID__FLAG\" TEXT," + // 17: Valid_Flag
                "\"CODE__NAME\" TEXT," + // 18: CODE_NAME
                "\"TEMP__VALUE\" TEXT," + // 19: TEMP_VALUE
                "\"PHOTO__PATH\" TEXT," + // 20: PHOTO_PATH
                "\"CONTENT__FINISH\" INTEGER," + // 21: CONTENT_FINISH
                "\"CONTENT__STATUS\" TEXT," + // 22: CONTENT_STATUS
                "\"FK_ITEM\" INTEGER);"); // 23: fk_item
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CONTENT_LIST\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ContentList entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String CONTENT_ID = entity.getCONTENT_ID();
        if (CONTENT_ID != null) {
            stmt.bindString(2, CONTENT_ID);
        }
 
        String CONTENT_PL_BZ_ID = entity.getCONTENT_PL_BZ_ID();
        if (CONTENT_PL_BZ_ID != null) {
            stmt.bindString(3, CONTENT_PL_BZ_ID);
        }
 
        String CONTENT_PART_ID = entity.getCONTENT_PART_ID();
        if (CONTENT_PART_ID != null) {
            stmt.bindString(4, CONTENT_PART_ID);
        }
 
        String CONTENT_ITEM_ID = entity.getCONTENT_ITEM_ID();
        if (CONTENT_ITEM_ID != null) {
            stmt.bindString(5, CONTENT_ITEM_ID);
        }
 
        String CONTENT_NAME = entity.getCONTENT_NAME();
        if (CONTENT_NAME != null) {
            stmt.bindString(6, CONTENT_NAME);
        }
 
        String CONTENT_CONTENT_TYPE = entity.getCONTENT_CONTENT_TYPE();
        if (CONTENT_CONTENT_TYPE != null) {
            stmt.bindString(7, CONTENT_CONTENT_TYPE);
        }
 
        Integer CONTENT_SORT = entity.getCONTENT_SORT();
        if (CONTENT_SORT != null) {
            stmt.bindLong(8, CONTENT_SORT);
        }
 
        String CONTENT_WAY = entity.getCONTENT_WAY();
        if (CONTENT_WAY != null) {
            stmt.bindString(9, CONTENT_WAY);
        }
 
        Integer CONTENT_IS_USE = entity.getCONTENT_IS_USE();
        if (CONTENT_IS_USE != null) {
            stmt.bindLong(10, CONTENT_IS_USE);
        }
 
        String CONTENT_STANDARD = entity.getCONTENT_STANDARD();
        if (CONTENT_STANDARD != null) {
            stmt.bindString(11, CONTENT_STANDARD);
        }
 
        Integer CONTENT_IS_PHOTO = entity.getCONTENT_IS_PHOTO();
        if (CONTENT_IS_PHOTO != null) {
            stmt.bindLong(12, CONTENT_IS_PHOTO);
        }
 
        Integer CONTENT_IS_PHOTO_EXCEPTION = entity.getCONTENT_IS_PHOTO_EXCEPTION();
        if (CONTENT_IS_PHOTO_EXCEPTION != null) {
            stmt.bindLong(13, CONTENT_IS_PHOTO_EXCEPTION);
        }
 
        String CONTENT_CREATE_DATE = entity.getCONTENT_CREATE_DATE();
        if (CONTENT_CREATE_DATE != null) {
            stmt.bindString(14, CONTENT_CREATE_DATE);
        }
 
        String CONTENT_ALARM_H1 = entity.getCONTENT_ALARM_H1();
        if (CONTENT_ALARM_H1 != null) {
            stmt.bindString(15, CONTENT_ALARM_H1);
        }
 
        String CONTENT_ALARM_H2 = entity.getCONTENT_ALARM_H2();
        if (CONTENT_ALARM_H2 != null) {
            stmt.bindString(16, CONTENT_ALARM_H2);
        }
 
        String CONTENT_ALARM_STYLE = entity.getCONTENT_ALARM_STYLE();
        if (CONTENT_ALARM_STYLE != null) {
            stmt.bindString(17, CONTENT_ALARM_STYLE);
        }
 
        String Valid_Flag = entity.getValid_Flag();
        if (Valid_Flag != null) {
            stmt.bindString(18, Valid_Flag);
        }
 
        String CODE_NAME = entity.getCODE_NAME();
        if (CODE_NAME != null) {
            stmt.bindString(19, CODE_NAME);
        }
 
        String TEMP_VALUE = entity.getTEMP_VALUE();
        if (TEMP_VALUE != null) {
            stmt.bindString(20, TEMP_VALUE);
        }
 
        String PHOTO_PATH = entity.getPHOTO_PATH();
        if (PHOTO_PATH != null) {
            stmt.bindString(21, PHOTO_PATH);
        }
 
        Boolean CONTENT_FINISH = entity.getCONTENT_FINISH();
        if (CONTENT_FINISH != null) {
            stmt.bindLong(22, CONTENT_FINISH ? 1L: 0L);
        }
 
        String CONTENT_STATUS = entity.getCONTENT_STATUS();
        if (CONTENT_STATUS != null) {
            stmt.bindString(23, CONTENT_STATUS);
        }
 
        Long fk_item = entity.getFk_item();
        if (fk_item != null) {
            stmt.bindLong(24, fk_item);
        }
    }

    @Override
    protected void attachEntity(ContentList entity) {
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
    public ContentList readEntity(Cursor cursor, int offset) {
        ContentList entity = new ContentList( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // CONTENT_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // CONTENT_PL_BZ_ID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // CONTENT_PART_ID
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // CONTENT_ITEM_ID
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // CONTENT_NAME
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // CONTENT_CONTENT_TYPE
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // CONTENT_SORT
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // CONTENT_WAY
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // CONTENT_IS_USE
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // CONTENT_STANDARD
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // CONTENT_IS_PHOTO
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // CONTENT_IS_PHOTO_EXCEPTION
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // CONTENT_CREATE_DATE
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // CONTENT_ALARM_H1
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // CONTENT_ALARM_H2
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // CONTENT_ALARM_STYLE
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // Valid_Flag
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // CODE_NAME
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // TEMP_VALUE
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // PHOTO_PATH
            cursor.isNull(offset + 21) ? null : cursor.getShort(offset + 21) != 0, // CONTENT_FINISH
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // CONTENT_STATUS
            cursor.isNull(offset + 23) ? null : cursor.getLong(offset + 23) // fk_item
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ContentList entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCONTENT_ID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCONTENT_PL_BZ_ID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCONTENT_PART_ID(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCONTENT_ITEM_ID(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCONTENT_NAME(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCONTENT_CONTENT_TYPE(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCONTENT_SORT(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setCONTENT_WAY(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCONTENT_IS_USE(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setCONTENT_STANDARD(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCONTENT_IS_PHOTO(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setCONTENT_IS_PHOTO_EXCEPTION(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setCONTENT_CREATE_DATE(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setCONTENT_ALARM_H1(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setCONTENT_ALARM_H2(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setCONTENT_ALARM_STYLE(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setValid_Flag(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setCODE_NAME(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setTEMP_VALUE(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setPHOTO_PATH(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setCONTENT_FINISH(cursor.isNull(offset + 21) ? null : cursor.getShort(offset + 21) != 0);
        entity.setCONTENT_STATUS(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setFk_item(cursor.isNull(offset + 23) ? null : cursor.getLong(offset + 23));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ContentList entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ContentList entity) {
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
    
    /** Internal query to resolve the "contents" to-many relationship of ItemList. */
    public List<ContentList> _queryItemList_Contents(Long fk_item) {
        synchronized (this) {
            if (itemList_ContentsQuery == null) {
                QueryBuilder<ContentList> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Fk_item.eq(null));
                itemList_ContentsQuery = queryBuilder.build();
            }
        }
        Query<ContentList> query = itemList_ContentsQuery.forCurrentThread();
        query.setParameter(0, fk_item);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getItemListDao().getAllColumns());
            builder.append(" FROM CONTENT_LIST T");
            builder.append(" LEFT JOIN ITEM_LIST T0 ON T.\"FK_ITEM\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected ContentList loadCurrentDeep(Cursor cursor, boolean lock) {
        ContentList entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ItemList itemList = loadCurrentOther(daoSession.getItemListDao(), cursor, offset);
        entity.setItemList(itemList);

        return entity;    
    }

    public ContentList loadDeep(Long key) {
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
    public List<ContentList> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<ContentList> list = new ArrayList<ContentList>(count);
        
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
    
    protected List<ContentList> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<ContentList> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
