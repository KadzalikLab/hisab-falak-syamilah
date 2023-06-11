package ephemeris.perhitungan


import ephemeris.perhitungan.term.Nutation
import prinKabeh
import utilitas.desKeDms
import utilitas.mod
import utilitas.toDeg
import utilitas.toRad
import kotlin.math.*

var daftar:ArrayList<Double> = ArrayList<Double>()
fun astroAlgo(julianDay: Double): EphemSunMoon {

    val t = (julianDay - 2451545) / 36525
    val tau = t / 10
    val nutasi = Nutation(t)
    val deltaPsi = nutasi.nutationInLongitude
    val epsilon = nutasi.obliquityOfEcliptic
    val epsilonRad = toRad(epsilon)
    val meanLongMoon = mod(218.3164591 + 481267.88134236 * t - 0.0013268 * t * t + t * t * t / 538841 - t * t * t * t / 65194000, 360)    //bujur rata-rata bulan
    val d = mod(297.8502042 + 445267.1115168 * t - 0.00163 * t * t + t * t * t / 545868 - t * t * t * t / 113065000, 360)
    val m = mod(357.5291092 + 35999.0502909 * t - 0.0001536 * t * t + t * t * t / 24490000, 360)
    val ma = mod(134.9634114 + 477198.8676313 * t + 0.008997 * t * t + t * t * t / 69699 - t * t * t * t / 14712000, 360)
    val f = mod(93.2720993 + 483202.0175273 * t - 0.0034029 * t * t - t * t * t / 3526000 + t * t * t * t / 863310000, 360)
    //elongsi rata2 bulan
    val d_r = toRad(d)
    //anomali rata2 matahari
    val m_r = toRad(m)
    //anomali rata2 bulan
    val ma_r = toRad(ma)
    //Argumen bujur bulan
    val f_r = toRad(f)


    val e = 1 - 0.002516 * t - 0.0000074 * t * t
    val argumenA1 = mod(119.75 + 131.849 * t, 360)
    val argumenA2 = mod(53.09 + 479264.29 * t, 360)
    val argumenA3 = mod(313.45 + 481266.484 * t, 360)
    val a1_r = toRad(argumenA1)
    val a2_r = toRad(argumenA2)
    val a3_r = toRad(argumenA3)
    val longitudeMoon = calculateMoonGeoLong(d_r, m_r, ma_r, f_r, e) //hasil dari perhitungan suku periodik bujur bulan (Lambda)
    val meanlongMoonRad = toRad(meanLongMoon)
    val longMoonCorrection = (longitudeMoon + 3958 * sin(a1_r) + 1962 * sin(toRad(meanLongMoon) - f_r) + 318 * sin(a2_r)) / 1000000
    val trueLongitudeMoon = mod(meanLongMoon + longMoonCorrection, 360) // bujur bulan yang sesungguhnya (bukan rata-rata)
    val apparentLongitudeMoon = mod(trueLongitudeMoon + deltaPsi, 360) // bujur bulan sejati yang sudah terkoreksi cahaya pembelokan
    val apparentLongitudeMoonRad = toRad(apparentLongitudeMoon)

    val bMoon = calculateMoonGeoLat(d_r, m_r, ma_r, f_r, e) // hasil dari perhitungan suku periodik lintang bulan (Beta)
    val latitudeMoon = (bMoon - 2235 * sin(meanlongMoonRad) + 382 * sin(a3_r) + 175 * sin(a1_r - f_r) + 175 * sin(a1_r + f_r) + 127 * sin(meanlongMoonRad - ma_r) - 115 * sin(meanlongMoonRad + ma_r)) / 1000_000
    val latitudeMoonRad = toRad(latitudeMoon)

    val moonEarthCorrection = calculateMoonGeoDistance(d_r, m_r, ma_r, f_r, e) // hasil dari perhitungan suku koreksi jarak bulan (R)
    val moonEarthDistance = 385000.56 + moonEarthCorrection // dalam satuan kilometer
    val horizontalParallaxMoon = toDeg(asin(6378.14 / moonEarthDistance)) // equatorial horizontal parallax
    val semiDiameterMoon = 358473400 / (moonEarthDistance * 3600) // angular moon semidiameter


    var apparentRightAscensionMoon = (toDeg(atan2(sin(apparentLongitudeMoonRad) * cos(epsilonRad) - tan(latitudeMoonRad) * sin(epsilonRad), cos(apparentLongitudeMoonRad))))
    apparentRightAscensionMoon = mod(apparentRightAscensionMoon, 360)

    val apparentDeclinationMoon = toDeg(asin(sin(latitudeMoonRad) * cos(epsilonRad) + cos(latitudeMoonRad) * sin(epsilonRad) * sin(apparentLongitudeMoonRad)))
    val apprDecMoonRad = toRad(apparentDeclinationMoon)


    // Matahari
    val lSun = calculateEarthHelioLongitude(tau)
    val theta = mod(lSun + 180, 360)
    val deltaTheta = -0.09033 / 3600
    val trueLongitudeSun = mod(theta + deltaTheta, 360)

    val sunEarthDistance = calculateEarthRadiusVector(tau)
    val sunEarthDistanceAU = 149598000 * sunEarthDistance

    val lambda1 = mod(theta - 1.397 * longMoonCorrection - 0.00031 * longMoonCorrection * longMoonCorrection, 360)
    val lambda1Rad = toRad(lambda1)

    val bSun = calculateEarthHeliocentricLatitude(tau)
    val beta = -bSun
    val deltaBeta = 0.03916 * (cos(lambda1Rad) - sin(lambda1Rad))
    val latitudeSun = beta + deltaBeta
    val latitudeSunRad = toRad(latitudeSun / 3600)

    val abberration = -20.4898 / (3600 * sunEarthDistance)
    val apparentLongitudeSun = mod(trueLongitudeSun + deltaPsi + abberration, 360)
    val apprLongSunRad = toRad(apparentLongitudeSun)

    val semiDiameterSun = (959.63 / 3600) / sunEarthDistance

    val apparentRightAscensionSun = mod(toDeg(atan2(sin(apprLongSunRad) * cos(epsilonRad) - tan(latitudeSunRad) * sin(epsilonRad), cos(apprLongSunRad))), 360)
    val apparentDeclinationSun = toDeg(asin(sin(latitudeSunRad) * cos(epsilonRad) + cos(latitudeSunRad) * sin(epsilonRad) * sin(apprLongSunRad)))
    val apprDecSunRad = toRad(apparentDeclinationSun)
    val y_num = cos(apprDecSunRad) * sin(toRad(apparentRightAscensionSun - apparentRightAscensionMoon))
    val x_num = sin(apprDecSunRad) * cos(apprDecMoonRad) - cos(toRad(apparentDeclinationSun)) * sin(apprDecMoonRad) * cos(toRad(apparentRightAscensionSun - apparentRightAscensionMoon))
    var moonBrightLimb = toDeg(atan2(y_num, x_num))
    moonBrightLimb = mod(moonBrightLimb, 360)
    val sudutFai = acos(sin(apprDecMoonRad) * sin(apprDecSunRad)  + cos(apprDecMoonRad)* cos(apprDecSunRad)*cos(toRad(apparentRightAscensionMoon - apparentRightAscensionSun)))
    val sudutFase = atan2(sunEarthDistanceAU * sin(sudutFai), moonEarthDistance - sunEarthDistanceAU * cos(sudutFai))
    val moonDiskIlluminatedFraction = (1 + cos(sudutFase)) / 2
    var L0 = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau.pow(2.0) + tau.pow(3.0) / 49931 - tau.pow(4.0) / 15300 - tau.pow(5.0) / 2000000
    L0 = mod(L0, 360)
    var eot = (L0 - 0.0057183 - apparentRightAscensionSun + deltaPsi * cos(epsilonRad))
    val kd = abs(eot)*4
    if (kd<20) eot /= 15
    else if (kd>=20 && eot>0) eot = eot/15-24
    else if (kd>=20 && eot<0) eot = eot/15+24
    else eot /= 15
    val horizontalParallaxSun = toDeg(asin(sin(toRad(8.794 / 3600)) / sunEarthDistanceAU))
    val moonSunGeoElongation = toDeg(acos(sin(apprDecSunRad) * sin(apprDecMoonRad) + cos(apprDecSunRad) * cos(apprDecMoonRad) * cos(toRad(apparentRightAscensionSun - apparentRightAscensionMoon))))

    val kabeh = listOf<Double>(trueLongitudeSun,
        apparentLongitudeSun,
        latitudeSun,
        apparentRightAscensionSun,
        apparentDeclinationSun,
        sunEarthDistanceAU,
        semiDiameterSun,
        eot,
        horizontalParallaxSun,
        epsilon,

        trueLongitudeMoon,
        apparentLongitudeMoon,
        latitudeMoon,
        apparentRightAscensionMoon,
        apparentDeclinationMoon,
        moonEarthDistance,
        semiDiameterMoon,
        horizontalParallaxMoon,
        sudutFai,
        0.0,
        sunEarthDistance,
        sudutFase,

        moonDiskIlluminatedFraction,
        0.0


    )
//    prinKabeh(kabeh)

    return EphemSunMoon(
            sun_trueGeocentricLongitude = trueLongitudeSun,
            sun_apparentGeocentricLongitude = apparentLongitudeSun,
            sun_geocentricLatitude = latitudeSun,
            sun_apparentRightAscension = apparentRightAscensionSun,
            sun_apparentDeclination = apparentDeclinationSun,
            sun_trueGeocentricDistance = sunEarthDistanceAU,
            sun_angularSemiDiameter = semiDiameterSun,
            sun_equationOfTime = eot,
            sun_equatorialHorizontalParallax = horizontalParallaxSun,
            earth_trueObliquity = epsilon,

            moon_trueGeocentricLongitude =trueLongitudeMoon,
            moon_apparentGeocentricLongitude =apparentLongitudeMoon,
            moon_apparentGeocentricLatitude =latitudeMoon,
            moon_apparentRightAscension =apparentRightAscensionMoon,
            moon_apparentDeclination =apparentDeclinationMoon,
            moon_trueGeocentricDistance =moonEarthDistance,
            moon_angularSemiDiameter =semiDiameterMoon,
            moon_equatorialHorizontalParallax =horizontalParallaxMoon,
            moon_diskIlluminatedFraction =moonDiskIlluminatedFraction,
            moon_brightLimbAngle =moonBrightLimb,
            moon_sunGeocentricElongation =moonSunGeoElongation
    )


}


