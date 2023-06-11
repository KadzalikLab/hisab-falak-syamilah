package anwarul_kawakib

import ephemeris.Ephem
import utilitas.desKeDms
import java.util.ArrayList
import kotlin.random.Random

fun main (){

    var tanggalM = 2
    var bulanM = 8
    var tahunM = 2033

    var daftarMasehi = ArrayList<String>()
    var daftarHijri1 = ArrayList<String>()
    var daftarHijri2 = ArrayList<String>()


        val hijri = tahwilSanah(tanggalM,bulanM,tahunM)
        val tanggal = "${hijri.tanggalHijri} - ${hijri.namaBulanHijri} (${hijri.bulanHijri}) - ${hijri.tahunHijri} "
//        println(tanggal)

        val hijri2 = masehiKeHijri(tanggalM,bulanM,tahunM)
        val tanggal2 = "${hijri2.tanggalHijri} - ${hijri2.namaBulanHijri} (${hijri2.bulanHijri}) - ${hijri2.tahunHijri} "
//        println(tanggal2)
//        println(tanggal2)


    val ep = Ephem(tanggalM,bulanM,tahunM,0)
    ep.setMetodeHisabBulan().ompec()
    ep.setMetodeHisabMatahari().astroAlgo()
    println("jum "+desKeDms(ep.sun_apparentDeclination).dmsss)
    ep.setMetodeHisabMatahari().ompec()
    println("jum2 "+desKeDms(ep.sun_apparentDeclination).dmsss)



    }