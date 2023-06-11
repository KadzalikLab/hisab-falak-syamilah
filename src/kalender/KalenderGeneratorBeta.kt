package kalender

import anwarul_kawakib.masehiKeHijri
import ephemeris.Ephem
import utilitas.*
import java.io.File
import java.lang.Math.toDegrees
import kotlin.math.*


//val bujur = dmsKeDesimal(105, 12, 0)
//val lintang = dmsKeDesimal(-4, 58, 0)

val bujur   = 104.427687
val lintang =  -3.242966
val ketinggianTempat = 50f
val zona_waktu = 7.0

//val tahunHijri = 1444 // Tahun Hijri Naqish ( Yang dicari)
//val bulanHijriDicari = 7 // Bulan Hijri Naqish bukan tamm



var tanggalIjtimaMasehi = arrayListOf<Int>()
var bulanIjtimaMasehi = arrayListOf<Int>()
var tahun = 2023
var jumlahHari = if(isKabisatMasehi(tahun)) 366 else 365

var bulanHijriSetahun = arrayListOf<Int>()
var tahunHijriSetahun = arrayListOf<Int>()


var dataAlmanakInkasyaf = Array(11){ arrayListOf<String>() }

var dataAlmanakEphem = Array(11){ arrayListOf<String>() }
var nolPrefixFormatter = "%.2f"

var mabdaTanggalHijri = 8
var mabdaBulanHijri = 6
var mabdaTahunHijri = 1444
var namaNamaHari = Array(7){ arrayListOf<String>() }

var imkan = arrayListOf<Boolean>()
var daftarHariPerminggu = Array(7){ Array(4){ arrayListOf<String>() } }


fun main(){

    rentangHijri()
    for (i in bulanHijriSetahun.indices){
        hisabInkasyaf(tahunHijriSetahun[i], bulanHijriSetahun[i])
    }
//    println(tanggalIjtimaMasehi)
//    println(bulanIjtimaMasehi)

    for (i in tanggalIjtimaMasehi.indices){
        hisabEphemerisKemenag(tanggalIjtimaMasehi[i], bulanIjtimaMasehi[i])
    }

    println()

    for (i in dataAlmanakInkasyaf.indices){
//        println(dataAlmanakInkasyaf[i])
    }


    println()

    for (i in dataAlmanakEphem.indices){
//        println(dataAlmanakEphem[i])
    }



    val fileName =  "testnulis.txt"
    val testFile = File(fileName)

    testFile.writeText("Metode,Awal Bulan,Waktu Ijtima,Ghurub,Irtifa',Elongasi,Letak Matahari,Letak Hilal,Keadaan Hilal,Durasi,Besar Cahaya\n")
//    testFile.appendText("\n"+art[0][2])
    for (i in dataAlmanakInkasyaf[1].indices){

        testFile.appendText("\n")
        for(j in dataAlmanakInkasyaf.indices){

            testFile.appendText(dataAlmanakInkasyaf[j][i]+",")
//            testFile.appendText(dataAlmanakEphem[j][i]+",")
        }
        testFile.appendText("\n")

        for(j in dataAlmanakInkasyaf.indices){

//            testFile.appendText(dataAlmanakInkasyaf[j][i]+",")
            testFile.appendText(dataAlmanakEphem[j][i]+",")

        }
        testFile.appendText("\n")

    }
    println("Sukses")
    val e = Ephem(masehiKeJd(28,10,2023,20,13,14.92))
//    println("${e.}${}${}")

//    println(bulanHijriSetahun)

//    var r = Array(4){ arrayListOf<String>() }

    for (i in 1..jumlahHari){
        var a0 = jdKeMasehi(masehiKeJd(i+7,1, tahun,0))
        var a = jdKeMasehi(masehiKeJd(i,1, tahun,0))
        var a2 = jdKeMasehi(masehiKeJd(i+1,1, tahun,0))

        daftarHari("Ahad",a,a0, daftarHariPerminggu[0])
        daftarHari("Senin",a,a0, daftarHariPerminggu[1])
        daftarHari("Selasa",a,a0, daftarHariPerminggu[2])
        daftarHari("Rabu",a,a0, daftarHariPerminggu[3])
        daftarHari("Kamis",a,a0, daftarHariPerminggu[4])
        daftarHari("Jumat",a,a0, daftarHariPerminggu[5])
        daftarHari("Sabtu",a,a0, daftarHariPerminggu[6])


//        if (a.tanggal==1) println("\nBulan ${namaBulan(a.bulan)}")

        if (mabdaTanggalHijri==29){

            if (imkan[a.bulan-1]) {
                mabdaTanggalHijri = 0
                mabdaBulanHijri++
            }
        }

        if (mabdaTanggalHijri==30) {
            mabdaTanggalHijri = 0
            mabdaBulanHijri++
        }

        mabdaTanggalHijri++

        if (mabdaBulanHijri>12){
            mabdaBulanHijri%=12
            mabdaTahunHijri++
        }
//        println("${a.pppddd_ddmmmyy},\t $mabdaTanggalHijri ${namaBulanHijri(mabdaBulanHijri)} $mabdaTahunHijri")



    }

//    daftarHariPerminggu.forEach { it.forEach { println(it) } ; println()}


    var g = arrayListOf<File>()
    for (i in 0..6){
        g.add(File("${i+1}.txt"))
        g[i].writeText("")

        for (k in daftarHariPerminggu[i].indices){
            for (p in daftarHariPerminggu[i][k].indices){
                g[i].appendText("\n ${daftarHariPerminggu[i][0][p]}\t ${ubahKeAr(daftarHariPerminggu[i][2][p])}\t ${daftarHariPerminggu[i][1][p]}\t ${daftarHariPerminggu[i][3][p]}")
//                println("${daftarHariPerminggu[i][0][p]}\t ${ubahKeAr(daftarHariPerminggu[i][2][p])}\t ${daftarHariPerminggu[i][1][p]}\t ${daftarHariPerminggu[i][3][p]}")

            }
//            println("\n\n")


        }
    }

//    for (i in daftarHariPerminggu[0][0].indices){
//        ah.appendText("\n ${daftarHariPerminggu[0][0][i]}\t ${daftarHariPerminggu[0][2][i]}\t ${daftarHariPerminggu[0][1][i]}\t ${daftarHariPerminggu[0][3][i]}")
//    }



}
fun ubahKeAr(x: Int): String {
    return ubahKeAr(x.toString())
}
fun ubahKeAr(x: String): String {
    return (x.toString())
        .replace("1".toRegex(), "١").replace("2".toRegex(), "٢")
        .replace("3".toRegex(), "٣").replace("4".toRegex(), "٤")
        .replace("5".toRegex(), "٥").replace("6".toRegex(), "٦")
        .replace("7".toRegex(), "٧").replace("8".toRegex(), "٨")
        .replace("9".toRegex(), "٩").replace("0".toRegex(), "٠")
}

