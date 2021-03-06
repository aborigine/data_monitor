package com.qa.data.visualization.services.jishu;

import com.qa.data.visualization.entities.jishu.pc.PCAPIStuActionGroupCount;
import com.qa.data.visualization.entities.jishu.pc.PCStuAPIAction;
import com.qa.data.visualization.entities.jishu.pc.PCTeaAPIAction;
import com.web.spring.datatable.DataSet;
import com.web.spring.datatable.DatatablesCriterias;
import com.web.spring.datatable.TableQuery;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PCServiceImpl implements PCService {
    @PersistenceContext(unitName = "primaryPersistenceUnit")
    private EntityManager entityManager;
    @PersistenceContext(unitName = "secondaryPersistenceUnit")
    private EntityManager entityManagerSec;

    @Override
    public DataSet<PCStuAPIAction> findPCStuAPIActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, PCStuAPIAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<PCAPIStuActionGroupCount> findPCAPIStuActionGroupCount(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, PCAPIStuActionGroupCount.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<PCTeaAPIAction> findPCTeaAPIActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        TableQuery query = new TableQuery(entityManager, PCTeaAPIAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Cacheable(value = "pc_app_version_cache", keyGenerator = "wiselyKeyGenerator")
    public LinkedHashMap<String, String> getPCApp() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        String s = String.format("select version as name,count(version) as count from (select * from ebk_pc_stu_client where version !='UnKnow version' and login_time > (UNIX_TIMESTAMP(now())- 3600*24*1)) a inner JOIN\n" +
                "(select sid,max(login_time) as Login_time from ebk_pc_stu_client where version !='UnKnow version' and login_time > (UNIX_TIMESTAMP(now())- 3600*24*1) group by sid) b\n" +
                "on a.login_time = b.login_time and a.sid = b.sid \n" +
                "GROUP BY (version)");
        Query q = entityManager.createNativeQuery(s);
        List<Object[]> list = q.getResultList();
        for (Object[] result : list) {
            map.put(result[0].toString(), result[1].toString());
        }
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Cacheable(value = "pc_os_version_cache", keyGenerator = "wiselyKeyGenerator")
    public LinkedHashMap<String, String> getPCSystem() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        String s = String.format("select os as name,count(os) as count from (select * from ebk_pc_stu_client where os!='Unknow os' and login_time > (UNIX_TIMESTAMP(now())- 3600*24*1)) a inner JOIN\n" +
                "(select sid,max(login_time) as Login_time from ebk_pc_stu_client where os!='Unknow os' and login_time > (UNIX_TIMESTAMP(now())- 3600*24*1) group by sid) b\n" +
                "on a.login_time = b.login_time and a.sid = b.sid \n" +
                "GROUP BY (os)");
        Query q = entityManager.createNativeQuery(s);
        List<Object[]> list = q.getResultList();
        for (Object[] result : list) {
            map.put(result[0].toString(), result[1].toString());
        }
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LinkedHashMap<String, String> getTchPCApp() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        String s = String.format("select version as name,count(version) as count from (select * from ebk_pc_tch_client where version !='UnKnow version' and login_time > (UNIX_TIMESTAMP(now())- 3600*24*1)) a inner JOIN\n" +
                "(select tid,max(login_time) as Login_time from ebk_pc_tch_client where version !='UnKnow version' and login_time > (UNIX_TIMESTAMP(now())- 3600*24*1) group by tid) b\n" +
                "on a.login_time = b.login_time and a.tid = b.tid \n" +
                "GROUP BY (version)");
        Query q = entityManagerSec.createNativeQuery(s);
        List<Object[]> list = q.getResultList();
        for (Object[] result : list) {
            map.put(result[0].toString(), result[1].toString());
        }
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LinkedHashMap<String, String> getTchPCSystem() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        String s = String.format("select os as name,count(os) as count from (select * from ebk_pc_tch_client where os!='Unknow os' and login_time > (UNIX_TIMESTAMP(now())- 3600*24*1)) a inner JOIN\n" +
                "(select tid,max(login_time) as Login_time from ebk_pc_tch_client where os!='Unknow os' and login_time > (UNIX_TIMESTAMP(now())- 3600*24*1) group by tid) b\n" +
                " on a.login_time = b.login_time and a.tid = b.tid \n" +
                " GROUP BY (os)");
        Query q = entityManagerSec.createNativeQuery(s);
        List<Object[]> list = q.getResultList();
        for (Object[] result : list) {
            map.put(result[0].toString(), result[1].toString());
        }
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<String,String> getPcApiResponse(String data){
        HashMap<String, String> map = new HashMap<String, String>();
        String s = String.format("select idx,response from ABC360_APP_API_CLASSPLAT_RESPONCE_TBL where idx='%s'", data);
        Query q = entityManager.createNativeQuery(s);
        List<Object[]> list = q.getResultList();
        for (Object[] result : list) {
            map.put(result[0].toString(), result[1].toString());
        }
        return map;
    }
}
