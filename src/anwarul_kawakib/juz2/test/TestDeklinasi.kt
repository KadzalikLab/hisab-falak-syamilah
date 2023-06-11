package anwarul_kawakib

import ephemeris.Ephem
import utilitas.*
import kotlin.math.asin
import kotlin.math.pow
import kotlin.math.sin

fun main (){

    val tgl = 3
    val bulan = 3
    val tahun = 2022
    val pukul = dmsKeDesimal(11,18,16.1)
    var jd = masehiKeJd(tgl,bulan,tahun,pukul)

    val ephem = Ephem(jd)
    val dekEphem = ephem.sun_apparentDeclination





    val bbb = 367*tahun-730531.5
    val ccc = ((7.0 * (tahun + (bulan + 9) / 12).toInt()) / 4).toInt()
    val ddd = (275 * bulan / 9).toInt() + tgl
    val d2000 = bbb - ccc + ddd + pukul / 24
//    val d2000 = masehiKeJd(tgl,bulan,tahun,pukul)
    val Cycle = (d2000/365.25).toInt()
    val Theta = 0.0172024 * (d2000 - 365.25 * Cycle)
//    var Amp1 = 23.2639 - Cycle * 0.000131 + 0.0024 * sin(Cycle * 0.335103 - 0.4)
    val Amp1 = 23.2639 - Cycle * 0.000131 + 0.0024 * sin(Cycle * 0.335 - 0.4)
    val Phi1 = -1.38819 + Cycle * 0.000135
    val Decl1 = Amp1 * sin(1 * (Theta + Phi1))
    val Decl2 = 0.380897 * sin(2 * (Theta - 0.720483))
    val Decl3 = 0.171178 * sin(3 * (Theta - 0.347175))
    val Decl4 = 0.008067 * sin(4 * (Theta - 0.272216))
    val dec = 0.37657 + Decl1 + Decl2 + Decl3


    val sdlM = (360 * (jd - 2451545) / 365.25)
    val dmoM = 0.37877 + 23.264 * sin(toRad(sdlM - 79.547))
    val dmiM = dmoM + 0.3812 * sin(toRad(2 * sdlM - 82.682))
    val dmModif = dmiM + 0.17132 * sin(toRad(3 * sdlM - 59.722))

    val t = (jd - 2451545) / 36525
    val tau =  t/10
    var lo = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau.pow(2.0) + tau.pow(3.0) / 49931 - tau.pow(4.0) / 15300 - tau.pow(5.0) / 2000000
    lo = mod(lo, 360)
    val dlp = (+(1.882 - 0.016 * t) * sin((57.24 + 150.27 * t)) + 6.4 * sin((231.19 + 20.2 * t)) + 0.266 * sin((31.8 + 119 * t))) / 3600 // Long period pertubation of the sun's mean longitude and it's mean anomaly
//    val lo = mod(279.6966778 + 36000 * t + (2768.13 * t + 1.089 * Math.pow(t, 2.0) + 0.202 * sin((315.6 + 893.3 * t))) / 3600 + dlp, 360) // Mean Longitude of the sun
    val dmlo = toDeg(asin(sin(toRad(lo)) * sin(toRad(dmsKeDesimal(23,26,0)))))


    val sdk = (280.46645 + 36000.76983 * t)
    val kkn = (toRad(357.5291 + 35999.05030 * t))
    val lof = ( sdk+ (1.916128*sin(kkn)+0.020026*sin(2*kkn))-0.0056)
    val dmlof = toDeg(asin(sin(toRad(lof)) * sin(toRad(dmsKeDesimal(23,26,0)))))


    println(bbb)
    println(ccc)
    println(ddd)
    println(d2000)
    println(Cycle)
    println(Theta)
    printDms(Amp1)
    printDms(Phi1)
    printDms(Decl1)
    printDms(Decl2)
    printDms(Decl3)
    printDms(Decl4)
    println()
    println(desKeDms(dekEphem).dmsss)
    println(desKeDms(dec).dmsss)
//    println(desKeDms(dmModif).dmsss)
    println(desKeDms(lof).dmsss)
    println(desKeDms(dmlof).dmsss)

}