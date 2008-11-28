package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Resultado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ResultadoDTO;

public class ResultadoTransformerFromDTO implements Transformer {

	@Override
	public Object transform(Object arg0) {
		ResultadoDTO resultadoDTO = (ResultadoDTO) arg0;
		Resultado resultado = new Resultado();
		resultado.setId(resultadoDTO.getId());
		resultado.setOrdenLlegada(resultadoDTO.getOrdenLlegada());
		resultado.setTiempo(resultadoDTO.getTiempo());
		return resultadoDTO;
	}

}
