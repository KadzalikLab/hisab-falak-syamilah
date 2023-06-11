package inkasyaf

import utilitas.*
import kotlin.math.*

fun main (){
    val lintangP= dmsKeDesimal(-4,29,0)
    val bujurP= dmsKeDesimal(105,14,0)
    val dt = DataMat(12.0,27,2,2023,
        lintang = lintangP,
        bujur = bujurP
    )


    val dm = dt.dm
    val pw = dt.pw
    val dmr = toRad(dm)
    val ltr = toRad(dt.lintang)



    val mw = cos(dmr) * cos(ltr)
    val ms = sin(dmr)*sin(ltr)
    val sa2 = abs(dt.lintang - dt.dm)
    val sa1 = sin(atan(1 / (1 + tan(toRad(sa2)))))
    val sa = toDeg(acos((sa1 - ms) / mw))
    val sm1 = sin(toRad(-0.8333 - 0.0347 * sqrt(dt.ketinggianTempat)))
    val swm = toDeg(acos((sm1 - ms) / mw))
    val swi = toDeg(acos((sin(toRad(-18.0)) - ms) / mw))
    val sws = toDeg(acos((sin(toRad(-20.0)) - ms) / mw))
//    val swd = toDeg(acos(-tan(ltr) * tan(dmr) + sin(toRad(3.5)) / mw))
    val swd = toDeg(acos((sin(toRad(3.5)) - ms) / mw))

    val ihtiyat = 1/60
    val ihtiyatTerbit = ihtiyat - 1.0/60
    val dz = 12 + dt.zona_waktu - dt.bujur / 15 - pw
    val dzuhur = dz + 4 / 60.0
    val asar = dz + sa / 15 + ihtiyat
    val maghrib = dz + swm / 15 + ihtiyat
    val isya = dz + swi / 15 + ihtiyat
    val subuh = dz - sws / 15 + ihtiyat
    val terbit = dz - swm / 15 - ihtiyat
    val dluha = dz - swd / 15 + ihtiyat

    println("mw \t\t: ${desKeDms(mw).dmsss}")
    val msd = toDeg(ms)
    println("ms \t\t: ${desKeDms(ms).dmsss}")
    println("sa2 \t\t: ${desKeDms(sa2).dmsss}")
    println("sa1 \t\t: ${desKeDms(sa1).dmsss}")
    println("sa \t\t: ${desKeDms(sa).dmsss}")
    println("sm1 \t\t: ${desKeDms(sm1).dmsss}")
    println("swm \t\t: ${desKeDms(swm).dmsss}")
    println("swi \t\t: ${desKeDms(swi).dmsss}")
    println("sws \t\t: ${desKeDms(sws).dmsss}")
    println("swd \t\t: ${desKeDms(swd).dmsss}")

    println("DM \t\t\t: ${desKeDms(dm).dmsss}")
    println("PW \t\t: ${desKeDms(pw).hmsss}")



    println("Dzirwah \t\t: ${desKeHms(dz).hmsss}")
    println("Dzuhur \t\t: ${desKeHms(dzuhur).hmsss}")
    println("Ashar \t\t: ${desKeHms(asar).hmsss}")
    println("Maghrib \t\t: ${desKeHms(maghrib).hmsss}")
    println("Isya \t\t: ${desKeHms(isya).hmsss}")
    println("Subuh \t\t: ${desKeHms(subuh).hmsss}")
    println("Terbit \t\t: ${desKeHms(terbit).hmsss}")
    println("Dluha \t\t: ${desKeHms(dluha).hmsss}")




}