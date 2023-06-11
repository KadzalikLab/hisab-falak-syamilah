package inkasyaf.gerhana

import utilitas.*
import kotlin.math.*

fun main(){

    val bulanHijriDicari = 4
    val tahunHijri = 1445
    val zona_waktu = 0


    val hy = tahunHijri + (bulanHijriDicari * 29.53) / 354.3671 // Ayyamu Attarikhil Hijrii
    val kr = ((hy - 1410) * 12).toInt() // Mahfudz Mabda'
    val k =kr+0.5 // Mahfudz Mabda'
    val to = k / 1200 //Juz Al ashli
    val jd = 2447740.652 + 29.53058868 * k + 0.0001178 * to.pow(2) // Tarikh Juliyani Ghouru Muaddal

    val m = 29.10535608 * k + 207.9587074 + -0.0000333 * to.pow(2) //Khossoh Syamsi
    val me = 111.1791307 + 385.81691806 * k + 0.0107306 * to.pow(2.0)//Khossoh Qomar


    val f = 164.2162296 + 390.67050646 * k + -0.0016528 * to.pow(2.0) //Hissoh Ardli
    val gp = abs(sin(toRad(f)))
    var kt =
        if (gp>0.36) "Tidak Terjadi Gerhana Bulan"
        else "Terjadi Gerhana Bulan"
    var statusGerhana = gp<0.36


    val t1 = ((0.1734 - 0.000393 * to) * sin(toRad(m))) + 0.0021 * sin(toRad(2 * m))
    val t2 = -0.4068 * sin(toRad(me)) + 0.0161 * sin(toRad(2 * me))

    val t3 =  (-0.0051 * sin(toRad(m + me))) + -0.0074 * sin(toRad(m - me))


//    val tpm1 =
//    val t4 = (-0.0051 * sin(toRad(m + mf)))+-0.0074 * sin(toRad(m - mf))
//    val t5 = (0.0004 * sin(toRad(2 * f + m)))+-0.0004 * sin(toRad(2 * f - m))
//    val t6 = (-0.0006 * sin(toRad(2 * f + mf)))+0.0010 * sin(toRad(2 * f - mf))
//    val t7 = 0.0005 * sin(toRad(m + 2 * mf))

    var mt = t1+t2+t3 + (-0.0104 * sin(toRad(2 * f))) // majmuah takdiilati
    val ktt = arrayListOf(t1 , t2 , t3 )
//    val ktt = arrayListOf(t1 , t2 , t3 )

//    for (i in ktt.indices){
//        mt+=ktt[i]
////        println("T$i: ${desKeDms(ktt[i]).dmsss}")
//
//    }




    val jd_istiqbal = jd + 0.5 + mt // Tarikh Juliyani Al Muaddal Lil ijtima'
//    val wut = frac(jd_istiqbal) //Juz Tarikh
//    val sku = (wut*24) // Saat Wasatul Khusuf UT

    val jd_istiqbalWd = jd_istiqbal+ zona_waktu/24.0
    val z = (jd_istiqbalWd).toInt() //Mahfudz tarikh Miladi
    val mtp = frac(jd_istiqbalWd)  // Juz Saat Wasatul Khusuf WD
    val skd = mtp*24  // Saat Wasatul Khusuf WD
    val pg = if (skd>24) skd-24 else skd
    var gerhanaMalam = true
    if (pg in 7.0..17.0) {
        kt = "Tidak Terjadi Gerhana Bulan"
        gerhanaMalam=false
    }

    val aa = ((z - 1867216.25) / 36524.25).toInt() //Mahfudz tarikh Miladi
    val akp = (aa/4).toInt() //Mahfudz tarikh Miladi
    val a = z + 1 + aa - akp //Mahfudz tarikh Miladi

    val b = a + 1524 //Mahfudz tarikh Miladi
    val cp = ((b - 122.1) / 365.25).toInt() //Mahfudz tarikh Miladi
    val d = (365.25 * cp).toInt() //Mahfudz tarikh Miladi
    val e = ((b - d) / 30.6001).toInt() //Mahfudz tarikh Miladi
    val ep = (30.6001 * e).toInt() //Mahfudz tarikh Miladi

    val tanggal_istiqbal_masehiLT = (b - d - ep) // Tarikh Milady
    val bulan_istiqbal_masehiLT = if (e >= 13.5) e - 13 else e - 1 //Syahr Milady
    val tahun_istiqbal_masehiLT = if (bulan_istiqbal_masehiLT >= 2.5) cp - 4716 else cp - 4715 //sanah Milady

    val pa = z + 2 // Mahfudz Tarikh Akhmusi Wa Asbu'i
    val nhm = (pa / 7).toInt()
    val npr = (pa / 5).toInt()
    val nomor_hari = pa - nhm * 7 //Ism Ayyam Al Asbu'i
    val nomor_pasaran = pa - npr * 5// Ism Ayyam Al Akmussi

    val tanggal_istiqbal_lokal = "${namaHari(nomor_hari)} ${namaPasaranIrmu(nomor_pasaran)}, $tanggal_istiqbal_masehiLT ${namaBulan(bulan_istiqbal_masehiLT)} $tahun_istiqbal_masehiLT, ${desKeDms(pg).hmsss}"


    //Ardul Qomar Alkulli
    val as1 = -0.0048 * cos(toRad(m)) +0.002 * cos(toRad(2 * m))
    val as2 = -0.3283 * cos(toRad(me))+ -0.006 * cos(toRad(m + me))
    val as3 = 0.0041 * cos(toRad(m - me))
    val s = 5.19595 + (as1 + as2 + as3)



    //Harokat Mahfudz
    val c1 = 0.0024 * sin(toRad(2 * m)) + -0.039 * sin(toRad(me))
    val c2 = 0.0115 * sin(toRad(2 * me))+-0.0073 * sin(toRad(m + me))
    val c3 = -0.0067 * sin(toRad(m - me)) +0.0117 * sin(toRad(2 * f))
    val c = 0.207 * sin(toRad(m)) + (c1 + c2 + c3)

    val y = s * sin(toRad(f))+c* cos(toRad(f))

    val u1 = 0.0046 * cos(toRad(m)) + -0.0182 * cos(toRad(me))
    val u2 = 0.0004 * cos(toRad(2 * me)) + -0.0005 * cos(toRad(m + me))
    val u = 0.0059 + (u1 + u2)

    val p = 1.0129 - u
    val r = 0.4679 - u
    val n = 0.5458 + 0.04 * cos(toRad(me))
    val rt = abs(y)
    val mg = (1.0129 - u - abs(y)) / 0.545 //Kecerahan Gerhana
    val mgb = (1.0129 - u - abs(y)) / 0.545 * 12 //Kecerahan Gerhana Usbu
    val jns = if (mg>1) "Total" else if (mg<=1&&mg>0) "Sebagian" else "Penumbra / Semu"
    val jnsBool = mg>1
    val ap1 = p.pow(2) - y.pow(2)
    if (ap1<0) {
        kt = "Tidak Terjadi Gerhana Bulan"
        statusGerhana = false
    }
    val tp1 = 60 / n * sqrt(ap1) / 60 // Nishfu zamanil Khusuf
    val awg = mod(pg - tp1,24) // Awal Gerhana
    val akg = mod(pg + tp1,24) //Akhir Gerhana

    val ap2 = r.pow(2) - y.pow(2)
    val tp2 = 60 / n * sqrt(ap2) / 60
    val awt = mod(pg - tp2,24) // Awal Total
    val akt = mod(pg + tp2,24) // Akhir Total

    printKl("HY",hy)
//    printKl("",)
    print("Kr",kr)
    print("K",k)
    printDms("to",to)
    printKl("Jd",jd)

    printKl("M",m)
    printKl("me",me)
    printKl("F",f)
    printDms("gp",gp)
    printDms("t1",t1)
    printDms("t2",t2)
    printDms("t3",t3)

    printDms("mt",mt)
    printKl("Jdi",jd_istiqbalWd)
    print("Z",z)
//    printDms("mtp",mtp)
    printDms("Pg",pg)

    println()
    printDms("as1",as1)
    printDms("as2",as2)
    printDms("as3",as3)
    printDms("S",s)
    printDms("c1",c1)
    printDms("c2",c2)
    printDms("c3",c3)
    printDms("C",c)
    printDms("Y",y)
    printDms("u1",u1)
    printDms("u2",u2)
    printDms("u",u)
    printDms("P",p)
    printDms("R",r)
    printDms("N",n)
    println()
    printDms("Ap1",ap1)
    printDms("tp1",tp1)
    printHms("Awg",awg)
    println()
    printHms("Akg",akg)
    printDms("Rt",rt)
    printKl("Mg",mg)

    printDms("Ap2",ap2)
    printDms("tp2",tp2)
    printHms("Awt",awt)
    printHms("Akt",akt)
    println()
    print("aa",aa)
    print("akp",akp)
    print("a",a)
    print("b",b)
    print("cp",cp)
    print("d",d)
    print("e",e)
    print("ep",ep)
    print("pa",pa)
    print("nhm",nhm)
    print("npr",npr)
    print("Nha",nomor_hari)
    print("Npas",nomor_pasaran)
    print("TGM",tanggal_istiqbal_masehiLT)
    print("BLM",bulan_istiqbal_masehiLT)
    print("THM",tahun_istiqbal_masehiLT)

    if (statusGerhana&&gerhanaMalam){
        println(kt)
        println(tanggal_istiqbal_lokal)
        println("Jenis Gerhana: $jns")
        println("Kecerahan $mg")
        println()
        printHms("Awal Gerhana", awg)
        if (jnsBool) printHms("Awal Total", awt)
        printHms("Puncak Gerhana", pg)
        if (jnsBool) printHms("Akhir Total", akt)
        printHms("Akhir Gerhana", akg)

    }else println(kt)

}