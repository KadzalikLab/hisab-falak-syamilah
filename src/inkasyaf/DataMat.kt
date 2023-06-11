package inkasyaf

import utilitas.*
import kotlin.math.*

class DataMat(
    var pukul: Double = 12.0,
    var tanggal: Int = 10,
    var bulan: Int = 6,
    var tahun: Int = 2021,
    var bujur: Double = dmsKeDesimal(105, 12, 0),
    var lintang: Double = dmsKeDesimal(-4, 58, 0),
    var ketinggianTempat: Double = 50.0,
    var zona_waktu: Double = 7.0,
    var zona_waktu_makkah: Double = 3.0,

    var bujur_kabah: Double = dmsKeDesimal(39, 49, 34),
    var lintang_kabah: Double = dmsKeDesimal(21, 25, 20.99)


) {
    /////////////////////////////////////////////////////////////////////////////////

    private val bulanHitung = if (bulan<3.0) bulan+12 else bulan
    private val tahunHitung = if (bulan<3.0) tahun-1 else tahun

    private val a = (tahunHitung / 100.0).toInt()
    private val ak = (a / 4.0).toInt()
    private val b = 2 + ak - a
    private val ac = (365.25 * tahunHitung).toInt()
    private val bc = (30.6001 * (bulanHitung + 1)).toInt()
    val jd = 1720994.5 + ac + bc + tanggal + b + pukul / 24.0

    private val u = (jd - zona_waktu / 24.0 - 2451545) / 36525.0 // u pada pukul 5 ut atau 12 wib atau waktu yang telah ditentukan
    private val lo = 280.46645 + 36000.76983 * u //Bujur Rata-rata matahari
    private val ld = toRad(357.5291 + 35999.0503 * u) // anomali rata-rata matahari
    private val lr = lo - 0.0056861 + 1.916128 * sin(ld) //+0.020026* sin(2*ld)           //Bujur Matahari terkoreksi

//    val lr =  lo+ (1.916128*sin(ld)+0.020026*sin(2*ld)+0.966/3600*sin(3*ld))-0.0056861 //Bujur Matahari terkoreksi          //        koreksi rentang abad
//    val usm = frac((125.04 - 1934.136 * u) / 360)
//    val us = mod(usm * 360,360) //uqdah syamsi
//    val r1 = 9.23 / 3600 * cos(toRad(us)) - 0.090 / 3600 * cos(toRad(2 * us))
//    val r2 = 0.548 / 3600 * cos(toRad(2 * lo))
//    val mk = 23.43929111 + r1 + r2 - 46.8150 / 3600 * u //mailkully terkoreksi                                    //        koreksi  rentang abad

    private val mk = dmsKeDesimal(23, 26, 0) // Mail Kully
    val dm = toDeg(asin(sin(toRad(lr)) * sin(toRad(mk)))) //deklinasi Matahari pada 12 WIB

    private val lor = toRad(lo)
    private val y = tan(toRad(mk / 2)).pow(2)
    private val pe = 0.016711651 // Kepipihan Orbit Bumi
    private val e1 = y * sin(2 * lor) - 2 * pe * sin(ld)
    private val e2 = e1 + 4 * pe * y * sin(ld) * cos(2 * lor)
    private val e3 = e2 - 0.5 * y.pow(2.0) * sin(4 * lor)
    private val e4 = e3 - 1.25 * pe.pow(2.0) * sin(2 * ld)
    val pw = toDeg(e4) / 15 //deklinasi Matahari pada 12 WIB

    init {
        println()
        println("Tanggal: $tanggal - $bulan - $tahun")
        println("Pukul ${desKeHms(pukul).hmsss}")
        println("Zona Waktu $zona_waktu")

        printDms("Lintang", lintang)
        printDms("Bujur", bujur)

        println("Ketinggianatempat $ketinggianTempat")
        println("A $a")
        println("Ak $ak")
        println("B $b")
        println("Ac $ac")
        println("Bc $bc")
        printKl("JD", jd)
        printDms("U  ", u)
        printKl("lo", lo)
        printKl("ld", toDeg(ld))
        printKl("lr", lr)
        printDms("Dm ", dm)
        printDms("e1", e1)
        printDms("e2", e2)
        printDms("e3", e3)
        printDms("e4", e4)
        printDms("Pw: ", pw)
        println()

    }


}