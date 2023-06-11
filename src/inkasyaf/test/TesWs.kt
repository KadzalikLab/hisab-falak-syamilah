package inkasyaf


import utilitas.*
import kotlin.math.*

fun main (){
    
    var i = 0
    var pukul: Double = 12.0
    var tanggal: Int = 1
    while (true){


        tanggal++
        var bulan: Int = 1
        var tahun: Int = 2023
        var bujur: Double = dmsKeDesimal(112, 13, 0)
        var lintang: Double = dmsKeDesimal(-7, 47, 0)
        var ketinggianTempat: Double = 200.0
        var zona_waktu: Double = 7.0
        var zona_waktu_makkah: Double = 3.0

        var bujur_kabah: Double = dmsKeDesimal(39, 49, 34)
        var lintang_kabah: Double = dmsKeDesimal(21, 25, 20.99)


        
            /////////////////////////////////////////////////////////////////////////////////


            val a = (tahun / 100.0).toInt()
            val ak = (a / 4.0).toInt()
            val b = 2 + ak - a
            val ac = (365.25 * tahun).toInt()
            val bc = (30.6001 * (bulan + 1)).toInt()
            val jd = 1720994.5 + ac + bc + tanggal + b + pukul / 24.0

            val u = (jd - zona_waktu / 24.0 - 2451545) / 36525.0 // u pada pukul 5 ut atau 12 wib atau waktu yang telah ditentukan
            val lo = 280.46645 + 36000.76983 * u //Bujur Rata-rata matahari
            val ld = toRad(357.5291 + 35999.0503 * u) // anomali rata-rata matahari
            val lr = lo - 0.0056861 + 1.916128 * sin(ld) //+0.020026* sin(2*ld)           //Bujur Matahari terkoreksi

//    val lr =  lo+ (1.916128*sin(ld)+0.020026*sin(2*ld)+0.966/3600*sin(3*ld))-0.0056861 //Bujur Matahari terkoreksi          //        koreksi rentang abad
//    val usm = frac((125.04 - 1934.136 * u) / 360)
//    val us = mod(usm * 360,360) //uqdah syamsi
//    val r1 = 9.23 / 3600 * cos(toRad(us)) - 0.090 / 3600 * cos(toRad(2 * us))
//    val r2 = 0.548 / 3600 * cos(toRad(2 * lo))
//    val mk = 23.43929111 + r1 + r2 - 46.8150 / 3600 * u //mailkully terkoreksi                                    //        koreksi  rentang abad

            val mk = dmsKeDesimal(23, 26, 0) // Mail Kully
            val dm = toDeg(asin(sin(toRad(lr)) * sin(toRad(mk)))) //deklinasi Matahari pada 12 WIB

            val lor = toRad(lo)
            val y = tan(toRad(mk / 2)).pow(2)
            val pe = 0.016711651 // Kepipihan Orbit Bumi
            val e1 = y * sin(2 * lor) - 2 * pe * sin(ld)
            val e2 = e1 + 4 * pe * y * sin(ld) * cos(2 * lor)
            val e3 = e2 - 0.5 * y.pow(2.0) * sin(4 * lor)
            val e4 = e3 - 1.25 * pe.pow(2.0) * sin(2 * ld)
            val pw = toDeg(e4) / 15 //deklinasi Matahari pada 12 WIB

        
        val dmr = toRad(dm)
        val ltr = toRad(lintang)



        val mw = cos(dmr) * cos(ltr)
        val ms = sin(dmr)*sin(ltr)
        val sa2 = abs(lintang - dm)
        val sa1 = sin(atan(1 / (1 + tan(toRad(sa2)))))
        val sa = toDeg(acos((sa1 - ms) / mw))
        val sm1 = sin(toRad(-0.8333 - 0.0347 * sqrt(ketinggianTempat)))
        val swm = toDeg(acos((sm1 - ms) / mw))
        val swi = toDeg(acos((sin(toRad(-17.0)) - ms) / mw))
        val sws = toDeg(acos((sin(toRad(-19.0)) - ms) / mw))
//    val swd = toDeg(acos(-tan(ltr) * tan(dmr) + sin(toRad(3.5)) / mw))
        val swd = toDeg(acos((sin(toRad(4.5)) - ms) / mw))

        val ihtiyat = 2.0/60
        val ihtiyatTerbit = ihtiyat - 1.0/60
        val dz = 12 + zona_waktu - bujur / 15 - pw
        val dzuhur = dz + 4 / 60.0
        val asar = dz + sa / 15 + ihtiyat
        val maghrib = dz + swm / 15 + ihtiyat
        val isya = dz + swi / 15 + ihtiyat
        val subuh = dz - sws / 15 + ihtiyat
        val terbit = dz - swm / 15 - ihtiyatTerbit
        val dluha = dz - swd / 15 + ihtiyat

//        println("Dzuhur \t\t: ${desKeHms(dzuhur).hmsss}")
//        println("Ashar \t\t: ${desKeHms(asar).hmsss}")
//        println("Maghrib \t\t: ${desKeHms(maghrib).hmsss}")
//        println("Isya \t\t: ${desKeHms(isya).hmsss}")
//        println("Subuh \t\t: ${desKeHms(subuh).hmsss}")
//        println("Terbit \t\t: ${desKeHms(terbit).hmsss}")
//        println("Dluha \t\t: ${desKeHms(dluha).hmsss}")


        val jjd = jdKeMasehi(jd)
        if (jjd.tanggal==1) {
            println()
            println(namaBulan(jjd.bulan))
            println("Maghrib     Isya     Subuh    Thulu    Dluha    Dzuhur   Ashar")
        }
        val str = " ${jjd.tanggal} ${desKeHms(maghrib).hm}    ${desKeHms(isya).hm}     ${desKeHms(subuh).hm}    ${desKeHms(terbit).hm}    ${desKeHms(dluha).hm}  ${desKeHms(dzuhur).hm}     ${desKeHms(asar).hm} "


        when (jjd.tanggal){
            1, 6,11,16,21,26->{
                println(str)
            }
        }

        if (tanggal>=365) break

    }
    
    


}