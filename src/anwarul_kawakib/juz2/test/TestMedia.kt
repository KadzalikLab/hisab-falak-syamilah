package anwarul_kawakib.juz2

import utilitas.*
import kotlin.math.*

// Backup Histori
fun main() {
    val bujur = dmsKeDesimal(105, 12, 0)
    val lintang = dmsKeDesimal(-4, 58, 0)
    val zona_waktu = 7.0
    val ketinggianTempat = 50.0
    val dip = 1.76 / 60 * sqrt(ketinggianTempat)

    val bulanHijriDicari = 6
    val tahunHijri = 1443

    val hy = tahunHijri + ((bulanHijriDicari-1) * 29.53) / 354.367
    val k = round((hy - 1410) * 12)
    val to = k / 1200
    val jd = 2447740.652 + 29.53058868 * k + 0.0001178 * to.pow(2)

    val md = frac((29.10535608 * k + 207.9587074 + -0.0000333 * to.pow(2)) / 360)  //Khossoh Syamsi
    val m = md * 360 //Khossoh Syamsi
    val mdf = frac((111.1791307 + 385.81691806 * k + 0.0107306 * to.pow(2.0)) / 360)  //Khossoh Qomar
    val mf = mdf * 360 //Khossoh Qomar

    val fd = frac((164.2162296 + 390.67050646 * k + -0.0016528 * to.pow(2.0)) / 360) //Hissoh Ardli
    val f = fd * 360 //Hissoh Ardli

    val t1 = (0.1734 - 0.000393 * to) * sin(toRad(m))
    val t2 = 0.0021 * sin(toRad(2 * m))
    val t3 = -0.4068 * sin(toRad(mf))
    val t4 = 0.0161 * sin(toRad(2 * mf))
    val t5 = -0.0004 * sin(toRad(3 * mf))
    val t6 = 0.0104 * sin(toRad(2 * f))
    val t7 = -0.0051 * sin(toRad(m + mf))
    val t8 = -0.0074 * sin(toRad(m - mf))
    val t9 = 0.0004 * sin(toRad(2 * f + m))
    val t10 = -0.0004 * sin(toRad(2 * f - m))
    val t11 = -0.0006 * sin(toRad(2 * f + mf))
    val t12 = 0.0010 * sin(toRad(2 * f - mf))
    val t13 = 0.0005 * sin(toRad(m + 2 * mf))

    var mt = 0.0 // majmuah takdiilati
    val ktt = arrayListOf(t1 , t2 , t3 , t4 , t5 , t6 , t7 , t8 , t9 , t10 , t11 , t12 , t13)

    for (i in ktt.indices){
        mt+=ktt[i]
//        println("T$i: ${desKeDms(ktt[i]).dmsss}")

    }



    val jd_ijtima = jd + 0.5 + mt
    val wut = frac(jd_ijtima)*24
    val mtu = wut+zona_waktu
    val wal =
            if (mtu>24) mtu-24
            else mtu

    //System.out.println(Konversi.hms(waktu_ijtima_wd));
    val z = jd_ijtima.toInt()

    val jdl = z - zona_waktu / 24
    val sdl = 2 * 3.14159265359 * (jdl - 2451545) / 365.25
    val dmo = 0.37877 + 23.264 * sin(toRad(57.297 * sdl - 79.547))
    val dmi = dmo + 0.3812 * sin(toRad(2 * 57.297 * sdl - 82.682))
    val dm = dmi + 0.17132 * sin(toRad(3 * 57.297 * sdl - 59.722))
    val u = (jdl - 2451545) / 36525
    var lo = 280.46607 + 36000.7698 * u
    lo = toRad(lo)
    val e1 = 1789 + 237 * u
    val e2 = 7146 - 62 * u
    val e3 = 9934 - 14 * u
    val e4 = -1 * e1 * sin(lo) - e2 * cos(lo) + e3 * sin(2 * lo)
    val e5 = e4 - (29 + 5 * u) * cos(2 * lo) + (74 + 10 * u) * sin(3 * lo)
    val e6 = (320 - 4 * u) * cos(3 * lo) - 212 * sin(4 * lo)
    val dmr = toRad(dm)
    val ltr = toRad(lintang)
    val pw = (e5 + e6) / 1000 / 60

    val mw = cos(dmr) * cos(ltr)
    val sm1 = sin(toRad(-0.8333 - 0.0347 * sqrt(ketinggianTempat)))
    val swm = toDeg(acos((sm1 - sin(dmr) * sin(ltr)) / mw))
    val dz = 12 - bujur / 15 - pw
    val maghrib = dz + swm / 15 //UT




    val jtm = jd_ijtima+(zona_waktu/24.0)
    val zp = jtm.toInt()
    val aap = ((zp - 1867216.25) / 36524.25).toInt()
    val ap: Int
    ap = if (zp >= 2299161) zp + 1 + aap - (aap / 4) else zp

    val bp = ap + 1524
    val cp = ((bp - 122.1) / 365.25).toInt()
    val dp = (365.25 * cp).toInt()
    val ep = ((bp - dp) / 30.6001).toInt()
    val epm = (30.6001 * ep).toInt()

    val tanggal_ijtima_masehiLT = (bp - dp - epm)
    val bulan_ijtima_masehiLT: Int
    bulan_ijtima_masehiLT = if (ep >= 13.5) ep - 13 else ep - 1
    val tahun_ijtima_masehiLT: Int
    tahun_ijtima_masehiLT = if (bulan_ijtima_masehiLT >= 2.5) cp - 4716 else cp - 4715

    val pa = zp + 2
    val nhm = (pa / 7).toInt()
    val npr = (pa / 5).toInt()
    val nomor_hari = pa - nhm * 7
    val nomor_pasaran = pa - npr * 5

    val tanggal_ijtima_lokal = "${namaHari(nomor_hari)} ${namaPasaranIrmu(nomor_pasaran)}, $tanggal_ijtima_masehiLT ${namaBulan(bulan_ijtima_masehiLT)} $tahun_ijtima_masehiLT, ${desKeDms(wal).hmsss}"

    var mj = bulan_ijtima_masehiLT
    var thj = tahun_ijtima_masehiLT

    if (bulan_ijtima_masehiLT<=2){
        mj+=12
        thj-=1
    }

    val ar = (tahun_ijtima_masehiLT / 100)
    val arb = (ar / 4).toInt()

    val btu =2 +  arb - ar


    val akl = (365.25 * (thj + 4716)).toInt()
    val akm = (30.6001 * (mj + 1)).toInt()



    val jd_hisab = akl + akm + tanggal_ijtima_masehiLT + (maghrib / 24) + btu - 1524.5 // aslul miladi
    val t = (jd_hisab-2451545)/36525


    // Data Matahari

    val wsm = frac((280.46645 + 36000.76983 * t) / 360)
    val ws =  wsm * 360 //wasat syamsi

    val khm = frac((357.5291 + 35999.05030 * t) / 360)
    val kkn = khm * 360 //khossoh syamsi

    val usm = frac((125.04 - 1934.136 * t) / 360)
    val us = usm * 360 //uqdah syamsi
//    val us = mod((125.04 - 1934.136 * T) / 360,1) * 360 //uqdah syamsi



    //Tashihatu li uqdah syamsi wal qomar
    val k1 = 17.264 / 3600 * sin(toRad(us)) + 0.206 / 3600 * sin(toRad(2 * us))
    val k2 = -1.264 / 3600 * sin(toRad(2 * ws))
    val r1 = 9.23 / 3600 * cos(toRad(us)) - 0.090 / 3600 * cos(toRad(2 * us))
    val r2 = 0.548 / 3600 * cos(toRad(2 * ws))
    val q1 = 23.43929111 + r1 + r2 - 46.8150 / 3600 * t //mailkully
    val tsm = 6898.06 / 3600 * sin(toRad(kkn))+ 72.095 / 3600* sin(toRad(2 * m))
    val ts = tsm  + 0.966 / 3600 * sin(toRad(3 * m)) //ta'dil syamsi
    val s1 = ws + ts + k1 + k2 - 20.47 / 3600
    val dkm = toDeg(asin(sin(toRad(s1)) * sin(toRad(q1))))
    var pt = toDeg(atan(tan(toRad(s1)) * cos(toRad(q1))))
    if (s1 in 0.0..90.0) pt += 0.0
    else if (s1 > 90 && s1 <= 270) pt += 180.0
    else if (s1 > 270 && s1 <= 360) pt += 360.0

    val mr = abs(ws-pt)
    val mm = if (mr>5) pt+360 else pt
    val df = (ws-mm)/15
    val sd = 0.267 / (1 - 0.017 * cos(toRad(kkn)))
    val iss = - (sd+(34.5/60)+dip)
    val npm = -tan(toRad(lintang)) * tan(toRad(dkm)) + sin(toRad(iss)) / cos(toRad(lintang)) / cos(toRad(dkm))
    val nfs = toDeg(acos(npm))
    val gis = nfs/15+(12-df)
    val ghurub_wd = gis+((zona_waktu*15)-bujur)/15



    //Data Bulan

    val wks = frac((218.31617 + 481267.88088 * t) / 360)
    val wq = wks * 360 //wasat qomar
    println(t)
    printDms(t)
    println(wks)

    val kks =  frac((134.96292 + 477198.86753 * t) / 360)
    val ksq = kks * 360

    val fks =  frac((93.27283 + 483202.01873 * t) / 360)
    val fq = fks* 360
    val fws = frac((297.85027 + 445267.11135 * t) / 360)
    val fw = fws * 360





    // 14 takdil qomar
    val tq1 = 22640 / 3600.0 * sin(toRad(ksq))
    val tq2 = -4586 / 3600.0 * sin(toRad(ksq - 2 * fw))
    val tq3 = 2370 / 3600.0 * sin(toRad(2 * fw)) //

    val tq4 = 769 / 3600.0 * sin(toRad(2 * ksq)) //

    val tq5 = -668 / 3600.0 * sin(toRad(kkn))
    val tq6 = -412 / 3600.0 * sin(toRad(2 * fq)) //

    val tq7 = -212 / 3600.0 * sin(toRad(2 * ksq - 2 * fw)) //

    val tq8 = -206 / 3600.0 * sin(toRad(ksq + kkn - 2 * fw))
    val tq9 = 192 / 3600.0 * sin(toRad(ksq + 2 * fw)) //

    val tq10 = -165 / 3600.0 * sin(toRad(kkn - 2 * fw))
    val tq11 = 148 / 3600.0 * sin(toRad(ksq - kkn))
    val tq12 = -125 / 3600.0 * sin(toRad(fw)) //

    val tq13 = -110 / 3600.0 * sin(toRad(ksq + kkn))
    val tq14 = -55 / 3600.0 * sin(toRad(2 * fq - 2 * fw)) //

    var mtq =0.0
    val tq = arrayListOf(tq1,tq2,tq3,tq4,tq5,tq6,tq7,tq8,tq9,tq10,tq11,tq12,tq13,tq14)
    for (i in tq.indices){
//        printDms("tq${i+1}",tq[i])
        mtq+=tq[i]
    }


    val mo = wq + mtq + k1 + k2 - 20.47 / 3600
//    val a1= ksq + tq2 + tq3 + tq5
    val lma =(18461 / 3600.0 )* sin(toRad(fq)) + 1010 / 3600.0 * sin(toRad(ksq + fq))
    val lmb=lma + 1000 / 3600.0 * sin(toRad(ksq - fq)) - 624 / 3600.0 * sin(toRad(fq - 2 * fw))
    val lmc= lmb - 199 / 3600.0 * sin(toRad(ksq - fq - 2 * fw))
    val lmd = lmc- 167 / 3600.0 * sin(toRad(ksq + fq - 2 * fw))
    val l1 = (18461 / 3600.0 )* sin(toRad(fq)) + 1010 / 3600.0 * sin(toRad(ksq + fq)) + 1000 / 3600.0 * sin(toRad(ksq - fq)) - 624 / 3600.0 * sin(toRad(fq - 2 * fw)) - 199 / 3600.0 * sin(toRad(ksq - fq - 2 * fw)) - 167 / 3600.0 * sin(toRad(ksq + fq - 2 * fw))
    val eb = toDeg(atan(sin(toRad(mo)) * tan(toRad(q1))))
    val hb = l1 + eb
    val dc = toDeg(asin(sin(toRad(mo)) * sin(toRad(q1)) * sin(toRad(hb)) / sin(toRad(eb))))



    var ptc = toDeg(acos(cos(toRad(mo)) * cos(toRad(l1)) / cos(toRad(dc))))
    println(ptc)
    if (mo > 180 && mo <= 360) ptc = 360 - ptc
    val tc = pt - ptc + nfs
    val hc = toDeg(asin(sin(toRad(lintang)) * sin(toRad(dc)) + cos(toRad(lintang)) * cos(toRad(dc)) * cos(toRad(tc))))

//    val p = 384401 * (1 - 0.0549 * 0.0549) / (1 + 0.0549 * cos(toRad(a1 + tq1)))
//    val hp1 = 0.9507 / (p / 384401)
//    val sdc = 0.5181 / (p / 384401) / 2
//    val hp2 = hp1 * cos(toRad(hc))
//    val ref = 0.0167 / tan(toRad(hc + 7.31 / (hc + 4.4)))
//    val hcm: Double
//    hcm = if (hc - hp2 > 0) hc - hp2 + sdc + ref + dip else if (hc - hp2 <= 0) hc - hp2 else 0.0



    val azm = toDeg(atan(-sin(toRad(lintang)) / tan(toRad(nfs)) + cos(toRad(lintang)) * tan(toRad(dkm)) / sin(toRad(nfs))))+270


    val azc = toDeg(atan(-sin(toRad(lintang)) / tan(toRad(tc)) + cos(toRad(lintang)) * tan(toRad(dc)) / sin(toRad(tc))))+270


    val sudut_hilal_matahari = azc - azm
    val muktsu_hilal = (ptc - pt) / 15
//    val mahfudz_samkulHilal = toDeg(acos(cos(toRad(abs(hcm - iss))) * cos(toRad(abs(azc - azm)))))
//    val samkul_hilal = (1 - cos(toRad(mahfudz_samkulHilal))) * sdc * 60

    val elongasi = toDeg(acos(cos(toRad(mo - s1)) * cos(toRad(l1))))
    val mahfudz_nh = toDeg(acos(-cos(toRad(elongasi))))
    val nurul_hilal = (1 + cos(toRad(mahfudz_nh))) / 2 * 100
    val ghurub_hilal_taqrib = ghurub_wd + muktsu_hilal
    val jarak_bumi_matahari = (1.000140 - 0.01671 * cos(toRad(kkn)) - 0.00014 * cos(toRad(2 * kkn))) * 149597870
    val nh = 0


    println("HY: $hy")
    println("K: $k")
    println("to: $to")
    println("JD: $jd")
    printDms("M: ",m)
    printDms("M': ",mf)
    printDms("F:" ,f)
    for (i in ktt.indices){
        println("T${i+1}: ${desKeDms(ktt[i]).dmsss}")

    }

    printDms("MT: ",mt)
    println("JD Ijtima: $jd_ijtima")
    println("JD Hisab: $jd_hisab")
    println("Jdl: $jdl")
    println("T: $t")
    printDms("T: ",t)
    printDms("Sdl: ",sdl)
    printDms("Dmo: ",dmo)
    printDms("Dmi: ",dmi)
    printDms("Dm: ",dm)
    println("lo \t\t: ${toDeg(lo)}")
    println("e1 \t\t: $e1")
    println("e2 \t\t: $e2")
    println("e3 \t\t: $e3")
    println("e4 \t\t: $e4")
    println("e5 \t\t: $e5")
    println("e6 \t\t: $e6")
    printDms("Pw: ",pw)
    printDms("sm1: ",sm1)
    printDms("Mgb: ",maghrib)
    printDms("Ws: ",ws)
    printDms("kkn: ",kkn)
    printDms("Us: ",us)
    println()

    printDms("k1: ",k1)
    printDms("k2: ",k2)
    printDms("r1: ",r1)
    printDms("r2: ",r2)
    printDms("q1: ",q1)
    printDms("tsm: ",tsm)
    printDms("ts: ",ts)
    printDms("s1: ",s1)
    printDms("Dm: ",dkm)
    printDms("pt: ",pt)
    printDms("df: ",df)
    printDms("sd: ",sd)
    printDms("iss: ",iss)
    printDms("npm: ",npm)
    printDms("nfs: ",nfs)
    printDms("gis: ",gis)
    printDms("Ghurub WD: ",ghurub_wd)
    println()

    printDms("Wq: ",wq)
    printDms("ksq: ",ksq)
    printDms("fq: ",fq)
    printDms("fw: ",fw)

    println()
    for (i in tq.indices){
        printDms("tq${i+1}",tq[i])
//        mtq+=tq[i]
    }
    printDms("Mtq: ",mtq)
    printDms("Mo: ",mo)
//    printDms("A1: ",a1)
    printDms("lmd: ",lmd)
    printDms("L1: ",l1)
    printDms("eb: ",eb)
    printDms("Hb: ",hb)
    printDms("dc: ",dc)

    printDms("Ptc: ",ptc)
    printDms("Tc: ",tc)
    printDms("hc: ",hc)

//    println("P: $p")
//    printDms("hp1: ",hp1)
//    printDms("sdc: ",sdc)
//    printDms("hp2: ",hp2)
//    printDms("ref: ",ref)
//    printDms("hcm: ",hcm)

    printDms("Azm: ",azm)
    printDms("Azb: ",azc)

    printDms("Sudut Mat-Bul: ",sudut_hilal_matahari)
    printDms("Mh: ",muktsu_hilal)
//    printDms("Msh: ",mahfudz_samkulHilal)
//    printDms("Sh: ",samkul_hilal)

    printDms("El: ",elongasi)
    printDms("Mnh: ",mahfudz_nh)
    printDms("Nh: ",nurul_hilal)
    printDms("Ght: ",ghurub_hilal_taqrib)
    println("Jarak: $jarak_bumi_matahari")
    println("Dip: $dip")
    printDms("dip: ",dip)


    println()
    println("Waktu Ijtima' LT: $tanggal_ijtima_lokal")
    println("Data Hisab Maghrib: ${jdKeMasehi(jd_hisab+(zona_waktu/24)).ddmmmyy_hms}")
    println("Data Hisab Maghrib: ${desKeDms(ghurub_wd).hmsss}")
    printDms("Irtifa' Hilal: ",hc)
    printDms("Azimut Matahari: ",azm)
    printDms("Azimut Hilal: ",azc)
    printDms("Ghurub Hilal",ghurub_hilal_taqrib)
    printDms("Lama Hilal",muktsu_hilal)
//    println("Lebar Sabit Hilal $samkul_hilal m")
    println("Cahaya Hilal: $nurul_hilal %")
//    printDms("Elongasi: ",elongasi)
//    printDms("Umur Hilal: ",1.0)



}
