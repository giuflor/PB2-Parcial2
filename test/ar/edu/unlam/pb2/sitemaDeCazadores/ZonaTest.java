package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.ProfugoEntrenado;
import ar.edu.unlam.pb2.sitemaDeCazadores.zona.Zona;

public class ZonaTest {
	@Test

	public void queZonaPermitaCapturarYReducirProfugos() {
		Zona zona = new Zona("Callejon");
		CazadorRural cazador = new CazadorRural("Rural");
		IProfugo base = new Profugo("Fuga", 0, 40, true);
		ProfugoEntrenado entrenado = new ProfugoEntrenado(base);
		entrenado.entrenarEnArtesMarciales();
		zona.agregarProfugo(entrenado);
		zona.operarCaptura(cazador);
		assertTrue(cazador.getCapturados().contains(entrenado));
		assertTrue(zona.getProfugos().isEmpty());
	}
}
