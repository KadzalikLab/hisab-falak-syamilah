import org.junit.Assert
import org.junit.Test
import utilitas.isKabisatMasehi
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class testKonversi {
@Test
fun main(){
    // Test konversi
    assertTrue(isKabisatMasehi(2000));
    assertTrue(isKabisatMasehi(1904));
    assertFalse(isKabisatMasehi(1900));
    assertFalse(isKabisatMasehi(1901));

}



}
