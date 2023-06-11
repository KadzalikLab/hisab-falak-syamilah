package utilitas

import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.round

/**
Mengubah nilai nomor bulan menjadi nama bulan, sesuai urutan yang berlaku
 */

fun namaBulan(nomorBulan: Int): String {
    return when (nomorBulan) {
        1 -> "Januari"
        2 -> "Februari"
        3 -> "Maret"
        4 -> "April"
        5 -> "Mei"
        6 -> "Juni"
        7 -> "Juli"
        8 -> "Agustus"
        9 -> "September"
        10 -> "Oktober"
        11 -> "November"
        12, 0 -> "Desember"
        else -> ""
    }
}


fun namaBln(nomorBulan: Int): String {
    return when (nomorBulan) {
        1 -> "Jan"
        2 -> "Feb"
        3 -> "Mar"
        4 -> "Apr"
        5 -> "Mei"
        6 -> "Jun"
        7 -> "Jul"
        8 -> "Ags"
        9 -> "Sep"
        10 -> "Okt"
        11 -> "Nov"
        12, 0 -> "Des"
        else -> ""
    }
}

fun namaBulanHijri(nomorBulan: Int): String {
    return when (nomorBulan) {
        1 -> "Muharram"
        2 -> "Shafar"
        3 -> "Rabiul Awal"
        4 -> "Rabiul Akhir"
        5 -> "Jumadil Ula"
        6 -> "Jumadil Tsani"
        7 -> "Rajab"
        8 -> "Sya'ban"
        9 -> "Ramadhan"
        10 -> "Syawal"
        11 -> "Dzulqo'dah"
        12, 0 -> "Dzulhijjah"
        else -> ""
    }
}


fun namaBlnHijri(nomorBulan: Int): String {
    return when (nomorBulan) {
        1 -> "Mhrm"
        2 -> "Sfr"
        3 -> "R Aw"
        4 -> "R Ah"
        5 -> "J Ul"
        6 -> "J Ts"
        7 -> "Rjb"
        8 -> "Sbn"
        9 -> "Rmdn"
        10 -> "Swl"
        11 -> "Dz.Q"
        12, 0 -> "Dz.H"
        else -> ""
    }
}

fun namaHari(julianDay:Double):String{
    var hasil = ""
    when(nomorHari(julianDay)){
        1-> hasil = "Ahad"
        2-> hasil = "Senin"
        3-> hasil = "Selasa"
        4-> hasil = "Rabu"
        5-> hasil = "Kamis"
        6-> hasil = "Jumat"
        7,0-> hasil = "Sabtu"
    }
    return hasil
}

fun namaHari(nomorHari:Int):String{
    var hasil = ""
    when(nomorHari){
        1-> hasil = "Ahad"
        2-> hasil = "Senin"
        3-> hasil = "Selasa"
        4-> hasil = "Rabu"
        5-> hasil = "Kamis"
        6-> hasil = "Jumat"
        7,0-> hasil = "Sabtu"
    }
    return hasil
}

fun nomorHari(julianDay: Double):Int{
    return ((julianDay + 1.5) % 7 + 1).toInt()
}
fun namaPasaran(julianDay:Double):String{
    var hasil = ""
    when(nomorPasaran(julianDay)){
        1-> hasil = "Legi"
        2-> hasil = "Pahing"
        3-> hasil = "Pon"
        4-> hasil = "Wage"
        5,0-> hasil = "Kliwon"
    }
    return hasil
}

fun namaPasaran(nomorPasaran:Int):String{
    var hasil = ""
    when(nomorPasaran){
        1-> hasil = "Legi"
        2-> hasil = "Pahing"
        3-> hasil = "Pon"
        4-> hasil = "Wage"
        5,0-> hasil = "Kliwon"
    }
    return hasil
}

