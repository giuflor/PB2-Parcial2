package ar.edu.unlam.pb2.sitemaDeCazadores;
import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;

import static org.junit.Assert.*;

public class ProfugoTest {
	@Test
	public void queSePuedaCrearUnProfugo() {
		Profugo p= new Profugo("Juan",30,75,true);
		assertEquals("Juan",p.getNombre());
		assertEquals(Integer.valueOf(30),p.getInocencia());
		assertEquals(Integer.valueOf(75),p.getHabilidad());
		assertTrue(p.isNervioso());
		
	}
	@Test
	public void queSePuedaReducirLaInocenciaYHabilidad() {
		Profugo p= new Profugo("Ana",50,60,false);
		p.reducirInocencia(10);
		p.reducirHabilidad(15);
		assertEquals(Integer.valueOf(40),p.getInocencia());
		assertEquals(Integer.valueOf(45),p.getHabilidad());
	}

}
