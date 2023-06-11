package astrologi

private var namaZodiak = arrayListOf("Aries","Taurus","Gemini","Cancer","Leo","Virgo","Libra","Scorpio","Sagitarius","Capricorn","Aquarius","Pisces")
private var namaBuruj = arrayListOf("Haml","Tsur","Jauza'","Sarothon","Asad","Sunbulah","Mizan","Aqrob","Qous","Jadyu","Dalwu","Huut")
private var namaBendaLangit =  arrayListOf("Mars","Venus","Merkurius","Bulan","Matahari","Merkurius","Venus","Mars","Jupiter","Saturnus","Saturnus","Jupiter")
private var namaBendaLangitArab =  arrayListOf("Marikh","Zuhrah","Utarid","Qomar","Syams","Utarid","Zuhrah","Marikh","Musytari","Zuhal","Zuhal","Musytari")
private var jenisTabiat  = arrayListOf("Api","Tanah","Angin","Air","Api","Tanah","Angin","Air","Api","Tanah","Angin","Air")
private var keteranganTabiat  = arrayListOf("","","","","","","","","","","","")

class BurujZodiakMode1(val namaZodiak: String,val namaBuruj:String,val namaBendaLangit:String,val namaBendaLangitArab:String,val jenisTabiat:String,val keteranganTabiat:String)
fun burujZodiakMode1(tanggal:Int,bulan:Int):BurujZodiakMode1{
    var index = 0
    when(bulan){
        3-> when(tanggal){
            in 1..20-> index=12
            in 21..31->index=1
        }
        4-> when(tanggal){
            in 1..19-> index=1
            in 20..30->index=2
        }
        5-> when(tanggal){
            in 1..20-> index=2
            in 21..31->index=3
        }
        6-> when(tanggal){
            in 1..20-> index=3
            in 21..30->index=4
        }
        7-> when(tanggal){
            in 1..22-> index=4
            in 23..31->index=5
        }
        8-> when(tanggal){
            in 1..22-> index=5
            in 23..31->index=6
        }
        9-> when(tanggal){
            in 1..22-> index=6
            in 23..30->index=7
        }
        10-> when(tanggal){
            in 1..22-> index=7
            in 23..31->index=8
        }
        11-> when(tanggal){
            in 1..22-> index=8
            in 23..30->index=9
        }
        12-> when(tanggal){
            in 1..22-> index=9
            in 23..31->index=10
        }
        1-> when(tanggal){
            in 1..19-> index=10
            in 20..31->index=11
        }
        2-> when(tanggal){
            in 1..18-> index=11
            in 19..31->index=12
        }


    }
    val i = index-1

    return BurujZodiakMode1(namaZodiak[i], namaBuruj[i], namaBendaLangit[i], namaBendaLangitArab[i], jenisTabiat[i], keteranganTabiat[i])



}


fun main(){
    val bz = burujZodiakMode1(21,6)
    val hasil = "Zodiak: ${bz.namaBuruj} (${bz.namaZodiak}) \nKawakib: ${bz.namaBendaLangitArab} (${bz.namaBendaLangit}) \nTabiat: ${bz.jenisTabiat}"
    println(hasil)


}