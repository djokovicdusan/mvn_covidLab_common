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

import domen.OpstiDomenskiObjekat;
import domen.Rezultat;
import domen.TerminTestiranja;

class RezultatTest {
	private Rezultat rezultat;
	@Mock
	ResultSet rs;

	@BeforeEach
	void setUp() throws Exception {
		// rezultat = new Rezultat(new domen.Test(), new TerminTestiranja(), true);
		rezultat = new Rezultat();
	}

	@AfterEach
	void tearDown() throws Exception {
		rezultat = null;
		rs = null;
	}

	@Test
	void testParametrizedConsructor() {
		rezultat = new Rezultat(new domen.Test(), new TerminTestiranja(), true);

		assertNotNull(rezultat);
		assertEquals(new domen.Test(), rezultat.getTest());
		assertEquals(new TerminTestiranja(), rezultat.getTerminTestiranja());
		assertEquals(true, rezultat.isVrednost());

	}

	@Test
	void testGetTest() {
		rezultat.setTest(new domen.Test());
		assertEquals(new domen.Test(), rezultat.getTest());

	}

	@Test
	void testGetTerminTest() {
		rezultat.setTerminTestiranja(new TerminTestiranja());
		assertEquals(new TerminTestiranja(), rezultat.getTerminTestiranja());

	}

	@Test
	void testGetVrednost() {
		rezultat.setVrednost(false);
		assertEquals(false, rezultat.isVrednost());

	}

	@Test
	void testGetTableName() {
		assertEquals("rezultat", rezultat.getTableName());
	}

	@Test
	void testGetResult() {
		try {
			rs = Mockito.mock(ResultSet.class);
			Mockito.when(rs.next()).thenReturn(false);
			assertNull(rezultat.getResult(rs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetList() {
		try {

			rs = Mockito.mock(ResultSet.class);

			Mockito.when(rs.getLong("testId")).thenReturn((long) 13);
			Mockito.when(rs.getLong("terminTestiranjaId")).thenReturn((long) 13);
			Mockito.when(rs.getBoolean("vrednost")).thenReturn(true);
			Mockito.when(rs.next()).thenReturn(true).thenReturn(false);

			List<OpstiDomenskiObjekat> rezultatList = rezultat.getList(rs);
			Rezultat rezultatDummy = (Rezultat) rezultatList.get(0);

			assertNotNull(rezultatList);
			assertNotNull(rezultatDummy);

			assertEquals(13, rezultatDummy.getTest().getTestId());
			assertEquals(13, rezultatDummy.getTerminTestiranja().getTerminTestiranjaId());
			assertEquals(true, rezultatDummy.isVrednost());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetAttributeNames() {
		assertEquals("testId,terminTestiranjaId,vrednost", rezultat.getAttributeNames());
	}

	@Test
	void testGetUnknownValues() {
		assertEquals("?,?,?", rezultat.getUnknownValues());
	}

	@Test
	void testGetUpdateQuerry() {
		assertEquals("vrednost=?", rezultat.getUpdateQuery());
	}

	@Test
	void testGetCondition() {
		rezultat.setTerminTestiranja(new TerminTestiranja());
		rezultat.getTerminTestiranja().setTerminTestiranjaId((long) 1);
		assertEquals("terminTestiranjaId=1", rezultat.getCondition(rezultat));

	}

	@Test
	void testGetOrderCondition() {

		assertEquals("vrednost", rezultat.getOrderCondition());
	}

}