fun main() {


}

private fun calculateMoonGeoLong(d_rad: Double, m_rad: Double, ma_rad: Double, f_rad: Double, e: Double): Double {


    //koreksi suku periodik bujur ekliptika bulan (sinus)
    var moonLongitude = 0.0

    val D = arrayOf(0, 2, 2, 0, 0, 0, 2, 2, 2, 2, 0, 1, 0, 2, 0, 0, 4, 0, 4, 2, 2, 1, 1, 2, 2, 4, 2, 0, 2, 2, 1, 2, 0, 0, 2, 2, 2, 4, 0, 3, 2, 4, 0, 2, 2, 2, 4, 0, 4, 1, 2, 0, 1, 3, 4, 2, 0, 1, 2)
    val M = arrayOf(0, 0, 0, 0, 1, 0, 0, -1, 0, -1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, -1, 0, 0, 0, 1, 0, -1, 0, -2, 1, 2, -2, 0, 0, -1, 0, 0, 1, -1, 2, 2, 1, -1, 0, 0, -1, 0, 1, 0, 1, 0, 0, -1, 2, 1, 0)
    val MA = arrayOf(1, -1, 0, 2, 0, 0, -2, -1, 1, 0, -1, 0, 1, 0, 1, 1, -1, 3, -2, -1, 0, -1, 0, 1, 2, 0, -3, -2, -1, -2, 1, 0, 2, 0, -1, 1, 0, -1, 2, -1, 1, -2, -1, -1, -2, 0, 1, 4, 0, -2, 0, 2, 1, -2, -3, 2, 1, -1, 3)
    val F = arrayOf(0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, -2, 2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, -2, 2, 0, 2, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, -2, -2, 0, 0, 0, 0, 0, 0, 0)
    val K = arrayOf(6288774, 1274027, 658314, 213618, -185116, -114332, 58793, 57066, 53322, 45758, -40923, -34720, -30383, 15327, -12528, 10980, 10675, 10034, 8548, -7888, -6766, -5163, 4987, 4036, 3994, 3861, 3665, -2689, -2602, 2390, -2348, 2236, -2120, -2069, 2048, -1773, -1595, 1215, -1110, -892, -810, 759, -713, -700, 691, 596, 549, 537, 520, -487, -399, -381, 351, -340, 330, 327, -323, 299, 294)


    for (i in D.indices) {
        moonLongitude += K[i] * e.pow(abs(M[i].toDouble())) * sin(D[i] * d_rad + M[i] * m_rad + MA[i] * ma_rad + F[i] * f_rad)
        ephemeris.perhitungan.daftar.add(K[i] * e.pow(abs(M[i].toDouble())) * sin(D[i] * d_rad + M[i] * m_rad + MA[i] * ma_rad + F[i] * f_rad))
    }



    return moonLongitude
}

