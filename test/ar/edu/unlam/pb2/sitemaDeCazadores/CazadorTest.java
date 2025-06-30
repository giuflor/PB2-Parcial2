package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class CazadorTest {

	@Test
	public void queUnCazadorUrbanoPuedaCapturarUnProfugoNoNerviosoConMenorInocencia() {
		CazadorUrbano cazador = new CazadorUrbano("Rick", 80);
		Profugo profugo = new Profugo("Smith", 60, 30, false);
		Zona zona = new Zona("Ciudad");
		zona.agregarProfugo(profugo);

		cazador.realizarCaptura(zona);

		assertTrue(cazador.getCapturados().contains(profugo));
		assertFalse(zona.getProfugos().contains(profugo));
	}

	@Test
	public void queUnCazadorIntimideSiNoPuedeCapturar() {
		CazadorRural cazador = new CazadorRural("Daryl", 80);
		Profugo profugo = new Profugo("Smith", 60, 30, false);
		Zona zona = new Zona("Campo");
		zona.agregarProfugo(profugo);

		cazador.realizarCaptura(zona);

		assertEquals((Integer) (58), profugo.getNivelInocencia());
		assertTrue(profugo.esNervioso());
	}

	@Test
	public void queElCazadorSumeExperienciaCorrectamente() {
		CazadorSigiloso cazador = new CazadorSigiloso("Ghost", 90);
		Profugo p1 = new Profugo("Uno", 60, 40, true);
		Profugo p2 = new Profugo("Dos", 60, 70, true); // no capturable
		Zona zona = new Zona("Subterraneo");
		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);

		cazador.realizarCaptura(zona);

		// Captura p1 (+2), intimida p2 (mínimo habilidad 70)
		assertEquals((Integer) (90 + 2 + 70), cazador.getExperiencia());
	}

	@Test(expected = ExceptionCapturaFallida.class)
	public void queLanceExcepcionSiZonaEsNula() throws ExceptionCapturaFallida {
		CazadorUrbano cazador = new CazadorUrbano("Rick", 80);
		Zona zona = null;
		cazador.realizarCaptura(zona);
	}

	@Test
	public void queNoSeIntimideNiSeCaptureUnProfugoConMayorInocenciaQueLaExperienciaDelCazador() {
		CazadorUrbano cazador = new CazadorUrbano("Rafael", 30);
		Profugo profugo = new Profugo("Pedro", 60, 30, false); // inocencia > experiencia
		Zona zona = new Zona("Ciudad");
		zona.agregarProfugo(profugo);

		cazador.realizarCaptura(zona);

		assertFalse(cazador.getCapturados().contains(profugo));
		assertTrue(zona.getProfugos().contains(profugo));
	}

	@Test
	public void queUnCazadorNoRepitaCapturaDelMismoProfugo() {
		CazadorUrbano cazador = new CazadorUrbano("Rick", 100);
		Profugo p1 = new Profugo("Smith", 50, 30, false);
		Zona zona = new Zona("Centro");
		zona.agregarProfugo(p1);

		cazador.realizarCaptura(zona);
		zona.agregarProfugo(p1); // lo volvemos a agregar (no debería volver a capturarlo)
		cazador.realizarCaptura(zona);

		assertEquals(1, cazador.getCapturados().size()); // solo una vez
	}

	@Test
	public void queSeMantengaElOrdenDeCapturas() {
		CazadorUrbano cazador = new CazadorUrbano("Rick", 100);
		Profugo p1 = new Profugo("Uno", 50, 30, false);
		Profugo p2 = new Profugo("Dos", 40, 20, false);
		Zona zona = new Zona("Villa");
		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);

		cazador.realizarCaptura(zona);

		List<Profugo> capturados = cazador.getCapturados();
		assertEquals("Uno", capturados.get(0).getNombre());
		assertEquals("Dos", capturados.get(1).getNombre());
	}

	@Test
	public void queNoSeIntimideAUnProfugoYaIntimidadoEnOtraZona() {
		CazadorSigiloso cazador = new CazadorSigiloso("Sombra", 100);
		Profugo p = new Profugo("Invisible", 90, 90, true);
		Zona zona1 = new Zona("Zona A");
		Zona zona2 = new Zona("Zona B");

		zona1.agregarProfugo(p);
		cazador.realizarCaptura(zona1);

		int habilidadDespues = p.getNivelHabilidad();

		zona2.agregarProfugo(p);
		cazador.realizarCaptura(zona2);

		// No debería volver a intimidar
		assertEquals((Integer) habilidadDespues, p.getNivelHabilidad());
	}

	@Test
	public void queUnCazadorNoSumeExperienciaSiNoHaceNada() {
		CazadorRural cazador = new CazadorRural("Gringo", 30);
		Profugo p = new Profugo("Inocente", 50, 60, false); // No puede ser capturado ni intimidado
		Zona zona = new Zona("Lejana");
		zona.agregarProfugo(p);

		cazador.realizarCaptura(zona);

		assertEquals((Integer) 30, cazador.getExperiencia()); // No cambió
	}

}
