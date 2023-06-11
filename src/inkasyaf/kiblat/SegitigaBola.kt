package inkasyaf.kiblat

import utilitas.dmsKeDesimal
import utilitas.printDms
import utilitas.toDeg
import utilitas.toRad
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
    val detik_lintangKB = 21

    val derajat_bujurKB = 39
    val menit_bujurKB = 49
    val detik_bujurKB = 34

    val lintang_tempat = dmsKeDesimal(derajat_lintang, menit_lintang, detik_lintang)
    val lintang_kabah = dmsKeDesimal(derajat_lintangKB, menit_lintangKB, detik_lintangKB)

    val bujur_tempat = dmsKeDesimal(derajat_bujur, menit_bujur, detik_bujur)
    val bujur_kabah = dmsKeDesimal(derajat_bujurKB, menit_bujurKB, detik_bujurKB)


    val a = 90-lintang_tempat
    val b = 90-lintang_kabah
    val c = bujur_tempat-bujur_kabah
    val d = sin(toRad(a))/ tan(toRad(b))/ sin(toRad(c))- cos(toRad(a))/ tan(toRad(c))
    val ub = toDeg(atan(1/d))
    val bu = 90 - ub

    printDms("A",a)
    printDms("B",b)
    printDms("C",c)
    printDms("D", d)
    printDms("UB",ub)
    printDms("BU",bu)




}