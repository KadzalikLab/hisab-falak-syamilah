package ephemeris
import ephemeris.perhitungan.astroAlgo
import ephemeris.perhitungan.ompec
import utilitas.*

public  class Ephem( julianDay:Double) { // Primary Constructor
    var jd:Double = Double.NaN

    //Secondary Contructor

    constructor(julianDay:Int):this(julianDay.toDouble())
    constructor(tanggal: Int, bulan: Int, tahun: Int, jamDes: Int):this(masehiKeJd(tanggal, bulan, tahun, jamDes))

    private var astroAlgoBoolSun = false
    private var ompecBoolSun = false

    private var astroAlgoBoolMoon = false
    private var ompecBoolMoon = false

    // ini property yang bisa diakses, bila user belum melakukan init maka default valuenya adalah NAN (not a number/bukan sebuah nilai)
    public var sun_trueGeocentricLongitude= Double.NaN
    public var sun_apparentGeocentricLongitude =  Double.NaN
    public var sun_geocentricLatitude =  Double.NaN
    public var sun_apparentRightAscension =  Double.NaN
    public var sun_apparentDeclination =  Double.NaN
    public var sun_trueGeocentricDistance =  Double.NaN
    public var sun_angularSemiDiameter =  Double.NaN
    public var sun_equationOfTime =  Double.NaN
    public var sun_equatorialHorizontalParallax =  Double.NaN
    public var earth_trueObliquity =  Double.NaN

    public var moon_trueGeocentricLongitude =  Double.NaN
    public var moon_apparentGeocentricLongitude =  Double.NaN
    public var moon_apparentGeocentricLatitude =  Double.NaN
    public var moon_apparentRightAscension =  Double.NaN
    public var moon_apparentDeclination =  Double.NaN
    public var moon_trueGeocentricDistance =  Double.NaN
    public var moon_angularSemiDiameter =  Double.NaN
    public var moon_equatorialHorizontalParallax =  Double.NaN
    public var moon_diskIlluminatedFraction =  Double.NaN
    public var moon_brightLimbAngle =  Double.NaN
    public var moon_sunGeocentricElongation =  Double.NaN

    init { /// yang pertama kali dijalankan saat object ephem dipanggil
        jd = julianDay
        fillAlgorithm() //ngisi property

    }



    fun setMetodeHisabBulan(): HisabBulan { return HisabBulan() }
    inner class HisabBulan(){
        fun astroAlgo(){
            astroAlgoBoolMoon = true
            ompecBoolMoon = false
        }
        fun ompec(){
            astroAlgoBoolMoon = false
            ompecBoolMoon = true
        }



    }
    fun setMetodeHisabMatahari(): HisabMatahari { return HisabMatahari() }
    inner class HisabMatahari(){
        fun astroAlgo(){
            astroAlgoBoolSun = true
            ompecBoolSun = false
            fillAlgorithm() //ngisi property
        }
        fun ompec(){
            astroAlgoBoolSun = false
            ompecBoolSun = true
            fillAlgorithm() //ngisi property

        }



    }

    private fun fillAlgorithm() {

        // cek, algoritma mana yang dipakai untuk data matahari
        when{
            astroAlgoBoolSun-> fillAstroALgoSun()
            ompecBoolSun->fillOmpecSun()
            else -> fillAstroALgoSun()
        }
        // cek, algoritma mana yang dipakai untuk data bulan
        when {

            astroAlgoBoolMoon-> fillAstroALgoMoon()
            ompecBoolMoon -> println()
            else -> fillAstroALgoMoon()
        }



    }

    private fun fillAstroALgoSun(){
        val ep = astroAlgo(jd)
        sun_trueGeocentricLongitude = ep.sun_trueGeocentricLongitude
        sun_apparentGeocentricLongitude = ep.sun_apparentGeocentricLongitude
        sun_geocentricLatitude=ep.sun_geocentricLatitude
        sun_apparentRightAscension = ep.sun_apparentRightAscension
        sun_apparentDeclination = ep.sun_apparentDeclination
        sun_trueGeocentricDistance = ep.sun_trueGeocentricDistance
        sun_angularSemiDiameter = ep.sun_angularSemiDiameter
        sun_equationOfTime = ep.sun_equationOfTime
        sun_equatorialHorizontalParallax = ep.sun_equatorialHorizontalParallax
        earth_trueObliquity = ep.earth_trueObliquity

    }
    private fun fillAstroALgoMoon(){
        val ep = astroAlgo(jd)
        moon_trueGeocentricLongitude =  ep.moon_trueGeocentricLongitude
        moon_apparentGeocentricLongitude =  ep.moon_apparentGeocentricLongitude
        moon_apparentGeocentricLatitude =  ep.moon_apparentGeocentricLatitude
        moon_apparentRightAscension =  ep.moon_apparentRightAscension
        moon_apparentDeclination =  ep.moon_apparentDeclination
        moon_trueGeocentricDistance =  ep.moon_trueGeocentricDistance
        moon_angularSemiDiameter =  ep.moon_angularSemiDiameter
        moon_equatorialHorizontalParallax =  ep.moon_equatorialHorizontalParallax
        moon_diskIlluminatedFraction =  ep.moon_diskIlluminatedFraction
        moon_brightLimbAngle =  ep.moon_brightLimbAngle
        moon_sunGeocentricElongation =  ep.moon_sunGeocentricElongation
    }



    private fun fillOmpecSun(){
        val ep = ompec(jd)
        sun_trueGeocentricLongitude = ep.sun_trueGeocentricLongitude
        sun_apparentGeocentricLongitude = ep.sun_apparentGeocentricLongitude
        sun_geocentricLatitude=ep.sun_geocentricLatitude
        sun_apparentRightAscension = ep.sun_apparentRightAscension
        sun_apparentDeclination = ep.sun_apparentDeclination
        sun_trueGeocentricDistance = ep.sun_trueGeocentricDistance
        sun_angularSemiDiameter = ep.sun_angularSemiDiameter
        sun_equationOfTime = ep.sun_equationOfTime
        sun_equatorialHorizontalParallax = ep.sun_equatorialHorizontalParallax
        earth_trueObliquity = ep.earth_trueObliquity

    }



}

