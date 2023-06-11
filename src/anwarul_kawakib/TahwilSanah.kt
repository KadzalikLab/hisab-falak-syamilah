package anwarul_kawakib

import utilitas.*
import java.util.*
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.round
import kotlin.random.Random

data class KalHijri(
        val tanggalHijri: Int,
        val bulanHijri: Int,
        val tahunHijri: Int,
        val jth:Int

) {
    private val kmh = round(frac(jth/7.0)*7)
    private val kmp = round(frac(jth/5.0)*5)

    val namaBulanHijri: String = utilitas.namaBulanHijri(bulanHijri)
    val namaHari:String = noHari(kmh.toInt())
    val namaPasaran:String = noPas(kmp.toInt())
    val namaHarPas = "$namaHari $namaPasaran"



}


fun tahwilSanah(tanggal: Int, bulan: Int, tahun: Int): KalHijri {
    val kabisat = isKabisatMasehi(tahun)
    val mo = bulan - 1
    val ma = (tahun - 1) / 4.0
    val mb = floor(ma)
    val mc = frac(ma)
    val a = mb * 1461
    val b = mc * 4 * 365
    val c = jumlahHariMasehi(mo, kabisat)

    //cek bila masuk periode Gregorian / cek rumus koreksi
    var e = 0.0
    if (tahun + bulan / 100 + tanggal / 10000 >= 1582.1015) {
        val ax = floor(tahun / 100.0)
        e = 2 + floor(ax / 4) - ax
    }

    val f = -227016

    val jth = a + b + c + tanggal + e + f

    val me = jth / 10631.0
    val mf = floor(me)
    val mg = frac(me)

    var i = mf * 30
    val j = (mg * 10631) / 354.0
    val mh = floor(j)
    val mi = frac(j)
    val k = round(mi * 354)
    val l = urutanKabisatHijri(mh.toInt())
    var jh = k - l

    val tnm = mh+i
    if (jh<=0){
        val mu = frac(tnm/30.0)
        val uf = round(mu*30)
        val kabisatTemp = isTnmKabisatHijri(uf.toInt())
        jh += if (kabisatTemp) 355 else 354
        i -=1
    }

    val op = jumlahHariHijriah(jh.toInt(), tnm.toInt()).jumlahHari
    val or = jumlahHariHijriah(jh.toInt(), tnm.toInt()).nomorBulan



    val th = i+mh+1
    val blh = or+1
    val tgh = jh-op


    return KalHijri(tgh.toInt(), blh, th.toInt(), jth.toInt())
}


fun jumlahHariMasehi(mo: Int, kabisatM:Boolean): Int {
    val jumlah: ArrayList<Int> = if (!kabisatM) arrayListOf<Int>(0,31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365)
    else arrayListOf<Int>(0,31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366)

    return jumlah[mo]

}

fun urutanKabisatHijri(mh: Int): Int {

    return when (mh) {
        1 -> 0
        in 2..4 -> 1
        in 5..6 -> 2
        in 7..9 -> 3
        in 10..12 -> 4
        in 13..14 -> 5
        in 15..17 -> 6
        in 18..20 -> 7
        in 21..23 -> 8
        in 24..25 -> 9
        in 26..28 -> 10
        in 29..30 -> 11

        else -> 0
    }
}


//fun urutanKabisatHijri(mh: Int): Int {
//
//    return when (mh) {
//        in 1..2 -> 0
//        in 3..5 -> 1
//        in 6..7->2
//        in 8..10->3
//        in 11..13->4
//        in 14..15->5
//        in 16..18->6
//        in 19..21->7
//        in 22..24->8
//        in 25..26->9
//        in 27..29->10
//        30->11
//
//        else -> 0
//    }
//}

class TabelUmurHariHijri(val nomorBulan: Int, val jumlahHari:Int)

fun jumlahHariHijriah(jh: Int, tahunHijri: Int): TabelUmurHariHijri {
    var nobulan = 0
    var jumlahHari = 0
    val kabisat = isKabisatHijri(tahunHijri)
    var umurBulanDzulhijjah = 354
    if (kabisat) umurBulanDzulhijjah += 1
//    println("$tahunHijri $kabisat")



    when (jh) {
        in 0..30 -> {
            nobulan = 0; jumlahHari = 0

        }
        in 31..59 -> {
            nobulan = 1; jumlahHari = 30
        }
        in 60..89 -> {
            nobulan = 2; jumlahHari = 59
        }
        in 90..118 -> {
            nobulan = 3; jumlahHari = 89
        }
        in 119..148 -> {
            nobulan = 4; jumlahHari = 118
        }
        in 149..177 -> {
            nobulan = 5; jumlahHari = 148
        }
        in 178..207 -> {
            nobulan = 6; jumlahHari = 177
        }
        in 208..236 -> {
            nobulan = 7; jumlahHari = 207
        }
        in 237..266 -> {
            nobulan = 8; jumlahHari = 236
        }
        in 267..295 -> {
            nobulan = 9; jumlahHari = 266
        }
        in 296..325 -> {
            nobulan = 10; jumlahHari = 295
        }
        in 326..umurBulanDzulhijjah -> {
            nobulan = 11; jumlahHari = 325
        }

    }

    return TabelUmurHariHijri(nobulan, jumlahHari)
}

