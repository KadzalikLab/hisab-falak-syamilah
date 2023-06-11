package inkasyaf.test

import utilitas.*
import kotlin.math.asin
import kotlin.math.sin

fun main (){
    var jd = masehiKeJd(1,1,2000,0)
    val interval = 6.0/24
    var daftarDm = ArrayList<Double>()


    while (true){



        val u = (jd - 7 / 24.0 - 2451545) / 36525.0 // u pada pukul 5 ut atau 12 wib atau waktu yang telah ditentukan
        val lo = 280.46645 + 36000.76983 * u //Bujur Rata-rata matahari
        val ld = toRad(357.5291 + 35999.0503 * u) // anomali rata-rata matahari
        val lr = lo - 0.0056861 + 1.916128 * sin(ld) //+0.020026* sin(2*ld)           //Bujur Matahari terkoreksi
        val mk = dmsKeDesimal(23, 26, 0) // Mail Kully
        val dm = toDeg(asin(sin(toRad(lr)) * sin(toRad(mk)))) //deklinasi Matahari pada 12 WIB
        daftarDm.add(dm)


        if (jdKeMasehi(jd).tahun==2030){
            println(daftarDm.maxOrNull())
            println(jdKeMasehi(jd).ddmmmyy_hms)
            break
        }
        jd+=interval



    }




}


