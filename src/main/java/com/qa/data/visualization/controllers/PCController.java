package com.qa.data.visualization.controllers;

import com.qa.data.visualization.entities.pc.PCAPIStuActionGroupCount;
import com.qa.data.visualization.entities.pc.PCStuAPIAction;
import com.qa.data.visualization.entities.pc.PCTeaAPIAction;
import com.qa.data.visualization.services.PCActionService;
import com.web.spring.datatable.DataSet;
import com.web.spring.datatable.DatatablesCriterias;
import com.web.spring.datatable.DatatablesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/pc")
public class PCController {

    @Autowired
    private PCActionService pcActionService;

    @RequestMapping(value = "/get_pc_stu_api_action")
    @ResponseBody
    public DatatablesResponse<PCStuAPIAction> findPCStuAPIActionsWithDatatablesCriterias(HttpServletRequest request) {
        DatatablesCriterias criterias = DatatablesCriterias.getFromRequest(request);
        DataSet<PCStuAPIAction> actions = pcActionService.findPCStuAPIActionsWithDatatablesCriterias(criterias);
        return DatatablesResponse.build(actions, criterias);
    }

    @RequestMapping(value = "/get_pc_stu_api_action_group")
    @ResponseBody
    public DatatablesResponse<PCAPIStuActionGroupCount> findPCAPIStuActionGroupCount(HttpServletRequest request) {
        DatatablesCriterias criterias = DatatablesCriterias.getFromRequest(request);
        DataSet<PCAPIStuActionGroupCount> actions = pcActionService.findPCAPIStuActionGroupCount(criterias);
        return DatatablesResponse.build(actions, criterias);
    }

    @RequestMapping(value = "/get_pc_tea_api_action")
    @ResponseBody
    public DatatablesResponse<PCTeaAPIAction> findPCTeaAPIActionsWithDatatablesCriterias(HttpServletRequest request) {
        DatatablesCriterias criterias = DatatablesCriterias.getFromRequest(request);
        DataSet<PCTeaAPIAction> actions = pcActionService.findPCTeaAPIActionsWithDatatablesCriterias(criterias);
        return DatatablesResponse.build(actions, criterias);
    }

}
