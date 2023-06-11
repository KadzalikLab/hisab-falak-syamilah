package inkasyaf

fun main (){
    val tahun = 2409
    var kabisat = false
    var if100 = false
    var if400 = false
    var kesimpulan=""
    var layer4="Apakah tahun Habis dibagi 4 = "
    var layer100="Apakah tahun Habis dibagi 100 = "
    var layer400="Apakah tahun Habis dibagi 400 = "

    if (habisDibagi(tahun,4)){
        layer4+="Ya"
        if (habisDibagi(tahun,100)){
            if100=true
            layer100+="Ya"
            if (habisDibagi(tahun,400)){
                if400=true
                kabisat = true
                layer400+="Ya"
            }else {
                if400=true
                layer400+="Tidak"
                kabisat=false

            }
        }else {
            if100=true
            layer100+="Tidak"

            kabisat = true
        }
    }else {

        layer4+="Tidak"
    }

    kesimpulan=if (kabisat)"Kabisat" else "Basitoh"
    println(layer4)
    if (if100)println(layer100)
    if (if400) println(layer400)
    println(kesimpulan)
    println("Kabisat = $kabisat")

}



fun habisDibagi(x:Int,pembagi:Int):Boolean{
    return x%pembagi==0
}