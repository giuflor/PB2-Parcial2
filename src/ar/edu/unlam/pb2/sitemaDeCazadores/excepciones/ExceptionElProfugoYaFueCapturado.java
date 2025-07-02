package ar.edu.unlam.pb2.sitemaDeCazadores.excepciones;

public class ExceptionElProfugoYaFueCapturado extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExceptionElProfugoYaFueCapturado(String mensaje) {
		super(mensaje);
	}

	public ExceptionElProfugoYaFueCapturado() {
		super("El profugo ya fue capturado");
	}
}
