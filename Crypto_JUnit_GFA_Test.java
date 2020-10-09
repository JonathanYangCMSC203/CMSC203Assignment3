
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Crypto_JUnit_GFA_Test {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStringInBounds() {
		assertTrue(CryptoManager.stringInBounds("THIS TEST SHOULD SUCCEED"));
	}

	@Test
	public void testEncryptCaesar() {
		assertEquals("OTSFYMFS%^FSL",CryptoManager.encryptCaesar("JONATHAN YANG", 5));
		assertEquals("TO]_]",CryptoManager.encryptCaesar("JESUS", 10));
		assertEquals("\\$WP\\\\PS",CryptoManager.encryptCaesar("MUHAMMAD", 15));
	}

	@Test
	public void testDecryptCaesar() {
		assertEquals("JONATHAN YANG", CryptoManager.decryptCaesar("OTSFYMFS%^FSL", 5));
		assertEquals("JESUS", CryptoManager.decryptCaesar("TO]_]", 10));
		assertEquals("MUHAMMAD", CryptoManager.decryptCaesar("\\$WP\\\\PS", 15));
	}

	@Test
	public void testEncryptBellaso() {
		assertEquals("KQQEUJDR![DRH", CryptoManager.encryptBellaso("JONATHAN YANG", "ABCD"));
		assertEquals("MM%^&", CryptoManager.encryptBellaso("JESUS", "CHRIST"));
		assertEquals("N!TBUNMP", CryptoManager.encryptBellaso("MUHAMMAD", "ALLAH"));
	}

	@Test
	public void testDecryptBellaso() {
		assertEquals("JONATHAN YANG", CryptoManager.decryptBellaso("KQQEUJDR![DRH", "ABCD"));
		assertEquals("JESUS", CryptoManager.decryptBellaso("MM%^&", "CHRIST"));
		assertEquals("MUHAMMAD", CryptoManager.decryptBellaso("N!TBUNMP", "ALLAH"));
	}


}
