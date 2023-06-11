package astrologi

import utilitas.namaHari
import utilitas.namaPasaran
import utilitas.nomorHari
import utilitas.nomorPasaran

fun neptuhari(nomorhari: Int): Int {
    var neptuhari = 0

    if (nomorhari == 1) neptuhari = 5
    else if (nomorhari == 2) neptuhari = 4
    else if (nomorhari == 3) neptuhari = 3
    else if (nomorhari == 4) neptuhari = 7
    else if (nomorhari == 5) neptuhari = 8
    else if (nomorhari == 6) neptuhari = 6
    else if (nomorhari == 7 || nomorhari == 0) neptuhari = 9
    return neptuhari
}

fun neptupasaran(nomorpasaran: Int): Int {
    var neptuPasaran = 0
    if (nomorpasaran == 1) neptuPasaran = 5
    else if (nomorpasaran == 2) neptuPasaran = 9
    else if (nomorpasaran == 3) neptuPasaran = 7
    else if (nomorpasaran == 4) neptuPasaran = 4
    else if (nomorpasaran == 5 || nomorpasaran == 0) neptuPasaran = 8
    return neptuPasaran
}

class JodohWeton (val onone:String, val maknane:String, val neptuLk:Int, val neptuPr:Int, val harpasLk:String, val harpasPr:String, val hasilHitung:Int)

fun hisabJodohWeton(jdCowok:Double, jdCewek:Double):JodohWeton{
    val nomorHariLk = nomorHari(jdCowok)
    val nomorPasaranLk = nomorPasaran(jdCowok)
    val harpasLk = "${namaHari(jdCowok)} ${namaPasaran(jdCowok)}"

    val nomorHariPr = nomorHari(jdCewek)
    val nomorPasaranPr = nomorPasaran(jdCewek)
    val harpasPr = "${namaHari(jdCewek)} ${namaPasaran(jdCewek)}"



    val neptuLk = neptuhari(nomorHariLk)+ neptupasaran(nomorPasaranLk)
    val neptuPr = neptuhari(nomorHariPr)+ neptupasaran(nomorPasaranPr)
    val totalNeptu = neptuLk+neptuPr
    val hasil:Int
    val mahfudz1 = totalNeptu%10
    hasil = if (mahfudz1>7) totalNeptu%7
    else mahfudz1

    var onone = ""
    var maknane=""
    when(hasil){
        1-> {
            onone="Waseno Segoro"
            maknane="Menjadi pasangan yang besar wibawanya, mempunyai budi pekerti yang luas, sabar dan pemaaf; Jodoh"
        }
        2-> {
            onone="Tunggak Semi"
            maknane="Rezekinya mudah dan melimpah; Jodoh"
        }
        3-> {
            onone="Satrio Wibowo"
            maknane="Mendapat derajat yang tinggi dan kemulian; Jodoh"
        }
        4-> {
            onone="Sumur Sanebo"
            maknane="Banyak yang datang untuk berguru, dihormati manusia; Jodoh"
        }
        5-> {
            onone="Satrio Wirang"
            maknane="Mengalami dukcita dan masalah yang memalukan; Tidak Jodoh" +
                    "" +
                    "Untuk menghindarinya disyarati:" +
                    "Sebelum pernikahan salah satu pengantin menyembelih ayam"
        }
        6-> {
            onone="Bumi Kapethak"
            maknane="Banyak mengalami kesedihan, tetapi menjadi pasangan yang tabah dan pekerja keras; Tidak Jodoh"+
                    "" +
                    "Untuk menghindarinya disyarati:" +
                    "Sebelum pernikahan salah satu pengantin menanam tanah"


        }

        7,0-> {
            onone="Labu Kasebul Angin"
            maknane="Mengalami duka, tidak pernah tercapai apa yang dicita-citakan; Tidak Jodoh"+
                    "" +
                    "Untuk menghindarinya disyarati:" +
                    "Sebelum pernikahan salah satu pengantin menghambur-hamburkan tanah"
        }
    }



    return JodohWeton(onone, maknane, neptuLk, neptuPr, harpasLk, harpasPr, hasil)


}

