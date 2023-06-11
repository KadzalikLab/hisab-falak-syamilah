package inkasyaf

import utilitas.*
import kotlin.math.round


//Mencari Tarikh Juliyan
//Hari Pasaran tanpa pukul dan dengan pukul
fun main() {

    val p = 13
//    val d = Random.nextInt(1, 31)
//    var m = Random.nextInt(1, 12)
//    var y = Random.nextInt(1, 5000)

    val tg = 10
    var bl = 6
    var th = 2021


    if (bl == 1 || bl == 2) {
        bl += 12
        th -= 1
    }


    val a = (th / 100.0).toInt()
    val ak = (a / 4.0).toInt()
    val b = 2 + ak - a
    val ac = (365.25 * th).toInt()
    val bc = (30.6001 * (bl + 1)).toInt()
    val jd = 1720994.5 + ac + bc + tg + b+p/24
    //nama hari dan pasaran
    val jdi = jd.toInt()
    val jdf = frac(jd)
    val k = if (jdf >= 0.5) jdi + 2 else jdi + 1

    val mh = frac((k) / 7.0)
    val hr = round(mh * 7 + 1)
    val mp = frac((k) / 5.0)
    val ps = round(mp * 5)

    val namaHari = namaHari(hr.toInt())
    val namaPasaran = namaPasaran(ps.toInt())
    val kesimpulan = "$namaHari $namaPasaran"



    println("$tg $bl $th")
    println(kesimpulan)
    println("A  :$a")
    println("AK  :$ak")
    println("B  :$b")
    println("Ac  :$ac")
    println("Bc  :$bc")
    dispCal("Jd", jd)
    println("Jdi  :$jdi")
    printKl("Jdf ",jdf)
    println("K  :$k")
    dispCal("MH", mh)
    println("Hr  :$hr")
    dispCal("MP", mp)
    println("Ps  :$ps")
    println()


}