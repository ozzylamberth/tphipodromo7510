package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Resultado;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ResultadoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ResultadoDTO;

public class ResultadoTransformerFromDTO implements Transformer {

	private ResultadoDao resultadoDao;
	
	public ResultadoTransformerFromDTO() {
		this.resultadoDao = HibernateDaoFactory.getInstance().getResultadoDAO();
	}
	
	@Override
	public Object transform(Object arg0) {
		ResultadoDTO resultadoDTO = (ResultadoDTO) arg0;
		Resultado resultado = this.getResultado(resultadoDTO);
		resultado.setOrdenLlegada(resultadoDTO.getOrdenLlegada());
		resultado.setTiempo(resultadoDTO.getTiempo());
		return resultadoDTO;
	}
	
	private Resultado getResultado(ResultadoDTO resultadoDTO) {
		try {
			return this.resultadoDao.buscarPorId(resultadoDTO.getId());
		} catch (ObjetoInexistenteException e) {
			return new Resultado();
		}
	}

}
