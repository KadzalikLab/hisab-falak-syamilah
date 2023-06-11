import ephemeris.perhitungan.astroAlgo
import utilitas.desKeDms
import utilitas.dispCal
import java.util.*


fun main (){

    val ep = astroAlgo(2455153.5)
    println(ep.moon_diskIlluminatedFraction)
}

fun prinKabeh(textViews: List<Double>) {
    for (i in textViews) {
        println(dispCal(i))
    }
}




