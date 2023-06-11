package anwarul_kawakib

import utilitas.desKeDms
import utilitas.desKeHms
import utilitas.toDeg
import utilitas.toRad
import kotlin.math.*

fun main(){
    val tanggal: Double = 3.0
    val bulan: Double = 3.0
    val tahun: Double = 2022.0
    val ihtiyat: Double = 0.0
    val ihtiyatTerbit: Double = 1.0
    val zona_waktu: Double = 7.0
    val tinggi: Double = 50.0
    val bujur: Double = 105.2
    val lintang: Double = -4.96666666667
    val a = (tahun / 100.0).toInt()
    val b = 2 + a / 4 - a
    val aa = (365.25 * tahun).toInt()
    val bb = (30.60001 * (bulan + 1)).toInt()
    val julianday12 = 1720994.5 + aa + bb + tanggal + b + 12 / 24.0
    val jdl = julianday12 - zona_waktu / 24
    val sdl = 2 * 3.14159265359 * (jdl - 2451545) / 365.25
    val dmo = 0.37877 + 23.264 * sin(toRad(57.297 * sdl - 79.547))
    val dmi = dmo + 0.3812 * sin(toRad(2 * 57.297 * sdl - 82.682))
    val dm = dmi + 0.17132 * sin(toRad(3 * 57.297 * sdl - 59.722))


    val u = (jdl - 2451545) / 36525
    var lo = 280.46607 + 36000.7698 * u
    val lor = toRad(lo)
    val e1 = 1789 + 237 * u
    val e2 = 7146 - 62 * u
    val e3 = 9934 - 14 * u
    val e4 = -1 * e1 * sin(lor) - e2 * cos(lor) + e3 * sin(2 * lor)
    val e5 = e4 - (29 + 5 * u) * cos(2 * lor) + (74 + 10 * u) * sin(3 * lor)
    val e6 = (320 - 4 * u) * cos(3 * lor) - 212 * sin(4 * lor)
    val pw = (e5 + e6) / 1000 / 60

    val dmr = toRad(dm)
    val ltr = toRad(lintang)



    val mw = cos(dmr) * cos(ltr)
    val sa2 = abs(lintang - dm)
    val sa1 = sin(atan(1 / (1 + tan(toRad(sa2)))))
    val sa = toDeg(acos((sa1 - sin(dmr) * sin(ltr)) / mw))
    val sm1 = sin(toRad(-0.8333 - 0.0347 * sqrt(tinggi)))
    val swm = toDeg(acos((sm1 - sin(dmr) * sin(ltr)) / mw))
    val swi = toDeg(acos((sin(toRad(-18.0)) - sin(ltr) * sin(dmr)) / mw))
    val sws = toDeg(acos((sin(toRad(-20.0)) - sin(ltr) * sin(dmr)) / mw))
    val swd = toDeg(acos(-tan(ltr) * tan(dmr) + sin(toRad(3.5)) / mw))

    val dz = 12 + zona_waktu - bujur / 15 - pw
    val dzuhur = dz + 4 / 60.0
    val asar = dz + sa / 15 + ihtiyat
    val maghrib = dz + swm / 15 + ihtiyat
    val isya = dz + swi / 15 + ihtiyat
    val subuh = dz - sws / 15 + ihtiyat
    val terbit = dz - swm / 15 - ihtiyatTerbit
    val dluha = dz - swd / 15 + ihtiyat

    println("tanggal \t\t: $tanggal")
    println("Bulan \t\t: $bulan")
    println("Tahun \t\t: $tahun")

    println("A \t\t: $a")
    println("B \t\t: $b")
    println("AA \t\t: $aa")
    println("BB \t\t: $bb")
    println("Jd 12 \t\t: $julianday12")
    println("Jdl \t\t: $jdl")
    println("Sdl\t ${desKeDms(sdl).dmsss}")
    println("dmo \t\t: $dmo")
    println("dmi \t\t: $dmi")
    println("dm \t\t: $dm")
    println("u \t\t: ${desKeDms(u).dmsss}")
    println("lo \t\t: ${desKeDms(toDeg(lo)).dmsss}")
    println("e1 \t\t: $e1")
    println("e2 \t\t: $e2")
    println("e3 \t\t: $e3")
    println("e4 \t\t: $e4")
    println("e5 \t\t: $e5")
    println("e6 \t\t: $e6")
    println("mw \t\t: ${desKeDms(mw).dmsss}")
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