package ar.edu.unlam.pb2.sitemaDeCazadores.zona;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.Cazador;
import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionCapturaFallida;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;

public class Zona {
	private String nombre;
	private Set<IProfugo> profugos = new HashSet<>();

	public Zona(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarProfugo(IProfugo p) {
		profugos.add(p);
	}

	public Set<IProfugo> getProfugos() {
		return profugos;
	}

	public void operarCaptura(Cazador cazador) {
		List<IProfugo> copia = new ArrayList<>(profugos);
		for (IProfugo p : copia) {
			try {
				cazador.capturar(p);
				profugos.remove(p);
			} catch (ExceptionCapturaFallida e) {
				// intimidacion ya aplicada
			}
		}
	}

}

//Lo único que necesitás para que todo compile y funcione es:

//Que tu clase CazadorRural extienda una clase Cazador que tenga:

//Un método capturar(IProfugo p) que pueda lanzar ExceptionCapturaFallida.

//Un método getCapturados() que devuelva una colección (por ejemplo Set<IProfugo>).
