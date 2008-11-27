package ar.edu.uba.fi.DTOBuilder;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import ar.edu.uba.fi.DTO.EstudianteDTO;
import ar.edu.uba.fi.model.businessobjet.Estudiante;

public class EstudianteDTOBuilder {

	public static void populateEnfermeroDTO(EstudianteDTO estudianteDTO, Estudiante estudiante){
		
		try {
			BeanUtils.copyProperties(estudianteDTO, estudiante);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	public static EstudianteDTO createEnfermeroDTO(Estudiante estudiante){
		
		EstudianteDTO estudianteDTO = new EstudianteDTO();
		try {
			BeanUtils.copyProperties(estudianteDTO, estudiante);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return estudianteDTO;
	}
	
	public static Estudiante createEnfermero(EstudianteDTO estudianteDTO){
		
		Estudiante estudiante = new Estudiante();
		try {
			BeanUtils.copyProperties(estudiante, estudianteDTO);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return estudiante;
	}
}
