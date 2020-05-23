package com.uca.capas.labo5.controller;


import com.uca.capas.labo5.domain.Estudiante;
import com.uca.capas.labo5.service.EstudianteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.validation.Valid;

@Controller
public class MainController {
	
    @Autowired
    private EstudianteService estudianteService;
    //mostrar lista de estudiantes

    @RequestMapping("/estudiante")
    public ModelAndView initMain(){
        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;
        try {
            estudiantes = estudianteService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        mav.addObject("estudiantes", estudiantes);
        mav.setViewName("main");
        return mav;
    }

    //mostrar por llave primaria
    @RequestMapping(value = "/mostrarEstudiante", method = RequestMethod.POST)
    public ModelAndView findOne(@RequestParam(value = "codigo") int id){
        ModelAndView mav = new ModelAndView();
        Estudiante estudiante = null;
        try{
            estudiante = estudianteService.findOne(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        mav.addObject("estudiante",estudiante);
        mav.setViewName("estudiante");
        return mav;
    }
    
    @PostMapping("/save")
	public ModelAndView guardar(@Valid @ModelAttribute Estudiante estudiante,BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("agregarEstudiante");
		}else {
			estudianteService.save(estudiante);
			List<Estudiante> estudiantes=null;
			try {
				estudiantes = estudianteService.findAll();
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.addObject("estudiantes", estudiantes);
			mav.setViewName("listaEstudiantes");
		}
		return mav;

	}
    
    @RequestMapping(value="/borrarEstudiante", method=RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value="codigo") int id) {
    	ModelAndView mav = new ModelAndView();
    	List<Estudiante> estudiantes = null;
    	try {
    		estudianteService.delete(id);
    		estudiantes = estudianteService.findAll();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	mav.addObject("estudiantes", estudiantes);
    	mav.setViewName("main");
    	return mav;
    }
    
    @GetMapping("/insertarEstudiante")
    public ModelAndView inicio() {
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("estudiante", new Estudiante());
    	mav.setViewName("agregarEstudiante");
    	return mav;
    }
    

    
}
