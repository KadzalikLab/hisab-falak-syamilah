package ephemeris.perhitungan

import utilitas.mod
import utilitas.toDeg
import utilitas.toRad
import java.lang.Math.pow
import kotlin.math.*

fun ompec(jd: Double):EphemSunMoon{

    val t: Double = (jd - 2451545) / 36525
//        print("Julian Day",jd,5);
//        print("Julian Ephemeris Day", jd,5);
//        print("JD from 2000 1.5 ET",t);

//        print("Julian Day",jd,5);
//        print("Julian Ephemeris Day", jd,5);
//        print("JD from 2000 1.5 ET",t);
    val mean_long_moon: Double = mod(218.316 + 481267.881 * t, 360)
    val mean_anomaly_moon: Double = mod(134.963 + 477198.867 * t, 360)
    var mean_long_node_moon: Double = mod(125.045 - 1934.136 * t, 360)
    mean_long_node_moon = mod(mean_long_node_moon, 360)
    val mean_long_sun: Double = mod(280.466 + 36000.77 * t, 360)
    val mean_anomaly_sun: Double = mod(357.528 + 35999.05 * t, 360)

//        printDms("mean_long_moon",mean_long_moon);
//        printDms("mean_anomaly_moon",mean_anomaly_moon);
//        printDms("mean_long_node_moon",mean_long_node_moon);
//        printDms("mean_long_sun",mean_long_sun);
//        printDms("mean_anomaly_sun",mean_anomaly_sun);
//        printDms("mean_long_moon",mean_long_moon);
//        printDms("mean_anomaly_moon",mean_anomaly_moon);
//        printDms("mean_long_node_moon",mean_long_node_moon);
//        printDms("mean_long_sun",mean_long_sun);
//        printDms("mean_anomaly_sun",mean_anomaly_sun);
    val nutation_long = (-17.2 * sin(toRad(mean_long_node_moon)) + 0.206 * sin((2 * mean_long_node_moon)) - 1.319 * sin((2 * mean_long_sun)) + 0.143 * sin((mean_anomaly_sun)) - 0.227 * sin((2 * mean_long_moon)) + 0.071 * sin((mean_anomaly_moon))) / 3600
    val nutation_obliquity = (9.203 * cos((mean_long_node_moon)) - 0.09 * cos((2 * mean_long_node_moon)) + 0.574 * cos((2 * mean_long_sun)) + 0.022 * cos((2 * mean_long_sun + mean_anomaly_sun)) + 0.098 * cos((2 * mean_long_moon)) + 0.02 * cos((2 * mean_long_moon - mean_long_node_moon))) / 3600
    val obliquity_ecl_mean = 23 + 26 / 60.0 + 21.45 / 3600 - 46.82 * t / 3600 // mean_obliquity of ecliptic

    val obliquity_ecl_true = obliquity_ecl_mean + nutation_obliquity //true_obliquity of ecliptic

//        printDms("Nutation in Longitude",nutation_long);
//        printDms("Nutation in Obliquity",nutation_obliquity);
//        printDms("Mean Obliquity of Ecl",obliquity_ecl_mean);
//        printDms("True Obliquity of Ecl",obliquity_ecl_true);

//        printDms("Nutation in Longitude",nutation_long);
//        printDms("Nutation in Obliquity",nutation_obliquity);
//        printDms("Mean Obliquity of Ecl",obliquity_ecl_mean);
//        printDms("True Obliquity of Ecl",obliquity_ecl_true);
    val jd_00_UT = (jd + 0.5).toInt() - 0.5 // MASALAH!!!!
    val decimal_hour_UT: Double = 24 * mod(jd + 0.5 + 0 / 24.0, 1)
    val gmst: Double = mod(6.656306 + 0.0657098242 * (jd_00_UT - 2445700.5) + 1.0027379093 * decimal_hour_UT, 24) // Greenwich Mean Sidereal Time

    val gast: Double = mod(gmst + nutation_long * cos((obliquity_ecl_true)) / 15, 24) //Greenwich Apparent Sidereal Time

//double lmst = mod(gmst+geographichal_longitude/15,24); //Local Mean Sidereal Time
// double last = mod(lmst+nutation_long*cos((obliquity_ecl_true))/15,24); // Local Apparent Sidereal Time
//        printHms("GMST",gmst);
//        printHms("GAST",gast);
//printHms("LMST",lmst);
//  printHms("LAST",last);

//Geocentric Ecliptic Coordinates of the Sun (this is an abridged version of Simon Newcomb’s Theory of the Solar Motion)
//double lmst = mod(gmst+geographichal_longitude/15,24); //Local Mean Sidereal Time
// double last = mod(lmst+nutation_long*cos((obliquity_ecl_true))/15,24); // Local Apparent Sidereal Time
//        printHms("GMST",gmst);
//        printHms("GAST",gast);
//printHms("LMST",lmst);
//  printHms("LAST",last);

//Geocentric Ecliptic Coordinates of the Sun (this is an abridged version of Simon Newcomb’s Theory of the Solar Motion)
    val t19: Double = (jd - 2415020) / 36525 //Julian Century from J1900.0


// The Mean Longitudes

// The Mean Longitudes
    val dlp = (+(1.882 - 0.016 * t19) * sin((57.24 + 150.27 * t19)) + 6.4 * sin((231.19 + 20.2 * t19)) + 0.266 * sin((31.8 + 119 * t19))) / 3600 // Long period pertubation of the sun's mean longitude and it's mean anomaly

    val LO: Double = mod(279.6966778 + 36000 * t19 + (2768.13 * t19 + 1.089 * pow(t19, 2.0) + 0.202 * sin((315.6 + 893.3 * t19))) / 3600 + dlp, 360) // Mean Longitude of the sun

    val g: Double = mod(358.4758333 + 35999 * t19 + (179.1 * t19 - 0.54 * pow(t19, 2.0)) / 3600 + dlp, 360) // Mean Anomaly of Sun

    val g2: Double = mod(212.45 + 58517.493 * t19, 360) // Mean Anomaly of Venus

    val g4: Double = mod(319.58 + 19139.977 * t19, 360) // Mean Anomaly of Mars

    val g5: Double = mod(225.28 + 3034.583 * t19 + 1300 * sin((133.775 + 39.804 * t19)) / 3600, 360) // Mean Anomaly of Jupiter

    val g6: Double = mod(175.6 + 1221.794 * t19, 360) // Mean Anomaly of saturn

    val d: Double = mod(350.737486 + 445267.114217 * t19, 360) // Mean Angular distance of the moon from sun

    val a: Double = mod(296.104608 + 477198.849108 * t19, 360) // Mean Nomaly of the Moon

    val u: Double = mod(11.250889 + 483202.02515 * t19, 360) // Mean Argument of the latitude of the moon


//        print("J Century from J1900",t19);
//        printDms("Long Period Pertubation",dlp);
//        printDms("Mean Longitudec Sun",LO);
//        printDms("Mean Anomaly Sun",g);
//        printDms("Mean Anomaly Venus",g2);
//        printDms("Mean Anomaly Mars",g4);
//        printDms("Mean Anomaly Jupiter",g5);
//        printDms("Mean  Anomaly Saturn",g6);
//        printDms("Mean Angular Dis moon-sun",d);
//        printDms("Mean Anomaly Moon",a);
//        printDms("Mean Argument lat Moon",u);


// Terms of the Two-Body Motion

//        print("J Century from J1900",t19);
//        printDms("Long Period Pertubation",dlp);
//        printDms("Mean Longitudec Sun",LO);
//        printDms("Mean Anomaly Sun",g);
//        printDms("Mean Anomaly Venus",g2);
//        printDms("Mean Anomaly Mars",g4);
//        printDms("Mean Anomaly Jupiter",g5);
//        printDms("Mean  Anomaly Saturn",g6);
//        printDms("Mean Angular Dis moon-sun",d);
//        printDms("Mean Anomaly Moon",a);
//        printDms("Mean Argument lat Moon",u);


// Terms of the Two-Body Motion
    val dl = (+(6910.057 - 17.24 * t19) * sin((g)) + (72.338 - 0.361 * t19) * sin((2 * g)) + 1.054 * sin((3 * g))) / 3600 // Diffrence between true an mean longitudes of the sun

    val r0 = +(0.00003057 - 0.00000015 * t19) + (-0.00727412 + 0.00001814 * t19) * cos((g)) + (-0.00009138 + 0.00000046 * t19) * cos((2 * g)) + -0.00000145 * cos((3 * g)) // logarithm to base ten of the ius in AU

//        printDms("Diffrence true - mean", dl);
//        printDms("Logarithm to b10 AU",r0);

//        printDms("Diffrence true - mean", dl);
//        printDms("Logarithm to b10 AU",r0);
    val dl2 = (4.838 * cos((299.102 + g2 - g)) + 0.116 * cos((148.9 + 2 * g2 - g)) + 5.526 * cos((148.313 + 2 * g2 - 2 * g)) + 2.497 * cos((315.943 + 2 * g2 - 3 * g)) + 0.66 * cos((177.71 + 3 * g2 - 3 * g)) + 1.559 * cos((345.253 + 3 * g2 - 4 * g)) + 1.024 * cos((318.15 + 3 * g2 - 5 * g)) + 0.21 * cos((206.2 + 4 * g2 - 4 * g)) + 0.144 * cos((195.4 + 4 * g2 - 5 * g)) + 0.152 * cos((343.8 + 4 * g2 - 6 * g)) + 0.123 * cos((195.3 + 5 * g2 - 7 * g)) + 0.154 * cos((359.6 + 5 * g2 - 8 * g))) / 3600 // Longitude perturbation by Venus

    val dl4 = (0.273 * cos((217.7 - g4 + g)) + 2.043 * cos((343.888 - 2 * g4 + 2 * g)) + 1.77 * cos((200.402 - 2 * g4 + g)) + 0.129 * cos((294.2 - 3 * g4 + 3 * g)) + 0.425 * cos((338.88 - 3 * g4 + 2 * g)) + 0.5 * cos((105.18 - 4 * g4 + 3 * g)) + 0.585 * cos((334.06 - 4 * g4 + 2 * g)) + 0.204 * cos((100.8 - 5 * g4 + 3 * g)) + 0.154 * cos((227.4 - 6 * g4 + 4 * g)) + 0.101 * cos((96.3 - 6 * g4 + 3 * g)) + 0.106 * cos((222.7 - 7 * g4 + 4 * g))) / 3600 // Longitude perturbation by Mars

    val dl5 = (0.163 * cos((198.6 - g5 + 2 * g)) + 7.208 * cos((179.532 - g5 + g)) + 2.6 * cos((263.217 - g5)) + 2.731 * cos((87.145 - 2 * g5 + 2 * g)) + 1.61 * cos((109.493 - 2 * g5 + g)) + 0.164 * cos((170.5 - 3 * g5 + 3 * g)) + 0.556 * cos((82.65 - 3 * g5 + 2 * g)) + 0.21 * cos((98.5 - 3 * g5 + g))) / 3600 // Longitude perturbation by Jupiter

    val dl6 = (0.419 * cos((100.58 - g6 + g)) + 0.32 * cos((269.46 - g6)) + 0.108 * cos((290.6 - 2 * g6 + 2 * g)) + 0.112 * cos((293.6 - 2 * g6 + g))) / 3600 // Longitude perturbation by Saturn

    val dlm = (6.454 * sin((d)) + 0.177 * sin((d + a)) - 0.424 * sin((d - a)) + 0.172 * sin((d - g))) / 3600 //Longitude perturbation by the Moon


// The True Longitude

// The True Longitude
    val ecl_long_sun_true: Double = mod(LO + dl + dl2 + dl4 + dl5 + dl6 + dlm, 360)
//        double l_appr = l+(-20.496/)

// Pertubations of the Radius Vector
//        double l_appr = l+(-20.496/)

// Pertubations of the Radius Vector
    val dr2 = (2359 * cos((209.08 + g2 - g)) + 160 * cos((58.4 + 2 * g2 - g)) + 6842 * cos((58.318 + 2 * g2 - 2 * g)) + 869 * cos((226.7 + 2 * g2 - 3 * g)) + 1045 * cos((87.57 + 3 * g2 - 3 * g)) + 1497 * cos((255.25 + 3 * g2 - 4 * g)) + 194 * cos((49.5 + 3 * g2 - 5 * g)) + 376 * cos((116.28 + 4 * g2 - 4 * g)) + 196 * cos((105.2 + 4 * g2 - 5 * g)) + 163 * cos((145.4 + 5 * g2 - 5 * g)) + 141 * cos((105.4 + 5 * g2 - 7 * g))) / 1000000000
    val dr4 = (150 * cos((127.7 - g4 + g)) + 2057 * cos((253.828 - 2 * g4 + 2 * g)) + 151 * cos((295 - 2 * g4 + g)) + 168 * cos((203.5 - 3 * g4 + 3 * g)) + 215 * cos((249 - 3 * g4 + 2 * g)) + 478 * cos((15.17 - 4 * g4 + 3 * g)) + 105 * cos((65.9 - 4 * g4 + 2 * g)) + 107 * cos((324.6 - 5 * g4 + 4 * g)) + 139 * cos((137.3 - 6 * g4 + 4 * g))) / 1000000000
    val dr5 = (208 * cos((112 - g5 + 2 * g)) + 7067 * cos((89.545 - g5 + g)) + 244 * cos((338.6 - g5)) + 103 * cos((350.5 - 2 * g5 + 3 * g)) + 4026 * cos((357.108 - 2 * g5 + 2 * g)) + 1459 * cos((19.467 - 2 * g5 + g)) + 281 * cos((81.2 - 3 * g5 + 3 * g)) + 803 * cos((352.56 - 3 * g5 + 2 * g)) + 174 * cos((8.6 - 3 * g5 + g)) + 113 * cos((347.7 - 4 * g5 + 2 * g))) / 1000000000
    val dr6 = (429 * cos((10.6 - g6 + g)) + 162 * cos((200.6 - 2 * g6 + 2 * g)) + 112 * cos((203.1 - 2 * g6 + g))) / 1000000000
    val drm = (13360 * cos((d)) + 370 * cos((d + a)) - 1330 * cos((d - a)) - 140 * cos((d + g)) + 360 * cos((d - g))) / 1000000000
    val r = pow(10.0, r0 + dr2 + dr4 + dr5 + dr6 + drm)
    val ecl_long_sun_appr = ecl_long_sun_true + -20.496 / r / 3600 + nutation_long


//        System.out.println("Pertubation");
//        printDms("- in log(R) by Venus ",dr2,7);
//        printDms("- in log(R) by Mars",dr4,7);
//        printDms("- in log(R) by Jupiter",dr5,7);
//        printDms("- in log(R) by Saturn",dr6,7);
//        printDms("- in log(R) by the Moon",drm,7);
//        //The ius Vector
//        printDigit("Radius Vector Sun AU",r,9);
//
//        printDms("Appr Longitude Sun",ecl_long_sun_appr);

//The ecliptic Latitude of the Sun


//        System.out.println("Pertubation");
//        printDms("- in log(R) by Venus ",dr2,7);
//        printDms("- in log(R) by Mars",dr4,7);
//        printDms("- in log(R) by Jupiter",dr5,7);
//        printDms("- in log(R) by Saturn",dr6,7);
//        printDms("- in log(R) by the Moon",drm,7);
//        //The ius Vector
//        printDigit("Radius Vector Sun AU",r,9);
//
//        printDms("Appr Longitude Sun",ecl_long_sun_appr);

//The ecliptic Latitude of the Sun
    val ecl_lat_sun = (-0.21 * cos((151.8 + 3 * g2 - 4 * g)) - 0.166 * cos((265.5 - 2 * g5 + g)) + 0.576 * sin((u))) / 3600

//        printDms("ecliptic Latitude Sun",ecl_lat_sun);
//        printDms("ecliptic Latitude Sun",ecl_lat_sun);
    val appr_semi_diameter_sun = toDeg(atan(1 / r * tan((15 / 60.0 + 59.63 / 3600)))) //Apparent Angular semi diameter of the sun

    val hp_sun = toDeg(asin(sin((8.794 / 3600)) / r)) //Equatorial-horizontal parallax of the sun

//        printDms("Appr  Sdiameter Sun",appr_semi_diameter_sun,4);
//        printDms("Equa-Horz parallax Sun",hp_sun,4);

// The mean Longitude
//        printDms("Appr  Sdiameter Sun",appr_semi_diameter_sun,4);
//        printDms("Equa-Horz parallax Sun",hp_sun,4);

// The mean Longitude
    val f34: Double = mod(270.434164 + 481267.883142 * t19 - 0.001133 * pow(t19, 2.0), 360) // Mean longitude of the moon

    val f35: Double = mod(296.104608 + 477198.849108 * t19 + 0.009192 * pow(t19, 2.0), 360) // Mean anomaly of the moon

    val f36: Double = mod(259.183275 - 1934.142008 * t19 + 0.002078 * pow(t19, 2.0), 360) // Mean longitude of the ascending node of the lunar orbit

    val f37: Double = mod(279.696678 + 36000.768925 * t19 + 0.000303 * pow(t19, 2.0), 360) // Mean longitude of the sun

    val f38: Double = mod(358.475833 + 35999.04975 * t19 - 0.00015 * pow(t19, 2.0), 360) //Mean Anomaly of the sun

//             double l_mm = mod(270.434164+481267.883142*t19-0.001133*pow(t19,2),360); // Mean longitude of the moon
//        double m_mm = mod(296.104608+477198.849108*t19+0.009192*pow(t19,2),360); // Mean anomaly of the moon
//        double o_mm = mod(259.183275-1934.142008*t19+0.002078*pow(t19,2),360); // Mean longitude of the ascending node of the lunar orbit
//        double l_sm = mod(279.696678+36000.768925*t19+0.000303*pow(t19,2),360); // Mean longitude of the sun
//        double m_sm = mod(358.475833+35999.04975*t19-0.00015*pow(t19,2),360); //Mean Anomaly of the sun
//


//        printDms("Mean long Moon",f34);
//        printDms("Mean anomaly Moon",f35);
//        printDms("Long asc lunar orbit",f36);
//        printDms("Mean long Sun",f37);
//        printDms("Mean Anomaly Sun",f38);
//             double l_mm = mod(270.434164+481267.883142*t19-0.001133*pow(t19,2),360); // Mean longitude of the moon
//        double m_mm = mod(296.104608+477198.849108*t19+0.009192*pow(t19,2),360); // Mean anomaly of the moon
//        double o_mm = mod(259.183275-1934.142008*t19+0.002078*pow(t19,2),360); // Mean longitude of the ascending node of the lunar orbit
//        double l_sm = mod(279.696678+36000.768925*t19+0.000303*pow(t19,2),360); // Mean longitude of the sun
//        double m_sm = mod(358.475833+35999.04975*t19-0.00015*pow(t19,2),360); //Mean Anomaly of the sun
//


//        printDms("Mean long Moon",f34);
//        printDms("Mean anomaly Moon",f35);
//        printDms("Long asc lunar orbit",f36);
//        printDms("Mean long Sun",f37);
//        printDms("Mean Anomaly Sun",f38);
    val ecl_long_moon_true: Double = mod(f34 + (22640 * sin((f35)) + 769 * sin((2 * f35)) + 36 * sin((3 * f35)) - 125 * sin((f34 - f37)) + 2370 * sin((2 * (f34 - f37))) - 668 *
            sin((f38)) - 412 * sin((2 * (f34 - f36))) + 212 * sin((2 * (f34 - f37 - f35))) + 4586 * sin((2 * (f34 - f37) - f35)) + 192 * sin((2 * (f34 - f37) + f35)) + 165 *
            sin((2 * (f34 - f37) - f38)) + 206 * sin((2 * (f34 - f37) - f35 - f38)) - 110 * sin((f35 + f38)) + 148 * sin((f35 - f38))) / 3600, 360)
    val ecl_long_moon_appr = ecl_long_moon_true + nutation_long / 3600 + (0.018 * cos((f35 - 2 * (f34 - f37))) + 0.007 * cos((2 * (f34 - f37)))) / 3600
    val hp_moon = (3423 + 187 * cos((f35)) + 10 * cos((2 * f35)) + 34 * cos((2 * (f34 - f37) - f35)) + 28 * cos((2 * (f34 - f37))) + 3 * cos((2 * (f34 - f37) + f35))) / 3600
    val appr_sdc = toDeg(asin(0.272493 * sin((hp_moon)))) // Apparent angular semi-diameter of the moon

    var rt_EM_ii = 1 / sin((hp_moon))
    val r_EM_KM = 6378.14 / sin((hp_moon))
    val r_EM_AU = r_EM_KM / 149597870
//        printDms("True ecl Long Moon",ecl_long_moon_true);
//        printDms("Appr ecl Long Moon",ecl_long_moon_appr);
//        printDms("Equa-Horz Parallax Moon",hp_moon);
//        print("Dist E-M in Earth Radii",rt_EM_ii);
//        print("Dist E-M in KM",r_EM_KM);
//        print("Dist E-M in AU",r_EM_AU);

// The ecliptic latitude of the Moon
//        printDms("True ecl Long Moon",ecl_long_moon_true);
//        printDms("Appr ecl Long Moon",ecl_long_moon_appr);
//        printDms("Equa-Horz Parallax Moon",hp_moon);
//        print("Dist E-M in Earth Radii",rt_EM_ii);
//        print("Dist E-M in KM",r_EM_KM);
//        print("Dist E-M in AU",r_EM_AU);

// The ecliptic latitude of the Moon
    val ecl_lat_moon = (18520 * sin((ecl_long_moon_true - f36 + 0.114 * sin((2 * (f34 - f36))) + 0.15 * sin((f38)))) - 526 * sin((2 * f37 - f34 - f36)) + 44 * sin((2 * f37 - f34 - f36 + f35)) - 31 * sin((2 * f37 - f34 - f36 - f35)) - 23 * sin((2 * f37 - f34 - f36 + f38)) + 11 * sin((2 * f37 - f34 - f36 - f38)) - 25 * sin((f34 - f36 - 2 * f35)) +
            21 * sin((f34 - f36 - f35))) / 3600
//        printDms("Ecl Latitude Moon",ecl_lat_moon);

// Geocentric Equatorial Coordinates of the sun and the Moon
//Sun
//        printDms("Ecl Latitude Moon",ecl_lat_moon);

// Geocentric Equatorial Coordinates of the sun and the Moon
//Sun
    var alpha_sun_true: Double = mod(toDeg(atan2(+cos((obliquity_ecl_mean)) * cos((ecl_lat_sun)) * sin((ecl_long_sun_true)) - sin((obliquity_ecl_mean)) * sin((ecl_lat_sun)), +cos((ecl_lat_sun)) * cos((ecl_long_sun_true)))), 360) // True Equatorial Right Ascension of the Sun

    var dec_sun_true = toDeg(asin(+sin((obliquity_ecl_mean)) * cos((ecl_lat_sun)) * sin((ecl_long_sun_true)) + cos((obliquity_ecl_mean)) * sin((ecl_lat_sun)))) // True Equatorial Declination of the Sun

    val alpha_sun_appr: Double = mod(toDeg(atan2(+cos((obliquity_ecl_true)) * cos((ecl_lat_sun)) * sin((ecl_long_sun_appr)) - sin((obliquity_ecl_true)) * sin((ecl_lat_sun)), +cos((ecl_lat_sun)) * cos((ecl_long_sun_appr)))), 360)
    val dec_sun_appr = toDeg(asin(+sin((obliquity_ecl_true)) * cos((ecl_lat_sun)) * sin((ecl_long_sun_appr)) + cos((obliquity_ecl_true)) * sin((ecl_lat_sun)))) // True Equatorial Declination of the Sun

//
//        printDms("True Right Asc Sun",alpha_sun_true,4);
//        printDms("Appr Rigth Asc Sun",alpha_sun_appr,4);
//        printDms("True Declination Sun",dec_sun_true,4);
//        printDms("Appr Declination Sun",dec_sun_appr,3);
//
//        printDms("True Right Asc Sun",alpha_sun_true,4);
//        printDms("Appr Rigth Asc Sun",alpha_sun_appr,4);
//        printDms("True Declination Sun",dec_sun_true,4);
//        printDms("Appr Declination Sun",dec_sun_appr,3);
    var alpha_moon_true: Double = mod(toDeg(atan2(+cos((obliquity_ecl_mean)) * cos((ecl_lat_moon)) * sin((ecl_long_moon_true)) - sin((obliquity_ecl_mean)) * sin((ecl_lat_moon)), +cos((ecl_lat_moon)) * cos((ecl_long_moon_true)))), 360) // True Equatorial Right Ascension of the Sun

    val alpha_moon_appr: Double = mod(toDeg(atan2(+cos((obliquity_ecl_true)) * cos((ecl_lat_moon)) * sin((ecl_long_moon_appr)) - sin((obliquity_ecl_true)) * sin((ecl_lat_moon)), +cos((ecl_lat_moon)) * cos((ecl_long_moon_appr)))), 360) // True Equatorial Right Ascension of the Sun

    var dec_moon_true = toDeg(asin(+sin((obliquity_ecl_mean)) * cos((ecl_lat_moon)) * sin((ecl_long_moon_true)) + cos((obliquity_ecl_mean)) * sin((ecl_lat_moon)))) // True Equatorial Declination of the Sun

    val dec_moon_appr = toDeg(asin(+sin((obliquity_ecl_true)) * cos((ecl_lat_moon)) * sin((ecl_long_moon_appr)) + cos((obliquity_ecl_true)) * sin((ecl_lat_moon)))) // True Equatorial Declination of the Sun


//        printDms("True Right Asc Moon",alpha_moon_true,4);
//        printDms("Appr Right Asc Moon",alpha_moon_appr,4);
//        printDms("True Declination Moon",dec_moon_true,4);
//        printDms("Appr Declination Moon",dec_moon_appr,3);


//        printDms("True Right Asc Moon",alpha_moon_true,4);
//        printDms("Appr Right Asc Moon",alpha_moon_appr,4);
//        printDms("True Declination Moon",dec_moon_true,4);
//        printDms("Appr Declination Moon",dec_moon_appr,3);
    var angle_brigth_limb_moon = toDeg(atan2(cos((dec_sun_appr)) * sin((alpha_sun_appr - alpha_moon_appr)), sin((dec_sun_appr)) * cos((dec_moon_appr)) - cos((dec_sun_appr)) * sin((dec_moon_appr)) * cos((alpha_sun_appr - alpha_moon_appr)))) //Position Angle of the Sun / angle of the moon's bright limb

    angle_brigth_limb_moon = mod(angle_brigth_limb_moon, 360)
    val elongation = toDeg(acos(sin((dec_sun_appr)) * sin((dec_moon_appr)) + cos((dec_sun_appr)) * cos((dec_moon_appr)) * cos((alpha_sun_appr - alpha_moon_appr)))) // Elongation of the moon / geocentric elongation of the moon from the sun

//        printDms("Angle Bright Limb M",angle_brigth_limb_moon);
//        printDms("Elongation Moon-Sun",elongation);

//        printDms("Angle Bright Limb M",angle_brigth_limb_moon);
//        printDms("Elongation Moon-Sun",elongation);
    val phase_angle = toDeg(atan2(r * sin((elongation)), r_EM_AU - r * cos((elongation))))
    val fib = 1 / 2.0 * (1 + cos((phase_angle)))
    var dib = 1 / 2.0 * 2 * appr_semi_diameter_sun * (1 - cos((phase_angle)))


// Equation of time
    val eccentricity = 0.01675104 - 0.0000418 * t19 - 0.000000126 * pow(t19, 2.0)
    val auxi_y = tan((obliquity_ecl_mean / 2)).pow(2.0)
    val eot = toDeg(+auxi_y * sin((2 * LO)) - 2 * eccentricity * sin((g)) + 4 * eccentricity * auxi_y * sin((g)) * cos((2 * LO)) - 1 / 2.0 * pow(auxi_y, 2.0) * sin((4 * LO)) - 5 / 4.0 * pow(eccentricity, 2.0) * sin((2 * g))) / 15
//    println(dec_sun_appr)
    return EphemSunMoon(
            sun_trueGeocentricLongitude = ecl_long_sun_true,
            sun_apparentGeocentricLongitude = ecl_long_sun_appr,
            sun_geocentricLatitude = ecl_lat_sun,
            sun_apparentRightAscension = alpha_sun_appr,
            sun_apparentDeclination = dec_sun_appr,
            sun_trueGeocentricDistance = r,
            sun_angularSemiDiameter = appr_semi_diameter_sun,
            sun_equationOfTime = eot,
            sun_equatorialHorizontalParallax = hp_sun,
            earth_trueObliquity = obliquity_ecl_true,

            moon_trueGeocentricLongitude =ecl_long_moon_true,
            moon_apparentGeocentricLongitude =ecl_long_moon_appr,
            moon_apparentGeocentricLatitude =ecl_lat_moon,
            moon_apparentRightAscension =alpha_moon_appr,
            moon_apparentDeclination =dec_moon_appr,
            moon_trueGeocentricDistance =r_EM_AU,
            moon_angularSemiDiameter =appr_sdc,
            moon_equatorialHorizontalParallax =hp_moon,
            moon_diskIlluminatedFraction =fib,
            moon_brightLimbAngle =angle_brigth_limb_moon,
            moon_sunGeocentricElongation =elongation
    )


}