fun namaPasaranIrmu(nomorPasaran:Int):String{
    var hasil = ""
    when(nomorPasaran){
        1-> hasil = "Kliwon"
        2-> hasil = "Legi"
        3-> hasil = "Pahing"
        4-> hasil = "Pon"
        5,0-> hasil = "Wage"
    }
    return hasil
}

fun nomorPasaran(julianDay: Double):Int{
    return ((julianDay + 1.5) % 5).toInt()
}

fun namaHariPasaran(julianDay: Double):String{
    return "${namaHari(julianDay) } ${namaPasaran(julianDay) }"

}

fun namaHariPasaran(tanggal: Int, bulan: Int, tahun: Int):String{
    return "${namaHariPasaran(masehiKeJd(tanggal,bulan,tahun,0))}"
}









fun masehiKeJd(tanggal: Int, bulan: Int, tahun: Int, jamDes: Double): Double {

    // karena data dari parameter tidak dapat diubah maka dibuak shadowing spt ini
    var bulan = bulan
    var tahun = tahun

    //bila bulan januari atau februari maka dianggap bulan ke 13/14 tahun sebelunmya
    if (bulan <= 2) {
        bulan += 12
        tahun -= 1
    }


    //cek bila masuk periode Gregorian
    var b = 0.0
    if (tahun + bulan / 100 + tanggal / 10000 >= 1582.1015) {
        val a = floor(tahun / 100.0)
        b = 2+ floor(a/4)-a
    }

    val julian_day = 1720994.5 + floor(365.25 * tahun) + floor(30.60001 * (bulan + 1)) + tanggal + b + jamDes / 24
    return julian_day

}


// function overloading, untuk handle variasi dari user input
fun masehiKeJd(tanggal: Int, bulan: Int, tahun: Int, jamDes: Int): Double {

    return masehiKeJd(tanggal, bulan, tahun, jamDes.toDouble())
}
fun masehiKeJd(tanggal: Int, bulan: Int, tahun: Int, jam:Int,menit:Int,detik:Int): Double {
    val x = dmsKeDesimal(jam, menit, detik)
    return masehiKeJd(tanggal, bulan, tahun, x)
}
fun masehiKeJd(tanggal: Int, bulan: Int, tahun: Int, jam:Int,menit:Int,detik:Double): Double {
    val x = dmsKeDesimal(jam, menit, detik)
    return masehiKeJd(tanggal, bulan, tahun, x)
}

fun jdKeMasehi(julianDay: Double): JdCal {
    val jdm = julianDay + 0.5
    val z = floor(jdm)
    val f = frac(jdm)
    val a: Double
    val aa: Double

    if (z < 2299161) a = z
    else {
        aa = floor((z - 1867216.25) / 36524.25)
        a = z + 1 + aa - (aa / 4)
    }

    val b = a + 1524
    val c = floor((b - 122.1) / 365.25)
    val d = floor(365.25 * c)
    val e = floor((b - d) / 30.60001).toInt()

    val tanggal = (b - d - floor(30.6001 * e) + f)

    val bulan = if (e == 14 || e == 15) e - 13 else e - 1

    val tahun = if (bulan <= 2) c - 4715 else c - 4716

    val pecahanHari = frac(tanggal)

    val pukul = pecahanHari * 24
    val jam = pukul
    val menit = frac(pukul) * 60
    val detik = frac(menit) *60
    val namaHari = namaHari(julianDay)
    val namaPasaran = namaPasaran(julianDay)

//    hasil.tanggal = tanggal.toInt()
//    hasil.bulan = bulan
//    hasil.tahun = tahun
//    hasil.jam = jam.toInt()
//    hasil.menit = menit.toInt()
//    hasil.detik = detik



    return JdCal(
            tanggal.toInt(),
            bulan,
            tahun.toInt(),
            pukul,
            jam.toInt(),
            menit.toInt(),
            detik,
            namaHari,
            namaPasaran,
            julianDay
    )


}

fun jdKeMasehi(julianDay: Int): JdCal = jdKeMasehi(julianDay.toDouble())


