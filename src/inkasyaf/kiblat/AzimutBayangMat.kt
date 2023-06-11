package inkasyaf.kiblat

import inkasyaf.DataMat
import utilitas.*
import kotlin.math.*

fun main (){
    val pukul = 9.0

    val dt = DataMat(pukul) //mengambil template data matahari

    val pg = 20.0 // Input dari User
    val lmt = (pukul+dt.pw)-(((dt.zona_waktu*15)-dt.bujur)/15)
    val to = (lmt-12)*15
    val m1 = cos(toRad(dt.lintang)) * tan(toRad(dt.dm)) / sin(toRad(to))
    var azo = atan(1/(m1+(-sin(toRad(dt.lintang)) / tan(toRad(to)))))
    azo = toDeg(azo)
    val sda = 90- abs(azo)
    val pa = tan(toRad(sda)) *pg
    val pb = pg/ cos(toRad(sda))




    println("Pg $pg")

    printDms("Lmt",lmt)
    printDms("To",to)
    printDms("M1",m1)
    printDms("Azo",azo)
    printDms("Sda",sda)
    printKl("Pa",pa)
    printKl("Pg",pb)


}