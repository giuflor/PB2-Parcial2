package ar.edu.unlam.pb2.sitemaDeCazadores.cazadores;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;

public class CazadorSigiloso extends Cazador {

	public CazadorSigiloso(String nombre, Integer experiencia) {
		// TODO Auto-generated constructor stub
		super(nombre, experiencia);
	}

	@Override
	public boolean puedeCapturar(Profugo profugo) {
		return profugo.getNivelHabilidad() < 50;
	}

	@Override
	protected void intimidar(Profugo profugo) {
		profugo.reducirInocencia(2);
		profugo.setNivelHabilidad(Math.max(0, profugo.getNivelHabilidad() - 5));
	}

}
