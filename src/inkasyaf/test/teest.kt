package inkasyaf

import utilitas.*
import kotlin.math.round
import kotlin.random.Random

fun main (){
    val p = 0
    val d = 10
    var m = 6
    var y = 2021

    if (m==1||m==2) {
        m += 12
        y-=1
    }


    val a = (y / 100.0).toInt()
    val ak = (a / 4.0).toInt()
    val b = 2 + ak - a
    val ac = (365.25 * y).toInt()
    val bc = (30.6001 * (m + 1)).toInt()
    val jd = 1720994.5 + ac + bc + d + b + (p / 24.0)
    //nama hari dan pasaran
    val jdi = jd.toInt()
    val jdf = frac(jd)
    val k = if (jdf >= 0.5) jdi + 2 else jdi + 1

    val mh = frac(k/7.0)
    val hr = round(mh*7+1)
    val mp = frac(k/5.0)
    val ps = round(mp*5)

    val namaHari = namaHari(hr.toInt())
    val namaPasaran = namaPasaran(ps.toInt())
    println("A  :$a")
    println("AK  :$ak")
    println("B  :$b")
    println("Ac  :$ac")
    println("Bc  :$bc")
    dispCal("Jd",jd)

    println("Jdi  :$jdi")
    println("Jdf  :$jdf")
    println("K  :$k")
    dispCal("MH",mh)
    println("Hr  :$hr")
    dispCal("MP",mp)
    println("Ps  :$ps")
    println("$namaHari $namaPasaran")
    re()



}

fun re(){

    while (true) {
//        val p = Random.nextInt(0, 24)
        val d = Random.nextInt(1, 31)
        var m = Random.nextInt(1, 12)
        var y = Random.nextInt(1, 5000)
        if (m==1||m==2) {
            m += 12
            y-=1
        }


        val a = (y / 100.0).toInt()
        val ak = (a / 4.0).toInt()
        val b = 2 + ak - a
        val ac = (365.25 * y).toInt()
        val bc = (30.6001 * (m + 1)).toInt()
        val jd = 1720994.5 + ac + bc + d + b
        //nama hari dan pasaran
        val jdi = jd.toInt()
        val jdf = frac(jd)
//        val k = if (jdf >= 0.5) jdi + 2 else jdi + 1

        val mh = frac((jdi+2) / 7.0)
        val hr = round(mh * 7 + 1)
        val mp = frac((jdi+2) / 5.0)
        val ps = round(mp * 5)

        val nmaHri = jdKeMasehi(jd).namaHari
        val nmaPas = jdKeMasehi(jd).namaPasaran
        val asli = "$nmaHri $nmaPas"

        val namaHari = namaHari(hr.toInt())
        val namaPasaran = namaPasaran(ps.toInt())
        val modif = "$namaHari $namaPasaran"


        if (asli!=modif){
            println(" $d $m $y")
            println(asli)
            println(modif)
            println("A  :$a")
            println("AK  :$ak")
            println("B  :$b")
            println("Ac  :$ac")
            println("Bc  :$bc")
            dispCal("Jd",jd)

            println("Jdi  :$jdi")
            println("Jdf  :$jdf")
//            println("K  :$k")
            dispCal("MH",mh)
            println("Hr  :$hr")
            dispCal("MP",mp)
            println("Ps  :$ps")

            println()
            break
        }else println(true)



    }


}