package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorRural;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.ProfugoEntrenado;
import ar.edu.unlam.pb2.sitemaDeCazadores.zona.Zona;

public class ZonaTest {
	@Test

	public void queZonaPermitaCapturarYReducirProfugos() {
		Zona zona = new Zona("Callejon");
		CazadorRural cazador = new CazadorRural("Rural");
		Profugo profugo = new Profugo("Fuga", 0, 40, true);
		zona.agregarProfugo(profugo);
		cazador.realizarCaptura(zona);
		assertTrue(cazador.contieneCaptura(profugo));
		assertTrue(zona.getProfugos().isEmpty());
	}
}