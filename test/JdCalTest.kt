import org.junit.Assert.assertEquals
import org.junit.Test
import utilitas.jdKeMasehi

class JdCalTest {

    @Test
    fun getTanggal() {
        var tanggal = 25
        assertEquals(tanggal, jdKeMasehi(tanggal.toDouble()).tanggal)
    }

    @Test
    fun getBulan() {
    }

    @Test
    fun getTahun() {
    }

    @Test
    fun getJam() {
    }

    @Test
    fun getMenit() {
    }

    @Test
    fun getDetik() {
    }

    @Test
    fun getNamaHari() {
    }

    @Test
    fun getNamaPasaran() {
    }
}