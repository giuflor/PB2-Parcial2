package ar.edu.unlam.pb2.sitemaDeCazadores.cazadores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionCapturaFallida;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.zona.Zona;

public abstract class Cazador {
	protected String nombre;
	protected Integer experiencia;
	protected HashSet<IProfugo> capturados;
	protected HashSet<IProfugo> intimidados;

	public Cazador(String nombre, Integer experiencia) {
		this.nombre = nombre;
		this.experiencia = experiencia;
		this.capturados = new HashSet<>();
		this.intimidados = new HashSet<>();
	}

	public Cazador(String nombre) {
		this.nombre = nombre;
		this.experiencia = 50;
		this.capturados = new HashSet<>();
		this.intimidados = new HashSet<>();
	}

	public abstract boolean puedeCapturar(IProfugo profugo);

	protected abstract void intimidar(IProfugo profugo);

	public void realizarCaptura(Zona zona) {
		if (zona == null) {
			throw new ExceptionCapturaFallida();
		}

		List<Profugo> profugosACapturar = new ArrayList<>();
		int minHabilidadIntimidado = Integer.MAX_VALUE;

		for (IProfugo profugo : new ArrayList<>(zona.getProfugos())) {
			if (this.experiencia > profugo.getInocencia() && puedeCapturar(profugo)) {
				capturados.add(profugo);
				profugosACapturar.add((Profugo) profugo);
			} else if (this.experiencia > profugo.getInocencia() && !intimidados.contains(profugo)) {
				intimidar(profugo);
				intimidados.add(profugo);
				minHabilidadIntimidado = Math.min(minHabilidadIntimidado, profugo.getHabilidad());
			}
		}

		for (Profugo profugo : profugosACapturar) {
			zona.removerProfugo(profugo);
		}

		int experienciaGanada = (minHabilidadIntimidado == Integer.MAX_VALUE ? 0 : minHabilidadIntimidado)
				+ (2 * profugosACapturar.size());
		this.experiencia += experienciaGanada;
	}

	public boolean contieneCaptura(IProfugo profugo) {
		return capturados.contains(profugo);
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getExperiencia() {
		return experiencia;
	}

	public ArrayList<IProfugo> getCapturados() {
		return new ArrayList<IProfugo>(capturados);
	}

	public void agregarCaptura(IProfugo profugo) {
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
