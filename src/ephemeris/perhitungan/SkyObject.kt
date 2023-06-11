package ephemeris.perhitungan

class EphemMoon(
        val trueGeocentricLongitude: Double,
        val apparentGeocentricLongitude: Double,
        val apparentGeocentricLatitude: Double,
        val apparentRightAscension: Double,
        val apparentDeclination: Double,
        val trueGeocentricDistance: Double,
        val angularSemiDiameter: Double,
        val equatorialHorizontalParallax: Double,
        val diskIlluminatedFraction: Double,
        val brightLimbAngle: Double,
        val sunGeocentricElongation: Double
) {}

class EphemSun(
        val trueGeocentricLongitude: Double,
        val apparentGeocentricLongitude: Double,
        val geocentricLatitude: Double,
        val apparentRightAscension: Double,
        val apparentDeclination: Double,
        val trueGeocentricDistance: Double,
        val angularSemiDiameter: Double,
        val equationOfTime: Double,
        val equatorialHorizontalParallax: Double,
        val earth_trueObliquity: Double

) {}

// sebagai wadah metode perhitungan yang memuat matahari dan bulan langsung, tidak yang seperti elp2000 (cuman data bulan) 
class EphemSunMoon(

        val sun_trueGeocentricLongitude: Double,
        val sun_apparentGeocentricLongitude: Double,
        val sun_geocentricLatitude: Double,
        val sun_apparentRightAscension: Double,
        val sun_apparentDeclination: Double,
        val sun_trueGeocentricDistance: Double,
        val sun_angularSemiDiameter: Double,
        val sun_equationOfTime: Double,
        val sun_equatorialHorizontalParallax: Double,
        val earth_trueObliquity: Double,

        val moon_trueGeocentricLongitude: Double,
        val moon_apparentGeocentricLongitude: Double,
        val moon_apparentGeocentricLatitude: Double,
        val moon_apparentRightAscension: Double,
        val moon_apparentDeclination: Double,
        val moon_trueGeocentricDistance: Double,
        val moon_angularSemiDiameter: Double,
        val moon_equatorialHorizontalParallax: Double,
        val moon_diskIlluminatedFraction: Double,
        val moon_brightLimbAngle: Double,
        val moon_sunGeocentricElongation: Double


) {

//    public var sun_trueGeocentricLongitude= Double.NaN
//    public var sun_apparentGeocentricLongitude =  Double.NaN
//    public var sun_geocentricLatitude =  Double.NaN
//    public var sun_apparentRightAscension =  Double.NaN
//    public var sun_apparentDeclination =  Double.NaN
//    public var sun_trueGeocentricDistance =  Double.NaN
//    public var sun_angularSemiDiameter =  Double.NaN
//    public var sun_equationOfTime =  Double.NaN
//    public var sun_equatorialHorizontalParallax =  Double.NaN
//    public var earth_trueObliquity =  Double.NaN
//
//    public var moon_trueGeocentricLongitude =  Double.NaN
//    public var moon_apparentGeocentricLongitude =  Double.NaN
//    public var moon_apparentGeocentricLatitude =  Double.NaN
//    public var moon_apparentRightAscension =  Double.NaN
//    public var moon_apparentDeclination =  Double.NaN
//    public var moon_trueGeocentricDistance =  Double.NaN
//    public var moon_angularSemiDiameter =  Double.NaN
//    public var moon_equatorialHorizontalParallax =  Double.NaN
//    public var moon_diskIlluminatedFraction =  Double.NaN
//    public var moon_brightLimbAngle =  Double.NaN
//    public var moon_sunGeocentricElongation =  Double.NaN



}