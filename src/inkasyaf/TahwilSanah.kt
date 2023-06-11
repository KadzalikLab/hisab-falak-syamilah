package inkasyaf

import utilitas.*
import java.util.*
import kotlin.math.floor
import kotlin.math.round

data class KalHijri(
    val tanggalHijri: Int,
    val bulanHijri: Int,
    val tahunHijri: Int,
    val jth: Int

) {
    private val kmh = round(frac(jth / 7.0) * 7)
    private val kmp = round(frac(jth / 5.0) * 5)

    val namaBulanHijri: String = utilitas.namaBulanHijri(bulanHijri)
    val namaHari: String = noHari(kmh.toInt())
    val namaPasaran: String = noPas(kmp.toInt())
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

    val tnm = mh + i
    if (jh <= 0) {
        val mu = frac(tnm / 30.0)
        val uf = round(mu * 30)
        val kabisatTemp = isTnmKabisatHijri(uf.toInt())
        jh += if (kabisatTemp) 355 else 354
        i -= 1
    }

    val op = jumlahHariHijriah(jh.toInt(), tnm.toInt()).jumlahHari
    val or = jumlahHariHijriah(jh.toInt(), tnm.toInt()).nomorBulan


    val th = i + mh + 1
    val blh = or + 1
    val tgh = jh - op


    return KalHijri(tgh.toInt(), blh, th.toInt(), jth.toInt())
}


fun jumlahHariMasehi(mo: Int, kabisatM: Boolean): Int {
    val jumlah: ArrayList<Int> = if (!kabisatM) arrayListOf<Int>(
        0,
        31,
        59,
        90,
        120,
        151,
        181,
        212,
        243,
        273,
        304,
        334,
        365
    ) // bila bukan kabisat
    else arrayListOf<Int>(0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366) // bila tahun kabisat

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

class TabelUmurHariHijri(val nomorBulan: Int, val jumlahHari: Int)

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

fun noHari(kmh: Int): String {
    return when (kmh) {
        1 -> "Jumat"
        2 -> "Sabtu"
        3 -> "Ahad"
        4 -> "Senin"
        5 -> "Selasa"
        6 -> "Rabu"
        7, 0 -> "Kamis"

        else -> ""
    }
}

fun noPas(kmp: Int): String {
    return when (kmp) {
        1 -> "Legi"
        2 -> "Pahing"
        3 -> "Pon"
        4 -> "Wage"
        5, 0 -> "Kliwon"


        else -> ""
    }
}


fun main() {

    val tg = 5
    val bl = 1
    val thn = 2022
    val kabisat = isKabisatMasehi(thn)
    val mo = bl - 1
    val ma = (thn - 1) / 4.0
    val mb = floor(ma)
    val mc = frac(ma)
    val a = mb * 1461
    val b = mc * 4 * 365
    val c = jumlahHariMasehi(mo, kabisat)

    //cek bila masuk periode Gregorian / cek rumus koreksi
    var e = 0.0
    if (thn + bl / 100 + tg / 10000 >= 1582.1015) {
        val ax = floor(thn / 100.0)
        e = 2 + floor(ax / 4) - ax
    }

    val f = -227016

    val jth = a + b + c + tg + e + f

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

    val tnm = mh + i
    val mu = frac(tnm / 30.0)
    val uf = round(mu * 30)
    val kabisatTemp = isTnmKabisatHijri(uf.toInt())
    var isjhkoreksi=false

    if (jh <= 0) {

       isjhkoreksi = true
        jh += if (kabisatTemp) 355 else 354
        i -= 1
    }

    val op = jumlahHariHijriah(jh.toInt(), tnm.toInt()).jumlahHari
    val or = jumlahHariHijriah(jh.toInt(), tnm.toInt()).nomorBulan


    val th = i + mh + 1
    val blh = or + 1
    val tgh = jh - op

    println("$tg $bl $thn")
    print("MO", mo)
    print("MA", ma)
    print("MB", mb)
    print("MC", mc)
    print("A", a)
    print("B", b)
    print("C", c)
    print("E", e)
    print("F", f)
    print("Jth", jth)

    println()
    printKl("ME", me)
    print("MF", mf)
    printKl("MG", mg)
    print("i", i)
    printKl("J", j)
    print("MH", mh)
    printKl("MI", mi)
    print("K", k)
    print("L", l)
    print("JH", jh)

    if (isjhkoreksi) {
        print("TNM",tnm)
        printKl("MU", mu)
        print("UF",uf)
        print("JH",jh)
        print("I",i)
    }
    println()
    print("OP", op)
    print("OR", or)
    print("TGH", tgh)
    print("BLH", blh)
    print("TH", th)
    println("Kesimpulan: ${tgh.toInt()} ${namaBulanHijri(blh)}  ${th.toInt()}")


}
