package com.qa.data.visualization.services;

import com.qa.data.visualization.entities.pc.PCAPIStuActionGroupCount;
import com.qa.data.visualization.entities.pc.PCStuAPIAction;
import com.qa.data.visualization.entities.pc.PCTeaAPIAction;
import com.web.spring.datatable.DataSet;
import com.web.spring.datatable.DatatablesCriterias;
import com.web.spring.datatable.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class PCActionServiceImpl implements PCActionService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DataSet<PCStuAPIAction> findPCStuAPIActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        Query query = new Query(entityManager, PCStuAPIAction.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<PCAPIStuActionGroupCount> findPCAPIStuActionGroupCount(DatatablesCriterias criterias) {
        Query query = new Query(entityManager, PCAPIStuActionGroupCount.class, criterias);
        return query.getResultDataSet();
    }

    @Override
    public DataSet<PCTeaAPIAction> findPCTeaAPIActionsWithDatatablesCriterias(DatatablesCriterias criterias) {
        Query query = new Query(entityManager, PCTeaAPIAction.class, criterias);
        return query.getResultDataSet();
    }

}
