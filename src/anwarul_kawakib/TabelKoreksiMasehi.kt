package anwarul_kawakib

fun main (){
    var tahun = 1800

    while (true){

        val am = (tahun-1)/100
        val axm = (am/4)
        val bm= 2+axm-am

        val a = tahun/100
        val ax = (a/4)
        val b = 2+ax-a

        if (bm!=b){
            println("$tahun, $b")
        }


        if (tahun==3000) break


        tahun++
    }






}