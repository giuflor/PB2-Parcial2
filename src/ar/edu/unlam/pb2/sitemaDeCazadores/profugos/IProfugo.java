package ar.edu.unlam.pb2.sitemaDeCazadores.profugos;

public interface IProfugo {
	String getNombre();

	Integer getInocencia();

	Integer getHabilidad();

	boolean isNervioso();

	void reducirInocencia(int puntos);

	void reducirHabilidad(int puntos);

	void setIsNervioso(boolean nervioso);
}

