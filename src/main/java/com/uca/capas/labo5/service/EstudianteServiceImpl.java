package com.uca.capas.labo5.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.labo5.dao.EstudianteDAO;
import com.uca.capas.labo5.domain.Estudiante;

@Service
public class EstudianteServiceImpl implements EstudianteService {
	
	@Autowired
	EstudianteDAO estudianteDAO;

	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return estudianteDAO.findAll();
	}

	@Override
	public Estudiante findOne(Integer code) throws DataAccessException {
		// TODO Auto-generated method stub
		return estudianteDAO.findOne(code);
	}

	@Override
	@Transactional
	public void save(Estudiante estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteDAO.save(estudiante);
		
	}

	@Override
	@Transactional
	public void delete(Integer codigoEstudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteDAO.delete(codigoEstudiante);
	}

}