private fun calculateMoonGeoLat(d_rad: Double, m_rad: Double, ma_rad: Double, f_rad: Double, e: Double): Double {
    var moonLatitude = 0.0
    val D = arrayOf(0, 0, 0, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 4, 0, 0, 0, 1, 0, 0, 0, 1, 0, 4, 4, 0, 4, 2, 2, 2, 2, 0, 2, 2, 2, 2, 4, 2, 2, 0, 2, 1, 1, 0, 2, 1, 2, 0, 4, 4, 1, 4, 1, 4, 2)
    val M = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 1, -1, -1, -1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 1, 1, 0, -1, -2, 0, 1, 1, 1, 1, 1, 0, -1, 1, 0, -1, 0, 0, 0, -1, -2)
    val MA = arrayOf(0, 1, 1, 0, -1, -1, 0, 2, 1, 2, 0, -2, 1, 0, -1, 0, -1, -1, -1, 0, 0, -1, 0, 1, 1, 0, 0, 3, 0, -1, 1, -2, 0, 2, 1, -2, 3, 2, -3, -1, 0, 0, 1, 0, 1, 1, 0, 0, -2, -1, 1, -2, 2, -2, -1, 1, 1, -1, 0, 0)
    val F = arrayOf(1, 1, -1, -1, 1, -1, 1, 1, -1, -1, -1, -1, 1, -1, 1, 1, -1, -1, -1, 1, 3, 1, 1, 1, -1, -1, -1, 1, -1, 1, -3, 1, -3, -1, -1, 1, -1, 1, -1, 1, 1, 1, 1, -1, 3, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, 1)
    val K = arrayOf(5128122, 280602, 277693, 173237, 55413, 46271, 32573, 17198, 9266, 8822, 8216, 4324, 4200, -3359, 2463, 2211, 2065, -1870, 1828, -1794, -1749, -1565, -1491, -1475, -1410, -1344, -1335, 1107, 1021, 833, 777, 671, 607, 596, 491, -451, 439, 422, 421, -366, -351, 331, 315, 302, -283, -229, 223, 223, -220, -220, -185, 181, -177, 176, 166, -164, 132, -119, 115, 107)

    for (i in D.indices) {
        moonLatitude += K[i] * e.pow(abs(M[i].toDouble())) * sin(D[i] * d_rad + M[i] * m_rad + MA[i] * ma_rad + F[i] * f_rad)
    }
    return moonLatitude
}

