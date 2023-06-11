package awalbulan

import utilitas.dmsKeDesimal
import utilitas.*
import kotlin.math.*


    var tanggal = 0.0
    var bulan = 0.0
    var tahun = 0.0
    var format = "%-25s%-20s%n"
    fun main() {

        //=================== data yang harus diisi =====================\\

        //
        val jah_lintang = -4
        val qoh_lintang = 58
        val ni_lintang = 0
        val lintang: Double = dmsKeDesimal(jah_lintang, qoh_lintang, ni_lintang)
        val jah_bujur = 105
        val qoh_bujur = 12
        val ni_bujur = 0
        val bujur: Double = dmsKeDesimal(jah_bujur, qoh_bujur, ni_bujur)
        val zw = 7.0
        val ketinggian_tempat = 50.0

        //masukan awal bulan yang dicari
        val bulan_hijri_cari = 6.0
        var tahun_hijri_cari = 1443.0
        val bulan_hijri: Double
        if (bulan_hijri_cari == 1.0) {
            bulan_hijri = 12.0
            tahun_hijri_cari -= 1.0
        } else bulan_hijri = bulan_hijri_cari - 1
        val tahun_hijri = tahun_hijri_cari
        val tanggal_hijri = 29.0
        tanggal = 0.0
        bulan = 0.0
        tahun = 0.0
        val julianday: Double = 0.0
        val tanggal_ijtima = tanggal
        println(
            """
    Hisab Awal Bulan Ephemeris Meeus
    
    """.trimIndent()
        )
        val tanggalDataEp = tanggal.toInt()
            .toString() + " - " + namaBulan(bulan.toInt()) + "(" + bulan.toInt() + ")" + " - " + tahun.toInt()
        val tglPerkiraanHijri = tanggal_hijri.toInt()
            .toString() + " - " + namaBulanHijri(bulan_hijri.toInt()) + " (" + bulan_hijri.toInt() + ")" + " - " + tahun_hijri.toInt()
        System.out.printf(format, "Tanggal Data Ephemeris ", "= $tanggalDataEp")
        System.out.printf(format, "Tanggal Hijriah Urfi ", "= $tglPerkiraanHijri")
        val fibTerkecil1: Double
        val fibTerkecil2: Double
        var jamFibTerkecil1 = 0
        var jamFibTerkecil2 = 0


        //mencari fib terkecil
        val gmt10 = 10
        val gmt11 = 11

        //ambil data Matahari
        val alb2: Double = 0.0
        val alb1: Double = 0.0
        val sdo11: Double = 0.0
        val do10: Double = 0.0
        val do11: Double = 0.0
        val e11: Double = 0.0
        val aro10: Double = 0.0
        val aro11: Double = 0.0


        println("\n")
        println("Data Matahari:")
        System.out.printf(format, "ALB jam $jamFibTerkecil1", "= " + dms(alb1))
        System.out.printf(format, "ALB jam $jamFibTerkecil2", "= " + dms(alb2))
        System.out.printf(format, "ARo jam $gmt10", "= " + dms(aro10))
        System.out.printf(format, "ARo jam $gmt11", "= " + dms(aro11))
        System.out.printf(format, "do jam $gmt10", "= " + dms(do10))
        System.out.printf(format, "do jam $gmt11", "= " + dms(do11))
        System.out.printf(format, "sdo jam $gmt11", "= " + dms(sdo11) + " (" + sdo11.toFloat() + ")")
        System.out.printf(format, "Eot jam $gmt11", "= " + dms(e11) + " (" + e11.toFloat() + ")")


        //ambil data bulan
        val elm1: Double = 0.0
        val elm2: Double = 0.0
        val sdc10: Double =0.0
        val arc10: Double =0.0
        val arc11: Double =0.0
        val dc10: Double =0.0
        val dc11: Double =0.0
        val paralax10: Double =0.0
        val fib10: Double = 0.0
        val fib11: Double = 0.0
        println("\n")
        println("Data Bulan:")
        System.out.printf(format, "ELM jam $jamFibTerkecil1", "= " + dms(elm1))
        System.out.printf(format, "ELM jam $jamFibTerkecil2", "= " + dms(elm2))
        System.out.printf(format, "ARc jam $gmt10", "= " + dms(arc10))
        System.out.printf(format, "ARc jam $gmt11", "= " + dms(arc11))
        System.out.printf(format, "dc jam $gmt10", "= " + dms(dc10))
        System.out.printf(format, "dc jam $gmt11", "= " + dms(dc11))
        System.out.printf(format, "sdc jam $gmt10", "= " + dms(sdc10) + " (" + sdc10.toFloat() + ")")
        System.out.printf(format, "Paralaks jam $gmt10", "= " + dms(paralax10) + " (" + paralax10.toFloat() + ")")
        System.out.printf(format, "fib jam $gmt10", "= " + dms(fib10))
        System.out.printf(format, "fib jam $gmt11", "= " + dms(fib11))
        //=============================proses hisab===================================\\
        //Mencari sabaq bulan dan matahari



        val sb = abs(alb1 - alb2)
        val sm = abs(elm1 - elm2)

        // waktu ijtima'
        val saat_itima = jamFibTerkecil1 + (elm1 - alb1) / (sb - sm) + zw
        //        if (saat_itima<0) {
//            tanggal_ijtima = +1;
//            saat_itima=+24; }
        //5

        //qoidah refraksi
        val refraksi_jam_ghurub = 0.575
        //rumus keitnggian ufuk
        val ketinggiian_ufuk = 1.76 * sqrt(ketinggian_tempat) / 60
        val tinggi_matahari_haqiqi = 0 - sdo11 - refraksi_jam_ghurub - ketinggiian_ufuk

        //6
        val sudut_waktu_m = toDeg(
            acos(
                -tan(toRad(lintang)) * tan(toRad(do11)) + sin(
                    toRad(tinggi_matahari_haqiqi)
                ) / cos(toRad(lintang)) / cos(toRad(do11))
            )
        )

        //7
        val koreksi_waktu_daerah = (zw * 15 - bujur) / 15

        //8
        val ghurub = sudut_waktu_m / 15 + (12 - e11) + koreksi_waktu_daerah

        //9
        val asensiorekta_m = aro10 - (aro10 - aro11) * (ghurub % 1)

        //10
        val asensiorekta_b = arc10 - (arc10 - arc11) * (ghurub % 1)

        //11
        val sudut_waktu_b = asensiorekta_m - asensiorekta_b + sudut_waktu_m

        //12
        val deklinasi_m_ghurub = do10 - (do10 - do11) * (ghurub % 1)
        val deklinasi_b_ghurub = dc10 - (dc10 - dc11) * (ghurub % 1)

        //Elongasi Bulan
        val elongasi = toDeg(
            acos(
                sin(toRad(deklinasi_b_ghurub)) * sin(toRad(deklinasi_m_ghurub)) + cos(
                    toRad(deklinasi_b_ghurub)
                ) * cos(toRad(deklinasi_m_ghurub)) * cos(toRad(asensiorekta_b - asensiorekta_m))
            )
        )


        //13
        val tinggi_bulan_hakiki = toDeg(
            asin(
                sin(toRad(lintang)) * sin(toRad(deklinasi_b_ghurub)) + cos(
                    toRad(lintang)
                ) * cos(toRad(deklinasi_b_ghurub)) * cos(toRad(sudut_waktu_b))
            )
        )
        //parallaks
        val hp10 = paralax10 * cos(toRad(tinggi_bulan_hakiki))

        //Refraksi
        val ref = 1.02 / tan(toRad(tinggi_bulan_hakiki + 10.3 / (tinggi_bulan_hakiki + 5.11))) / 60
        //tinggi bulan mar'i
        val tinggi_b_mari = tinggi_bulan_hakiki - hp10 + ref + sdc10 + ketinggiian_ufuk

        //16
        var azimuth_m_ghurub = toDeg(
            atan(
                1 / (-sin(toRad(lintang)) / tan(toRad(sudut_waktu_m)) + cos(
                    toRad(lintang)
                ) * tan(toRad(deklinasi_m_ghurub)) / sin(toRad(sudut_waktu_m)))
            )
        )
        var azimuth_b_ghurub = toDeg(
            atan(
                1 / (-sin(toRad(lintang)) / tan(toRad(sudut_waktu_b)) + cos(
                    toRad(lintang)
                ) * tan(toRad(deklinasi_b_ghurub)) / sin(toRad(sudut_waktu_b)))
            )
        )
        var arah_m = ""
        var arah_b = ""
        var arah_h = ""
        var azimuth_m_barat = 0.0
        if (azimuth_m_ghurub < 0) {
            azimuth_m_barat = azimuth_m_ghurub + 90
            arah_m = "BS"
        } else {
            azimuth_m_ghurub = 90 - azimuth_m_ghurub
            arah_m = "BU"
        }
        var azimuth_b_barat = 0.0
        if (azimuth_b_ghurub < 0) {
            azimuth_b_barat = azimuth_b_ghurub + 90
            arah_b = "BS"
            arah_h = "Selatan Titik Barat"
        } else {
            azimuth_b_ghurub = 90 - azimuth_b_ghurub
            arah_b = "BU"
            arah_h = "Utara Titik Barat"
        }

        //18
        val posisi_hilal = azimuth_m_ghurub - azimuth_b_ghurub
        val lama_hilal = tinggi_b_mari / 15

        //19
        val luas_cahaya_hilal = fib10 - (fib10 - fib11) * (ghurub % 1)

        //20
        val lebar_nur_hilal = sqrt((posisi_hilal.pow(2.0)) + tinggi_b_mari.pow(2.0)) / 15
        var keadaan_h = ""
        //21
        val kemiringan_hilal = toDeg(atan(abs(posisi_hilal) / tinggi_b_mari))
        if (kemiringan_hilal <= 15) keadaan_h =
            "Hilal Terlentang" else if (kemiringan_hilal > 15 && posisi_hilal > 0) keadaan_h =
            "Miring ke Utara" else if (kemiringan_hilal > 15 && posisi_hilal < 0) keadaan_h = "Miring ke Selatan"

        //kesimpulan i'tibar
        var tanggal_awalB = tanggal_ijtima
        var iktibar = ""
        val umur_hilal = ghurub - saat_itima
        if (tinggi_bulan_hakiki > 2 && umur_hilal > 8 || tinggi_bulan_hakiki > 2 && elongasi > 3) {
            iktibar = "Imkan Ru'yah"
            tanggal_awalB++
        } else {
            iktibar = "Istikmal"
            tanggal_awalB = +2.0
        }


        //=================== Kesimpulan =======================\\
        //String format="%-25s%-12s%n";
        System.out.printf(format, "Tanggal data Ephemeris", "= " + tanggal.toInt())
        System.out.printf(format, "Lintang tempat", "= " + dms(lintang))
        System.out.printf(format, "Bujur tempat", "= " + dms(bujur))
        System.out.printf(format, "Jam FIB Terkecil", "= $jamFibTerkecil1")
        println(System.lineSeparator())
        System.out.printf(format, "Eot", "= " + dms(e11))
        System.out.printf(format, "Ketingggian Ufuk", "= " + dms(ketinggiian_ufuk))
        System.out.printf(format, "Sabaq Bulan", "= " + dms(sb))
        System.out.printf(format, "Sabaq Matahari", "= " + dms(sm))
        //System.out.printf(format,"SDC10", "= "+ dms());
        println(System.lineSeparator())
        System.out.printf(format, "Waktu ijtima", "= " + dms(saat_itima))
        System.out.printf(format, "Tinggi Matahari (ho)", "= " + dms(tinggi_matahari_haqiqi))
        System.out.printf(format, "Sudut Waktu Mat (to)", "= " + dms(sudut_waktu_m))
        System.out.printf(format, "Koreksi Waktu D (KWD)", "= " + dms(koreksi_waktu_daerah))
        System.out.printf(format, "Jam Ghurub", "= " + dms(ghurub))
        System.out.printf(format, "Asensio R Matahari (ARo)", "= " + dms(asensiorekta_m))
        System.out.printf(format, "Asensio R Bulan (ARc)", "= " + dms(asensiorekta_b))
        System.out.printf(format, "Sudut Waktu Bul (tc)", "= " + dms(sudut_waktu_b))
        System.out.printf(format, "Deklinasi Matahari (do)", "= " + dms(deklinasi_m_ghurub))
        System.out.printf(format, "Deklinasi Bulan (dc)", "= " + dms(deklinasi_b_ghurub))
        System.out.printf(format, "Elongasi Bulan (el)", "= " + dms(elongasi))
        System.out.printf(format, "Tinggi Hilal Hakiki (hc)", "= " + dms(tinggi_bulan_hakiki))
        System.out.printf(format, "Refraksi (Ref)", "= " + dms(refraksi_jam_ghurub))
        System.out.printf(format, "Tinggi Hilal Mar'i (h'c)", "= " + dms(tinggi_b_mari))
        System.out.printf(format, "Azimuth Matahari (Azo)", "= " + dms(azimuth_m_ghurub))
        System.out.printf(format, "Azimuth Bulan (Azc)", "= " + dms(azimuth_b_ghurub))
        System.out.printf(format, "Posisi Hilal (ph)", "= " + dms(posisi_hilal))
        System.out.printf(format, "Lama hilal Diatas ufuk", "= " + dms(lama_hilal))
        System.out.printf(format, "Luas Cahaya hilal", "= " + String.format("%.6f", luas_cahaya_hilal))
        System.out.printf(format, "Lebar  Hilal (Usbu')", "= " + String.format("%.4f", lebar_nur_hilal))
        System.out.printf(format, "Keadaan Hilal", "= $kemiringan_hilal")
        println(System.lineSeparator())
        println("Kesimpulan pekerjaan")
        println(System.lineSeparator())
        System.out.printf(
            format,
            "Hisab Awal bulan",
            "= " + namaBulanHijri(bulan_hijri_cari.toInt()).toString() + " (" + bulan_hijri_cari.toInt()
                .toString() + ")" + " - " + tahun_hijri.toInt()
        )
        System.out.printf(format, "Adalah Tanggal ", "= " + tanggal_awalB.toInt())
        System.out.printf(format, "I'tibar", "= $iktibar")
        System.out.printf(format, "Ij'tima tanggal", "= " + tanggal_ijtima.toInt())
        System.out.printf(format, "Pukul", "= " + dms(saat_itima))
        System.out.printf(format, "tinggi Hilal Hakiki", "= " + dms(tinggi_bulan_hakiki))
        System.out.printf(format, "tinggi Hilal Mar'i", "= " + dms(tinggi_b_mari))
        System.out.printf(format, "Lama Hilal di atas ufuk", "= " + dms(lama_hilal))
        System.out.printf(format, "Besar cahaya  Hilal", "= " + String.format("%.4f", lebar_nur_hilal) + " Jari")
        System.out.printf(format, "Luas cahaya  Hilal", "= " + String.format("%.2f", luas_cahaya_hilal * 100) + " %")
        System.out.printf(format, "Arah Hilal", "= $arah_h")
        System.out.printf(format, "Elongasi Bulan", "= " + dms(elongasi))
        System.out.printf(format, "Keadaan Hilal", "= $keadaan_h")
        System.out.printf(format, "Azimuth matahari", "= " + dms(azimuth_m_barat))
        System.out.printf(format, "Azimuth bulan", "= " + dms(azimuth_b_barat))
        println(System.lineSeparator())
    }


    fun dms(desimal: Double): String {
        return desKeDms(desimal).dmsss
    }
