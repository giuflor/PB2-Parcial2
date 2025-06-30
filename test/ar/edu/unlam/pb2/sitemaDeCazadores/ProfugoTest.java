package ar.edu.unlam.pb2.sitemaDeCazadores;

public class ProfugoTest {
	@Test
	public void queSePuedaCrearUnProfugo() {
		Profugo p= new Profugo("Juan",30,75,true);
		assertEquals("Juan",p.getNombre());
		assertEquals(Integer.valueOf(30),p.getInocencia());
		assertEquals(Integer.valueOf(75),p.getHabilidad());
		assertTrue(p.isNevioso());
	}
	

}
