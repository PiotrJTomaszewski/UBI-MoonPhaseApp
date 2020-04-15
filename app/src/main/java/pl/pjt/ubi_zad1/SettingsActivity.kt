package pl.pjt.ubi_zad1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.settings_activity.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        val extras = intent.extras ?: return
        val currentAlgorithm = extras.getInt("SelectedAlgorithm")
        val currentHemisphere = extras.getChar("SelectedHemisphere")
        if (currentHemisphere == 'n') hemisphereN.isChecked = true
        else hemisphereS.isChecked = true
        when (currentAlgorithm) {
            0 -> algorithmSimple.isChecked = true
            1 -> algorithmConway.isChecked = true
            2 -> algorithmTrig1.isChecked = true
            3 -> algorithmTrig2.isChecked = true
        }
    }

    private fun confirmAndSave() {
        val data = Intent()
        // TODO: Save settings
        val selectedAlgorithm = when {
            algorithmSimple.isChecked -> 0
            algorithmConway.isChecked -> 1
            algorithmTrig1.isChecked -> 2
            else -> 3 // Trig2
        }
        val selectedHemisphere = if (hemisphereN.isChecked) 'n' else 's'
        data.putExtra("SelectedAlgorithm", selectedAlgorithm)
        data.putExtra("SelectedHemisphere", selectedHemisphere)
        setResult(Activity.RESULT_OK, data)
    }

    fun confirmButtonClick(v: View) {
        confirmAndSave()
        finish()
    }

    fun cancelButtonClick(v: View) {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}