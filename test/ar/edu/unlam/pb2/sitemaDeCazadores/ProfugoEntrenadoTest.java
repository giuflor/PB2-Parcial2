package ar.edu.unlam.pb2.sitemaDeCazadores.profugos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorRural;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.ProfugoEntrenado;
import ar.edu.unlam.pb2.sitemaDeCazadores.zona.Zona;

public class ProfugoEntrenadoTest {
	@Test
	public void queCazadorIntimideAProfugoEntrenadoYSeReduzcaCorrectamenteLosAtributos() {
		Profugo base = new Profugo("Entrena", 50, 80, true);
		ProfugoEntrenado entrenado = new ProfugoEntrenado(base);
		entrenado.entrenarEnElite();// Ya no podra ser nervioso
		entrenado.recibirProteccionLegal();// Su inocencia no puede bajar de 40
		entrenado.entrenarEnArtesMarciales();// Duplica la habilidad (hasta 100)
		Zona zona = new Zona("Zona Protegida");
		zona.agregarProfugo(entrenado);
		CazadorRural cazador = new CazadorRural("Rural Pro", 70);
		cazador.realizarCaptura(zona);
		// la inocencia se reduce pero no puede bajar de 40
		assertEquals(Integer.valueOf(40), entrenado.getInocencia());
		// El cazador rural vuelve nervioso al profugo,pero como tiene elite,sigue sin
		// ser nervioso
		assertFalse(entrenado.isNervioso());
		// La habilidad se duplica con el entrenamiento y deberia seguir duplicada
		assertEquals(Integer.valueOf(100), entrenado.getHabilidad());
	}
}