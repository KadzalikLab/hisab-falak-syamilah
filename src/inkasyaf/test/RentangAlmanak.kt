package inkasyaf.test

import anwarul_kawakib.masehiKeHijri
import utilitas.jdKeMasehi
import utilitas.masehiKeJd

class RentangAlmanak {
}


fun main (){
    // mulai 1 januari samapai 31 desember, itu memuat bulan hijriyah apa saja? nah ini jawabanya
    var tahun = 2023
    var tanggalAwalTahun = masehiKeJd(1,1,tahun,0)
    var tanggalAkhirTahun = masehiKeJd(31,12,tahun,0)
    val arrayTanggalHijri = arrayListOf<String>()
    val arrayBulanHijri = arrayListOf<Int>()
    val arrayTahunHijri = arrayListOf<Int>()

    for (i in 1..366){
        var tglAwal = jdKeMasehi(tanggalAwalTahun+(i))
        if ((tanggalAwalTahun+i)>=tanggalAkhirTahun) break
        val hijri = masehiKeHijri(tglAwal.tanggal,tglAwal.bulan,tahun)

        arrayBulanHijri.add(hijri.bulanHijri)
        arrayTahunHijri.add(hijri.tahunHijri)
        arrayTanggalHijri.add("${hijri.bulanHijri}.${hijri.tahunHijri}")


    }

    var daftarBulanHijri = arrayListOf<Int>()
    var daftarTahunHijri = arrayListOf<Int>()
    var daftarBulanTahunHijri = arrayTanggalHijri.distinct()

    for (i in daftarBulanTahunHijri.indices){
        val j = daftarBulanTahunHijri[i].split(".")
        daftarBulanHijri.add(j[0].toInt())
        daftarTahunHijri.add(j[1].toInt())
    }

    println(daftarBulanHijri)
    println(daftarTahunHijri)
//    println(daftarBulanTahunHijri)






}