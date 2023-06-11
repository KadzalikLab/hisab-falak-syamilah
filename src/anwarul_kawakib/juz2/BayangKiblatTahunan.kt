package anwarul_kawakib.juz2

import utilitas.*
import kotlin.math.cos
import kotlin.math.sin

fun main (){
    val zona_waktu = 7
    val ast = 3 // Arabia Standar time
    val tahun = 2023
    val kabisat = isKabisatMasehi(tahun)

    val bulan1 = 5
    var tanggal1 = 28

    val bulan2 = 7
    var tanggal2 = 16
     if (kabisat){
         tanggal1--
         tanggal2--
     }

    val a11 = (tahun / 100.0).toInt()
    val b1 = 2 + a11 / 4.0 - a11
    val aa1 = (365.25 * tahun).toInt()
    val bb1 = (30.60001 * (bulan1 + 1)).toInt()
    val julianday121 = 1720994.5 + aa1 + bb1 + tanggal1 + b1 + (9 / 24.0)
    val jdl1 = julianday121 - zona_waktu / 24
    val u1 = (jdl1 - 2451545) / 36525
    val lo1 = 280.46607 + 36000.7698 * u1
    val lo_Rad1 = toRad(lo1)
    val e11 = 1789 + 237 * u1
    val e21 = 7146 - 62 * u1
    val e31 = 9934 - 14 * u1
    val e41 = -1 * e11 * sin(lo_Rad1) - e21 * cos(lo_Rad1) + e31 * sin(2 * lo_Rad1)
    val e51 = e41 - (29 + 5 * u1) * cos(2 * lo_Rad1) + (74 + 10 * u1) * sin(3 * lo_Rad1)
    val e61 = (320 - 4 * u1) * cos(3 * lo_Rad1) - 212 * sin(4 * lo_Rad1)

    val a1 = (tahun / 100.0).toInt()
    val b = 2 + a1 / 4.0 - a1
    val aa = (365.25 * tahun).toInt()
    val bb = (30.60001 * (bulan2 + 1)).toInt()
    val julianday12 = 1720994.5 + aa + bb + tanggal2 + b + 12 / 24.0
    val jdl = julianday12 - zona_waktu / 24
    val u = (jdl - 2451545) / 36525
    val lo = 280.46607 + 36000.7698 * u
    val lo_Rad = toRad(lo)
    val e1 = 1789 + 237 * u
    val e2 = 7146 - 62 * u
    val e3 = 9934 - 14 * u
    val e4 = -1 * e1 * sin(lo_Rad) - e2 * cos(lo_Rad) + e3 * sin(2 * lo_Rad)
    val e5 = e4 - (29 + 5 * u) * cos(2 * lo_Rad) + (74 + 10 * u) * sin(3 * lo_Rad)
    val e6 = (320 - 4 * u) * cos(3 * lo_Rad) - 212 * sin(4 * lo_Rad)

    val pw1 = (e51 + e61) / 1000 / 60
    val pw2 = (e5 + e6) / 1000 / 60

    val tr1 = 12 - pw1 + ((ast*15)- dmsKeDesimal(39,49,34))/15 + (zona_waktu-ast)
    val tr2 = 12 - pw2 + ((ast*15)- dmsKeDesimal(39,49,34))/15



    println("${namaHariPasaran(jdl1)}, $tanggal1 - $bulan1 - $tahun : ${ desKeDms(tr1).hmsss }")
    println("${namaHariPasaran(jdl)}, $tanggal2 - $bulan2 - $tahun : ${ desKeDms(tr2).hmsss }")



}