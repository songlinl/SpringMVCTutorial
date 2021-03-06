package com.kevinseries.studentadmissioncontroller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

@Controller
public class StudentAdmissionController{
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		//binder.setDisallowedFields(new String[] {"studentMobile"});
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "studentDOB", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class,  "studentName", new StudentNameEditor());
	}
	
	@RequestMapping(value="/admissionForm.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() {

		ModelAndView model = new ModelAndView("AdmissionForm");

		return model;
	}
	
	@ModelAttribute
	public void addingCommonObjects(Model model){
		model.addAttribute("headerMessage","Songlin University of Engineering, Canada");
	}
	
	@RequestMapping(value="/submitAdmissionForm.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("student1") Student student, BindingResult result) {
		
		if(result.hasErrors()){
			ModelAndView model = new ModelAndView("AdmissionForm");
			return model;
		}

		ModelAndView model = new ModelAndView("AdmissionSuccess");

		return model;
	}
}