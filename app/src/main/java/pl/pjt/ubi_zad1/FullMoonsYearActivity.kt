package pl.pjt.ubi_zad1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.full_moons_year_activity.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FullMoonsYearActivity : AppCompatActivity() {
//    private val dateFieldsArray = arrayOf(
//        fullMoonDate00, fullMoonDate01, fullMoonDate02, fullMoonDate03, fullMoonDate04,
//        fullMoonDate05, fullMoonDate06, fullMoonDate07, fullMoonDate08, fullMoonDate09,
//        fullMoonDate10, fullMoonDate11
//    )

    val minYear = 1900
    val maxYear = 2200
    val yearRegex = Regex("[1-9][0-9]{3}")


    var selectedAlgorithm: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_moons_year_activity)
        val extras = intent.extras ?: return
        selectedAlgorithm = extras.getInt("SelectedAlgorithm")
        chosenYear.setText(LocalDate.now().year.toString())
        calculateFullMoonsInYear(chosenYear.text.toString())
        chosenYear.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (yearRegex.matches(chosenYear.text.toString())) {
                    calculateFullMoonsInYear(chosenYear.text.toString())
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    fun yearMinusOne(view: View) {
        if (yearRegex.matches(chosenYear.text.toString())) {
            val newYear = chosenYear.text.toString().toInt() - 1
            if (newYear in minYear..maxYear) {
                chosenYear.setText(newYear.toString())
            }
        }
    }

    fun yearPlusOne(view: View) {
        if (yearRegex.matches(chosenYear.text.toString())) {
            val newYear = chosenYear.text.toString().toInt() + 1
            if (newYear in minYear..maxYear) {
                chosenYear.setText(newYear.toString())
            }
        }
    }

    private fun formatDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return date.format(formatter)
    }


    private fun calculateFullMoonsInYear(year: String) {
        Log.d("DEBUG", year)
        val calculator = MoonPhaseCalculator()
        val dates = calculator.calculateFullMoonsInYear(selectedAlgorithm, year)
        fullMoonDate00.text = formatDate(dates[0])
        fullMoonDate01.text = formatDate(dates[1])
        fullMoonDate02.text = formatDate(dates[2])
        fullMoonDate03.text = formatDate(dates[3])
        fullMoonDate04.text = formatDate(dates[4])
        fullMoonDate05.text = formatDate(dates[5])
        fullMoonDate06.text = formatDate(dates[6])
        fullMoonDate07.text = formatDate(dates[7])
        fullMoonDate08.text = formatDate(dates[8])
        fullMoonDate09.text = formatDate(dates[9])
        fullMoonDate10.text = formatDate(dates[10])
        fullMoonDate11.text = formatDate(dates[11])
    }

}