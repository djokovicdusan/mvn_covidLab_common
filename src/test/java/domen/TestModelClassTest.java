package domen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import domen.Laborant;
import domen.OpstiDomenskiObjekat;

class TestModelClassTest {
	private domen.Test covidTest;
	@Mock
	ResultSet rs;

	@BeforeEach
	void setUp() throws Exception {
		covidTest = new domen.Test();
	}

	@AfterEach
	void tearDown() throws Exception {
		covidTest = null;
		rs = null;
	}

	@Test
	void testParametrizedConstructor() {
		covidTest = new domen.Test((long) 432, "magicTest", "desc", "bestTest", "asdf");
		assertNotNull(covidTest);
		assertEquals((long) 432, covidTest.getTestId());
		assertEquals("magicTest", covidTest.getNaziv());
		assertEquals("desc", covidTest.getOpis());
		assertEquals("bestTest", covidTest.getVrsta());
		assertEquals("asdf", covidTest.getUputstvoZaUpotrebu());
	}

	@Test
	void testGetTestId() {
		covidTest.setTestId((long) 33);
		assertEquals(33, covidTest.getTestId());
	}

	@Test
	void testGetNaziv() {
		covidTest.setNaziv("Koronko");
		assertEquals("Koronko", covidTest.getNaziv());
	}

	@Test
	void testGetOpis() {
		covidTest.setOpis("Koronko");
		assertEquals("Koronko", covidTest.getOpis());
	}

	@Test
	void testGetVrsta() {
		covidTest.setVrsta("Koronko");
		assertEquals("Koronko", covidTest.getVrsta());
	}

	@Test
	void testGetUputstvoZaUpotrebu() {
		covidTest.setUputstvoZaUpotrebu("Koronko");
		assertEquals("Koronko", covidTest.getUputstvoZaUpotrebu());
	}

	@Test
	void testToString() {
		covidTest.setNaziv("Koronko");
		assertEquals("Koronko", covidTest.toString());
	}

	@Test
	void testGetTableName() {
		assertEquals("test", covidTest.getTableName());
	}

	@Test
	void testGetResult() {
		try {
			rs = Mockito.mock(ResultSet.class);
			Mockito.when(rs.next()).thenReturn(false);
			assertNull(covidTest.getResult(rs));
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
			Mockito.when(rs.getString("naziv")).thenReturn("test");
			Mockito.when(rs.getString("opis")).thenReturn("test");
			Mockito.when(rs.getString("vrsta")).thenReturn("test");
			Mockito.when(rs.getString("uputstvoZaUpotrebu")).thenReturn("test");

			Mockito.when(rs.next()).thenReturn(true).thenReturn(false);

			List<OpstiDomenskiObjekat> covidTestList = covidTest.getList(rs);
			domen.Test covidTestDummy = (domen.Test) covidTestList.get(0);

			assertNotNull(covidTestList);
			assertNotNull(covidTestDummy);

			assertEquals(13, covidTestDummy.getTestId());
			assertEquals("test", covidTestDummy.getNaziv());
			assertEquals("test", covidTestDummy.getOpis());
			assertEquals("test", covidTestDummy.getVrsta());
			assertEquals("test", covidTestDummy.getUputstvoZaUpotrebu());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetAttributeNames() {
		assertEquals("naziv,opis,vrsta,uputstvoZaUpotrebu", covidTest.getAttributeNames());
	}

	@Test
	void testGetUnknownValues() {
		assertEquals("?,?,?,?", covidTest.getUnknownValues());
	}

	@Test
	void testGetId() {
		covidTest.setTestId((long) 12);
		assertEquals(String.valueOf(12), covidTest.getID(covidTest));
	}

	@Test
	void testGetOrderCondition() {

		assertEquals("naziv", covidTest.getOrderCondition());
	}

}
