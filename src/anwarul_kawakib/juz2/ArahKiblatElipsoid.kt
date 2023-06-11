package anwarul_kawakib.juz2

import utilitas.*
import kotlin.math.*

fun main (){

    val derajat_lintang = -4
    val menit_lintang = 58
    val detik_lintang = 0.0

    val derajat_bujur = 105
    val menit_bujur = 12
    val detik_bujur = 0.0

    //KOORDINAT KA'BAH DIAMBIL DARI PERSATUAN DATABASE GNSS (GLOBAL NAVIGATION SATELLITE SYSTEM) internasional bukan hanya Gps saja!
    val derajat_lintangKB = 21
    val menit_lintangKB = 25
    val detik_lintangKB = 21.08

    val derajat_bujurKB = 39
    val menit_bujurKB = 49
    val detik_bujurKB = 34.25



    val lintang_tempat = dmsKeDesimal(derajat_lintang, menit_lintang, detik_lintang)
    val lintang_kabah = dmsKeDesimal(derajat_lintangKB, menit_lintangKB, detik_lintangKB)
    
    val bujur_tempat = dmsKeDesimal(derajat_bujur, menit_bujur, detik_bujur)
    val bujur_kabah = dmsKeDesimal(derajat_bujurKB, menit_bujurKB, detik_bujurKB)

    val e =  0.0066943800229


    val lt_terkoreksi = toDeg(atan((1-e) * tan(toRad(lintang_tempat))))
    val lk_terkoreksi = toDeg(atan((1-e) * tan(toRad(lintang_kabah))))

    val a = toRad(90-lt_terkoreksi)
    val b = toRad(90 - lk_terkoreksi)
    val c = toRad(bujur_tempat-bujur_kabah)

    val sb = sin(b)*sin(c)
    val cb = cos(b)*sin(a)-cos(a)*sin(b)*cos(c)
    val bb = atan2(sb,cb)
    val db = atan(sb/cb)
    val arahKiblat = mod(360- toDeg(bb),360)
    println(desKeDms((sb)).dmsss)
    println(desKeDms((cb)).dmsss)
    println(desKeDms(bb).dmsss)
    println(desKeDms(toDeg(bb)).dmsss)
    println(desKeDms(360-toDeg(db)).dmsss)
    println(desKeDms(arahKiblat).dmsss)


}