package ar.edu.uba.fi.main;

import ar.edu.uba.fi.DTO.EstudianteDTO;
import ar.edu.uba.fi.model.businessobjet.Estudiante;
import ar.edu.uba.fi.DTOBuilder.EstudianteDTOBuilder;

public class principal {

	public static void main(String[] args) {

		Estudiante estudiante = new Estudiante(1234, "Statile", "SI", "Pablo", 33555666, "DNI");
		EstudianteDTO estudianteDTO = new EstudianteDTO();
		
		EstudianteDTOBuilder.populateEstudianteDTO(estudianteDTO, estudiante);
		
		System.out.println("Nombre del EstudianteDTO: " + estudianteDTO.getNombre());
		System.out.println("Apellido del EstudianteDTO: " + estudianteDTO.getApellido());
			
		
		for (Integer i : estudianteDTO.getListaNotas()){
			System.out.println("   -Nota: " + i );
		}
		
	}

}
