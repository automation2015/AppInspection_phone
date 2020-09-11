package auto.cn.greendaogenerate;

import java.util.List;
import auto.cn.greendaogenerate.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "EQUIPLIST".
 */
public class Equiplist {

    private Long id;
    private String EL_NAME;
    private String EL_Depart_Name;
    private String EL_ID;
    private String EL_Type_Name;
    private String EL_EIS_ID;
    private String EL_EIS_NAME;
    private String EL_VALID_FLAG;
    private Integer PL_AREA_ID;
    private Boolean EQUIP_FINISH;
    private Long fk_area;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient EquiplistDao myDao;

    private AreaList areaList;
    private Long areaList__resolvedKey;

    private List<PartList> parts;
    private List<ItemList> items;

    public Equiplist() {
    }

    public Equiplist(Long id) {
        this.id = id;
    }

    public Equiplist(Long id, String EL_NAME, String EL_Depart_Name, String EL_ID, String EL_Type_Name, String EL_EIS_ID, String EL_EIS_NAME, String EL_VALID_FLAG, Integer PL_AREA_ID, Boolean EQUIP_FINISH, Long fk_area) {
        this.id = id;
        this.EL_NAME = EL_NAME;
        this.EL_Depart_Name = EL_Depart_Name;
        this.EL_ID = EL_ID;
        this.EL_Type_Name = EL_Type_Name;
        this.EL_EIS_ID = EL_EIS_ID;
        this.EL_EIS_NAME = EL_EIS_NAME;
        this.EL_VALID_FLAG = EL_VALID_FLAG;
        this.PL_AREA_ID = PL_AREA_ID;
        this.EQUIP_FINISH = EQUIP_FINISH;
        this.fk_area = fk_area;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEquiplistDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEL_NAME() {
        return EL_NAME;
    }

    public void setEL_NAME(String EL_NAME) {
        this.EL_NAME = EL_NAME;
    }

    public String getEL_Depart_Name() {
        return EL_Depart_Name;
    }

    public void setEL_Depart_Name(String EL_Depart_Name) {
        this.EL_Depart_Name = EL_Depart_Name;
    }

    public String getEL_ID() {
        return EL_ID;
    }

    public void setEL_ID(String EL_ID) {
        this.EL_ID = EL_ID;
    }

    public String getEL_Type_Name() {
        return EL_Type_Name;
    }

    public void setEL_Type_Name(String EL_Type_Name) {
        this.EL_Type_Name = EL_Type_Name;
    }

    public String getEL_EIS_ID() {
        return EL_EIS_ID;
    }

    public void setEL_EIS_ID(String EL_EIS_ID) {
        this.EL_EIS_ID = EL_EIS_ID;
    }

    public String getEL_EIS_NAME() {
        return EL_EIS_NAME;
    }

    public void setEL_EIS_NAME(String EL_EIS_NAME) {
        this.EL_EIS_NAME = EL_EIS_NAME;
    }

    public String getEL_VALID_FLAG() {
        return EL_VALID_FLAG;
    }

    public void setEL_VALID_FLAG(String EL_VALID_FLAG) {
        this.EL_VALID_FLAG = EL_VALID_FLAG;
    }

    public Integer getPL_AREA_ID() {
        return PL_AREA_ID;
    }

    public void setPL_AREA_ID(Integer PL_AREA_ID) {
        this.PL_AREA_ID = PL_AREA_ID;
    }

    public Boolean getEQUIP_FINISH() {
        return EQUIP_FINISH;
    }

    public void setEQUIP_FINISH(Boolean EQUIP_FINISH) {
        this.EQUIP_FINISH = EQUIP_FINISH;
    }

    public Long getFk_area() {
        return fk_area;
    }

    public void setFk_area(Long fk_area) {
        this.fk_area = fk_area;
    }

    /** To-one relationship, resolved on first access. */
    public AreaList getAreaList() {
        Long __key = this.fk_area;
        if (areaList__resolvedKey == null || !areaList__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AreaListDao targetDao = daoSession.getAreaListDao();
            AreaList areaListNew = targetDao.load(__key);
            synchronized (this) {
                areaList = areaListNew;
            	areaList__resolvedKey = __key;
            }
        }
        return areaList;
    }

    public void setAreaList(AreaList areaList) {
        synchronized (this) {
            this.areaList = areaList;
            fk_area = areaList == null ? null : areaList.getId();
            areaList__resolvedKey = fk_area;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<PartList> getParts() {
        if (parts == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PartListDao targetDao = daoSession.getPartListDao();
            List<PartList> partsNew = targetDao._queryEquiplist_Parts(id);
            synchronized (this) {
                if(parts == null) {
                    parts = partsNew;
                }
            }
        }
        return parts;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetParts() {
        parts = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ItemList> getItems() {
        if (items == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ItemListDao targetDao = daoSession.getItemListDao();
            List<ItemList> itemsNew = targetDao._queryEquiplist_Items(id);
            synchronized (this) {
                if(items == null) {
                    items = itemsNew;
                }
            }
        }
        return items;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetItems() {
        items = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
