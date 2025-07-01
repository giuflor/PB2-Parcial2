package ar.edu.unlam.pb2.sitemaDeCazadores.cazadores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionCapturaFallida;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.zona.Zona;

public abstract class Cazador {
	private String nombre;
	private Integer experiencia;
	private ArrayList<Profugo> capturados;
	private HashSet<Profugo> intimidados;

	public Cazador(String nombre, Integer experiencia) {
		this.nombre = nombre;
		this.experiencia = experiencia;
		this.capturados = new ArrayList<>();
		this.intimidados = new HashSet<>();
	}

	public Cazador(String nombre) {
		this.nombre = nombre;
		this.experiencia = 50;
		this.capturados = new ArrayList<>();
		this.intimidados = new HashSet<>();
	}

	public abstract boolean puedeCapturar(Profugo profugo);

	public void realizarCaptura(Zona zona) throws ExceptionCapturaFallida {
		if (zona == null) {
			throw new ExceptionCapturaFallida();
		}

		List<Profugo> aCapturar = new ArrayList<>();
		List<Profugo> profugosZona = new ArrayList<>(zona.getProfugos());
		List<Integer> habilidadesIntimidados = new ArrayList<>();

		for (Profugo profugo : profugosZona) {
			if (this.experiencia > profugo.getNivelInocencia() && puedeCapturar(profugo)) {
				capturados.add(profugo);
				aCapturar.add(profugo);
			} else if (this.experiencia > profugo.getNivelInocencia() && !intimidados.contains(profugo)) {
				intimidar(profugo);
				intimidados.add(profugo);
				habilidadesIntimidados.add(profugo.getNivelHabilidad());
			}
		}

		// Remover capturados de la zona
		for (Profugo p : aCapturar) {
			zona.removerProfugo(p);
		}

		// Sumar experiencia
		int minHabilidad = habilidadesIntimidados.stream().min(Integer::compare).orElse(0);
		this.experiencia += minHabilidad + (2 * aCapturar.size());
	}

	protected abstract void intimidar(Profugo profugo);

	public String getNombre() {
		return nombre;
	}

	public Integer getExperiencia() {
		return experiencia;
	}

	public List<Profugo> getCapturados() {
		return capturados;
	}

	public void agregarCaptura(Profugo profugo) {
		if (!capturados.contains(profugo)) {
			capturados.add(profugo);
		} else {
			throw new RuntimeException("El prófugo ya fue capturado.");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cazador))
			return false;
		Cazador otro = (Cazador) obj;
		return this.nombre.equals(otro.nombre);
	}

	@Override
	public int hashCode() {
		return nombre.hashCode();
	}
}
