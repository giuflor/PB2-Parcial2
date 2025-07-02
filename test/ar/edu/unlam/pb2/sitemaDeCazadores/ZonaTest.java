package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorRural;
import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionElProfugoNoEstaEnLaZona;
import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionElProfugoYaEstaEnLaZona;
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
		zona.agregarProfugos(List.of(profugo, profugo));
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

		zona.buscarYRemoverProfugo(profugo);
	}
}