package ar.edu.unlam.pb2.sitemaDeCazadores.cazadores;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;

public class CazadorRural extends Cazador {

	public CazadorRural(String nombre, Integer experiencia) {
		// TODO Auto-generated constructor stub
		super(nombre, experiencia);
	}

	@Override
	public boolean puedeCapturar(Profugo profugo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void intimidar(Profugo profugo) {
		// TODO Auto-generated method stub

	}

}
