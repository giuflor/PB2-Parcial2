package ar.edu.unlam.pb2.sitemaDeCazadores.agencia;
package ar.edu.unlam.pb2.sitemaDeCazadores.agencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.Cazador;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;

public class Agencia {

	private String nombre;
	private HashSet<Cazador> cazadores;

	public Agencia(String nombre) {
		this.nombre = nombre;
		this.cazadores = new HashSet<>();
	}

	public void agregarCazador(Cazador cazador) {
		this.cazadores.add(cazador);
	}

	public List<Profugo> obtenerTodosLosProfugosCapturados() {
		List<Profugo> todos = new ArrayList<>();
		for (Cazador cazador : cazadores) {
			todos.addAll(cazador.getCapturados());
		}
		return todos;
	}

	

}