fun noHari(kmh:Int):String{
    return when(kmh){
        1 -> "Jumat"
        2 -> "Sabtu"
        3 -> "Ahad"
        4 -> "Senin"
        5 -> "Selasa"
        6 -> "Rabu"
        7,0 -> "Kamis"

        else -> ""
    }
}

fun noPas(kmp:Int):String{
    return when(kmp){
        1 -> "Legi"
        2 -> "Pahing"
        3 -> "Pon"
        4 -> "Wage"
        5,0 -> "Kliwon"


        else -> ""
    }
}


fun main() {

//    val tanggalM = 17
//    val bulanM = 8
//    val tahunM = 1945
//
//    // Metode Anwarul Kawakib
//    val hijri = tahwilSanah(tanggalM,bulanM,tahunM)
//    val tanggal = "${hijri.tanggalHijri} - ${hijri.namaBulanHijri} (${hijri.bulanHijri}) - ${hijri.tahunHijri} "
//    println(tanggal)
//
//    dataTest(20)




}


fun dataTest(error:Int){

    var daftarMasehi = ArrayList<String>()
    var daftarHijri1 = ArrayList<String>()
    var daftarHijri2 = ArrayList<String>()

    while (true){
        val tanggalM =Random.nextInt(1,31)
        val bulanM = Random.nextInt(1,12)
        val tahunM = Random.nextInt(1900,2100)

        // Metode Anwarul Kawakib
        val hijri = tahwilSanah(tanggalM,bulanM,tahunM)
        val tanggal = "${hijri.tanggalHijri} - ${hijri.namaBulanHijri} (${hijri.bulanHijri}) - ${hijri.tahunHijri} "
//        println(tanggal)

        //Metode Mbl
        val hijri2 = masehiKeHijri(tanggalM,bulanM,tahunM)
        val tanggal2 = "${hijri2.tanggalHijri} - ${hijri2.namaBulanHijri} (${hijri2.bulanHijri}) - ${hijri2.tahunHijri} "
//        println(tanggal2)
//        println(true)

        val tgl1 = hijri.tanggalHijri
        val tgl2 = hijri2.tanggalHijri
        val pembanding = abs(tgl1-tgl2)


        if (pembanding==1){
            val masehi = "$tanggalM ${namaBulan(bulanM)} $tahunM"
            daftarMasehi.add(masehi)
            daftarHijri1.add(tanggal)
            daftarHijri2.add(tanggal2)
            println(false)
        }

        if (daftarMasehi.size>=error) break




    }

    for (v in daftarMasehi.indices){
        println(daftarMasehi[v])
        println(daftarHijri1[v])
        println(daftarHijri2[v])
        println()

    }





}

