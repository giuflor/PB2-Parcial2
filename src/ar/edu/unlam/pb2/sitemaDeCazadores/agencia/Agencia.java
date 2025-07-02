package ar.edu.unlam.pb2.sitemaDeCazadores.agencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.Cazador;
import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionAgenciaSinCazadores;
import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionAgenciaSinProfugosCapturados;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;

public class Agencia {

	private String nombre;
	private HashSet<Cazador> cazadores;

	public Agencia(String nombre) {
		this.setNombre(nombre);
		this.cazadores = new HashSet<>();
	}

	public void agregarCazador(Cazador cazador) {
		this.cazadores.add(cazador);
	}

	public List<IProfugo> obtenerTodosLosProfugosCapturados() {
		List<IProfugo> todos = new ArrayList<>();
		for (Cazador cazador : cazadores) {
			todos.addAll(cazador.getCapturados());
		}
		return todos;
	}

	public IProfugo obtenerProfugoMasHabilCapturado() throws ExceptionAgenciaSinProfugosCapturados {

		IProfugo masHabil = null;
		int maxHabilidad = Integer.MIN_VALUE;

		for (Cazador cazador : cazadores) {
			for (IProfugo profugo : cazador.getCapturados()) {
				if (profugo.getHabilidad() > maxHabilidad) {
					maxHabilidad = profugo.getHabilidad();
					masHabil = profugo;
				}
			}
		}
		if (masHabil == null) {
			throw new ExceptionAgenciaSinProfugosCapturados();
		}
		return masHabil;
	}

	public Cazador obtenerCazadorConMasCapturas() throws ExceptionAgenciaSinCazadores{
		if (cazadores.isEmpty()) {
			throw new ExceptionAgenciaSinCazadores();
		}
		
		Cazador mejor = null;
		int maxCapturas = 0;

		for (Cazador cazador : cazadores) {
			if (cazador.getCapturados().size() > maxCapturas) {
				maxCapturas = cazador.getCapturados().size();
				mejor = cazador;
			}
		}
		return mejor;
	}

	public HashSet<Cazador> getCazadores() {
		return cazadores;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
