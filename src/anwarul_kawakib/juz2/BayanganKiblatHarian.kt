package anwarul_kawakib.juz2

import utilitas.*
import kotlin.math.*

fun main(){
hisab3()
}

fun hisab3(){
    val tanggal=5
    val bulan= 12
    val tahun = 2021
    val zona_waktu = 7
    val bujur_tempat = dmsKeDesimal(105,12,0)
    val lintang_tempat = dmsKeDesimal(-4,58,0)

    val bujur_kabah = dmsKeDesimal(39,49,34)
    val lintang_kabah = dmsKeDesimal(21,25,20.99)


    val a1 = (tahun / 100.0).toInt()
    val b = 2 + a1 / 4.0 - a1
    val aa = (365.25 * tahun).toInt()
    val bb = (30.60001 * (bulan + 1)).toInt()
    val julianday12 = 1720994.5 + aa + bb + tanggal + b + 12 / 24.0
    val jdl = julianday12 - zona_waktu / 24
    val sdl = 2 * 3.14159265359 * (jdl - 2451545) / 365.25
    val dmo = 0.37877 + 23.264 * sin(toRad(57.297 * sdl - 79.547))
    val dmi = dmo + 0.3812 * sin(toRad(2 * 57.297 * sdl - 82.682))
    val u = (jdl - 2451545) / 36525
    val lo = 280.46607 + 36000.7698 * u
    val lo_Rad = toRad(lo)
    val e1 = 1789 + 237 * u
    val e2 = 7146 - 62 * u
    val e3 = 9934 - 14 * u
    val e4 = -1 * e1 * sin(lo_Rad) - e2 * cos(lo_Rad) + e3 * sin(2 * lo_Rad)
    val e5 = e4 - (29 + 5 * u) * cos(2 * lo_Rad) + (74 + 10 * u) * sin(3 * lo_Rad)
    val e6 = (320 - 4 * u) * cos(3 * lo_Rad) - 212 * sin(4 * lo_Rad)


    val deklinasi= dmi + 0.17132 * sin(toRad(3 * 57.297 * sdl - 59.722))
    val pw = (e5 + e6) / 1000 / 60

    val y = sin(toRad(bujur_kabah-bujur_tempat))
    val x = cos(toRad(lintang_tempat))* tan(toRad(lintang_kabah))- sin(toRad(lintang_tempat))* cos(toRad(bujur_kabah-bujur_tempat))
    var a = atan(y/x)
    a = toDeg(a)
    val aq:Double
    aq = if (x>0&&y>=0) a
    else if (x>0 && y<0) a+360
    else if (x<0) a+180
    else if (x==0.0 && y>0) 90.0
    else if (x==0.0 && y<0) 270.0
    else Double.NaN

    val p = toDeg(atan(1/(sin(toRad(lintang_tempat))* tan(toRad(aq)))))
    val ca = toDeg(acos(tan(toRad(deklinasi))/ tan(toRad(lintang_tempat))* cos(toRad(p))))


    val bb1 = (- (p-ca)/15+12)-pw
    val bq1 = bb1 + (15* zona_waktu - bujur_tempat)/15

    val ba2 = - (p+ca)/15+12 - pw
    val bq2 = ba2 + (15* zona_waktu - bujur_tempat)/15




//    val tet = toDeg(atan2(y,a))

//    println(bujur_kabah)
//    println(lintang_kabah)
//    println(bujur_tempat)
//    println(lintang_tempat)
    println(desKeDms(deklinasi).dmsss)
    println(desKeDms(pw).hmsss)


    println(x)
    println(y)
    println(desKeDms(a).dmsss)
    println(desKeDms(aq).dmsss)
    println(desKeDms(p).dmsss)
    println(desKeDms(ca).dmsss)
    println("Bayangan Kiblat 1 "+desKeDms(bq1).hmsss)
    println("Bayangan Kiblat 2 "+desKeDms(bq2).hmsss)
//    println(desKeDms(tet).dmsss)
}


