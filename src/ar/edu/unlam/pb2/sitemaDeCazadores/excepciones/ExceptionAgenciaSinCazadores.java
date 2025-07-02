package ar.edu.unlam.pb2.sitemaDeCazadores.excepciones;

public class ExceptionAgenciaSinCazadores extends RuntimeException {

	public ExceptionAgenciaSinCazadores() {
		// TODO Auto-generated constructor stub
		super("La agencia no tiene cazadores");
	}

	public ExceptionAgenciaSinCazadores(String mensaje) {
		super(mensaje);
	}
}
