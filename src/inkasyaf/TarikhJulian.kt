package inkasyaf

import utilitas.*
import kotlin.math.round


//Mencari Tarikh Juliyan
//Hari Pasaran tanpa pukul dan dengan pukul
fun main() {

//        val p = Random.nextInt(0, 24)
//    val d = Random.nextInt(1, 31)
//    var m = Random.nextInt(1, 12)
//    var y = Random.nextInt(1, 5000)

    val tanggal = 10
    var bulan = 2
    var tahun = 2022
    if (bulan == 1 || bulan == 2) {
        bulan += 12
        tahun -= 1
    }

    var a =0
    var ak = 0
    var b = 0

    //bila masuk periode Gregorian (yang dipakai saat ini)
    if (tahun + bulan / 100 + tanggal / 10000 >= 1582.1015) {
        a = (tahun / 100.0).toInt()
        ak = (a / 4.0).toInt()
        b = 2 + ak - a
    }



    val ac = (365.25 * tahun).toInt()
    val bc = (30.6001 * (bulan + 1)).toInt()
    val jd = 1720994.5 + ac + bc + tanggal + b

    //nama hari dan pasaran
    val jdi = jd.toInt()


    val mh = frac((jdi + 2) / 7.0)
    val hr = round(mh * 7 + 1)
    val mp = frac((jdi + 2) / 5.0)
    val ps = round(mp * 5)


    val namaHari = namaHari(hr.toInt())
    val namaPasaran = namaPasaran(ps.toInt())
    val kesimpulan = "$namaHari $namaPasaran"


    println()

    println("$tanggal $bulan $tahun")
    println("A  :$a")
    println("AK  :$ak")
    println("B  :$b")
    println("Ac  :$ac")
    println("Bc  :$bc")
    dispCal("Jd", jd)
    println("Jdi  :$jdi")
    dispCal("MH", mh)
    println("Hr  :$hr")
    dispCal("MP", mp)
    println("Ps  :$ps")
    println(kesimpulan)


}