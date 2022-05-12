package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import domen.Administrator;
import domen.Laborant;
import domen.OpstiDomenskiObjekat;

class LaborantTest {
	private Laborant laborant;
	@Mock
	ResultSet rs;

	@BeforeEach
	void setUp() throws Exception {
		laborant = new Laborant();
	}

	@AfterEach
	void tearDown() throws Exception {
		laborant = null;
		rs = null;
	}

	@Test
	void testParametrizedConstructor() {
		laborant = new Laborant((long) 16, "Pera", "Djokovic", 15);
		assertNotNull(laborant);
		assertEquals(16, laborant.getLaborantId());
		assertEquals("Pera", laborant.getIme());
		assertEquals("Djokovic", laborant.getPrezime());
		assertEquals(15, laborant.getBrojOrdinacije());
	}

	@Test
	void testGetLaborantId() {
		laborant.setLaborantId((long) 33);
		assertEquals(33, laborant.getLaborantId());
	}

	@Test
	void testGetIme() {
		laborant.setIme("Pera");
		assertEquals("Pera", laborant.getIme());
	}
	@Test
	void testGetImeShortString() {
		assertThrows(java.lang.RuntimeException.class, () -> laborant.setIme("d"));
	}

	@Test
	void testGetPrezime() {
		laborant.setPrezime("Peric");
		assertEquals("Peric", laborant.getPrezime());
	}
	@Test
	void testGetPrezimeShortString() {
		assertThrows(java.lang.RuntimeException.class, () -> laborant.setPrezime("d"));
	}

	@Test
	void testGetBrojOrdinacije() {
		laborant.setBrojOrdinacije(44);
		assertEquals(44, laborant.getBrojOrdinacije());
	}

	@Test
	void testToString() {
		laborant.setIme("Pera");
		laborant.setPrezime("Peric");
		String display = laborant.getIme() + " " + laborant.getPrezime();
		assertEquals("Pera Peric", display);
	}

	@Test
	void testGetTableName() {
		assertEquals("laborant", laborant.getTableName());
	}

	@Test
	void testGetResult() {
		try {
			rs = Mockito.mock(ResultSet.class);
			Mockito.when(rs.next()).thenReturn(false);
			assertNull(laborant.getResult(rs));
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
			Mockito.when(rs.getString("ime")).thenReturn("Pera");
			Mockito.when(rs.getString("prezime")).thenReturn("Peric");
			Mockito.when(rs.getInt("brojOrdinacije")).thenReturn(13);
			Mockito.when(rs.next()).thenReturn(true).thenReturn(false);

			List<OpstiDomenskiObjekat> laborantList = laborant.getList(rs);
			Laborant laborantDummy = (Laborant) laborantList.get(0);

			assertNotNull(laborantList);
			assertNotNull(laborantDummy);

			assertEquals(13, laborantDummy.getLaborantId());
			assertEquals("Pera", laborantDummy.getIme());
			assertEquals("Peric", laborantDummy.getPrezime());
			assertEquals(13, laborantDummy.getBrojOrdinacije());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetAttributeNames() {
		assertEquals("ime,prezime,brojOrdinacije", laborant.getAttributeNames());
	}

	@Test
	void testGetUnknownValues() {
		assertEquals("?,?,?", laborant.getUnknownValues());
	}

	@Test
	void testGetUpdateQuery() {
		assertEquals("ime=?,prezime=?,brojOrdinacije=?", laborant.getUpdateQuery());
	}

	@Test
	void testGetCondition() {
		laborant.setPrezime("Djokovic");
		assertEquals("prezime LIKE '" + "Djokovic" + "%'", laborant.getCondition(laborant));
	}

	@Test
	void testGetId() {
		laborant.setLaborantId((long) 12);
		assertEquals(String.valueOf(12), laborant.getID(laborant));
	}

	@Test
	void testGetOrderCondition() {

		assertEquals("prezime", laborant.getOrderCondition());
	}

}
