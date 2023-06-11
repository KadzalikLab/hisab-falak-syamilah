package utilitas
import kotlin.math.abs

var format = "%-25s%5s%n"
fun printDms(x:Double){
    println(desKeDms(x).dmsss)
}
fun printHms(x:Double){
    println(desKeDms(x).hmsss)
}

fun printKl(text:String,x:Double){
    println("$text  : ${dispCal(x)}")
}
fun printKl(text:String,x:Int){
    println("$text  : ${dispCal(x.toDouble())}")
}

fun printDms(text:String,x:Double){
    println("$text : "+desKeDms(x).dmsss)
}
fun printHms(text:String,x:Double){
    println("$text "+desKeDms(x).hmsss)
}

fun print(teks:String, value:Int){
    println("$teks : $value")
}

fun print(teks:String, value:Double){
    println("$teks : $value")

}

//Ngeprint nilai doube sesuai yang tampil dalam kalkulator ilmiah ( 10 digit)
fun dispCal(nilai:Double):String{


    //Mencari panjang digit sebuah nilai
    val splitter = nilai.toString().split("\\.".toRegex()).toTypedArray()
    val i = splitter[0].length // Before Decimal Count
    val f = splitter[1].length // After  Decimal Count
    val idanf = i+f
    var t = 0

    if (idanf>10){
        while (true){
            if ((i+t)==10)break
            t++
        } } else t = f

    val negatif = nilai<0
    val n = abs(nilai)
    val integerN = n.toInt()
    val fracN = frac(n)

    if (negatif) t++
    val p = String.format("%."+t+"f", fracN).substringAfter(".")
    var hasil = "$integerN.$p"
    if (negatif) hasil = "-$hasil"

    return hasil}

fun dispCal(teks:String, nilai:Double){
    println("$teks  :${dispCal(nilai)}")
}