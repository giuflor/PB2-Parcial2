package ar.edu.unlam.pb2.sitemaDeCazadores.profugos;

public class Profugo implements IProfugo {
	private String nombre;
	private int inocencia;
	private int habilidad;
	private boolean nervioso;

	public Profugo(String nombre, int inocencia, int habilidad, boolean nervioso) {
		if (nombre == null || nombre.trim().isEmpty())
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
		if (inocencia < 0)
			throw new IllegalArgumentException("La inocencia no puede ser negativa.");
		if (habilidad < 0)
			throw new IllegalArgumentException("La habilidad no puede ser negativa.");
		this.nombre = nombre;
		this.inocencia = inocencia;
		this.habilidad = habilidad;
		this.nervioso = nervioso;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getInocencia() {
		return inocencia;
	}

	public Integer getHabilidad() {
		return habilidad;
	}

	public boolean isNervioso() {
		return nervioso;
	}

	public void reducirInocencia(int puntos) {
		this.inocencia = Math.max(0, this.inocencia - puntos);
	}

	public void reducirHabilidad(int puntos) {
		this.habilidad = Math.max(0, this.habilidad - puntos);
	}

	public void setIsNervioso(boolean nervioso) {
		this.nervioso = nervioso;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Profugo))
			return false;
		Profugo otro = (Profugo) o;
		return this.nombre.equals(otro.nombre);
	}

	@Override
	public int hashCode() {
		return this.nombre.hashCode();
	}

}
