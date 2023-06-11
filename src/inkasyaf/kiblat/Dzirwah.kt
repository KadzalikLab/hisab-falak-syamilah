package inkasyaf.kiblat

import inkasyaf.DataMat
import utilitas.printHms

fun main (){
    val dt = DataMat(tanggal = 28, bulan = 2, tahun = 2022)
    val dz = 12 + dt.zona_waktu - dt.bujur/15 -dt.pw

    printHms("Dzirwah",dz)




}