private fun calculateMoonGeoDistance(d_rad: Double, m_rad: Double, ma_rad: Double, f_rad: Double, e: Double): Double {
    var moonDistance = 0.0
    val D = arrayOf(0, 2, 2, 0, 2, 2, 2, 2, 0, 1, 0, 0, 0, 4, 2, 2, 0, 4, 1, 2, 2, 4, 2, 2, 2, 2, 2, 1, 0, 1, 0, 2, 0, 2, 4, 3, 0, 2, 2, 0, 4, 1, 4, 4, 0, 0)
    val M = arrayOf(0, 0, 0, 0, 0, -1, 0, -1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, -1, 0, 0, 0, -1, -2, 0, 0, 1, 0, 1, -2, 0, 0, -1, 0, 0, 1, 2, 2, -1, 0, -1, 0, 2, 0)
    val MA = arrayOf(1, -1, 0, 2, -2, 0, 1, -1, -1, 0, 1, 1, 0, -1, 0, -1, 3, -2, 0, -3, 1, 0, 2, 0, -2, 0, -1, -1, -2, 1, 2, -1, 2, 1, -1, -1, 0, 1, -1, -1, -2, -2, 0, 1, 1, 4)
    val F = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, -2, 0, 0, 0, 0, 0, -2, -2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    val K = arrayOf(-20905355, -3699111, -2955968, -569925, 246158, -204586, -170733, -152138, -129620, 108743, 104755, 79661, 48888, -34782, 30824, 24208, -23210, -21636, -16675, 14403, -12831, -11650, -10445, 10321, 10056, -9884, 8752, -8379, -7003, 6322, 5751, -4950, -4421, 4130, -3958, 3258, -3149, 2616, 2354, -2117, -1897, -1739, -1571, -1423, 1165, -1117)

    for (i in D.indices) {
        moonDistance += K[i] * e.pow(abs(M[i].toDouble())) * cos(D[i] * d_rad + M[i] * m_rad + MA[i] * ma_rad + F[i] * f_rad)
    }
    moonDistance /= 1000





    return moonDistance

}


