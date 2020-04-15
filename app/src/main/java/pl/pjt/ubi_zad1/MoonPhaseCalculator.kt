package pl.pjt.ubi_zad1

import java.time.LocalDate
import java.util.*
import kotlin.math.*

class MoonPhaseCalculator() {
    private val rad: Double = 3.14159265 / 180.0

    fun calculateMoonPhase(method: Int, date: LocalDate): Double {
        return when (method) {
            0 -> calculatePhaseSimple(date.year, date.monthValue, date.dayOfMonth)
            1 -> calculatePhaseConway(date.year, date.monthValue, date.dayOfMonth)
            2 -> calculatePhaseTrig(date.year, date.monthValue, date.dayOfMonth)
            3 -> calculatePhaseTrig2(date.year, date.monthValue, date.dayOfMonth)
            else -> 0.0
        }
    }

    fun getMoonPercent(moonPhase: Double): Double {
        return if (moonPhase <= 15) 100 * moonPhase / 15
        else (1 - ((moonPhase - 16) / (29 - 16))) * 100
    }

    fun isFirstHalf(moonPhase: Double): Boolean {
        return (moonPhase <= 15)
    }

    fun isNewMoon(moonPhase: Double): Boolean {
        return (moonPhase <= 0.1)
    }

    fun isFullMoon(moonPhase: Double): Boolean {
        return (abs(moonPhase - 15) <= 0.1)
    }

    fun calculateLastNewMoon(method: Int, date_now: LocalDate): LocalDate {
        var date = date_now.minusDays(1)
        var curPhase = calculateMoonPhase(method, date)
        while (!isNewMoon(curPhase)) {
            date = date.minusDays(1)
            curPhase = calculateMoonPhase(method, date)
        }
        return date
    }

    fun calculateNextFullMoon(method: Int, date_now: LocalDate): LocalDate {
        var date = date_now.plusDays(1)
        var curPhase = calculateMoonPhase(method, date)
        while (!isFullMoon(curPhase)) {
            date = date.plusDays(1)
            curPhase = calculateMoonPhase(method, date)
        }
        return date
    }

    fun calculateFullMoonsInYear(method: Int, year: String): Array<LocalDate> {
        var date = LocalDate.parse("$year-01-01")
        var dates = Array<LocalDate>(12) { _ -> LocalDate.now() }
        for (i in 0..11) {
            date = calculateNextFullMoon(method, date)
            dates[i] = date
        }
        return dates
    }

    private fun calculatePhaseSimple(year: Int, month: Int, day: Int): Double {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day, 20, 35, 0)
        val now = calendar.time
        calendar.set(1970, 0, 7, 20, 35, 0)
        val newMoon = calendar.time
        val phase = ((now.time - newMoon.time) / 1000) % 2551443
        return floor(phase / (24.0 * 3600)) + 1
    }

    private fun calculatePhaseConway(year: Int, month: Int, day: Int): Double {
        var r: Double = (year % 100).toDouble()
        r %= 19
        if (r > 9) {
            r -= 19
        }
        r = ((r * 11) % 30) + month + day
        if (month < 3) {
            r += 2
        }
        if (year < 2000) {
            r -= 4
        } else {
            r -= 8.3
        }
        r = floor(r + 0.5) % 30
        return if (r < 0) {
            r + 30
        } else {
            r
        }
    }

    private fun getFrac(fr: Double): Double {
        return fr - floor(fr)
    }

    private fun calculatePhaseTrig(year: Int, month: Int, day: Int): Double {
        val thisJD = julianDay(year, month, day)
        val k0 = floor((year - 1900) * 12.3685)
        val t = (year - 1899.5) / 100
        val t2 = t * t
        val t3 = t2 * t
        val j0 = 2415020 + 29 * k0
        val f0 = 0.0001178 * t2 - 0.000000155 * t3 +
                (0.75933 + 0.53058868 * k0) - (0.000837 * t + 0.000335 * t2)
        val m0 = 360 * getFrac(k0 * 0.08084821133) + 359.2242 - 0.0000333 * t2 - 0.00000347 * t3
        val m1 = 360 * getFrac(k0 * 0.07171366128) + 306.0253 + 0.0107306 * t2 + 0.00001236 * t3
        val b1 = 360 * getFrac(k0 * 0.08519585128) + 21.2964 -
                (0.0016528 * t2) - (0.00000239 * t3)
        var phase = 0.0
        var jday = 0.0
        var oldJ = 0.0
        while (jday < thisJD) {
            var f = f0 + 1.530588 * phase
            val m5 = (m0 + phase * 29.10534508) * rad
            val m6 = (m1 + phase * 385.81691806) * rad
            val b6 = (b1 + phase * 390.67050646) * rad
            f -= 0.4068 * sin(m6) + (0.1734 - 0.000393 * t) * sin(m5)
            f += 0.0161 * sin(2 * m6) + 0.0104 * sin(2 * b6)
            f -= 0.0074 * sin(m5 - m6) - 0.0051 * sin(m5 + m6)
            f += 0.0021 * sin(2 * m5) + 0.0010 * sin(2 * b6 - m6)
            f += 0.5 / 1440
            oldJ = jday
            jday = j0 + 28 * phase + floor(f)
            phase++
        }
        return (thisJD - oldJ) % 30
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