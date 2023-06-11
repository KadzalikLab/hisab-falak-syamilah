package inkasyaf.kiblat.roshdul_kiblat

import inkasyaf.*
import utilitas.desKeDms
import utilitas.printDms
import utilitas.toDeg
import utilitas.toRad
import kotlin.math.*


fun main (){

    val dt =DataMat(12.0,17,11,2022) //mengambil template data matahari

    val y = sin(toRad(dt.bujur_kabah-dt.bujur))
    val x = cos(toRad(dt.lintang)) * tan(toRad(dt.lintang_kabah)) - sin(toRad(dt.lintang)) * cos(toRad(dt.bujur_kabah-dt.bujur))
    var a = atan(y/x)
    a = toDeg(a)
    val aq:Double =
        if (x>0&&y>=0) a
        else if (x>0 && y<0) a+360
        else if (x<0) a+180
        else if (x==0.0 && y>0) 90.0
        else if (x==0.0 && y<0) 270.0
        else Double.NaN

    val p = toDeg(atan(1/(sin(toRad(dt.lintang)) * tan(toRad(aq)))))
    val ca = toDeg(acos(tan(toRad(dt.dm)) / tan(toRad(dt.lintang)) * cos(toRad(p))))


    val bb1 = (- (p-ca)/15+12)-dt.pw
    val bq1 = bb1 + (15* dt.zona_waktu - dt.bujur)/15

//    Roshdul Kiblat  Tsani
    val ba2 = - (p+ca)/15+12  - dt.pw
    val bq2 = ba2 + (15* dt.zona_waktu - dt.bujur)/15


    printDms("P",p)
    printDms("Ca",ca)
    println("Bayangan Kiblat 1 "+ desKeDms(bq1).hmsss)
    printDms("P",p)
    printDms("Ca",ca)
    println("Bayangan Kiblat 2 "+ desKeDms(bq2).hmsss)

}