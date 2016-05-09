package com.dhtmlx.demoapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxTime;
import com.dhtmlx.planner.extensions.DHXExtension;

@Controller
public class SimpleInitController {

	@RequestMapping({"/01_simple_init.html", "/index", "/"})
    public ModelAndView scheduler_01(HttpServletRequest request) throws Exception {
    	// creates and configures scheduler instance
    	DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
    	
    	s.localizations.set("es");
    	s.config.setScrollHour(8);
    	s.setWidth(1000);
    	s.templates.getEventText();

    	// sets events set
    	CustomEventsManager evs = new CustomEventsManager(request);
    	s.parse(evs.getEvents());

    	ModelAndView mnv = new ModelAndView("article");
    	mnv.addObject("title", "Calendario de Alvaky");
    	mnv.addObject("sample_name", "Calendario de Visitas");
    	mnv.addObject("sample_dsc", "");
    	// puts scheduler code in view
    	
		mnv.addObject("body", s.render());

        return mnv;
    }
	
}