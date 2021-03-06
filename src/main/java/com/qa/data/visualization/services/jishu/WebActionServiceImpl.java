package com.qa.data.visualization.services.jishu;

import com.qa.data.visualization.entities.jishu.mobile.AndroidModelCnt;
import com.qa.data.visualization.entities.jishu.web.*;
import com.web.spring.datatable.DataSet;
import com.web.spring.datatable.DatatablesCriterias;
import com.web.spring.datatable.TableQuery;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class WebActionServiceImpl implements WebActionService {
    @PersistenceContext(unitName = "primaryPersistenceUnit")
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    @Cacheable(value = "web_hotspot_cache", keyGenerator = "wiselyKeyGenerator")
    public LinkedHashMap<String, String> getHotSpot() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        String s = String.format("select left(right(time,8),5) as min,count(*) as cnt,time  from ABC360_WEB_STUDENT_ACTION_LAST_TBL group by min ORDER BY time ASC");
        javax.persistence.Query q = entityManager.createNativeQuery(s);
        List<Object[]> list = q.getResultList();
        for (Object[] result : list) {
            map.put(result[2].toString(), result[1].toString());
        }
        return map;
    }

    @Override
    public DataSet<WebStuAction> findStuActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebStuAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebStuActionGroupCount> findStuGroupCountWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebStuActionGroupCount.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebTeaAction> findTeaActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebTeaAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebTeaActionGroupCount> findTeaGroupCountWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebTeaActionGroupCount.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebUserAction> findUserActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebUserAction.class, criterias);
        return query.getResultDataSet();
    }


    @Override
    public DataSet<WebUserActionGroupCount> findUserGroupCountWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebUserActionGroupCount.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebCrmAction> findCrmActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebCrmAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebCrmActionGroupCount> findCrmGroupCountWithDatatablesCriterias(DatatablesCriterias criterias){
        Date d=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now=df.format(new Date(d.getTime() - 7 * 24 * 60 * 60 * 1000));
        String sql=String.format("select controller,method,count(id) as count from ABC360_WEB_CRM_ACTION_LAST_MONTH_WITH_TODAY_TBL where time>'%s' group by controller,method",now);
        TableQuery query = new TableQuery(entityManager, WebCrmActionGroupCount.class, criterias, sql);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebErrorAction> findErrorActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebErrorAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebDebugAction> findDebugActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebDebugAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebCronAction> findCronActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebCronAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebRoleAction> findRoleActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebRoleAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<WebPropertyAction> findPropertyActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, WebPropertyAction.class, criterias);
        return query.getResultDataSet();
    }

}
