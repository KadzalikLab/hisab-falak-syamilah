package inkasyaf.gerhana

import utilitas.dmsKeDesimal
import utilitas.frac
import utilitas.namaBulanHijri
import utilitas.printDms

fun main(){

    // 12 dalam tahun tertentu terdapat kemungkinan gerhana bulan atau tidak

    val tahun = 1445
    for (bulan in 1..12){


        val a = dmsKeDesimal(15,20,7)
        val b = dmsKeDesimal(30,40,15)
        val c = dmsKeDesimal(8,2,48)
        val d = dmsKeDesimal(76,26,12)

        val km = frac((a+b*(bulan-1)+(tahun-1220)*c+d)/360)
        val gb = km*360
        val keterangan:String
        keterangan = when (gb){
            in 0.0..14.0 -> "Imkan Gerhana"
            in 165.0..194.0 -> "Imkan Gerhana"
            in 345.0..360.0 -> "Imkan Gerhana"
            else -> "Belum Imkan Gerhana"
        }
        println()
        println("${namaBulanHijri(bulan)} $keterangan")
        printDms("GB",gb)

    }

}