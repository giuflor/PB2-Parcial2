package ar.edu.unlam.pb2.sitemaDeCazadores.excepciones;

public class ExceptionCapturaFallida extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExceptionCapturaFallida() {
		super("La captura ha fallado. La zona no puede ser nula.");
	}

}
