package pl.pjt.ubi_zad1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    private fun selectCalculator() {
        calculator = MoonPhaseCalculator(2)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showCurrentMoonData() {
        val currentDate = LocalDateTime.now()
        val phase = calculator!!.calculateMoonPhase(
            currentDate.year,
            currentDate.monthValue,
            currentDate.dayOfMonth
        )
        todayPercent.text = getString(R.string.today, phase.toInt())
        setMoonImg(phase)
    }

    private fun setMoonImg(phase: Double) {
        moonImg.setImageResource(MoonImage('n', phase).imgName)
    }
}
