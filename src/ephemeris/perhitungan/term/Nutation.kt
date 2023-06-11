package ephemeris.perhitungan.term

import utilitas.mod
import utilitas.toRad
import kotlin.math.*

public  class Nutation(private val t:Double){
    public var nutationInLongitude = Double.NaN
    public var obliquityOfEcliptic = Double.NaN

    init {
        calculateNutation()
    }
    
    private fun calculateNutation(){
        val d = toRad(mod(297.85036 + 445267.11148 * t - 0.0019142 * t * t + t * t * t / 189474,360) ) //Mean elongation of the Moon from the Sun

        val m = toRad(mod(357.52772 + 35999.05034 * t - 0.0001603 * t * t - t * t * t / 300000,360) ) //Mean anomaly of the Sun (Earth)

        val m1 = toRad(mod(134.96298 + 477198.867398 * t + 0.0086972 * t * t + t * t * t / 56250,360) ) //Mean anomaly of the Moon

        val f = toRad(mod(93.27191 + 483202.017538 * t - 0.0036825 * t * t + t * t * t / 327270,360)) //Moon's argument of latitude

        var omega1: Double = mod(125.04452 - 1934.136261 * t + 0.0020708 * t * t + t * t * t / 450000,360) //Longitude of ascending node of the Moon's mean orbit

        val omega = toRad(omega1)

        //nutation in longitude

        //nutation in longitude
        var deltaPsi = 0.0
        //(koefisien1+koefisien2*t)*sin(D*d+M*m+M'*m1+F*f+OMEGA*omega)
        //(koefisien1+koefisien2*t)*sin(D*d+M*m+M'*m1+F*f+OMEGA*omega)
        deltaPsi += (-171996 + -174.2 * t) * sin(0 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-13187 + -1.6 * t) * sin(-2 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-2274 + -0.2 * t) * sin(0 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (2062 + 0.2 * t) * sin(0 * d + 0 * m + 0 * m1 + 0 * f + 2 * omega)
        deltaPsi += (1426 + -3.4 * t) * sin(0 * d + 1 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (712 + 0.1 * t) * sin(0 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-517 + 1.2 * t) * sin(-2 * d + 1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-386 + -0.4 * t) * sin(0 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (217 + -0.5 * t) * sin(-2 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (129 + 0.1 * t) * sin(-2 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (63 + 0.1 * t) * sin(0 * d + 0 * m + 1 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-58 + -0.1 * t) * sin(0 * d + 0 * m + -1 * m1 + 0 * f + 1 * omega)
        deltaPsi += (17 + -0.1 * t) * sin(0 * d + 2 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-16 + 0.1 * t) * sin(-2 * d + 2 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-301 + 0 * t) * sin(0 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-158 + 0 * t) * sin(-2 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (123 + 0 * t) * sin(0 * d + 0 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (63 + 0 * t) * sin(2 * d + 0 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-59 + 0 * t) * sin(2 * d + 0 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-51 + 0 * t) * sin(0 * d + 0 * m + 1 * m1 + 2 * f + 1 * omega)
        deltaPsi += (48 + 0 * t) * sin(-2 * d + 0 * m + 2 * m1 + 0 * f + 0 * omega)
        deltaPsi += (46 + 0 * t) * sin(0 * d + 0 * m + -2 * m1 + 2 * f + 1 * omega)
        deltaPsi += (-38 + 0 * t) * sin(2 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-31 + 0 * t) * sin(0 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaPsi += (29 + 0 * t) * sin(0 * d + 0 * m + 2 * m1 + 0 * f + 0 * omega)
        deltaPsi += (29 + 0 * t) * sin(-2 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaPsi += (26 + 0 * t) * sin(0 * d + 0 * m + 0 * m1 + 2 * f + 0 * omega)
        deltaPsi += (-22 + 0 * t) * sin(-2 * d + 0 * m + 0 * m1 + 2 * f + 0 * omega)
        deltaPsi += (21 + 0 * t) * sin(0 * d + 0 * m + -1 * m1 + 2 * f + 1 * omega)
        deltaPsi += (16 + 0 * t) * sin(2 * d + 0 * m + -1 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-15 + 0 * t) * sin(0 * d + 1 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-13 + 0 * t) * sin(-2 * d + 0 * m + 1 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-12 + 0 * t) * sin(0 * d + -1 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (11 + 0 * t) * sin(0 * d + 0 * m + 2 * m1 + -2 * f + 0 * omega)
        deltaPsi += (-10 + 0 * t) * sin(2 * d + 0 * m + -1 * m1 + 2 * f + 1 * omega)
        deltaPsi += (-8 + 0 * t) * sin(2 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (7 + 0 * t) * sin(0 * d + 1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-7 + 0 * t) * sin(-2 * d + 1 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-7 + 0 * t) * sin(0 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-7 + 0 * t) * sin(2 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (6 + 0 * t) * sin(2 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (6 + 0 * t) * sin(-2 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaPsi += (6 + 0 * t) * sin(-2 * d + 0 * m + 1 * m1 + 2 * f + 1 * omega)
        deltaPsi += (-6 + 0 * t) * sin(2 * d + 0 * m + -2 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-6 + 0 * t) * sin(2 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (5 + 0 * t) * sin(0 * d + -1 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-5 + 0 * t) * sin(-2 * d + -1 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (-5 + 0 * t) * sin(-2 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-5 + 0 * t) * sin(0 * d + 0 * m + 2 * m1 + 2 * f + 1 * omega)
        deltaPsi += (4 + 0 * t) * sin(-2 * d + 0 * m + 2 * m1 + 0 * f + 1 * omega)
        deltaPsi += (4 + 0 * t) * sin(-2 * d + 1 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (4 + 0 * t) * sin(0 * d + 0 * m + 1 * m1 + -2 * f + 0 * omega)
        deltaPsi += (-4 + 0 * t) * sin(-1 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-4 + 0 * t) * sin(-2 * d + 1 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-4 + 0 * t) * sin(1 * d + 0 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (3 + 0 * t) * sin(0 * d + 0 * m + 1 * m1 + 2 * f + 0 * omega)
        deltaPsi += (-3 + 0 * t) * sin(0 * d + 0 * m + -2 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-3 + 0 * t) * sin(-1 * d + -1 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-3 + 0 * t) * sin(0 * d + 1 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-3 + 0 * t) * sin(0 * d + -1 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-3 + 0 * t) * sin(2 * d + -1 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-3 + 0 * t) * sin(0 * d + 0 * m + 3 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-3 + 0 * t) * sin(2 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi /= 10000.0
        val deltaPsiDeg = deltaPsi / 3600

        //Mean Obliquity Of Ecliptic

        //Mean Obliquity Of Ecliptic
        val u = t / 100
        val epsilonZero = 23 + 26.toDouble() / 60 + 21.448 / 3600 + (-4680.93 * u - 1.55 * u * u + 1999.25 * u * u * u - 51.38 * u * u * u * u - 249.67 * u * u * u * u * u - 39.05 * u * u * u * u * u * u + 7.12 * u * u * u * u * u * u * u + 27.87 * u * u * u * u * u * u * u * u + 5.79 * u * u * u * u * u * u * u * u * u + 2.45 * u * u * u * u * u * u * u * u * u * u) / 3600

        //Nutation In Obliquity

        //Nutation In Obliquity
        var deltaEpsilon = 0.0
        deltaEpsilon += (92025 + 8.9 * t) * cos(0 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (5736 + -3.1 * t) * cos(-2 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (977 + -0.5 * t) * cos(0 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-895 + 0.5 * t) * cos(0 * d + 0 * m + 0 * m1 + 0 * f + 2 * omega)
        deltaEpsilon += (54 + -0.1 * t) * cos(0 * d + 1 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaEpsilon += (224 + -0.6 * t) * cos(-2 * d + 1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (129 + -0.1 * t) * cos(0 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-95 + 0.3 * t) * cos(-2 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-7 + 0 * t) * cos(0 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaEpsilon += (200 + 0 * t) * cos(0 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-70 + 0 * t) * cos(-2 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-53 + 0 * t) * cos(0 * d + 0 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-33 + 0 * t) * cos(0 * d + 0 * m + 1 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (26 + 0 * t) * cos(2 * d + 0 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (32 + 0 * t) * cos(0 * d + 0 * m + -1 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (27 + 0 * t) * cos(0 * d + 0 * m + 1 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-24 + 0 * t) * cos(0 * d + 0 * m + -2 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (16 + 0 * t) * cos(2 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (13 + 0 * t) * cos(0 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-12 + 0 * t) * cos(-2 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-10 + 0 * t) * cos(0 * d + 0 * m + -1 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-8 + 0 * t) * cos(2 * d + 0 * m + -1 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (7 + 0 * t) * cos(-2 * d + -2 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (9 + 0 * t) * cos(0 * d + 1 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (7 + 0 * t) * cos(-2 * d + 0 * m + 1 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (6 + 0 * t) * cos(0 * d + -1 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (5 + 0 * t) * cos(2 * d + 0 * m + -1 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * cos(2 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-3 + 0 * t) * cos(0 * d + 1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (3 + 0 * t) * cos(0 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (3 + 0 * t) * cos(2 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-3 + 0 * t) * cos(-2 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-3 + 0 * t) * cos(-2 * d + 0 * m + 1 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * cos(2 * d + 0 * m + -2 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * cos(2 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * cos(-2 * d + -1 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * cos(-2 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * cos(0 * d + 0 * m + 2 * m1 + 2 * f + 1 * omega)
        deltaEpsilon /= 10000.0
        val deltaEpsilon_d = deltaEpsilon / 3600


        nutationInLongitude = deltaPsiDeg
        obliquityOfEcliptic = epsilonZero + deltaEpsilon_d // Obliquity Of Ecliptic


    }
    
}