fun hisab2(){
    val zona_waktu = 7
    val bujur_tempat = dmsKeDesimal(109,54,11.16)
    val lintang_tempat = dmsKeDesimal(-7,21,31.51)

    val bujur_kabah = dmsKeDesimal(39,49,34.27)
    val lintang_kabah = dmsKeDesimal(21,25,21.02)



    val deklinasi=  dmsKeDesimal(-22,45,29)
    val pw =  dmsKeDesimal(0,8,1)

    val y = sin(toRad(bujur_kabah-bujur_tempat))
    val x = cos(toRad(lintang_tempat))* tan(toRad(lintang_kabah))- sin(toRad(lintang_tempat))* cos(toRad(bujur_kabah-bujur_tempat))
    var a = atan(y/x)
    a = toDeg(a)
    val aq:Double
    aq = if (x>0&&y>=0) a
    else if (x>0 && y<0) a+360
    else if (x<0) a+180
    else if (x==0.0 && y>0) 90.0
    else if (x==0.0 && y<0) 270.0
    else Double.NaN

    val p = toDeg(atan(1/(sin(toRad(lintang_tempat))* tan(toRad(aq)))))
    val ca = toDeg(acos(tan(toRad(deklinasi))/ tan(toRad(lintang_tempat))* cos(toRad(p))))

    val ba1 = - (p-ca)/15+12
    val bb1 = ba1-pw
    val bq1 = bb1 + (15* zona_waktu - bujur_tempat)/15

    val ba2 = - (p+ca)/15+12
    val bb2 = ba2-pw
    val bq2 = bb2 + (15* zona_waktu - bujur_tempat)/15




//    val tet = toDeg(atan2(y,a))

//    println(bujur_kabah)
//    println(lintang_kabah)
//    println(bujur_tempat)
//    println(lintang_tempat)
    println(y)
    println(x)
    println(desKeDms(a).dmsss)
    println(desKeDms(aq).dmsss)
    println(desKeDms(p).dmsss)
    println(desKeDms(ca).dmsss)
    println("Bayangan Kiblat 1 "+desKeDms(bq1).hmsss)
    println("Bayangan Kiblat 2 "+desKeDms(bq2+12).hmsss)
//    println(desKeDms(tet).dmsss)










}


fun hisab(){

    val zona_waktu = 7
    val bujur_tempat = dmsKeDesimal(105,12,0)
    val lintang_tempat = dmsKeDesimal(-4,58,0)

    val bujur_kabah = dmsKeDesimal(39,49,34)
    val lintang_kabah = dmsKeDesimal(21,25,21)



    val deklinasi= 0.0
    val pw =0.0

    val a: Double = bujur_tempat - bujur_kabah //selisihBujur

    val b = -sin(toRad(lintang_tempat)) / tan(toRad(a))
    val c = cos(toRad(lintang_tempat)) * tan(toRad(lintang_kabah)) / sin(toRad(a))
    val m = toDeg(atan(b + c)) + 270 //aimuth kiblat

    val f = 90 - deklinasi
    val y: Double = 90 - lintang_tempat
    val a2 = 1 / (cos(toRad(y)) * tan(toRad(m)))
    val b2 = toDeg(abs(atan(a2)))
    val b3 = 1 / tan(toRad(f)) * tan(toRad(y)) * cos(toRad(b2))
    val aa = toDeg(abs(acos(b3))) // sudut waktu

    val transit: Double = 12 - pw + (zona_waktu * 15 - bujur_tempat) / 15
    var perkiraan_I = transit + (aa - b2) / 15
    if (perkiraan_I < 0) perkiraan_I += 24.0 else if (perkiraan_I >= 24) perkiraan_I -= 24.0

    var perkiraan_II = transit - (aa + b2) / 15
    if (perkiraan_II < 0) perkiraan_II += 24.0 else if (perkiraan_II >= 24) perkiraan_II -= 24.0

    val koreksiPukul: Double = perkiraan_I - 12 + 7 - zona_waktu
    val deklinasi_new: Double = 0.0
    val perataWaktu_new: Double = .0
//    val deklinasi_new: Double = deklinasi(jd as Int + 0.5 + (perkiraan_I - zona_waktu) / 24.0)
//    val perataWaktu_new: Double = peratawaktu(jd as Int + 0.5 + (perkiraan_I - zona_waktu) / 24.0)


    val m3 = 90 - deklinasi_new
    val m4: Double = 90 - lintang_tempat
    val m5 = 1 / (cos(toRad(m4)) * tan(toRad(m)))
    val m6 = toDeg(abs(atan(m5)))
    val m7 = 1 / tan(toRad(m3)) * tan(toRad(m4)) * cos(toRad(m6))
    val sudutWaktu = toDeg(abs(acos(m7)))
    val transit_hakiki: Double = 12 - perataWaktu_new + (zona_waktu * 15 - bujur_tempat) / 15
    val transit_hakiki_W = transit_hakiki + (sudutWaktu - m6) / 15
    val roshdu_hakiki: Double
    roshdu_hakiki = if (transit_hakiki < 0) transit_hakiki_W + 24 else if (perkiraan_II >= 24) transit_hakiki_W - 24 else transit_hakiki_W


}