private fun calculateEarthHelioLongitude(tau: Double): Double {
    var l0 = 0.0
    var l1 = 0.0
    var l2 = 0.0
    var l3 = 0.0
    var l4 = 0.0
    var l5 = 0.0

    l0 += 175347046 * cos(0 + 0 * tau)
    l0 += 3341656 * cos(4.6692568 + 6283.07585 * tau)
    l0 += 34896 * cos(4.6261 + 12566.1517 * tau)
    l0 += 3497 * cos(2.7441 + 5753.3849 * tau)
    l0 += 3418 * cos(2.8289 + 3.5231 * tau)
    l0 += 3136 * cos(3.6277 + 77713.7715 * tau)
    l0 += 2676 * cos(4.4181 + 7860.4194 * tau)
    l0 += 2343 * cos(6.1352 + 3930.2097 * tau)
    l0 += 1324 * cos(0.7425 + 11506.7691 * tau)
    l0 += 1273 * cos(2.0371 + 529.691 * tau)
    l0 += 1199 * cos(1.1096 + 1577.3435 * tau)
    l0 += 990 * cos(5.233 + 5884.927 * tau)
    l0 += 902 * cos(2.045 + 26.298 * tau)
    l0 += 857 * cos(3.508 + 398.149 * tau)
    l0 += 780 * cos(1.179 + 5223.694 * tau)
    l0 += 753 * cos(2.533 + 5507.553 * tau)
    l0 += 505 * cos(4.583 + 18849.228 * tau)
    l0 += 492 * cos(4.205 + 775.523 * tau)
    l0 += 357 * cos(2.92 + 0.067 * tau)
    l0 += 317 * cos(5.849 + 11790.629 * tau)
    l0 += 284 * cos(1.899 + 796.298 * tau)
    l0 += 271 * cos(0.315 + 10977.079 * tau)
    l0 += 243 * cos(0.345 + 5486.778 * tau)
    l0 += 206 * cos(4.806 + 2544.314 * tau)
    l0 += 205 * cos(1.869 + 5573.143 * tau)
    l0 += 202 * cos(2.458 + 6069.777 * tau)
    l0 += 156 * cos(0.833 + 213.299 * tau)
    l0 += 132 * cos(3.411 + 2942.463 * tau)
    l0 += 126 * cos(1.083 + 20.775 * tau)
    l0 += 115 * cos(0.645 + 0.98 * tau)
    l0 += 103 * cos(0.636 + 4694.003 * tau)
    l0 += 102 * cos(0.976 + 15720.839 * tau)
    l0 += 102 * cos(4.267 + 7.114 * tau)
    l0 += 99 * cos(6.21 + 2146.17 * tau)
    l0 += 98 * cos(0.68 + 155.42 * tau)
    l0 += 86 * cos(5.98 + 161000.69 * tau)
    l0 += 85 * cos(1.3 + 6275.96 * tau)
    l0 += 85 * cos(3.67 + 71430.7 * tau)
    l0 += 80 * cos(1.81 + 17260 * tau)
    l0 += 79 * cos(3.04 + 12036.46 * tau)
    l0 += 75 * cos(1.76 + 5088.63 * tau)
    l0 += 74 * cos(3.5 + 3154.69 * tau)
    l0 += 74 * cos(4.68 + 801.82 * tau)
    l0 += 70 * cos(0.83 + 9437.76 * tau)
    l0 += 62 * cos(3.98 + 8827.39 * tau)
    l0 += 61 * cos(1.82 + 7084.9 * tau)
    l0 += 57 * cos(2.78 + 6286.6 * tau)
    l0 += 56 * cos(4.39 + 14143.5 * tau)
    l0 += 56 * cos(3.47 + 6279.55 * tau)
    l0 += 52 * cos(0.19 + 12139.55 * tau)
    l0 += 52 * cos(1.33 + 1748.02 * tau)
    l0 += 51 * cos(0.28 + 5856.48 * tau)
    l0 += 49 * cos(0.49 + 1194.45 * tau)
    l0 += 41 * cos(5.37 + 8429.24 * tau)
    l0 += 41 * cos(2.4 + 19651.05 * tau)
    l0 += 39 * cos(6.17 + 10447.39 * tau)
    l0 += 37 * cos(6.04 + 10213.29 * tau)
    l0 += 37 * cos(2.57 + 1059.38 * tau)
    l0 += 36 * cos(1.71 + 2352.87 * tau)
    l0 += 36 * cos(1.78 + 6812.77 * tau)
    l0 += 33 * cos(0.59 + 17789.85 * tau)
    l0 += 30 * cos(0.44 + 83996.85 * tau)
    l0 += 30 * cos(2.74 + 1349.87 * tau)
    l0 += 25 * cos(3.16 + 4690.48 * tau)


    l1 += 628331966747L * cos(0 + 0 * tau)
    l1 += 206059 * cos(2.678235 + 6283.07585 * tau)
    l1 += 4303 * cos(2.6351 + 12566.1517 * tau)
    l1 += 425 * cos(1.59 + 3.523 * tau)
    l1 += 119 * cos(5.796 + 26.298 * tau)
    l1 += 109 * cos(2.966 + 1577.344 * tau)
    l1 += 93 * cos(2.59 + 18849.23 * tau)
    l1 += 72 * cos(1.14 + 529.69 * tau)
    l1 += 68 * cos(1.87 + 398.15 * tau)
    l1 += 67 * cos(4.41 + 5507.55 * tau)
    l1 += 59 * cos(2.89 + 5223.69 * tau)
    l1 += 56 * cos(2.17 + 155.42 * tau)
    l1 += 45 * cos(0.4 + 796.3 * tau)
    l1 += 36 * cos(0.47 + 775.52 * tau)
    l1 += 29 * cos(2.65 + 7.11 * tau)
    l1 += 21 * cos(5.34 + 0.98 * tau)
    l1 += 19 * cos(1.85 + 5486.78 * tau)
    l1 += 19 * cos(4.97 + 213.3 * tau)
    l1 += 17 * cos(2.99 + 6275.96 * tau)
    l1 += 16 * cos(0.03 + 2544.31 * tau)
    l1 += 16 * cos(1.43 + 2146.17 * tau)
    l1 += 15 * cos(1.21 + 10977.08 * tau)
    l1 += 12 * cos(2.83 + 1748.02 * tau)
    l1 += 12 * cos(3.26 + 5088.63 * tau)
    l1 += 12 * cos(5.27 + 1194.45 * tau)
    l1 += 12 * cos(2.08 + 4694 * tau)
    l1 += 11 * cos(0.77 + 553.57 * tau)
    l1 += 10 * cos(1.3 + 6286.6 * tau)
    l1 += 10 * cos(4.24 + 1349.87 * tau)
    l1 += 9 * cos(2.7 + 242.73 * tau)
    l1 += 9 * cos(5.64 + 951.72 * tau)
    l1 += 8 * cos(5.3 + 2352.87 * tau)
    l1 += 6 * cos(2.65 + 9437.76 * tau)
    l1 += 6 * cos(4.67 + 4690.48 * tau)
    l1 *= tau

    l2 += 52919 * cos(0 + 0 * tau)
    l2 += 8720 * cos(1.0721 + 6283.0758 * tau)
    l2 += 309 * cos(0.867 + 12566.152 * tau)
    l2 += 27 * cos(0.05 + 3.52 * tau)
    l2 += 16 * cos(5.19 + 26.3 * tau)
    l2 += 16 * cos(3.68 + 155.42 * tau)
    l2 += 10 * cos(0.76 + 18849 * tau)
    l2 += 9 * cos(2.06 + 77713.77 * tau)
    l2 += 7 * cos(0.83 + 775.52 * tau)
    l2 += 5 * cos(4.66 + 1577.34 * tau)
    l2 += 4 * cos(1.03 + 7.11 * tau)
    l2 += 4 * cos(3.44 + 5573.14 * tau)
    l2 += 3 * cos(5.14 + 796.3 * tau)
    l2 += 3 * cos(6.05 + 5507.55 * tau)
    l2 += 3 * cos(1.19 + 242.73 * tau)
    l2 += 3 * cos(6.12 + 529.69 * tau)
    l2 += 3 * cos(0.31 + 398.15 * tau)
    l2 += 3 * cos(2.28 + 553.57 * tau)
    l2 += 2 * cos(4.38 + 5223.69 * tau)
    l2 += 2 * cos(3.75 + 0.98 * tau)
    l2 *= tau * tau


    l3 += 289 * cos(5.844 + 6283.076 * tau)
    l3 += 35 * cos(0 + 0 * tau)
    l3 += 17 * cos(5.49 + 12566.15 * tau)
    l3 += 3 * cos(5.2 + 155.42 * tau)
    l3 += 1 * cos(4.72 + 3.52 * tau)
    l3 += 1 * cos(5.3 + 18849.23 * tau)
    l3 += 1 * cos(5.97 + 242.73 * tau)
    l3 *= tau * tau * tau

    l4 += 114 * cos(3.142 + 0 * tau)
    l4 += 8 * cos(4.13 + 6283.08 * tau)
    l4 += 1 * cos(3.84 + 12566.15 * tau)
    l4 *= tau * tau * tau * tau

    l5 += 1 * cos(3.14 + 0 * tau)
    l5 *= tau * tau * tau * tau * tau

    val l = mod(toDeg((l0 + l1 + l2 + l3 + l4 + l5) / 100_000_000L), 360)
    return l
}


