package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.Timestamp;
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

class PacijentTest {
	private Pacijent pacijent;
	@Mock
	ResultSet rs;

	@BeforeEach
	void setUp() throws Exception {
		pacijent = new Pacijent();

	}

	@AfterEach
	void tearDown() throws Exception {
		pacijent = null;
		rs = null;
	}

	@Test
	void testParametrizedConsructor() {
		Laborant laborant = new Laborant((long) 16, "Pera", "Djokovic", 15);

		pacijent = new Pacijent((long) 13, "Dule", "Djokovic", new Date(1999, 16, 16), "test", "test", laborant);
		assertNotNull(pacijent);
		assertEquals(13, pacijent.getPacijentId());
		assertEquals("Dule", pacijent.getIme());
		assertEquals("Djokovic", pacijent.getPrezime());
		assertEquals(new Date(1999, 16, 16), pacijent.getDatumRodjenja());
		assertEquals("test", pacijent.getTelefon());
		assertEquals("test", pacijent.getEmail());
		assertEquals(laborant, pacijent.getLaborant());

	}

	@Test
	void testGetPacijentId() {
		pacijent.setPacijentId((long) 33);
		assertEquals(33, pacijent.getPacijentId());
	}

	@Test
	void testGetIme() {
		pacijent.setIme("Pera");
		assertEquals("Pera", pacijent.getIme());
	}



	@Test
	void testGetPrezime() {
		pacijent.setPrezime("Peric");
		assertEquals("Peric", pacijent.getPrezime());
	}


	@Test
	void testGetDatumRodjenja() {
		pacijent.setDatumRodjenja(new Date(1999, 16, 16));
		assertEquals(new Date(1999, 16, 16), pacijent.getDatumRodjenja());
	}

	@Test
	void testGetTelefon() {
		pacijent.setTelefon("123");
		assertEquals("123", pacijent.getTelefon());
	}

	@Test
	void testGetEmail() {
		pacijent.setEmail("gmail");
		assertEquals("gmail", pacijent.getEmail());
	}

	@Test
	void testGetLaborant() {
		Laborant laborant = new Laborant((long) 16, "Pera", "Djokovic", 15);
		pacijent.setLaborant(laborant);
		assertEquals(laborant, pacijent.getLaborant());

	}

	@Test
	void testToString() {
		pacijent.setIme("Pera");
		pacijent.setPrezime("Peric");
		String display = pacijent.getIme() + " " + pacijent.getPrezime();
		assertEquals("Pera Peric", display);
	}

	@Test
	void testGetTableName() {
		assertEquals("pacijent", pacijent.getTableName());
	}

	@Test
	void testGetResult() {
		// to-do
		// remove try catch blocks from all test methods
		try {
			rs = Mockito.mock(ResultSet.class);
			Mockito.when(rs.next()).thenReturn(false);
			assertNull(pacijent.getResult(rs));
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
			Mockito.when(rs.getString("ime")).thenReturn("Pera");
			Mockito.when(rs.getString("prezime")).thenReturn("Peric");
//			Date fakeDate = new Date(0);
			Mockito.when(rs.getDate("datumRodjenja")).thenReturn(new java.sql.Date(0));
			Mockito.when(rs.getString("telefon")).thenReturn("test");
			Mockito.when(rs.getString("email")).thenReturn("test");
			Mockito.when(rs.next()).thenReturn(true).thenReturn(false);

			List<OpstiDomenskiObjekat> pacijentList = pacijent.getList(rs);
			Pacijent pacijentDummy = (Pacijent) pacijentList.get(0);

			assertNotNull(pacijentList);
			assertNotNull(pacijentDummy);

			assertEquals(13, pacijentDummy.getPacijentId());
			assertEquals(13, pacijentDummy.getLaborant().getLaborantId());
			assertEquals("Pera", pacijentDummy.getIme());
			assertEquals("Peric", pacijentDummy.getPrezime());
			assertEquals("test", pacijentDummy.getEmail());
			assertEquals("test", pacijentDummy.getTelefon());
			assertEquals(new java.sql.Date(0), pacijentDummy.getDatumRodjenja());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetAttributeNames() {
		assertEquals("ime,prezime,datumRodjenja,telefon,email,laborantId", pacijent.getAttributeNames());
	}

	@Test
	void testGetUnknownValues() {
		assertEquals("?,?,?,?,?,?", pacijent.getUnknownValues());
	}

	@Test
	void testGetUpdateQuery() {
		assertEquals("ime=?,prezime=?,datumRodjenja=?,telefon=?,email=?,laborantId=?", pacijent.getUpdateQuery());
	}

	@Test
	void testGetId() {
		pacijent.setPacijentId((long) 12);
		assertEquals(String.valueOf(12), pacijent.getID(pacijent));
	}

	@Test
	void testGetCondition() {
		pacijent.setIme("Dule");
		pacijent.setPrezime("Djokovic");
//		if (pacijent.getLaborant() == null) {
//			String desire = //

		assertEquals("ime LIKE '" + pacijent.getIme() + "%' AND prezime LIKE '" + pacijent.getPrezime() + "%'",
				pacijent.getCondition(pacijent));

	}

	@Test
	void testGetConditionSecondCase() {
		pacijent.setIme("Dule");
		pacijent.setPrezime("Djokovic");
		Laborant laborant = new Laborant((long) 16, "Pera", "Djokovic", 15);

		pacijent.setLaborant(laborant);
//		if (pacijent.getLaborant() == null) {
//			String desire = //

		assertEquals("laborantId=16", pacijent.getCondition(pacijent));

	}

	@Test
	void testGetOrderCondition() {

		assertEquals("ime", pacijent.getOrderCondition());
	}

}
