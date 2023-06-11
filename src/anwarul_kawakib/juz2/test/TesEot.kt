package anwarul_kawakib.juz2.test


import ephemeris.Ephem
import utilitas.*
import kotlin.math.*

fun main() {

    val tgl = 13
    val bln = 10
    val tahun = 1992
    val jam = 13


    var jd = utilitas.masehiKeJd(tgl, bln, tahun, jam)
    jd = 2448908.5
    println(jd)

    val ephem: Ephem = Ephem(jd)
    val dekEphem = ephem.sun_apparentDeclination
    val eotEphem = ephem.sun_equationOfTime

    val q1 = dmsKeDesimal(23,26,0)
//    val q1 = 23.44023
    val t = (jd - 2451545) / 36525
//    val t = -0.072183436
    val lp = 280.46645 + 36000.76983 * t
    val lk = toRad(357.5291 + 35999.05030 * t) // anomali rata-rata matahari
    var lo =  (280.46645 + 360007.6983 * t)+ (1.916128*sin(lk))-0.0056861
    val dmModif = toDeg(asin(sin(toRad(lo)) * sin(toRad(q1))))


    lo =toRad( lp)
    val y = tan(toRad(q1/2)).pow(2)
    val pm = 0.016711651
    val e1 = y * sin(2*lo) - 2 *pm* sin(lk)
    val e2 = e1+4* pm *y* sin(lk)* cos(2*lo)
    val e3 = e2-0.5*y.pow(2.0) * sin(4*lo)
    val es = e3- 1.25* pm.pow(2.0) * sin(2*lk)
    val eot = toDeg(es/15)
//    val es = y * sin((2*lo)) - 2 *eks* sin((m))+4* eks *y * sin(m)* cos((2*lo))-1/2.0*y.pow(2.0)*sin((4*lo))-(5/4.0)* eks.pow(2.0) * sin((2*m))



    val lor = toRad(lp)
    val e11 = 1789 + 237 * t
    val e22 = 7146 - 62 * t
    val e33 = 9934 - 14 * t
    val e4 = -1 * e11 * sin(lor) - e22 * cos(lor) + e33 * sin(2 * lor)
    val e5 = e4 - (29 + 5 * t) * cos(2 * lor) + (74 + 10 * t) * sin(3 * lor)
    val e6 = (320 - 4 * t) * cos(3 * lor) - 212 * sin(4 * lor)
    val pw = (e5 + e6) / 1000 / 60

    println(desKeDms(eot).dmsss)
    println(desKeDms(pw).dmsss)





}
