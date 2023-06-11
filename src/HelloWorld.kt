import ephemeris.Ephem
import org.junit.Test

@Test
fun main(){



}

fun checkPosNeg(x: Int): String? {
    val result = arrayOf("Positive", "Negative")

    // checks if the number is positive or negative
    return result[x shr 31 and 1]
}


