package pl.pjt.ubi_zad1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    var selectedHemisphere: Char = 'n'
    var selectedAlgorithm: Int = 0
    var calculator: MoonPhaseCalculator? = null

    private val settingsViewReqCode: Int = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calculator = MoonPhaseCalculator()
        setContentView(R.layout.activity_main)
        showAllMoonInfo()
        test()
    }

    private fun showCurrentMoonPercent() {
        val currentDate = LocalDate.now()
        val phase = calculator!!.calculateMoonPhase(
            selectedAlgorithm, currentDate
        )
        val moonPercent = calculator!!.getMoonPercent(phase)
        val isFirstHalf = calculator!!.isFirstHalf(phase)
        todayPercent.text = getString(R.string.today, moonPercent.toInt())
        moonImg.setImageResource(MoonImage(selectedHemisphere, moonPercent, isFirstHalf).imgName)
    }

    private fun formatDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return date.format(formatter)
    }

    private fun showLastNewMoonDate() {
        val date = calculator!!.calculateLastNewMoon(selectedAlgorithm, LocalDate.now())
        lastNewMoon.text = getString(R.string.last_new_moon, formatDate(date))
    }

    private fun showNextFullMoonDate() {
        val date = calculator!!.calculateNextFullMoon(selectedAlgorithm, LocalDate.now())
        nextFullMoon.text = getString(R.string.next_full_moon, formatDate(date))
    }

    private fun showAllMoonInfo() {
        showCurrentMoonPercent()
        showLastNewMoonDate()
        showNextFullMoonDate()
    }

    fun settingsButtonClick(v: View) {
        showSettingsActivity()
    }

    private fun showSettingsActivity() {
        val i = Intent(this, SettingsActivity::class.java);
        i.putExtra("SelectedAlgorithm", selectedAlgorithm)
        i.putExtra("SelectedHemisphere", selectedHemisphere)
        startActivityForResult(i, settingsViewReqCode)
    }

    fun fullMoonsInYearButtonClick(v: View) {
        showFullMoonsYearActivity()
    }

    private fun showFullMoonsYearActivity() {
        val i = Intent(this, FullMoonsYearActivity::class.java)
        i.putExtra("SelectedAlgorithm", selectedAlgorithm)
        startActivity(i)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == settingsViewReqCode) && (resultCode == Activity.RESULT_OK)) {
            if (data != null) {
                if (data.hasExtra("SelectedAlgorithm")) {
                    selectedAlgorithm = data.extras!!.getInt("SelectedAlgorithm")
                }
                if (data.hasExtra("SelectedHemisphere")) {
                    selectedHemisphere = data.extras!!.getChar("SelectedHemisphere")
                }
                showAllMoonInfo()
            }
        }
    }

    private fun test() {
        val currentDate = LocalDate.now()
        for (i in 0..4) {
            val phase = calculator!!.calculateMoonPhase(i, currentDate)
            Log.d("phaseTest", calculator!!.getMoonPercent(phase).toString())
        }
    }
}