fun masehiKeHijri(tanggal: Int, bulan: Int, tahun: Int): KalHijri {

    //tanggal diatas dijadikan julian day dulu melalui fungsi Konversi
    val julian_day: Double = masehiKeJd(tanggal, bulan, tahun, 0.0)
    val selisih_hari = julian_day - 1948438.5 //selisih hari tanggal masehi dengan epoch/tahun nol kalender hijri
    var siklus = ((selisih_hari - 1) / 10631).toInt() //kelipatan 30 tahun atau berapakah siklus kabisat yang terjadi selama "selisih hari"
    if (selisih_hari < 0) siklus = floor((selisih_hari - 1) / 10631).toInt()
    val tahun_siklus = siklus * 30 //jumlah siklus dijadikan tahun
    val sisahari1 = selisih_hari - siklus * 10631 //  total selisih hari yang masih sisa setelah sebelumya dijadikan satuan siklus


    //rentang hari (satuan hari dari "sisahari1" dijadikan satuan tahun dan masih berapa sisa hari)
    var tambahan_tahun = 0
    var sisahari2 = 0 //ini nilai hari// ditambah satu karna masih berupa bulan yang telah berlalu, bukan yang sedang dijalani
    //tnggal diambil dari jumlah total hari yang tersisa diambil umur bulan sebelumnya
    // jumlah hari yang terdapat pada bulan sebelumnya
    // proses menjadikan satuan hari ke satuan bulan




    when (sisahari1) {
        in 1.0..354.0 -> {
            tambahan_tahun = 1
            sisahari2 = sisahari1.toInt()
        }
        in 355.0..709.0 -> {
            tambahan_tahun = 2
            sisahari2 = (sisahari1 - 354).toInt()
        }
        in 710.0..1063.0 -> {
            tambahan_tahun = 3
            sisahari2 = (sisahari1 - 709).toInt()
        }
        in 1064.0..1417.0 -> {
            tambahan_tahun = 4
            sisahari2 = (sisahari1 - 1063).toInt()
        }
        in 1418.0..1772.0 -> {
            tambahan_tahun = 5
            sisahari2 = (sisahari1 - 1417).toInt()
        }
        in 1772.0..2126.0 -> {
            tambahan_tahun = 6
            sisahari2 = (sisahari1 - 1772).toInt()
        }
        in 2127.0..2481.0 -> {
            tambahan_tahun = 7
            sisahari2 = (sisahari1 - 2126).toInt()
        }
        in 2482.0..2835.0 -> {
            tambahan_tahun = 8
            sisahari2 = (sisahari1 - 2481).toInt()
        }
        in 2836.0..3189.0 -> {
            tambahan_tahun = 9
            sisahari2 = (sisahari1 - 2835).toInt()
        }
        in 3190.0..3544.0 -> {
            tambahan_tahun = 10
            sisahari2 = (sisahari1 - 3189).toInt()
        }
        in 3545.0..3898.0 -> {
            tambahan_tahun = 11
            sisahari2 = (sisahari1 - 3544).toInt()
        }
        in 3899.0..4252.0 -> {
            tambahan_tahun = 12
            sisahari2 = (sisahari1 - 3898).toInt()
        }
        in 4253.0..4607.0 -> {
            tambahan_tahun = 13
            sisahari2 = (sisahari1 - 4252).toInt()
        }
        in 4608.0..4961.0 -> {
            tambahan_tahun = 14
            sisahari2 = (sisahari1 - 4607).toInt()
        }
        in 4962.0..5315.0 -> {
            tambahan_tahun = 15
            sisahari2 = (sisahari1 - 4961).toInt()
        }
        in 5316.0..5670.0 -> {
            tambahan_tahun = 16
            sisahari2 = (sisahari1 - 5315).toInt()
        }
        in 5671.0..6024.0 -> {
            tambahan_tahun = 17
            sisahari2 = (sisahari1 - 5670).toInt()
        }
        in 6025.0..6379.0 -> {
            tambahan_tahun = 18
            sisahari2 = (sisahari1 - 6024).toInt()
        }
        in 6380.0..6733.0 -> {
            tambahan_tahun = 19
            sisahari2 = (sisahari1 - 6379).toInt()
        }
        in 6734.0..7087.0 -> {
            tambahan_tahun = 20
            sisahari2 = (sisahari1 - 6733).toInt()
        }
        in 7088.0..7442.0 -> {
            tambahan_tahun = 21
            sisahari2 = (sisahari1 - 7087).toInt()
        }
        in 7443.0..7796.0 -> {
            tambahan_tahun = 22
            sisahari2 = (sisahari1 - 7442).toInt()
        }
        in 7797.0..8150.0 -> {
            tambahan_tahun = 23
            sisahari2 = (sisahari1 - 7796).toInt()
        }
        in 8150.0..8505.0 -> {
            tambahan_tahun = 24
            sisahari2 = (sisahari1 - 8150).toInt()
        }
        in 8506.0..8859.0 -> {
            tambahan_tahun = 25
            sisahari2 = (sisahari1 - 8505).toInt()
        }
        in 8860.0..9214.0 -> {
            tambahan_tahun = 26
            sisahari2 = (sisahari1 - 8859).toInt()
        }
        in 9215.0..9568.0 -> {
            tambahan_tahun = 27
            sisahari2 = (sisahari1 - 9214).toInt()
        }
        in 9569.0..9922.0 -> {
            tambahan_tahun = 28
            sisahari2 = (sisahari1 - 9568).toInt()
        }
        in 9923.0..10277.0 -> {
            tambahan_tahun = 29
            sisahari2 = (sisahari1 - 9922).toInt()
        }
        in 10277.0..10631.0 -> {
            tambahan_tahun = 30
            sisahari2 = (sisahari1 - 10277).toInt()
        }
    }
    val bulan_utuh_tamm: Int // proses menjadikan satuan hari ke satuan bulan
    bulan_utuh_tamm = if (sisahari2 == 355) 11 else ((sisahari2 - 1) / 29.5).toInt()
    val jumlah_hari_bulan_tamm: Double // jumlah hari yang terdapat pada bulan sebelumnya
    jumlah_hari_bulan_tamm = if (bulan_utuh_tamm % 2 == 0) 29.5 * bulan_utuh_tamm else 29.5 * (bulan_utuh_tamm - 1) + 30
    val tanggal_hijri = (sisahari2 - jumlah_hari_bulan_tamm).toInt() //tnggal diambil dari jumlah total hari yang tersisa diambil umur bulan sebelumnya
    val bulan_hijri = bulan_utuh_tamm + 1 // ditambah satu karna masih berupa bulan yang telah berlalu, bukan yang sedang dijalani
    val tahun_hijri = tahun_siklus + tambahan_tahun

//    println("julian day: $julian_day")
//    println("selisih hari = $selisih_hari")
//    println("siklus = $siklus")
//    println("tahun siklus = $tahun_siklus")
//    println("sisahari1 = $sisahari1")
//    println("tambahan tahun = $tambahan_tahun")
//    println("sisahari2 = $sisahari2")
//    println("")

    return KalHijri(tanggal_hijri, bulan_hijri, tahun_hijri, 0)
}


