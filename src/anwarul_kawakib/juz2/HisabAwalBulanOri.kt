package anwarul_kawakib.juz2

import utilitas.*
import kotlin.math.*

// YANG SAYA MODIF WD / LT
fun main() {
    val bujur = dmsKeDesimal(105, 12, 0)
    val lintang = dmsKeDesimal(-4, 58, 0)
    val zona_waktu = 7.0
    val ketinggianTempat = 50f

//    bulan = Random.nextInt(1,12)
//    tahun = Random.nextInt(1410,1700)
//    val bulanHijriDicari = Random.nextInt(1,12)
//    val tahunHijri = Random.nextInt(1440,1450)

    val tahunHijri = 1410
    val bulanHijriDicari = 0
    val hy = tahunHijri + ((bulanHijriDicari-1) * 29.53) / 354.3671 // Ayyamu Attarikhil Hijrii
    val k = round((hy - 1410) * 12) // Mahfudz Mabda'
    val to = k / 1200 //Juz Al ashli
    val jd = 2447740.652 + 29.53058868 * k + 0.0001178 * to.pow(2) // Tarikh Juliyani Ghouru Muaddal

    val md = frac((29.10535608 * k + 207.9587074 + -0.0000333 * to.pow(2)) / 360)
    val m = md * 360 //Khossoh Syamsi
    val mdf = frac((111.1791307 + 385.81691806 * k + 0.0107306 * to.pow(2.0)) / 360)
    val mf = mdf * 360 //Khossoh Qomar

    val fh = frac((164.2162296 + 390.67050646 * k + -0.0016528 * to.pow(2.0)) / 360)
    val f = fh * 360 //Hissoh Ardli

    val t1 = ((0.1734 - 0.000393 * to) * sin(toRad(m)))+0.0021 * sin(toRad(2 * m))
    val t2 = (-0.4068 * sin(toRad(mf)))+0.0161 * sin(toRad(2 * mf))
    val t3 = (-0.0004 * sin(toRad(3 * mf)))+0.0104 * sin(toRad(2 * f))
    val tpm1 = t1+t2+t3 +0.0005 * sin(toRad(m + 2 * mf))
    val t4 = (-0.0051 * sin(toRad(m + mf)))+-0.0074 * sin(toRad(m - mf))
    val t5 = (0.0004 * sin(toRad(2 * f + m)))+-0.0004 * sin(toRad(2 * f - m))
    val t6 = (-0.0006 * sin(toRad(2 * f + mf)))+0.0010 * sin(toRad(2 * f - mf))

    var mt = 0.0 // majmuah takdiilati
    val ktt = arrayListOf(t1 , t2 , t3 , t4 , t5 , t6 )

    for (i in ktt.indices){
        mt+=ktt[i]
//        println("T$i: ${desKeDms(ktt[i]).dmsss}")

    }



    val jd_ijtima = jd + 0.5 + mt // Tarikh Juliyani Al Muaddal Lil ijtima'
    val wut = frac(jd_ijtima) //Juz Tarikh
    val mtp = (wut*24)+zona_waktu // Saat Ijtima'
    val wal = if (mtp>24) mtp-24 else mtp



    //System.out.println(Konversi.hms(waktu_ijtima_wd));
    val z = jd_ijtima.toInt()


    val sdl = 2 * 3.14159265359 * (z +1 - 2451545) / 365.25 //Mahfudz Mail Awal
    val dmo = 0.37877 + 23.264 * sin(toRad(57.297 * sdl - 79.547))
    val dmi = dmo + 0.3812 * sin(toRad(2 * 57.297 * sdl - 82.682))
    val dm = dmi + 0.17132 * sin(toRad(3 * 57.297 * sdl - 59.722)) //Mail Awwal saat 12
    val u = (z +1 - 2451545) / 36525.0 //Mahfudz Tuul Syamsi
    val lo = 280.46607 + 36000.7698 * u // Thul Syamsy saah tsania 'asyar
    val loRad = toRad(lo)
    val e1 = 1789 + 237 * u //Mahfudz Daqoiq tafawut
    val e2 = 7146 - 62 * u //Mahfudz Daqoiq tafawut
    val e3 = 9934 - 14 * u //Mahfudz Daqoiq tafawut
    val e4 = -1 * e1 * sin(loRad) - e2 * cos(loRad) + e3 * sin(2 * loRad) //Mahfudz Daqoiq tafawut
    val e5 = e4 - (29 + 5 * u) * cos(2 * loRad) + (74 + 10 * u) * sin(3 * loRad) //Mahfudz Daqoiq tafawut
    val e6 = (320 - 4 * u) * cos(3 * loRad) - 212 * sin(4 * loRad) //Mahfudz Daqoiq tafawut
    val pw = (e5 + e6) / 1000 / 60 //Daqoiq tafawut saah tsania 'asyar
    val dmr = toRad(dm)
    val ltr = toRad(lintang)

    val sm1 = sin(toRad(-0.8333 - 0.0347 * sqrt(ketinggianTempat))) //Mahfudz Inkhifadul Ufuq
    val swm = toDeg(acos((sm1 - sin(dmr) * sin(ltr)) / (cos(dmr) * cos(ltr)))) //Nishfu Qousi Nahar Syamsi Taqribi
    val mgb = (12 - bujur / 15 - pw) + (swm / 15) // Waqtu Magrib Taqribi
//    val mgb = 11.0 // Waqtu Magrib Taqribi


    val zp = (jd_ijtima+zona_waktu/24.0).toInt() //Mahfudz tarikh Miladi
    val aa = ((zp - 1867216.25) / 36524.25).toInt() //Mahfudz tarikh Miladi
    val akp = (aa/4).toInt() //Mahfudz tarikh Miladi
    val a = zp + 1 + aa - akp //Mahfudz tarikh Miladi

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

    val tanggal_ijtima_lokal = "$tanggal_ijtima_masehiLT ${namaBulan(bulan_ijtima_masehiLT)} $tahun_ijtima_masehiLT"
    val harpas_ijtima_lokal = "${namaHari(nomor_hari)} ${namaPasaranIrmu(nomor_pasaran)}"


    val ar = (tahun_ijtima_masehiLT / 100)
    val arb = (ar / 4).toInt()

    val rk =2 +  arb - ar //Tashih Tarikh Masehi



    val jd_hisab = d + ep + tanggal_ijtima_masehiLT + mgb / 24.0 + rk - 1524.5 // aslul miladi
    val t = (jd_hisab-2451545)/36525 //Juz Aslil Miladi


    // Data Matahari

    val wsm = frac((280.46645 + 36000.76983 * t) / 360)
    val ws = mod( wsm * 360,360) //wasat syamsi

    val khm = frac((357.5291 + 35999.05030 * t) / 360)
    val kkn = mod(khm * 360,360) //khossoh syamsi

    val usm = frac((125.04 - 1934.136 * t) / 360)
    val us = mod(usm * 360,360) //uqdah syamsi




    //Tashihatu li uqdah syamsi wal qomar
    val k1 = 17.264 / 3600 * sin(toRad(us)) + 0.206 / 3600 * sin(toRad(2 * us))
    val k2 = -1.264 / 3600 * sin(toRad(2 * ws))
    val r1 = 9.23 / 3600 * cos(toRad(us)) - 0.090 / 3600 * cos(toRad(2 * us))
    val r2 = 0.548 / 3600 * cos(toRad(2 * ws))
//    val q1 = 23.43929111 + r1 + r2 - 46.8150 / 3600 * t //mailkully
    val q1 = dmsKeDesimal(23,26,0) //mailkully
    val tsm = 6898.06 / 3600 * sin(toRad(kkn))+ 72.095 / 3600* sin(toRad(2 * m))
    val ts = tsm  + 0.966 / 3600 * sin(toRad(3 * m)) //ta'dil syamsi
    val s1 = mod(ws + ts + k1 + k2 - 20.47 / 3600,360) // Thul Syamsy


    val dkm = toDeg(asin(sin(toRad(s1)) * sin(toRad(q1)))) //Mail Syamsi
    var pt = 0.0
    val ptm = toDeg(atan(tan(toRad(s1)) * cos(toRad(q1)))) //Matholi' ul Mustaqimah Li Syamsi

    if (s1 >=0&&s1<=90) pt = ptm
    else if (s1 >90&&s1<=270)pt = ptm+180
    else if (s1 >270&&s1<=360)pt = ptm+360

//    when(s1){
//        in 0.0..89.0 -> pt = ptm
//        in 90.0..269.0 -> pt = ptm+180
//        in 270.0..360.0 -> pt = ptm+360
//    }

    val mr = mod(abs(ws-pt),360)
    val mm = if (mr>5) (pt+360) else pt
    val df = (ws-mm)/15 // Daqoiq Tafawut
    val sd = 0.267 / (1 - 0.017 * cos(toRad(kkn))) //Nishfu Qotri Syamsi
    val iss = - (sd+(34.5/60)+(1.76 / 60 * sqrt(ketinggianTempat))) //Irtifa' Syamsi waqtal Ghurub
    val nfs = toDeg(acos(-tan(toRad(lintang)) * tan(toRad(dkm)) + sin(toRad(iss)) / cos(toRad(lintang)) / cos(toRad(dkm)))) //Nishfu Qousi Nahar Syamsi
    val ghurub_wd = mod(nfs/15 + 12-df +(zona_waktu*15-bujur)/15,24) //Ghurub Syamsi ghurubiyyah



    //Data Bulan

    val wks = frac((218.31617 + 481267.88088 * t) / 360)
    val wq = mod(wks * 360,360) //wasat qomar

    val kks =  frac((134.96292 + 477198.86753 * t) / 360)
    val kq = mod(kks * 360,360) //Khossoh Qomar

    val fks =  frac((93.27283 + 483202.01873 * t) / 360)
    val fd = mod(fks* 360,360) //Hissah 'Ardli
    val fws = frac((297.85027 + 445267.11135 * t) / 360)
    val fw = mod(fws * 360,360) //Fadlul Wasath





    // 14 takdil qomar
    val tq1 = 22640 / 3600.0 * sin(toRad(kq))+ -4586 / 3600.0 * sin(toRad(kq - 2 * fw))
    val tq2 = 2370 / 3600.0 * sin(toRad(2 * fw) )+769 / 3600.0 * sin(toRad(2 * kq))
    val tq3 = -668 / 3600.0 * sin(toRad(kkn))+-412 / 3600.0 * sin(toRad(2 * fd))
    val tq4 =-212 / 3600.0 * sin(toRad(2 * kq - 2 * fw))
    val tpq1 = tq1+tq2+tq3+tq4
    val tq5 = -206 / 3600.0 * sin(toRad(kq + kkn - 2 * fw))
    val tq6 = 192 / 3600.0 * sin(toRad(kq + 2 * fw))+ -165 / 3600.0 * sin(toRad(kkn - 2 * fw))

    val tq7 =148 / 3600.0 * sin(toRad(kq - kkn))+-125 / 3600.0 * sin(toRad(fw))

    val tq8 = -110 / 3600.0 * sin(toRad(kq + kkn))+-55 / 3600.0 * sin(toRad(2 * fd - 2 * fw))

    var mtq =0.0 // Majmu' Ta'dil
    val tq = arrayListOf(tq1,tq2,tq3,tq4,tq5,tq6,tq7,tq8)
    for (i in tq.indices){
//        printDms("tq${i+1}",tq[i])
        mtq+=tq[i]
    }


    val mo = (wq + mtq + k1 + k2 - 20.47 / 3600) //Thul Qomar
//    val mo = (wq + mtq + k1 + k2 - 20.47 / 3600)
//    val a1= ksq + tq2 + tq3 + tq5
    val lma =(18461 / 3600.0 )* sin(toRad(fd)) + 1010 / 3600.0 * sin(toRad(kq + fd))
    val lmb=lma + 1000 / 3600.0 * sin(toRad(kq - fd)) - 624 / 3600.0 * sin(toRad(fd - 2 * fw))
    val lmc= lmb - 199 / 3600.0 * sin(toRad(kq - fd - 2 * fw))
    val aq = lmc- 167 / 3600.0 * sin(toRad(kq + fd - 2 * fw)) //'Ardul Qomar
//    val l1 = (18461 / 3600.0 )* sin(toRad(fq)) + 1010 / 3600.0 * sin(toRad(ksq + fq)) + 1000 / 3600.0 * sin(toRad(ksq - fq)) - 624 / 3600.0 * sin(toRad(fq - 2 * fw)) - 199 / 3600.0 * sin(toRad(ksq - fq - 2 * fw)) - 167 / 3600.0 * sin(toRad(ksq + fq - 2 * fw))
    val eb = toDeg(atan(sin(toRad(mo)) * tan(toRad(q1)))) //Mail Tsani Qomar
    val dc = toDeg(asin(sin(toRad(mo)) * sin(toRad(q1)) * sin(toRad(aq + eb)) / sin(toRad(eb)))) //Bu'dul Qomar 'An madaril I'tidal



    var ptc = 0.0
    val ptb = toDeg(acos(cos(toRad(mo)) * cos(toRad(aq)) / cos(toRad(dc)))) //Matoli' Mustaqimah Lil Qomar
    if (mo in 0.0..180.0)ptc = ptb
    else ptc = 360-ptb
    val tc = mod(pt - ptc + nfs,360) //Fadlu Dair
    val rh = toDeg(asin(sin(toRad(lintang)) * sin(toRad(dc)) + cos(toRad(lintang)) * cos(toRad(dc)) * cos(toRad(tc)))) //Irtifa' Hilal Haqiqi


    val azm = toDeg(atan(-sin(toRad(lintang)) / tan(toRad(nfs)) + cos(toRad(lintang)) * tan(toRad(dkm)) / sin(toRad(nfs))))+270 //Samtu Irtifa' Syamsi 'Inda Ghurub
    val azb = toDeg(atan(-sin(toRad(lintang)) / tan(toRad(tc)) + cos(toRad(lintang)) * tan(toRad(dc)) / sin(toRad(tc))))+270 //Samtu Irtifa' Qomar 'Inda Ghurub


    var muktsu_hilal = (ptc - pt) / 15 //Muktsu Hilal Fauqol Ufuq

    val elongasi = toDeg(acos(cos(toRad(mo - s1)) * cos(toRad(aq))))
    val mahfudz_nh = toDeg(acos(-cos(toRad(elongasi))))
    var nurul_hilal = (1 + cos(toRad(mahfudz_nh))) / 2 * 100 //nururl hilal
    val ghurub_hilal_taqrib = ghurub_wd + muktsu_hilal
    val sudut = abs(azm-azb)
    var ket=""
    if (sudut>2)
        if (azb>azm) ket ="Menghadap ke Utara"
        else ket = "Menghadap Ke Selatan"
    else ket ="Terlentang"
    if (rh<0) {
        muktsu_hilal = 0.0
        ket = ""
        nurul_hilal = 0.0
    }
    val jr = azb - azm
    val mrj = toDeg(atan((jr/rh)))
    println(mrj)
    val bk = if (jr<0) mrj+360 else mrj
    val rt = mod(bk+180,360)


//    println("HY: $hy")
//    println("K: $k")
//    println("to: $to")


    printKl("HY: ",hy)
    printKl("K: ",k)
    printKl("to: ",to)
    printKl("JD: ",jd)
        println("JD: $jd")

    printDms("md: ",md)
    printDms("m: ",m)
    printDms("mdf: ",mdf)
    printDms("mf: ",mf)
    printDms("fh:" ,fh)
    printDms("f:" ,f)

    for (i in ktt.indices){
        println("T${i+1}: ${desKeDms(ktt[i]).dmsss}")
        if (i==2) printDms("Tpm1" ,tpm1)

    }

    printDms("MT: ",mt)
    println("JDi: $jd_ijtima")
    printKl("Jdi",jd_ijtima)
    printDms("mtp: ",wut)
    printDms("wal: ",wal)
    println("z: $z")
    printKl("z",z)
    printDms("Sdl: ",sdl)
    printDms("Dmo: ",dmo)
    printDms("Dmi: ",dmi)
    printDms("Dm: ",dm)
    printDms("U: ",u)
//    println("lo \t\t: $lo")
//    println("e1 \t\t: $e1")
//    println("e2 \t\t: $e2")
//    println("e3 \t\t: $e3")
//    println("e4 \t\t: $e4")
//    println("e5 \t\t: $e5")
//    println("e6 \t\t: $e6")

    printKl("lo",lo)
    printKl("e1",e1)
    printKl("e2",e2)
    printKl("e3",e3)
    printKl("e4",e4)
    printKl("e5",e5)
    printKl("e6",e6)


    printDms("Pw: ",pw)
    printDms("sm1: ",sm1)
    printDms("swm: ",swm)
    printDms("Mgb: ",mgb)
    println()
    println("zp: $zp")
    println("aa: $aa")
    println("akp: $akp")
    println("a-zp: ${a-zp}")
    println("a: $a")
    println("b: $b")
    println("c: $c")
    println("d: $d")
    println("e: $e")
    println("ep: $ep")
    println("pa: $pa")
    println("nhm: $nhm")
    println("npr: $npr")
    println("NHa: $nomor_hari")
    println("NPas: $nomor_pasaran")
    println("TGM: $tanggal_ijtima_masehiLT")
    println("BLM: $bulan_ijtima_masehiLT")
    println("THM: $tahun_ijtima_masehiLT")

    println("Rk: $rk")
//    println("JD Hisab: $jd_hisab")
    printKl("Jd Hisab",jd_hisab)
    println("t : $t")
    printDms("T: ",t)
    printDms("Wsm ",wsm)

    printDms("Ws: ",ws)
    printDms("Khm: ",khm)
    printDms("kkn: ",kkn)
    printDms("usm: ",usm)
    printDms("Us: ",us)
    printDms("k1: ",k1)
    printDms("k2: ",k2)
    printDms("q1: ",q1)
    printDms("tsm: ",tsm)
    printDms("ts: ",ts)
    printDms("s1: ",s1)
    printDms("Dkm: ",dkm)
    printDms("ptm: ",ptm)
    printDms("pt: ",pt)
    printDms("mr: ",mr)
    printDms("mm: ",mm)
    printDms("df: ",df)
    printDms("sd: ",sd)
    printDms("iss: ",iss)
    printDms("nfs: ",nfs)
    printDms("Maghrib WD: ",ghurub_wd)
    println()


    printDms("wks: ",wks)
    printDms("Wq: ",wq)
    printDms("kks: ",kks)
    printDms("ksq: ",kq)
    printDms("fks: ",fks)
    printDms("fd: ",fd)
    printDms("fws: ",fws)
    printDms("fw: ",fw)
    println()


    println()
    for (i in tq.indices){
        printDms("tq${i+1}",tq[i])
        if (i==4)    printDms("tpq1: ",tpq1)
    }

    printDms("Mtq: ",mtq)
    printDms("Mo: ",mo)

    printDms("lma: ",lma)
    printDms("lmb: ",lmb)
    printDms("lmc: ",lmc)
    printDms("aq: ",aq)
    printDms("eb: ",eb)
    printDms("dc: ",dc)

    printDms("ptb: ",ptb)
    printDms("Ptc: ",ptc)
    printDms("Tc: ",tc)
    printDms("rh: ",rh)

    printDms("Azm: ",azm)
    printDms("Azb: ",azb)

    printDms("Mh: ",muktsu_hilal)
//    printDms("Msh: ",mahfudz_samkulHilal)
//    printDms("Sh: ",samkul_hilal)

    printDms("Elo: ",elongasi)
    printDms("NH: ",nurul_hilal)
    printDms("GH: ",ghurub_hilal_taqrib)
    printDms("st: ",sudut)

    println()
    println("Hari Ijtima' LT: $harpas_ijtima_lokal")
    println("Tanggal Ijtima' LT: $tanggal_ijtima_lokal")
    println("Pukul Ijtima' LT: ${desKeDms(wal).hmsss}")
    println("Data Hisab Maghrib: ${desKeDms(ghurub_wd).hmsss}")
    printDms("Irtifa' Hilal: ",rh)
    printDms("Lama Hilal",muktsu_hilal)
    println("Cahaya Hilal: $nurul_hilal %")
    printDms("Elongasi ",elongasi)
    printDms("Azimut Matahari: ",azm)
    printDms("Azimut Hilal: ",azb)
//    printDms("Azimut Matahari: ",abs(azm-270))
//    printDms("Azimut Hilal: ",abs(azb-270))
    printDms("Ghurub Hilal",ghurub_hilal_taqrib)
    println("Kedaaan Hilal: $ket")
    printDms("Rotasi Hilal",rt)
}


