package inkasyaf.kiblat.roshdul_kiblat

import inkasyaf.DataMat
import utilitas.desKeDms
import utilitas.desKeHms
import kotlin.math.abs

fun main (){
    val zw = 7
    val zw_makkah = 3
    val selisih = zw-zw_makkah

    /*

    bila kabisat:
    27 Mei
    15 Juli

    bila Basitoh :
    28 Mei
    16 Juli

     */

    val dt = DataMat(12.0-zw_makkah, tanggal =  16, bulan = 7)
    //12 – PW + ( ( Ast x 15 ) – Bk) / 15 + ( Zw – Ast )
    val tr = 12 - dt.pw + ((dt.zona_waktu_makkah*15)-dt.bujur_kabah)/15+(selisih)
    println(desKeHms(tr).hmsss)
    println(desKeDms(abs(dt.lintang_kabah-dt.dm)).dms)



}