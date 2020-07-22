package auto.cn.greendaogenerate;

import java.util.List;
import auto.cn.greendaogenerate.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PLAN_LIST".
 */
public class PlanList {

    private Long id;
    private String PLAN_NAME;
    private String PLAN_ORG_NAME;
    private String PLAN_PART_NAME;
    private String PLAN_PART_ID;
    private Integer PLAN_NUM;
    private Integer PLAN_CYCLE_TYPE;
    private java.util.Date PLAN_LAST_DATE;
    private String PLAN_LAST_USER_NAME;
    private java.util.Date PLAN_CREATE_DATE;
    private String Valid_Flag;
    private Integer Shift;
    private String CODE_NAME;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient PlanListDao myDao;

    private List<AreaList> areas;

    public PlanList() {
    }

    public PlanList(Long id) {
        this.id = id;
    }

    public PlanList(Long id, String PLAN_NAME, String PLAN_ORG_NAME, String PLAN_PART_NAME, String PLAN_PART_ID, Integer PLAN_NUM, Integer PLAN_CYCLE_TYPE, java.util.Date PLAN_LAST_DATE, String PLAN_LAST_USER_NAME, java.util.Date PLAN_CREATE_DATE, String Valid_Flag, Integer Shift, String CODE_NAME) {
        this.id = id;
        this.PLAN_NAME = PLAN_NAME;
        this.PLAN_ORG_NAME = PLAN_ORG_NAME;
        this.PLAN_PART_NAME = PLAN_PART_NAME;
        this.PLAN_PART_ID = PLAN_PART_ID;
        this.PLAN_NUM = PLAN_NUM;
        this.PLAN_CYCLE_TYPE = PLAN_CYCLE_TYPE;
        this.PLAN_LAST_DATE = PLAN_LAST_DATE;
        this.PLAN_LAST_USER_NAME = PLAN_LAST_USER_NAME;
        this.PLAN_CREATE_DATE = PLAN_CREATE_DATE;
        this.Valid_Flag = Valid_Flag;
        this.Shift = Shift;
        this.CODE_NAME = CODE_NAME;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlanListDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPLAN_NAME() {
        return PLAN_NAME;
    }

    public void setPLAN_NAME(String PLAN_NAME) {
        this.PLAN_NAME = PLAN_NAME;
    }

    public String getPLAN_ORG_NAME() {
        return PLAN_ORG_NAME;
    }

    public void setPLAN_ORG_NAME(String PLAN_ORG_NAME) {
        this.PLAN_ORG_NAME = PLAN_ORG_NAME;
    }

    public String getPLAN_PART_NAME() {
        return PLAN_PART_NAME;
    }

    public void setPLAN_PART_NAME(String PLAN_PART_NAME) {
        this.PLAN_PART_NAME = PLAN_PART_NAME;
    }

    public String getPLAN_PART_ID() {
        return PLAN_PART_ID;
    }

    public void setPLAN_PART_ID(String PLAN_PART_ID) {
        this.PLAN_PART_ID = PLAN_PART_ID;
    }

    public Integer getPLAN_NUM() {
        return PLAN_NUM;
    }

    public void setPLAN_NUM(Integer PLAN_NUM) {
        this.PLAN_NUM = PLAN_NUM;
    }

    public Integer getPLAN_CYCLE_TYPE() {
        return PLAN_CYCLE_TYPE;
    }

    public void setPLAN_CYCLE_TYPE(Integer PLAN_CYCLE_TYPE) {
        this.PLAN_CYCLE_TYPE = PLAN_CYCLE_TYPE;
    }

    public java.util.Date getPLAN_LAST_DATE() {
        return PLAN_LAST_DATE;
    }

    public void setPLAN_LAST_DATE(java.util.Date PLAN_LAST_DATE) {
        this.PLAN_LAST_DATE = PLAN_LAST_DATE;
    }

    public String getPLAN_LAST_USER_NAME() {
        return PLAN_LAST_USER_NAME;
    }

    public void setPLAN_LAST_USER_NAME(String PLAN_LAST_USER_NAME) {
        this.PLAN_LAST_USER_NAME = PLAN_LAST_USER_NAME;
    }

    public java.util.Date getPLAN_CREATE_DATE() {
        return PLAN_CREATE_DATE;
    }

    public void setPLAN_CREATE_DATE(java.util.Date PLAN_CREATE_DATE) {
        this.PLAN_CREATE_DATE = PLAN_CREATE_DATE;
    }

    public String getValid_Flag() {
        return Valid_Flag;
    }

    public void setValid_Flag(String Valid_Flag) {
        this.Valid_Flag = Valid_Flag;
    }

    public Integer getShift() {
        return Shift;
    }

    public void setShift(Integer Shift) {
        this.Shift = Shift;
    }

    public String getCODE_NAME() {
        return CODE_NAME;
    }

    public void setCODE_NAME(String CODE_NAME) {
        this.CODE_NAME = CODE_NAME;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<AreaList> getAreas() {
        if (areas == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AreaListDao targetDao = daoSession.getAreaListDao();
            List<AreaList> areasNew = targetDao._queryPlanList_Areas(id);
            synchronized (this) {
                if(areas == null) {
                    areas = areasNew;
                }
            }
        }
        return areas;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetAreas() {
        areas = null;
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