package inkasyaf

import utilitas.*
import kotlin.math.*

// Hisab Awal Bulan Hijriyah Hakiki Metode Inkasyaf Al-Hisab
fun main() {
    val bujur = dmsKeDesimal(105, 12, 0)
    val lintang = dmsKeDesimal(-4, 58, 0)
    val ketinggianTempat = 50f
    val zona_waktu = 7.0

//    val bujur = dmsKeDesimal(112, 37, 2.5)
//    val lintang = dmsKeDesimal(-7, 10, 11)
//    val ketinggianTempat = 120.0

    val tahunHijri = 1444 // Tahun Hijri Naqish ( Yang dicari)
    val bulanHijriDicari = 7 // Bulan Hijri Naqish bukan tamm

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
    val mtp = (frac(jd_ijtima)*24)+zona_waktu // Saat Ijtima'
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

    val tanggal_ijtima_lokal = "$tanggal_ijtima_masehiLT ${namaBulan(bulan_ijtima_masehiLT)} $tahun_ijtima_masehiLT"
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
    val lr =  lo-0.0056861+1.916128*sin(ld)//+0.020026*sin(2*ld) //Bujur Matahari terkoreksi

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
    val e2 = e1+4* pe *y* sin(ld)* cos(2*lor)
    val e3 = e2-0.5*y.pow(2.0) * sin(4*lor)
    val e4 = e3- 1.25* pe.pow(2.0) * sin(2*ld)
    val pw = toDeg(e4)/15 //deklinasi Matahari pada 12 WIB


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
    val tsm = 6898.06 / 3600 * sin(toRad(cs))+ 72.095 / 3600* sin(toRad(2 * cs))
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
    val tq1 = 22640 / 3600.0 * sin(toRad(fd))+ -4586 / 3600.0 * sin(toRad(fd - 2 * ff))
    val tq2 = 2370 / 3600.0 * sin(toRad(2 * ff) )+769 / 3600.0 * sin(toRad(2 * fd))
    val tq3 = -668 / 3600.0 * sin(toRad(cs))+-412 / 3600.0 * sin(toRad(2 * fe))
    val tq4 =-212 / 3600.0 * sin(toRad(2 * fd - 2 * ff))
    val tpq1 = tq1+tq2+tq3+tq4
    val tq5 = -206 / 3600.0 * sin(toRad(fd + cs - 2 * ff))
    val tq6 = 192 / 3600.0 * sin(toRad(fd + 2 * ff))+ -165 / 3600.0 * sin(toRad(cs - 2 * ff))

    val tq7 =148 / 3600.0 * sin(toRad(fd - cs))+-125 / 3600.0 * sin(toRad(ff))

    val tq8 = -110 / 3600.0 * sin(toRad(fd + cs))+-55 / 3600.0 * sin(toRad(2 * fe - 2 * ff))

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


    val azm = toDeg(atan(-sin(toRad(lintang)) / tan(toRad(ns)) + cos(toRad(lintang)) * tan(toRad(dkm)) / sin(toRad(ns))))+270 //Samtu Irtifa' Syamsi 'Inda Ghurub
    val azb = toDeg(atan(-sin(toRad(lintang)) / tan(toRad(tc)) + cos(toRad(lintang)) * tan(toRad(dc)) / sin(toRad(tc))))+270 //Samtu Irtifa' Qomar 'Inda Ghurub


    var muktsu_hilal = (ptc - pt) / 15 //Muktsu Hilal Fauqol Ufuq

    val elongasi = toDeg(acos(cos(toRad(sa - s)) * cos(toRad(aq))))
    var nurul_hilal = (1 + -cos(toRad(elongasi))) / 2 * 100 //nururl hilal
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
    val bk = if (jr<0) mrj+360 else mrj
    val rt = mod(bk+180,360)


//    println("HY: $hy")
//    println("K: $k")
//    println("to: $to")


    printKl("HY ",hy)
    printKl("K ",k)
    printKl("to ",to)
    printKl("JD ",jd)

    printKl("m ",m)
    printKl("me ",me)
    printKl("f" ,f)

    for (i in ktt.indices){
        println("T${i+1}: ${desKeDms(ktt[i]).dmsss}")
        if (i==2) printDms("Tpm1" ,tpm1)

    }

    printDms("MT: ",mt)
    printKl("Jdi",jd_ijtima)
    print("Z ",jd_ijtima.toInt())
    printDms("wal: ",wal)
    println()
    println("zp: $zp")
    println("Rku: $rku")
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
    println("Mj: $mj")

    printDms("Mk: ",mk)
    printDms("U: ",u)
    printKl("lo",lo)
    printKl("ld", toDeg( ld))
    printKl("lr",lr)
    printDms("Dm: ",dm)
    printDms("y: ",y)
    printDms("Pe: ",pe)

    printDms("e1",e1)
    printDms("e2",e2)
    printDms("e3",e3)
    printDms("e4" , e4)
    printDms("Pw: ",pw)
    printDms("sm: ",sm)
    printDms("swm: ",swm)
    printDms("Mg: ",mg)


    printKl("Jd Hisab",jd_hisab)
    printDms("T: ",t)

    printDms("Wsm: ",wsm)
    printDms("Ws: ",ws)
    printDms("Cs: ",cs)
    printDms("Ds: ",ds)
    printDms("k1: ",k1)
    printDms("k2: ",k2)
    printDms("Mk: ",mk)
    printDms("tsm: ",tsm)
    printDms("ts: ",ts)
    printDms("s: ",s)
    printDms("ptm: ",ptm)
    printDms("pt: ",pt)
    printDms("mw: ",mw)
    printDms("mm: ",mm)
    printDms("df: ",df)
    printDms("sd: ",sd)
    printDms("iss: ",iss)
    printDms("Dkm: ",dkm)
    printDms("ns: ",ns)
    printDms("Azm: ",azm)
    printDms("Maghrib WD: ",ghurub_wd)
    println()


    printDms("Wks: ",wks)
    printDms("Wq: ",wq)
    printKl("fd",fd)
    printKl("fe",fe)
    printKl("ff",ff)



    println()
    for (i in tq.indices){
        printDms("tq${i+1}",tq[i])
        if (i==3)    printDms("tpq1: ",tpq1)
    }

    printDms("Mtq: ",mtq)
    printDms("Sa: ",sa)
    println()
    printDms("lma: ",lma)
    printDms("lmb: ",lmb)
    printDms("lmc: ",lmc)
    printDms("aq: ",aq)
    printDms("eb: ",eb)
    printDms("dc: ",dc)

    printDms("ptb: ",ptb)
    printDms("Ptc: ",ptc)
    printDms("Tc: ",tc)
    printDms("Lt: ",lintang)
    printDms("RH: ",rh)


    printDms("Azb: ",azb)

//    printDms("Msh: ",mahfudz_samkulHilal)
//    printDms("Sh: ",samkul_hilal)

    printDms("Elo: ",elongasi)
    printDms("Mh: ",muktsu_hilal)
    printDms("Ch: ",nurul_hilal)
    printDms("GH: ",ghurub_hilal_taqrib)
    printDms("st: ",sudut)

    println()
    println("Pukul Ijtima' LT: ${desKeDms(wal).hmsss}")
    println("Hari Ijtima' LT: $harpas_ijtima_lokal")
    println("Tanggal Ijtima' LT: $tanggal_ijtima_lokal")
    println("Data Hisab Maghrib: ${desKeDms(ghurub_wd).hmsss}")
    printDms("Irtifa' Hilal: ",rh)
    printDms("Lama Hilal",muktsu_hilal)
    println("Cahaya Hilal: $nurul_hilal %")
    println("Kedaaan Hilal: $ket")
    printDms("Ghurub Hilal",ghurub_hilal_taqrib)
    printDms("Elongasi ",elongasi)
    printDms("Azimut Matahari: ",azm)
    printDms("Azimut Hilal: ",azb)
//    printDms("Rotasi Hilal",rt)
}


