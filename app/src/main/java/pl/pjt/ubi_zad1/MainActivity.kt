package pl.pjt.ubi_zad1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import pl.pjt.ubi_zad1.MoonPhaseCalculator
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    var calculator: MoonPhaseCalculator? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectCalculator()
        showCurrentMoonData()
        test()
    }

    private fun selectCalculator() {
        calculator = MoonPhaseCalculator(2)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showCurrentMoonData() {
        val currentDate = LocalDateTime.now()
        calculator!!.calculateMoonPhase(
            currentDate.year,
            currentDate.monthValue,
            currentDate.dayOfMonth
        )
        val moonPercent = calculator!!.moonPercent!!
        val isFirstHalf = calculator!!.isFirstHalf!!
        todayPercent.text = getString(R.string.today, moonPercent.toInt())
        moonImg.setImageResource(MoonImage('n', moonPercent, isFirstHalf).imgName)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun test() {
        val currentDate = LocalDateTime.now()

        calculator = MoonPhaseCalculator(0)
        calculator!!.calculateMoonPhase(
            currentDate.year,
            currentDate.monthValue,
            currentDate.dayOfMonth
        )
        Log.d(
            "phaseTest",
            calculator!!.moonPercent.toString()
        )
        calculator = MoonPhaseCalculator(1)
        calculator!!.calculateMoonPhase(
            currentDate.year,
            currentDate.monthValue,
            currentDate.dayOfMonth
        )
        Log.d(
            "phaseTest",
            calculator!!.moonPercent.toString()
        )
        calculator = MoonPhaseCalculator(2)
        calculator!!.calculateMoonPhase(
            currentDate.year,
            currentDate.monthValue,
            currentDate.dayOfMonth
        )
        Log.d(
            "phaseTest",
            calculator!!.moonPercent.toString()
        )
        calculator = MoonPhaseCalculator(3)
        calculator!!.calculateMoonPhase(
            currentDate.year,
            currentDate.monthValue,
            currentDate.dayOfMonth
        )
        Log.d(
            "phaseTest",
            calculator!!.moonPercent.toString()
        )
    }
}