fun daftarHari(namaHari:String,a:JdCal,a0: JdCal,r:Array<ArrayList<String>>){
    val delimiter = namaBln(a.bulan)
    if (a.namaHari==namaHari){
        if (a0.tanggal<a.tanggal){
            r[0].add(a.tanggal.toString())
            r[1].add(a.namaPasaran)
            r[2].add(mabdaTanggalHijri.toString())
            r[3].add(namaBlnHijri(mabdaBulanHijri))


            r[0].add(delimiter)
            r[1].add(delimiter)
            r[2].add(delimiter)
            r[3].add(delimiter)
        }
        else{
            r[0].add(a.tanggal.toString())
            r[1].add(a.namaPasaran)
            r[2].add(mabdaTanggalHijri.toString())
            r[3].add(namaBlnHijri(mabdaBulanHijri))


        }


    }

}

fun rentangHijri(){

    var blnThnTemp = arrayListOf<String>()

    for (i in 30..jumlahHari){
        val j = masehiKeJd(i,1, tahun,0);
        val k = jdKeMasehi(j)
        val hijri = masehiKeHijri(k.tanggal,k.bulan,k.tahun)
        blnThnTemp.add("${hijri.bulanHijri}.${hijri.tahunHijri}")
    }

    blnThnTemp = blnThnTemp.distinct() as ArrayList<String>

    for (i in blnThnTemp.indices){
        val j = blnThnTemp[i].split(".")

        bulanHijriSetahun.add(j[0].toInt())
        tahunHijriSetahun.add(j[1].toInt())

    }



}

