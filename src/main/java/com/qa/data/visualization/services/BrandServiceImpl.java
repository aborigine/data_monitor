package com.qa.data.visualization.services;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by dykj on 2016/10/9.
 */
@Service
public class BrandServiceImpl implements BrandService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public LinkedHashMap<String, String> getBrand() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        Query q = entityManager.createNativeQuery("select s.model as model,count(s.model) as count from (select max(time),uid,model from ABC360_ANDROID_APP_DEVICE_TBL group by uid ) s group by s.model order by count(s.model) desc");
        List<Object[]> list = q.getResultList();
        for (Object[] result : list) {
            map.put(result[0].toString(), result[1].toString());
        }
        return map;
    }
}