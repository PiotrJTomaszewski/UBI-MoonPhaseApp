package pl.pjt.ubi_zad1

import kotlin.math.*

class MoonPhaseCalculator(method: Int) {
    private val rad: Double = 3.14159265 / 180.0
    private val selectedMethod: Int? = method

    fun calculateMoonPhase(year: Int, month: Int, day: Int): Double {
        return when (selectedMethod) {
            2 -> calculatePhaseTrig2(year, month, day)
            else -> 0.0
        }
    }

    private fun calculatePhaseTrig2(year: Int, month: Int, day: Int): Double {
        val n = floor(12.37 * (year - 1900 + ((1.0 * month - 0.5) / 12.0)))
        val t = n / 1236.85
        val t2 = t * t
        val aas = 359.2242 + 29.105356 * n
        val am = 306.0253 + 385.816918 * n + 0.010730 * t2
        var xtra = 0.75933 + 1.53058868 * n + ((1.178e-4) - (1.55e-7) * t) * t2
        xtra += (0.1734 - 3.93e-4 * t) * sin(rad * aas) - 0.4068 * sin(rad * am)
        val i = if (xtra > 0.0) floor(xtra) else ceil(xtra - 1.0)
        val j1 = julianDay(year, month, day)
        val jd = (2415020 + 28 * n) + i
        return (j1 - jd + 30) % 30
    }

    private fun julianDay(year: Int, month: Int, day: Int): Double {
        var jy = year
        if (jy < 0) {
            jy += 1
        }
        var jm = month + 1
        if (jm <= 1) {
            jy -= 1
            jm += 12
        }
        var jul = floor(365.25 * jy) + floor(30.6001 * jm) + day + 1720995
        if (day + 31 * (month + 12 * year) >= (15 + 31 * (10 + 12 * 1582))) {
            val ja = floor(0.01 * jy)
            jul += 2 - ja + floor(0.25 * ja)
        }
        return jul
    }
}