fun hisabInkasyaf(tahunHijri:Int,bulanHijriDicari:Int){

    val hy = tahunHijri + ((bulanHijriDicari-1) * 29.53) / 354.3671 // Ayyamu Attarikhil Hijrii
    val k = round((hy - 1410) * 12) // Mahfudz Mabda'
    val to = k / 1200 //Juz Al ashli
    val jd = 2447740.652 + 29.53058868 * k + 0.0001178 * to.pow(2) // Tarikh Juliyani Ghouru Muaddal

    val m = 29.10535608 * k + 207.9587074 + -0.0000333 * to.pow(2) //Khossoh Syamsi
    val me = 111.1791307 + 385.81691806 * k + 0.0107306 * to.pow(2.0) //Khossoh Qomar
    val f = 164.2162296 + 390.67050646 * k + -0.0016528 * to.pow(2.0) //Hissoh Ardli

    val t1 = ((0.1734 - 0.000393 * to) * sin(toRad(m)))+0.0021 * sin(toRad(2 * m))
    val t2 = (-0.4068 * sin(toRad(me)))+0.0161 * sin(toRad(2 * me))
    val t3 = (-0.0004 * sin(toRad(3 * me)))+0.0104 * sin(toRad(2 * f))
    val tpm1 = t1+t2+t3 +0.0005 * sin(toRad(m + 2 * me))
    val t4 = (-0.0051 * sin(toRad(m + me)))+-0.0074 * sin(toRad(m - me))
    val t5 = (0.0004 * sin(toRad(2 * f + m)))+-0.0004 * sin(toRad(2 * f - m))
    val t6 = (-0.0006 * sin(toRad(2 * f + me)))+0.0010 * sin(toRad(2 * f - me))

    var mt =  tpm1 + t4 + t5 + t6 // majmuah takdiilati
    // majmuah takdiilati
    val ktt = arrayListOf(t1 , t2 , t3 , t4 , t5 , t6 )

    val jd_ijtima = jd + 0.5 + mt // Tarikh Juliyani Al Muaddal Lil ijtima'
    val mtp = (frac(jd_ijtima) *24)+zona_waktu // Saat Ijtima'
    val wal = if (mtp>24) mtp-24 else mtp



    val zp = (jd_ijtima+zona_waktu/24.0).toInt() //Mahfudz tarikh Miladi
    val aa = ((zp - 1867216.25) / 36524.25).toInt() //Mahfudz tarikh Miladi
    val akp = (aa/4).toInt() //Mahfudz tarikh Miladi
    val rku =  1 + aa - akp //Mahfudz tarikh Miladi
    val a = zp+rku

    val b = a + 1524 //Mahfudz tarikh Miladi
    val c = ((b - 122.1) / 365.25).toInt() //Mahfudz tarikh Miladi
    val d = (365.25 * c).toInt() //Mahfudz tarikh Miladi
    val e = ((b - d) / 30.6001).toInt() //Mahfudz tarikh Miladi
    val ep = (30.6001 * e).toInt() //Mahfudz tarikh Miladi

    val tanggal_ijtima_masehiLT = (b - d - ep) // Tarikh Milady
    val bulan_ijtima_masehiLT = if (e >= 13.5) e - 13 else e - 1 //Syahr Milady
    val tahun_ijtima_masehiLT = if (bulan_ijtima_masehiLT >= 2.5) c - 4716 else c - 4715 //sanah Milady

    val pa = zp + 2 // Mahfudz Tarikh Akhmusi Wa Asbu'i
    val nhm = (pa / 7).toInt()
    val npr = (pa / 5).toInt()
    val nomor_hari = pa - nhm * 7 //Ism Ayyam Al Asbu'i
    val nomor_pasaran = pa - npr * 5// Ism Ayyam Al Akmussi

    val tanggal_ijtima_lokal = "$tanggal_ijtima_masehiLT ${namaBln(bulan_ijtima_masehiLT)} $tahun_ijtima_masehiLT"
    val harpas_ijtima_lokal = "${namaHari(nomor_hari)} ${namaPasaranIrmu(nomor_pasaran)}"

    val ar = (tahun_ijtima_masehiLT / 100)
    val arb = (ar / 4).toInt()

    val rk =2 +  arb - ar //Tashih Tarikh Masehi


    val mj = d + ep + tanggal_ijtima_masehiLT  + rk - 1524.5
//    println(jdKeMasehi(mj+(12-zona_waktu)).ddmmmyy_hms)
//    println((mj+(12-zona_waktu)))
    val u = (mj+(12-zona_waktu)/24.0 - 2451545) / 36525.0 // u pada pukul 5 ut atau 12 wib
    val lo = 280.46645 + 36000.76983 * u //Bujur Rata-rata matahari
    val ld = toRad(357.5291 + 35999.05030 * u) // anomali rata-rata matahari
    val lr =  lo-0.0056861+1.916128* sin(ld)//+0.020026*sin(2*ld) //Bujur Matahari terkoreksi

//    val lr =  lo -0.0056861 + 1.916128*sin(ld)+0.020026*sin(2*ld)+0.966/3600*sin(3*ld))-0.0056861 //Bujur Matahari terkoreksi          //        koreksi rentang abad
//    val usm = frac((125.04 - 1934.136 * u) / 360)
//    val us = mod(usm * 360,360) //uqdah syamsi
//    val r1 = 9.23 / 3600 * cos(toRad(us)) - 0.090 / 3600 * cos(toRad(2 * us))
//    val r2 = 0.548 / 3600 * cos(toRad(2 * lo))
//    val mk = 23.43929111 + r1 + r2 - 46.8150 / 3600 * u //mailkully terkoreksi                                    //        koreksi  rentang abad

    val mk = dmsKeDesimal(23,26,0) // Mail Kully
    val dm = toDeg(asin(sin(toRad(lr)) * sin(toRad(mk)))) //deklinasi Matahari pada 12 WIB

    val lor = toRad(lo)
    val y = tan(toRad(mk/2)).pow(2)
    val pe = 0.016711651 // Kepipihan Orbit Bumi
    val e1 = y * sin(2*lor) - 2 *pe* sin(ld)
    val e2 = e1+4* pe *y* sin(ld) * cos(2*lor)
    val e3 = e2-0.5*y.pow(2.0) * sin(4*lor)
    val e4 = e3- 1.25* pe.pow(2.0) * sin(2*ld)
    val pw = toDeg(e4) /15 //deklinasi Matahari pada 12 WIB


    val dmr = toRad(dm)
    val ltr = toRad(lintang)
    val sm = sin(toRad(-0.8333 - 0.0347 * sqrt(ketinggianTempat))) //Mahfudz Inkhifadul Ufuq
    val swm = toDeg(acos((sm - sin(dmr) * sin(ltr)) / (cos(dmr) * cos(ltr)))) //Nishfu Qousi Nahar Syamsi Taqribi
    val mg = (12 - bujur / 15 - pw) + (swm / 15) // Waqtu Magrib UT Taqribi
//    val mgb = 11.0 // Waqtu Magrib Taqribi



    val jd_hisab = mj + mg/24.0 // aslul miladi
    val t = (jd_hisab-2451545)/36525 //Juz Aslil Miladi

//    val tett = z-7/24.0
//    println(jdKeMasehi(tett).ddmmmyy_hms)
//    println((tett))
//    println(jdKeMasehi(jd_ijtima-7/24.0).ddmmmyy_hms)

    // Data Matahari

    val wsm = frac((280.46645 + 36000.76983 * t) / 360) //bujur mathari
    val ws = mod( wsm * 360,360)  //wasat syamsi
//    val ws = toDeg(acos(cos(toRad(280.46645 + 36000.76983 * t)))) //wasat syamsi
    val cs = 357.5291 + 35999.05030 * t //khossoh syamsi
    val ds = (125.04 - 1934.136 * t) //uqdah syamsi




    //Tashihatu li uqdah syamsi wal qomar
//    val r1 = 9.23 / 3600 * cos(toRad(us)) - 0.090 / 3600 * cos(toRad(2 * us))
//    val r2 = 0.548 / 3600 * cos(toRad(2 * ws))
//    val q1 = 23.43929111 + r1 + r2 - 46.8150 / 3600 * t //mailkully

    val k1 = 17.264 / 3600 * sin(toRad(ds)) + 0.206 / 3600 * sin(toRad(2 * ds))
    val k2 = -1.264 / 3600 * sin(toRad(2 * ws))
    val tsm = 6898.06 / 3600 * sin(toRad(cs)) + 72.095 / 3600* sin(toRad(2 * cs))
    val ts = tsm  + 0.966 / 3600 * sin(toRad(3 * cs)) //ta'dil syamsi
    val s = mod(ws + ts + k1 + k2 - 20.47 / 3600,360) // Thul Syamsy /Bujur Matahari


    val dkm = toDeg(asin(sin(toRad(s)) * sin(toRad(mk)))) //Mail Syamsi
    var pt = 0.0
    val ptm = toDeg(atan(tan(toRad(s)) * cos(toRad(mk)))) //Matholi' ul Mustaqimah Li Syamsi

    if (s >=0&&s<=90) pt = ptm
    else if (s >90&&s<=270)pt = ptm+180
    else if (s >270&&s<=360)pt = ptm+360

//    when(s1){
//        in 0.0..89.0 -> pt = ptm
//        in 90.0..269.0 -> pt = ptm+180
//        in 270.0..360.0 -> pt = ptm+360
//    }

    val mw = mod(abs(ws-pt),360)
    val mm = if (mw>5) (pt+360) else pt
    val df = (ws-mm)/15 // Daqoiq Tafawut
    val sd = 0.267 / (1 - 0.017 * cos(toRad(cs))) //Nishfu Qotri Syamsi
    val iss = - (sd+ 0.575  +1.76 / 60 * sqrt(ketinggianTempat)) //Irtifa' Syamsi waqtal Ghurub
    val ns = toDeg(acos(-tan(toRad(lintang)) * tan(toRad(dkm)) + sin(toRad(iss)) / cos(toRad(lintang)) / cos(toRad(dkm)))) //Nishfu Qousi Nahar Syamsi
    val ghurub_wd = ns/15 + 12-df +((zona_waktu*15)-bujur)/15 //Ghurub Syamsi ghurubiyyah



    //Data Bulan

    val wks = frac((218.31617 + 481267.88088 * t) / 360)
    val wq = mod(wks * 360,360) //wasat qomar

    val fd = 134.96292 + 477198.86753 * t //Khossoh Qomar
    val fe = 93.27283 + 483202.01873 * t//Hissah 'Ardli
    val ff = 297.85027 + 445267.11135 * t //Fadlul Wasath





    // 14 takdil qomar
    val tq1 = 22640 / 3600.0 * sin(toRad(fd)) + -4586 / 3600.0 * sin(toRad(fd - 2 * ff))
    val tq2 = 2370 / 3600.0 * sin(toRad(2 * ff) ) +769 / 3600.0 * sin(toRad(2 * fd))
    val tq3 = -668 / 3600.0 * sin(toRad(cs)) +-412 / 3600.0 * sin(toRad(2 * fe))
    val tq4 =-212 / 3600.0 * sin(toRad(2 * fd - 2 * ff))
    val tpq1 = tq1+tq2+tq3+tq4
    val tq5 = -206 / 3600.0 * sin(toRad(fd + cs - 2 * ff))
    val tq6 = 192 / 3600.0 * sin(toRad(fd + 2 * ff)) + -165 / 3600.0 * sin(toRad(cs - 2 * ff))

    val tq7 =148 / 3600.0 * sin(toRad(fd - cs)) +-125 / 3600.0 * sin(toRad(ff))

    val tq8 = -110 / 3600.0 * sin(toRad(fd + cs)) +-55 / 3600.0 * sin(toRad(2 * fe - 2 * ff))

    var mtq =0.0 // Majmu' Ta'dil
    val tq = arrayListOf(tq1,tq2,tq3,tq4,tq5,tq6,tq7,tq8)
    for (i in tq.indices){
//        printDms("tq${i+1}",tq[i])
        mtq+=tq[i]
    }


    val sa = mod(wq + mtq + k1 + k2 - 20.47 / 3600,360) //Thul Qomar
    val lma =(18461 / 3600.0 )* sin(toRad(fe)) + 1010 / 3600.0 * sin(toRad(fd + fe))
    val lmb=lma + 1000 / 3600.0 * sin(toRad(fd - fe)) - 624 / 3600.0 * sin(toRad(fe - 2 * ff))
    val lmc= lmb - 199 / 3600.0 * sin(toRad(fd - fe - 2 * ff))
    val aq = lmc- 167 / 3600.0 * sin(toRad(fd + fe - 2 * ff)) //'Ardul Qomar
    val eb = toDeg(atan(sin(toRad(sa)) * tan(toRad(mk)))) //Mail Tsani Qomar
    val dc = toDeg(asin(sin(toRad(sa)) * sin(toRad(mk)) * sin(toRad(aq + eb)) / sin(toRad(eb)))) //Bu'dul Qomar 'An madaril I'tidal



    var ptc = 0.0
    val ptb = toDeg(acos(cos(toRad(sa)) * cos(toRad(aq)) / cos(toRad(dc)))) //Matoli' Mustaqimah Lil Qomar
    if (sa in 0.0..180.0)ptc = ptb
    else ptc = 360-ptb
    val tc = mod(pt - ptc + ns,360) //Fadlu Dair
    val rh = toDeg(asin(sin(toRad(lintang)) * sin(toRad(dc)) + cos(toRad(lintang)) * cos(toRad(dc)) * cos(toRad(tc)))) //Irtifa' Hilal Haqiqi


    val azm = toDeg(atan(-sin(toRad(lintang)) / tan(toRad(ns)) + cos(toRad(lintang)) * tan(toRad(dkm)) / sin(toRad(ns)))) +270 //Samtu Irtifa' Syamsi 'Inda Ghurub
    val azb = toDeg(atan(-sin(toRad(lintang)) / tan(toRad(tc)) + cos(toRad(lintang)) * tan(toRad(dc)) / sin(toRad(tc)))) +270 //Samtu Irtifa' Qomar 'Inda Ghurub


    var muktsu_hilal = (rh) / 15 //Muktsu Hilal Fauqol Ufuq

    val elongasi = toDeg(acos(cos(toRad(sa - s)) * cos(toRad(aq))))
    var nurul_hilal = (1 + -cos(toRad(elongasi))) / 2 * 100 //nururl hilal
    val ghurub_hilal_taqrib = ghurub_wd + muktsu_hilal

    val sudut = abs(azm-azb)
    var kh=""
    kh = if (sudut>2)
        if (azb>azm) "Menghadap ke Utara"
        else "Menghadap Ke Selatan"
    else "Terlentang"
    if (rh<0) {
        muktsu_hilal = 0.0
        kh = "-"
        nurul_hilal = 0.0
    }
    val jr = azb - azm
    val mrj = toDeg(atan((jr/rh)))
    val bk = if (jr<0) mrj+360 else mrj
    val rt = mod(bk+180,360)

//    tanggalIjtimaMasehi = tanggal_ijtima_masehiLT
//    bulanIjtimaMasehi = bulan_ijtima_masehiLT
//    tahun = tahun_ijtima_masehiLT

    if (tahun_ijtima_masehiLT== tahun){
        if (bulanHijriDicari!=10)  imkan.add(kriteriaImkan(rh,elongasi))
        else imkan.add(true)
        tanggalIjtimaMasehi.add(tanggal_ijtima_masehiLT)
        bulanIjtimaMasehi.add(bulan_ijtima_masehiLT)

        dataAlmanakInkasyaf[0].add("Inkaysaf AL Hisab")
        dataAlmanakInkasyaf[1].add(hariAwalBulan(
            masehiKeJd(tanggal_ijtima_masehiLT,bulan_ijtima_masehiLT,tahun_ijtima_masehiLT,0),
            kriteriaImkan(rh,elongasi)
        ))
        dataAlmanakInkasyaf[2].add("$harpas_ijtima_lokal# $tanggal_ijtima_lokal Pukul ${desKeHms(wal).hm}")
        dataAlmanakInkasyaf[3].add(desKeHms(ghurub_wd).hm)
        dataAlmanakInkasyaf[4].add(desKeDms(rh).dm)
        dataAlmanakInkasyaf[5].add(desKeDms(elongasi).dm)
        dataAlmanakInkasyaf[6].add(desKeDms(azm).dms)
        dataAlmanakInkasyaf[7].add(desKeDms(azb).dms)
        dataAlmanakInkasyaf[8].add(kh)
        if (rh>0)dataAlmanakInkasyaf[9].add(desKeDms(muktsu_hilal).ms) else dataAlmanakInkasyaf[9].add("-")
        var besarCahaya = if (rh>0) String.format(nolPrefixFormatter,nurul_hilal)+"%" else "-"
        dataAlmanakInkasyaf[10].add(besarCahaya)
    }







}
fun hisabEphemerisKemenag(tgl:Int, bln:Int){

    var jd = masehiKeJd(tgl,bln,tahun,0)



    var jamFibTerkecil = 0
    //mencari fib terkecil
    for (i in 0..24){
        var fibSatar1 = Ephem(jd+i/24.0).moon_diskIlluminatedFraction
        var fibSatar2 = Ephem(jd+((i+1)/24.0)).moon_diskIlluminatedFraction
//        printDms(ep.sun_trueGeocentricLongitude)
//        dispCal("Fib $i",fib/Satar1)

        if (fibSatar1<fibSatar2){
            jamFibTerkecil=i
//            println("Jam Fib Terkecil $jamFibTerkecil ")
//            dispCal("Fib ${i+1}",fibSatar2)

            break

        }

    }

    val jdGmt10= masehiKeJd(tgl,bln,tahun,10)
    val  jdGmt11=masehiKeJd(tgl,bln,tahun,11)
    val  jdGmtFibTerkecilSatar1=masehiKeJd(tgl,bln,tahun,jamFibTerkecil)
    val  jdGmtFibTerkecilSatar2=masehiKeJd(tgl,bln,tahun,jamFibTerkecil+1)
    val ephem10 = Ephem(jdGmt10)
    val ephem11 = Ephem(jdGmt11)
    val epFibS1 = Ephem(jdGmtFibTerkecilSatar1)
    val epFibS2 = Ephem(jdGmtFibTerkecilSatar2)

    val alb1     =epFibS1.sun_trueGeocentricLongitude
    val alb2     =epFibS2.sun_trueGeocentricLongitude
    val sdo11    =ephem11.sun_angularSemiDiameter
    val do10     =ephem10.sun_apparentDeclination
    val do11     =ephem11.sun_apparentDeclination
    val e11      =ephem11.sun_equationOfTime
    val aro10    =ephem10.sun_apparentRightAscension
    val aro11    =ephem11.sun_apparentRightAscension

    //Data Bulan
    val elm1      =epFibS1.moon_apparentGeocentricLongitude
    val elm2      =epFibS2.moon_apparentGeocentricLongitude
    val sdc10     =ephem10.moon_angularSemiDiameter
    val arc10     =ephem10.moon_apparentRightAscension
    val arc11     =ephem11.moon_apparentRightAscension
    val dc10      =ephem10.moon_apparentDeclination
    val dc11      =ephem11.moon_apparentDeclination
    val paralax10 =ephem10.moon_equatorialHorizontalParallax

    val fib10 =ephem10.moon_diskIlluminatedFraction
    val fib11 =ephem11.moon_diskIlluminatedFraction

    val sb = abs(alb1 - alb2)
    val sm = abs(elm1 - elm2)

    val saat_itima = jamFibTerkecil + (elm1 - alb1) / (sb - sm) + zona_waktu
    val jd_waktuIjtima = jd + (saat_itima / 24)


    //qoidah refraksi
    //5

    //qoidah refraksi
    val refraksi_jam_ghurub = 0.575
    //rumus keitnggian ufuk
    //rumus keitnggian ufuk
    val ketinggiian_ufuk = 1.76 * sqrt(ketinggianTempat) / 60.0
    val tinggi_matahari_haqiqi = 0 - sdo11 - refraksi_jam_ghurub - ketinggiian_ufuk

    //6

    //6
    val sudut_waktu_m = Math.toDegrees(
        acos(
            -tan(Math.toRadians(lintang)) * tan(Math.toRadians(do11)) + sin(
                Math.toRadians(tinggi_matahari_haqiqi)
            ) / cos(Math.toRadians(lintang)) / cos(Math.toRadians(do11))
        )
    )

    //7

    //7
    val koreksi_waktu_daerah = (zona_waktu * 15 - bujur) / 15

    //8

    //8
    val ghurub = sudut_waktu_m / 15 + (12 - e11) + koreksi_waktu_daerah

    //9

    //9
    val asensiorekta_m = aro10 - (aro10 - aro11) * (ghurub % 1)

    //10

    //10
    val asensiorekta_b = arc10 - (arc10 - arc11) * (ghurub % 1)

    //11

    //11
    val sudut_waktu_b = asensiorekta_m - asensiorekta_b + sudut_waktu_m

    //12

    //12
    val deklinasi_m_ghurub = do10 - (do10 - do11) * (ghurub % 1)
    val deklinasi_b_ghurub = dc10 - (dc10 - dc11) * (ghurub % 1)

    //Elongasi Bulan

    //Elongasi Bulan
    val elongasi = Math.toDegrees(
        acos(
            sin(Math.toRadians(deklinasi_b_ghurub)) * sin(Math.toRadians(deklinasi_m_ghurub)) + cos(
                Math.toRadians(deklinasi_b_ghurub)
            ) * cos(Math.toRadians(deklinasi_m_ghurub)) * cos(Math.toRadians(asensiorekta_b - asensiorekta_m))
        )
    )


    //13


    //13
    val tinggi_bulan_hakiki = Math.toDegrees(
        asin(
            sin(Math.toRadians(lintang)) * sin(Math.toRadians(deklinasi_b_ghurub)) + cos(
                Math.toRadians(lintang)
            ) * cos(Math.toRadians(deklinasi_b_ghurub)) * cos(Math.toRadians(sudut_waktu_b))
        )
    )
    //parallaks
    //parallaks
    val hp10 = paralax10 * cos(Math.toRadians(tinggi_bulan_hakiki))

    //Refraksi

    //Refraksi
    val ref = 1.02 / tan(Math.toRadians(tinggi_bulan_hakiki + 10.3 / (tinggi_bulan_hakiki + 5.11))) / 60
    //tinggi bulan mar'i
    //tinggi bulan mar'i
    val tinggi_b_mari = tinggi_bulan_hakiki - hp10 + ref + sdc10 + ketinggiian_ufuk

    //16

    //16
    var azimuth_m_ghurub = Math.toDegrees(
        atan(
            1 / (-sin(Math.toRadians(lintang)) / tan(Math.toRadians(sudut_waktu_m)) + cos(
                Math.toRadians(lintang)
            ) * tan(Math.toRadians(deklinasi_m_ghurub)) / sin(Math.toRadians(sudut_waktu_m)))
        )
    )

    var azimuth_b_ghurub = Math.toDegrees(
        atan(
            1 / (-sin(Math.toRadians(lintang)) / tan(Math.toRadians(sudut_waktu_b)) + cos(
                Math.toRadians(lintang)
            ) * tan(Math.toRadians(deklinasi_b_ghurub)) / sin(Math.toRadians(sudut_waktu_b)))
        )
    )

    var arah_m = ""
    var arah_b = ""
    var arah_h = ""

    var azo = 0.0
    var azc = 0.0

    var azimuth_m_barat = 0.0
    if (azimuth_m_ghurub < 0) {
        azimuth_m_barat = azimuth_m_ghurub + 90
        azo = 270-azimuth_m_barat
        arah_m = "BS"
    } else {
        azimuth_m_barat = 90 - azimuth_m_ghurub
        azo = 270+azimuth_m_barat

        arah_m = "BU"
    }

    var azimuth_b_barat = 0.0
    if (azimuth_b_ghurub < 0) {
        azimuth_b_barat = azimuth_b_ghurub + 90
        azc = 270-azimuth_b_barat
        arah_b = "BS"
        arah_h = "Selatan Titik Barat"
    } else {
        azimuth_b_barat = 90 - azimuth_b_ghurub
        azc = 270+azimuth_b_barat
        arah_b = "BU"
        arah_h = "Utara Titik Barat"
    }

    //18

    //18
    val posisi_hilal = azimuth_m_ghurub - azimuth_b_ghurub
    var lama_hilal = tinggi_b_mari / 15

    //19

    //19
    val luas_cahaya_hilal = fib10 - (fib10 - fib11) * (ghurub % 1)

    //20

    //20
    val lebar_nur_hilal = sqrt(posisi_hilal.pow(2.0) + tinggi_b_mari.pow(2.0)) / 15

    var keadaan_h = ""
    //21
    //21
    val kemiringan_hilal = toDegrees(atan(abs(posisi_hilal) / tinggi_b_mari))
    if (kemiringan_hilal <= 15) keadaan_h =
        "Hilal Terlentang" else if (kemiringan_hilal > 15 && posisi_hilal > 0) keadaan_h =
        "Miring ke Utara" else if (kemiringan_hilal > 15 && posisi_hilal < 0) keadaan_h = "Miring ke Selatan"
    var besarCahaya = if (tinggi_bulan_hakiki>0) String.format(nolPrefixFormatter,luas_cahaya_hilal*100)+"%" else "-"

    var muktsu_hilal = desKeHms(tinggi_bulan_hakiki/15).ms
    if (tinggi_bulan_hakiki<0){
        keadaan_h = "-"
        muktsu_hilal = "-"

    }

//    println("${}")
//    printDms("Irtifa ${namaBulan(bln)}",tinggi_bulan_hakiki)
//    printDms("Elongasi ${namaBulan(bln)}",elongasi)
    dataAlmanakEphem[0].add("Ephemeris")
    dataAlmanakEphem[1].add(hariAwalBulan(jd,kriteriaImkan(tinggi_bulan_hakiki,elongasi)))
    dataAlmanakEphem[2].add("${namaHariPasaran(tgl,bln, tahun)}# $tgl ${namaBln(bln)}  $tahun Pukul ${desKeHms(saat_itima).hm}")
    dataAlmanakEphem[3].add(desKeHms(ghurub).hm)
    dataAlmanakEphem[4].add(desKeHms(tinggi_bulan_hakiki).dm)
    dataAlmanakEphem[5].add(desKeHms(elongasi).dm)
    dataAlmanakEphem[6].add(desKeHms(azo).dms)
    dataAlmanakEphem[7].add(desKeHms(azc).dms)
    dataAlmanakEphem[8].add(keadaan_h)
    dataAlmanakEphem[9].add(muktsu_hilal)
    dataAlmanakEphem[10].add(besarCahaya)


}

fun kriteriaImkan(irtifa:Double,elongasi:Double):Boolean{
    return irtifa>=3 && elongasi>=6.4
}

fun hariAwalBulan(jd:Double, imkan:Boolean):String{
    var jdAwalBulan = if (imkan) jd+1 else jd+2
    return jdKeMasehi(jdAwalBulan).pppddd_ddmmmyy

}