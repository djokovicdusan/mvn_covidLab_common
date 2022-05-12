package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import domen.Laborant;
import domen.OpstiDomenskiObjekat;
import domen.Pacijent;
import domen.TerminTestiranja;

class TerminTestiranjaTest {
	private TerminTestiranja terminTestiranja;
	@Mock
	ResultSet rs;

	@BeforeEach
	void setUp() throws Exception {
		terminTestiranja = new TerminTestiranja();
		terminTestiranja.setLaborant(new Laborant());
		terminTestiranja.setPacijent(new Pacijent());

	}

	@AfterEach
	void tearDown() throws Exception {
		terminTestiranja = null;
		rs = null;
	}

	@Test
	void testParametrizedConstructor() {
		Laborant laborant = new Laborant((long) 16, "Pera", "Djokovic", 15);
		Pacijent pacijent = new Pacijent((long) 13, "Dule", "Djokovic", new Date(1999, 16, 16), "test", "test",
				laborant);
		terminTestiranja = new TerminTestiranja((long) 15, new Date(1999, 16, 6), "test", pacijent, laborant);
		assertEquals(15, terminTestiranja.getTerminTestiranjaId());
		assertEquals(new Date(1999, 16, 6), terminTestiranja.getDatum());
		assertEquals("test", terminTestiranja.getNapomena());
		assertEquals(laborant, terminTestiranja.getLaborant());
		assertEquals(pacijent, terminTestiranja.getPacijent());

		assertNotNull(terminTestiranja);
	}

	@Test
	void testGetTerminId() {
		terminTestiranja.setTerminTestiranjaId((long) 33);
		assertEquals(33, terminTestiranja.getTerminTestiranjaId());
	}

	@Test
	void testGetDatum() {
		terminTestiranja.setDatum(new Date(1999, 16, 16));
		assertEquals(new Date(1999, 16, 16), terminTestiranja.getDatum());
	}

	@Test
	void testGetNapomena() {
		terminTestiranja.setNapomena("123");
		assertEquals("123", terminTestiranja.getNapomena());
	}

	@Test
	void testGetPacijent() {
		terminTestiranja.setPacijent(new Pacijent());
		assertEquals(new Pacijent(), terminTestiranja.getPacijent());
	}

	@Test
	void testGetLaborant() {
		terminTestiranja.setLaborant(new Laborant());
		assertEquals(new Laborant(), terminTestiranja.getLaborant());
	}

	@Test
	void testToString() {
		terminTestiranja = new TerminTestiranja((long) 15, new Date(1999, 16, 6), "test", new Pacijent(),
				new Laborant());

		terminTestiranja.getPacijent().setIme("Pera");
		String test = terminTestiranja.toString();
		assertTrue(test.contains("Pera"));

	}

	@Test
	void testGetTableName() {
		assertEquals("termintestiranja", terminTestiranja.getTableName());
	}

	@Test
	void testGetResult() {
		try {
			rs = Mockito.mock(ResultSet.class);
			Mockito.when(rs.next()).thenReturn(false);
			assertNull(terminTestiranja.getResult(rs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetList() {
		try {

			rs = Mockito.mock(ResultSet.class);

			Mockito.when(rs.getLong("id")).thenReturn((long) 13);
			Mockito.when(rs.getLong("laborantId")).thenReturn((long) 13);
			Mockito.when(rs.getLong("pacijentId")).thenReturn((long) 13);
			Mockito.when(rs.getDate("datum")).thenReturn(new java.sql.Date(0));
			Mockito.when(rs.getString("napomena")).thenReturn("test");
			Mockito.when(rs.next()).thenReturn(true).thenReturn(false);

			List<OpstiDomenskiObjekat> terminTestiranjaList = terminTestiranja.getList(rs);
			TerminTestiranja terminTestiranjaDummy = (TerminTestiranja) terminTestiranjaList.get(0);

			assertNotNull(terminTestiranjaList);
			assertNotNull(terminTestiranjaDummy);

			assertEquals(13, terminTestiranjaDummy.getPacijent().getPacijentId());
			assertEquals(13, terminTestiranjaDummy.getLaborant().getLaborantId());
			assertEquals("test", terminTestiranjaDummy.getNapomena());

			assertEquals(new java.sql.Date(0), terminTestiranjaDummy.getDatum());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetAttributeNames() {
		assertEquals("datum,napomena,pacijentId,laborantId", terminTestiranja.getAttributeNames());
	}

	@Test
	void testGetUnknownValues() {
		assertEquals("?,?,?,?", terminTestiranja.getUnknownValues());
	}

	@Test
	void testGetId() {
		terminTestiranja.setTerminTestiranjaId((long) 12);
		assertEquals(String.valueOf(12), terminTestiranja.getID(terminTestiranja));
	}

	@Test
	void testGetOrderCondition() {

		assertEquals("id", terminTestiranja.getOrderCondition());
	}

}