class JdCal(
        val tanggal: Int,
        val bulan: Int,
        val tahun: Int,
        val pukul:Double,
        val jam: Int,
        val menit: Int,
        val detik: Double,
        val namaHari:String,
        val namaPasaran:String,
        val jd :Double
) {

    val hms = desKeHms(pukul).hms
    val hmsss = desKeHms(pukul).hmsss
    val ddmmmyy: String = "${"%02d".format(tanggal)} ${namaBulan(bulan)} $tahun"
    val ddmmmyy_hms: String = "${"%02d".format(tanggal)} ${namaBulan(bulan)} $tahun, $hms"
    val pppddd_ddmmmyy: String = "$namaHari $namaPasaran, ${"%02d".format(tanggal)} ${namaBulan(bulan)} $tahun"
    val pppddd_ddmmmyy_hms: String = "$namaHari $namaPasaran, ${"%02d".format(tanggal)} ${namaBulan(bulan)} $tahun, $hms"
    val ddmmyy: String = "${"%02d".format(tanggal)} ${"%02d".format(bulan)} $tahun"
    val ddmmyy_hms: String = "${"%02d".format(tanggal)} ${"%02d".format(bulan)} $tahun, $hms"


}













fun dmsKeDesimal(derajat:Int,menit:Int,detik:Double):Double{
    val negatif = derajat<0||menit<0||detik<0

    return if (negatif) derajat - abs(menit/60.0) - abs(detik/3600.0)
    else derajat + (menit/60.0)+(detik/3600.0)
}

fun dmsKeDesimal(derajat:Int,menit:Int,detik:Int):Double{ //Method overloading unutk handle user input Integer pada detik
    return dmsKeDesimal(derajat, menit, detik.toDouble())
}


fun desKeDms(desDeg:Double): Dms {
    val isNegative = desDeg<0
    var desDeg = abs(desDeg)
    var derajat = floor(desDeg)
    var menit = frac(desDeg) *60
    var detik = frac(menit) *60

    if (detik>=59.99){menit+=1
    detik= round(detik)%60}
    if (menit>=59.99){derajat+=1
    menit = round(menit)%60}

    return Dms(derajat.toInt(), menit.toInt(), detik, isNegative)
}

fun desKeHms(desDeg:Double): Dms {
    return desKeDms(desDeg)
}

class Dms (
        val derajat: Int,
        val menit: Int,
        val detik: Double,
        negatif:Boolean
){
    private var tandaNeg:String = if (negatif) "-" else ""
    var dms: String = "$tandaNeg${"%02d".format(derajat)}°${"%02d".format(menit)}′${"%02d".format(detik.toInt())}″"
    var dm: String = "$tandaNeg${"%02d".format(derajat)}°${"%02d".format(menit)}′"
    var dmsss: String = "$tandaNeg${"%02d".format(derajat)}°${"%02d".format(menit)}′${"%.2f".format(detik)}″"
    var hms: String = "$tandaNeg${"%02d".format(derajat)}:${"%02d".format(menit)}:${"%02d".format(detik.toInt())}"
    var ms: String = "${"%02d".format(menit)}m ${"%02d".format(detik.toInt())}d"
    var hm: String = "$tandaNeg${"%02d".format(derajat)}:${"%02d".format(menit)}"
    var hmsss: String = "$tandaNeg${"%02d".format(derajat)}:${"%02d".format(menit)}:${"%.2f".format(detik)}"

}



 fun toDMS(number: Double): String {
    val degree = kotlin.math.floor(number)
    val minutes = (number - kotlin.math.floor(number)) * 60.0
    val seconds = (minutes - kotlin.math.floor(minutes)) * 60.0

    return "%1\$d%2\$s %3\$d%4\$s %5\$.${2}f%6\$s".format(
            degree.toInt(), "°",
            minutes.toInt(), "'",
            seconds, "\""
    )
}



