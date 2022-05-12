package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import domen.Administrator;
import domen.OpstiDomenskiObjekat;

class AdministratorTest {
	private Administrator administrator;
	@Mock
	ResultSet rs;

	@BeforeEach
	void setUp() throws Exception {
		administrator = new Administrator();
	}

	@AfterEach
	void tearDown() throws Exception {
		administrator = null;
		rs = null;
	}

	@Test
	void testParametrizedConstructor() {
		administrator = new Administrator("user", "password", "Pera", "Peric");
		assertNotNull(administrator);
		assertEquals("user", administrator.getKorisnickoIme());
		assertEquals("password", administrator.getLozinka());
		assertEquals("Pera", administrator.getIme());
		assertEquals("Peric", administrator.getPrezime());

	}

	@Test
	void testGetKorisnickoIme() {
		administrator.setKorisnickoIme("djokovicdusan");
		assertEquals("djokovicdusan", administrator.getKorisnickoIme());
	}

	@Test
	void testGetKorisnickoImeShortString() {

		assertThrows(java.lang.RuntimeException.class, () -> administrator.setKorisnickoIme("du"));
	}

	@Test
	void testGetLozinka() {
		administrator.setLozinka("password");
		assertEquals("password", administrator.getLozinka());
	}

	@Test
	void testGetIme() {
		administrator.setIme("Pera");
		assertEquals("Pera", administrator.getIme());
	}

	@Test
	void testGetPrezime() {
		administrator.setPrezime("Peric");
		assertEquals("Peric", administrator.getPrezime());
	}

	@Test
	void testToString() {
		administrator.setIme("Pera");
		administrator.setPrezime("Peric");
		String display = administrator.getIme() + " " + administrator.getPrezime();
		assertEquals("Pera Peric", display);
	}

	@Test
	void testGetTableName() {
		assertEquals("admin", administrator.getTableName());
	}

	@Test
	void testGetResult() {
		try {
			rs = Mockito.mock(ResultSet.class);
			Mockito.when(rs.next()).thenReturn(false);
			assertNull(administrator.getResult(rs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetList() {
		try {

			rs = Mockito.mock(ResultSet.class);

			Mockito.when(rs.getString("korisnickoime")).thenReturn("korisnickoime");
			Mockito.when(rs.getString("ime")).thenReturn("Pera");
			Mockito.when(rs.getString("prezime")).thenReturn("Peric");
			Mockito.when(rs.getString("lozinka")).thenReturn("lozinka");
			Mockito.when(rs.next()).thenReturn(true).thenReturn(false);

			List<OpstiDomenskiObjekat> adminsList = administrator.getList(rs);
			Administrator a = (Administrator) adminsList.get(0);

			assertNotNull(adminsList);
			assertNotNull(a);

			assertEquals("korisnickoime", a.getKorisnickoIme());
			assertEquals("Pera", a.getIme());
			assertEquals("Peric", a.getPrezime());
			assertEquals("lozinka", a.getLozinka());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetAttributeNames() {
		assertEquals("korisnickoIme,lozinka,ime,prezime", administrator.getAttributeNames());
	}

	@Test
	void testGetUnknownValues() {
		assertEquals("?,?,?,?", administrator.getUnknownValues());
	}

	@Test
	void testGetId() {
		administrator.setKorisnickoIme("djokovicdusan");
		assertEquals("djokovicdusan", administrator.getID(administrator));
	}

	@Test
	void testGetOrderCondition() {

		assertEquals("username", administrator.getOrderCondition());
	}

}
