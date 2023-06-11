package utilitas

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.log10

// kumpulan fungsi // method matematika yang tidak ada di kotlin (Math falak)
fun frac (nilai:Double):Double{
    return nilai%1 // modulo 1 (dikurangi terus sampai menyisakan pecahan)
}

fun toRad(angDeg:Double):Double = angDeg / 180.0 * PI

fun mod(a: Double, b: Double): Double {
    return (a % b + b) % b
}

fun mod(a: Double, b: Int): Double {
    return mod(a,b.toDouble())
}

fun mod(a: Int, b: Int): Double {
    return mod(a.toDouble(),b.toDouble())
}

fun toDeg(angrad: Double): Double {
    return angrad * 180.0 / Math.PI
}

fun isKabisatMasehi(tahun:Int):Boolean{
    return  (tahun % 400 == 0) || ((tahun % 4 == 0) && (tahun % 100 != 0))
}

fun isKabisatHijri(tahun: Int):Boolean{
    val i = (tahun/30)*30
    val n =tahun-i
    return when (n){
        2,5,7,10,13,15,18,21,24,26,29-> true

        else -> false
    }
}

fun isTnmKabisatHijri(tnm: Int):Boolean{
    return when (tnm){
        2,5,7,10,13,15,18,21,24,26,29-> true
        else -> false
    }
}

fun Int.length() = when(this) {
    0 -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}