private fun calculateEarthHeliocentricLatitude(tau: Double): Double {
    var b0 = 0.0
    b0 += 280 * cos(3.199 + 84334.662 * tau)
    b0 += 102 * cos(5.422 + 5507.553 * tau)
    b0 += 80 * cos(3.88 + 5223.69 * tau)
    b0 += 44 * cos(3.7 + 2352.87 * tau)
    b0 += 32 * cos(4 + 1577.34 * tau)
    var b1 = 0.0
    b1 += 9 * cos(3.9 + 5507.55 * tau)
    b1 += 6 * cos(1.73 + 5223.69 * tau)
    b1 *= tau

    val B = toDeg((b0 + b1) / 100000000) * 3600

    return B
}

private fun calculateEarthRadiusVector(tau: Double): Double {

    var r0 = 0.0
    r0 += 100013989 * cos(0 + 0 * tau)
    r0 += 1670700 * cos(3.0984635 + 6283.07585 * tau)
    r0 += 13956 * cos(3.05525 + 12566.1517 * tau)
    r0 += 3084 * cos(5.1985 + 77713.7715 * tau)
    r0 += 1628 * cos(1.1739 + 5753.3849 * tau)
    r0 += 1576 * cos(2.8469 + 7860.4194 * tau)
    r0 += 925 * cos(5.453 + 11506.77 * tau)
    r0 += 542 * cos(4.564 + 3930.21 * tau)
    r0 += 472 * cos(3.661 + 5884.927 * tau)
    r0 += 346 * cos(0.964 + 5507.553 * tau)
    r0 += 329 * cos(5.9 + 5223.694 * tau)
    r0 += 307 * cos(0.299 + 5573 * tau)
    r0 += 243 * cos(4.273 + 11790.629 * tau)
    r0 += 212 * cos(5.847 + 1577.344 * tau)
    r0 += 186 * cos(5.022 + 10977.079 * tau)
    r0 += 175 * cos(3.012 + 18849.228 * tau)
    r0 += 110 * cos(5.055 + 5486.778 * tau)
    r0 += 98 * cos(0.89 + 6069.78 * tau)
    r0 += 86 * cos(5.69 + 15720.84 * tau)
    r0 += 86 * cos(1.27 + 161000.69 * tau)
    r0 += 65 * cos(0.27 + 17260.15 * tau)
    r0 += 63 * cos(0.92 + 529.69 * tau)
    r0 += 57 * cos(2.01 + 83996.85 * tau)
    r0 += 56 * cos(5.24 + 71430.7 * tau)
    r0 += 49 * cos(3.25 + 2544.31 * tau)
    r0 += 47 * cos(2.58 + 775.52 * tau)
    r0 += 45 * cos(5.54 + 9437.76 * tau)
    r0 += 43 * cos(6.01 + 6275.96 * tau)
    r0 += 39 * cos(5.36 + 4694 * tau)
    r0 += 38 * cos(2.39 + 8827.39 * tau)
    r0 += 37 * cos(0.83 + 19651.05 * tau)
    r0 += 37 * cos(4.9 + 12139.55 * tau)
    r0 += 36 * cos(1.67 + 12036.46 * tau)
    r0 += 35 * cos(1.84 + 2942.46 * tau)
    r0 += 33 * cos(0.24 + 7084.9 * tau)
    r0 += 32 * cos(0.18 + 5088.63 * tau)
    r0 += 32 * cos(1.78 + 398.15 * tau)
    r0 += 28 * cos(1.21 + 6286.6 * tau)
    r0 += 28 * cos(1.9 + 6279.55 * tau)
    r0 += 26 * cos(4.59 + 10447.39 * tau)

    var r1 = 0.0
    r1 += 103019 * cos(1.10749 + 6283.07585 * tau)
    r1 += 1721 * cos(1.0644 + 12566.1517 * tau)
    r1 += 702 * cos(3.142 + 0 * tau)
    r1 += 32 * cos(1.02 + 18849.23 * tau)
    r1 += 31 * cos(2.84 + 5507.55 * tau)
    r1 += 25 * cos(1.32 + 5223.69 * tau)
    r1 += 18 * cos(1.42 + 1577.34 * tau)
    r1 += 10 * cos(5.91 + 10977.08 * tau)
    r1 += 9 * cos(1.42 + 6275.96 * tau)
    r1 += 9 * cos(0.27 + 5486.78 * tau)
    r1 *= tau

    var r2 = 0.0
    r2 += 4359 * cos(5.7846 + 6283.0758 * tau)
    r2 += 124 * cos(5.579 + 12566.152 * tau)
    r2 += 12 * cos(3.14 + 0 * tau)
    r2 += 9 * cos(3.63 + 77713.77 * tau)
    r2 += 6 * cos(1.87 + 5573.14 * tau)
    r2 += 3 * cos(5.47 + 18849.23 * tau)
    r2 *= tau * tau

    var r3 = 0.0
    r3 += 145 * cos(4.273 + 6283.076 * tau)
    r3 += 7 * cos(3.92 + 12566.15 * tau)
    r3 *= tau * tau * tau

    var r4 = 0.0
    r4 += 4 * cos(2.56 + 6283.08 * tau)
    r4 *= tau * tau * tau * tau


    return (r0 + r1 + r2 + r3 + r4) / 100_000_000L
}

//val D = arrayOf()
//    val M = arrayOf()
//    val MA = arrayOf()
//    val F = arrayOf( )
//    val K = arrayOf( )
