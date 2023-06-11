package kalender

import ephemeris.Ephem
import utilitas.dispCal
import utilitas.dmsKeDesimal
import utilitas.masehiKeJd
import utilitas.printDms
import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import kotlin.math.*

class KalenderGeneratorAlpha {
}


fun main (){


    val lintang: Double = dmsKeDesimal(-4, 58, 0)
    val bujur: Double = dmsKeDesimal(105, 12, 0)
    val zw = 7.0
    val ketinggian_tempat = 50.0

    val tgl = 1
    val bulan = 2
    val tahun = 2022
    val pukul = dmsKeDesimal(11,18,16.1)
    var jd = masehiKeJd(tgl,bulan,tahun,0)



    var jamFibTerkecil = 0
    //mencari fib terkecil
    for (i in 0..24){
        var fibSatar1 = Ephem(jd+i/24.0).moon_diskIlluminatedFraction
        var fibSatar2 = Ephem(jd+((i+1)/24.0)).moon_diskIlluminatedFraction
//        printDms(ep.sun_trueGeocentricLongitude)
        dispCal("Fib $i",fibSatar1)

        if (fibSatar1<fibSatar2){
            jamFibTerkecil=i
            println("Jam Fib Terkecil $jamFibTerkecil ")
            dispCal("Fib ${i+1}",fibSatar2)

            break

        }

    }

    val jdGmt10= masehiKeJd(tgl,bulan,tahun,10)
    val  jdGmt11=masehiKeJd(tgl,bulan,tahun,11)
    val  jdGmtFibTerkecilSatar1=masehiKeJd(tgl,bulan,tahun,jamFibTerkecil)
    val  jdGmtFibTerkecilSatar2=masehiKeJd(tgl,bulan,tahun,jamFibTerkecil+1)
    val ephem10 = Ephem(jdGmt10)
    val ephem11 = Ephem(jdGmt11)
    val epFibS1 = Ephem(jdGmtFibTerkecilSatar1)
    val epFibS2 = Ephem(jdGmtFibTerkecilSatar2)

    val alb1     =epFibS1.sun_trueGeocentricLongitude
    val alb2     =epFibS2.sun_trueGeocentricLongitude
    val sdo11    =ephem11.sun_angularSemiDiameter
    val do10     =ephem10.sun_apparentDeclination
    val do11     =ephem11.sun_apparentDeclination
    val e11      =ephem11.sun_equationOfTime
    val aro10    =ephem10.sun_apparentRightAscension
    val aro11    =ephem11.sun_apparentRightAscension

    //Data Bulan
    val elm1      =epFibS1.moon_apparentGeocentricLongitude
    val elm2      =epFibS2.moon_apparentGeocentricLongitude
    val sdc10     =ephem10.moon_angularSemiDiameter
    val arc10     =ephem10.moon_apparentRightAscension
    val arc11     =ephem11.moon_apparentRightAscension
    val dc10      =ephem10.moon_apparentDeclination
    val dc11      =ephem11.moon_apparentDeclination
    val paralax10 =ephem10.moon_equatorialHorizontalParallax

    val fib10 =ephem10.moon_diskIlluminatedFraction
    val fib11 =ephem11.moon_diskIlluminatedFraction

    val sb = abs(alb1 - alb2)
    val sm = abs(elm1 - elm2)

    val saat_itima = jamFibTerkecil + (elm1 - alb1) / (sb - sm) + zw
    val jd_waktuIjtima = jd + (saat_itima / 24)


    //qoidah refraksi
    //5

    //qoidah refraksi
    val refraksi_jam_ghurub = 0.575
    //rumus keitnggian ufuk
    //rumus keitnggian ufuk
    val ketinggiian_ufuk = 1.76 * sqrt(ketinggian_tempat) / 60
    val tinggi_matahari_haqiqi = 0 - sdo11 - refraksi_jam_ghurub - ketinggiian_ufuk

    //6

    //6
    val sudut_waktu_m = toDegrees(
        acos(
            -tan(toRadians(lintang)) * tan(toRadians(do11)) + sin(
                toRadians(tinggi_matahari_haqiqi)
            ) / cos(toRadians(lintang)) / cos(toRadians(do11))
        )
    )

    //7

    //7
    val koreksi_waktu_daerah = (zw * 15 - bujur) / 15

    //8

    //8
    val ghurub = sudut_waktu_m / 15 + (12 - e11) + koreksi_waktu_daerah

    //9

    //9
    val asensiorekta_m = aro10 - (aro10 - aro11) * (ghurub % 1)

    //10

    //10
    val asensiorekta_b = arc10 - (arc10 - arc11) * (ghurub % 1)

    //11

    //11
    val sudut_waktu_b = asensiorekta_m - asensiorekta_b + sudut_waktu_m

    //12

    //12
    val deklinasi_m_ghurub = do10 - (do10 - do11) * (ghurub % 1)
    val deklinasi_b_ghurub = dc10 - (dc10 - dc11) * (ghurub % 1)

    //Elongasi Bulan

    //Elongasi Bulan
    val elongasi = toDegrees(
        acos(
            sin(toRadians(deklinasi_b_ghurub)) * sin(toRadians(deklinasi_m_ghurub)) + cos(
                toRadians(deklinasi_b_ghurub)
            ) * cos(toRadians(deklinasi_m_ghurub)) * cos(toRadians(asensiorekta_b - asensiorekta_m))
        )
    )


    //13


    //13
    val tinggi_bulan_hakiki = toDegrees(
        asin(
            sin(toRadians(lintang)) * sin(toRadians(deklinasi_b_ghurub)) + cos(
                toRadians(lintang)
            ) * cos(toRadians(deklinasi_b_ghurub)) * cos(toRadians(sudut_waktu_b))
        )
    )
    //parallaks
    //parallaks
    val hp10 = paralax10 * cos(toRadians(tinggi_bulan_hakiki))

    //Refraksi

    //Refraksi
    val ref = 1.02 / tan(toRadians(tinggi_bulan_hakiki + 10.3 / (tinggi_bulan_hakiki + 5.11))) / 60
    //tinggi bulan mar'i
    //tinggi bulan mar'i
    val tinggi_b_mari = tinggi_bulan_hakiki - hp10 + ref + sdc10 + ketinggiian_ufuk

    //16

    //16
    var azimuth_m_ghurub = toDegrees(
        atan(
            1 / (-sin(toRadians(lintang)) / tan(toRadians(sudut_waktu_m)) + cos(
                toRadians(lintang)
            ) * tan(toRadians(deklinasi_m_ghurub)) / sin(toRadians(sudut_waktu_m)))
        )
    )

    var azimuth_b_ghurub = toDegrees(
        atan(
            1 / (-sin(toRadians(lintang)) / tan(toRadians(sudut_waktu_b)) + cos(
                toRadians(lintang)
            ) * tan(toRadians(deklinasi_b_ghurub)) / sin(toRadians(sudut_waktu_b)))
        )
    )

    var arah_m = ""
    var arah_b = ""
    var arah_h = ""

    var azimuth_m_barat = 0.0
    if (azimuth_m_ghurub < 0) {
        azimuth_m_barat = azimuth_m_ghurub + 90
        arah_m = "BS"
    } else {
        azimuth_m_ghurub = 90 - azimuth_m_ghurub
        arah_m = "BU"
    }

    var azimuth_b_barat = 0.0
    if (azimuth_b_ghurub < 0) {
        azimuth_b_barat = azimuth_b_ghurub + 90
        arah_b = "BS"
        arah_h = "Selatan Titik Barat"
    } else {
        azimuth_b_ghurub = 90 - azimuth_b_ghurub
        arah_b = "BU"
        arah_h = "Utara Titik Barat"
    }

    //18

    //18
    val posisi_hilal = azimuth_m_ghurub - azimuth_b_ghurub
    val lama_hilal = tinggi_b_mari / 15

    //19

    //19
    val luas_cahaya_hilal = fib10 - (fib10 - fib11) * (ghurub % 1)

    //20

    //20
    val lebar_nur_hilal = sqrt(posisi_hilal.pow(2.0) + tinggi_b_mari.pow(2.0)) / 15

    var keadaan_h = ""
    //21
    //21
    val kemiringan_hilal = toDegrees(atan(abs(posisi_hilal) / tinggi_b_mari))
    if (kemiringan_hilal <= 15) keadaan_h =
        "Hilal Terlentang" else if (kemiringan_hilal > 15 && posisi_hilal > 0) keadaan_h =
        "Miring ke Utara" else if (kemiringan_hilal > 15 && posisi_hilal < 0) keadaan_h = "Miring ke Selatan"


    printDms(tinggi_b_mari)
    println(azimuth_b_barat)

}