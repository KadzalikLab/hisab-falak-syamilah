package anwarul_kawakib.juz2.test

import utilitas.frac
import kotlin.math.abs

fun main (){
    val nilai = 0.3283333333333333
    val negatif = nilai<0
    val n = abs(nilai)
    val intN = n.toInt()
    val fracN = frac(n)


    val splitter = nilai.toString().split("\\.".toRegex()).toTypedArray()
    val i = splitter[0].length // Before Decimal Count
    val f = splitter[1].length // After  Decimal Count
    val idanf = i+f
    var t = 0

    if (idanf>10){
    while (true){
        if ((i+t)==10)break
        t++

    } } else t = f

    if (negatif) t++
    val p = String.format("%."+t+"f", fracN).substringAfter(".")
    var hasil = "$intN.$p"
    if (negatif) hasil = "-$hasil"

    println("$i dan $f = $idanf")
    println(hasil)


}

