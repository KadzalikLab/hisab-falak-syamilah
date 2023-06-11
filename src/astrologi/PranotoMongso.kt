package astrologi

class PmCal(val tanggal:Int, val nomorBulan:Int,val umurBulan:Int, val namaBulan:String,val namaAgraris:String, val namaMusimUtama:String,val namaMusimRinci:String){
}

private var namaBulan = arrayListOf<String>("Kaso","Karo","Katelu","Kapat","Kalimo","Kanem","Kapitu","Kawolu","Kasongo","Kasedoso","Dasto","Sodo")
private var namaAgraris = arrayListOf("Kartika","Pusa","Manggasari","Sitra","Manggakala","Naya","Palguna","Wisaka","Jita","Srawana","Padrawana","Asuji")
private var namaMusimUtama= arrayListOf<String>("Katigo","Katigo","Katigo","Katigo","Labuh","Labuh","Rendheng","Rendheng","Rendheng","Maréng","Maréng","Maréng")
private var namaRinciMusimUtama= arrayListOf("Padang","Paceklik","Semplah","Semplah","Semplah","Udan","Udan","Pengarep-arep","Pengarep-arep","Pengarep-arep","Panen","Padang")
private var umurBulan = arrayListOf<Int>(41,24,24,25,27,43,42,26,25,23,24,41)
fun pranotoMongso(tglMasehi:Int, blnMasehi:Int,thnMasehi: Int): PmCal {
    var tanggalPm = 0
    var index=0
    val kabisat = isKabisat(thnMasehi)

    when (blnMasehi){
        6 -> when (tglMasehi){
            in 1..21-> {
                index=12
                if(!kabisat)tanggalPm=tglMasehi+20
                else {
                    umurBulan[11] = 42; tanggalPm=tglMasehi+21}
            }
            in 22..30->{ index=1; tanggalPm = tglMasehi-21 }
        }

        7-> {index=1; tanggalPm=tglMasehi+9}

        8-> when (tglMasehi) {
            1 -> {index=1; tanggalPm=tglMasehi+40}
            in 2..25 -> {index=2; tanggalPm=tglMasehi-1 }
            in 26..31 -> {index=3; tanggalPm=tglMasehi-25}
        }
        9 -> when (tglMasehi){
            in 1..18-> {index=3; tanggalPm=tglMasehi+6}
            in 19..30->{ index=4; tanggalPm = tglMasehi-18 }
        }
        10 -> when (tglMasehi){
            in 1..13-> {index=4; tanggalPm=tglMasehi+12}
            in 14..31->{ index=5; tanggalPm = tglMasehi-13 }
        }

        11 -> when (tglMasehi){
            in 1..9-> {index=5; tanggalPm=tglMasehi+18} //jarak tgl terakhir sampai umur bulan
            in 10..30->{ index=6; tanggalPm = tglMasehi-9 } // mulai tgl dikurang tgl yang telah berlalu
        }
        12 -> when (tglMasehi){
            in 1..22-> {index=6; tanggalPm=tglMasehi+21} //jarak tgl terakhir sampai umur bulan
            in 23..31->{ index=7; tanggalPm = tglMasehi-22 } // mulai tgl dikurang tgl yang telah berlalu
        }

        1 ->{index=7; tanggalPm=tglMasehi+9}



        2 -> when (tglMasehi){
            in 1..2-> {index=7; tanggalPm=tglMasehi+40}
            in 3..28->{ index=8; tanggalPm = tglMasehi-2 }
            29->{ index=9; tanggalPm = tglMasehi-28 }
        }



        3 -> if (!kabisat)when (tglMasehi){
            in 1..25-> {index=9; tanggalPm=tglMasehi}
            in 26..31->{ index=10; tanggalPm = tglMasehi-25 }
        }else when (tglMasehi){
            in 1..24-> {index=9; tanggalPm=tglMasehi+1}
            in 25..31->{ index=10; tanggalPm = tglMasehi-24 }
        }

        4 -> if (!kabisat) when (tglMasehi){
            in 1..17-> {index=10; tanggalPm=tglMasehi+6}
            in 18..30->{ index=11; tanggalPm = tglMasehi-17 }
        }else when (tglMasehi){
            in 1..16-> {index=10; tanggalPm=tglMasehi+7}
            in 17..30->{ index=11; tanggalPm = tglMasehi-16 }
        }

        5 -> if (!kabisat) when (tglMasehi){
            in 1..11-> {index=11; tanggalPm=tglMasehi+13}
            in 12..31->{ index=12; tanggalPm = tglMasehi-11 }
        }else when (tglMasehi){
            in 1..10-> {index=11; tanggalPm=tglMasehi+14}
            in 11..31->{ index=12; tanggalPm = tglMasehi-10 }
        }



    }

    val nomorBulanPm=index
    val i = index-1



    return PmCal(tanggalPm, nomorBulanPm, umurBulan[i], namaBulan[i], namaAgraris[i], namaMusimUtama[i], namaRinciMusimUtama[i])
}


fun isKabisat(tahun:Int):Boolean{

    return tahun%4 == 0 && (tahun%100 != 0 || tahun%400 == 0)
}


fun main(){
    val tanggal = 8
    val bulan = 9
    val tahun = 2021
    val tglPm= pranotoMongso(tanggal, bulan, tahun).tanggal
    val namaBulan = pranotoMongso(tanggal, bulan, tahun).namaBulan
    val bagianMusim = pranotoMongso(tanggal, bulan, tahun).namaMusimUtama
    val agraris = pranotoMongso(tanggal, bulan, tahun).namaAgraris
    val bagianMusimRinci = pranotoMongso(tanggal, bulan, tahun).namaMusimRinci
    val umurBulan = pranotoMongso(tanggal, bulan, tahun).umurBulan
    println(" $tanggal ${utilitas.namaBulan(bulan)} $tahun = $tglPm  $namaBulan  ($agraris), Mongso: $bagianMusim $bagianMusimRinci")





}