package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorRural;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorUrbano;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
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

	@Test
	public void queLaZonaActualiceElListadoDeProfugosDespuesDeVariasCapturas() {
		Zona zona = new Zona("Zona Este");

		Profugo p1 = new Profugo("Uno", 30, 40, false);
		Profugo p2 = new Profugo("Dos", 30, 40, false);
		Profugo p3 = new Profugo("Tres", 30, 40, false);

		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);
		zona.agregarProfugo(p3);

		CazadorUrbano cazador = new CazadorUrbano("Rick", 80);
		cazador.realizarCaptura(zona);

		// Después de la captura, la zona debe estar vacía
		assertTrue(zona.getProfugos().isEmpty());
	}

	@Test
	public void queLaZonaPuedaEstarVaciaSinErrores() {
		Zona zona = new Zona("Zona Desierta");

		assertTrue(zona.getProfugos().isEmpty());
	}
	
	
}
