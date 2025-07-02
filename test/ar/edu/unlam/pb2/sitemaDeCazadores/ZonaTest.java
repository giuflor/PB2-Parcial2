package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorRural;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorUrbano;
import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionElProfugoNoEstaEnLaZona;
import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionElProfugoYaEstaEnLaZona;
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
	public void queSeAgregueUnProfugoALaZona() {
		Zona zona = new Zona("Zona Norte");
		Profugo profugo = new Profugo("Smith", 30, 40, false);

		zona.agregarProfugo(profugo);

		assertTrue(zona.getProfugos().contains(profugo));
	}

	@Test(expected = ExceptionElProfugoYaEstaEnLaZona.class)
	public void queNoSeAgreguenProfugosDuplicadosALaZona() throws ExceptionElProfugoYaEstaEnLaZona {
		Zona zona = new Zona("Zona Sur");
		Profugo profugo = new Profugo("Smith", 30, 40, false);

		// Intentamos agregarlo dos veces y tirará exception
		zona.agregarProfugo(profugo);
		zona.agregarProfugo(profugo);
	}

	@Test
	public void queSePuedaRemoverUnProfugoDeLaZona(){
		Zona zona = new Zona("Zona Oeste");
		Profugo profugo = new Profugo("Smith", 30, 40, false);

		zona.agregarProfugo(profugo);
		zona.removerProfugo(profugo);

		assertTrue(zona.getProfugos().isEmpty());
	}
	
	@Test(expected = ExceptionElProfugoNoEstaEnLaZona.class)
	public void queNoSePuedaRemoverUnProfugoQueNoEstaEnLaZona() throws ExceptionElProfugoNoEstaEnLaZona{
		Zona zona = new Zona("Zona Oeste");
		Profugo profugo = new Profugo("Smith", 30, 40, false);

		zona.removerProfugo(profugo);
	}

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
