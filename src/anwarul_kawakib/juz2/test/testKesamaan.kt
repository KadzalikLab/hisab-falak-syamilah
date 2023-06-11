package anwarul_kawakib.juz2.test


import ephemeris.Ephem
import utilitas.*
import java.util.ArrayList
import kotlin.math.*

fun main() {

    val tgl = 1
    val bulan = 1
    val tahun = 2000
    val sampaiTahun = tahun+1
    val pukul = 12
    val toleransiPw = dmsKeDesimal(0,1,0)
    val toleransiDm = dmsKeDesimal(0,4,0)


    var jd = masehiKeJd(tgl,bulan,tahun,pukul)

    var ephem: Ephem
    var i = 0

    var daftarPw = ArrayList<Double>()
    var daftarDM = ArrayList<Double>()



    do {
        i++

        ephem = Ephem(jd)
        val dekEphem = ephem.sun_apparentDeclination
        val eotEphem = ephem.sun_equationOfTime




        val t = (jd - 2451545) / 36525
        val lo = 280.46645 + 36000.76983 * t //Bujur Rata-rata matahari
        val ld = toRad(357.5291 + 35999.05030 * t) // anomali rata-rata matahari
        val lr =  lo+ (1.916128*sin(ld)+0.020026*sin(2*ld)+0.966/3600*sin(3*ld))-0.0056861 //Bujur Matahari terkoreksi
//        val lr =  lo+ (1.916128*sin(ld))-0.0056861 //Bujur Matahari terkoreksi

//        koreksi kemiringan bumi rentang abad
        val usm = frac((125.04 - 1934.136 * t) / 360)
        val us = mod(usm * 360,360) //uqdah syamsi
        val r1 = 9.23 / 3600 * cos(toRad(us)) - 0.090 / 3600 * cos(toRad(2 * us))
        val r2 = 0.548 / 3600 * cos(toRad(2 * lo))
        val q1 = 23.43929111 + r1 + r2 - 46.8150 / 3600 * t //mailkully terkoreksi
//        val q1 = dmsKeDesimal(23,26,0)

        val dm = toDeg(asin(sin(toRad(lr)) * sin(toRad(q1)))) //deklinasi Matahari

        val lor = toRad(lo)
        val y = tan(toRad(q1/2)).pow(2)
        val pm = 0.016711651
        val e1 = y * sin(2*lor) - 2 *pm* sin(ld)
        val e2 = e1+4* pm *y* sin(ld)* cos(2*lor)
        val e3 = e2-0.5*y.pow(2.0) * sin(4*lor)
        val es = e3- 1.25* pm.pow(2.0) * sin(2*ld)
        val pw = toDeg(es/15)


        val selisihPW = abs(eotEphem-pw)
        val selisihDM = abs(dekEphem-dm)

        daftarPw.add(selisihPW)
        daftarDM.add(selisihDM)


        //Koreksi Jangka Abad untuk Deklinasi Matahari
//        val lr =  lp+ (1.916128*sin(lk)+0.020026*sin(2*lk)+0.966/3600*sin(3*lk))-0.0056861
//        val dm = toDeg(asin(sin(toRad(lof)) * sin(toRad(q1))))






//        println("$i. ${jdKeMasehi(jd).tahun} : ${desKeDms(selisihOri).dmsss}  vs ${desKeDms(selisih).dmsss} ")
        println("$i. ${jdKeMasehi(jd).ddmmmyy_hms} :  ${desKeDms(selisihDM).dmsss} | ${desKeDms(selisihPW).dmsss} ")

        if (abs(selisihPW)> toleransiPw||abs(selisihDM)> toleransiDm ) {
            println()
            println("$i. ${jdKeMasehi(jd).ddmmmyy_hms} : ${desKeDms(selisihPW).dmsss} | ${desKeDms(selisihDM).dmsss}  ")

            break
        }
        jd++
    } while (jdKeMasehi(jd).tahun<3000)

    val rata2Dm = daftarDM.average()
    val rata2Pw = daftarPw.average()
    val rata2 = "DM: ${desKeDms(rata2Dm).dmsss} dan Pw: ${desKeDms(rata2Pw).dmsss}"
    println(rata2)

    var maxDm = daftarDM.maxOrNull()
    if (maxDm==null) maxDm = .0
    var maxPw = daftarPw.maxOrNull()
    if (maxPw==null) maxPw = .0
    val selisihMax = "Selisih Maksimal DM: ${desKeDms(maxDm).dmsss} dan Pw: ${desKeDms(maxPw).dmsss}"
    println(selisihMax)


}



// Catatan
/*
*  dalam rentang 500 tahun ( 2000 s/d 2500 ) Menggunakan rumus ini tanpa koreksi q1 (Kemiringan bumi) dan koreksi lr
* deklinasi matahari mengasilkan nilai akurat dengan rata-rata kesalahan 1 menit busur
* dan perata waktu dengan maksimal kesalahan 12 detik   dan rata-rata kesalahan hanya 2.45 detik saja.
*
* sedangkan bila  q1 dan lr di koreksi
*  kesalahan deklinasi matahari rata-rata 10 detik busur
* dan perata waktu dengan maksimal kesalahan 9 detik  dan rata-rata kesalahan hanya 2.12 detik saja.
*
* dan dalam rentang 1000 tahun ( 2000 s/d 3000 )
* kesalahan deklinasi matahari rata-rata 19 detik busur
* dan perata waktu dengan maksimal kesalahan 15 detik busur dan rata-rata kesalahan hanya 3 detik saja.
* * */

