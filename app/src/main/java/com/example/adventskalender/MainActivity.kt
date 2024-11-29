package com.example.adventskalender

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * Hauptaktivität des Adventskalenders, die 24 Türchen mit jeweiligem Inhalt anzeigt.
 * Bei Klick auf einen Stern wird der Inhalt des jeweiligen Türchens geöffnet.
 *
 * @author Marco Martins
 * @created 05.11.2024
 */
class MainActivity : AppCompatActivity() {

    private val doorContent = mapOf(
        1 to Pair("Türchen 1", "Dies ist der Text für Türchen 1."),
        2 to Pair("Türchen 2", "Dies ist der Text für Türchen 2."),
        3 to Pair("Türchen 3", "Dies ist der Text für Türchen 3."),
        4 to Pair("Türchen 4", "Dies ist der Text für Türchen 4."),
        5 to Pair("Türchen 5", "Dies ist der Text für Türchen 5."),
        6 to Pair("Türchen 6", "Dies ist der Text für Türchen 6."),
        7 to Pair("Türchen 7", "Dies ist der Text für Türchen 7."),
        8 to Pair("Türchen 8", "Dies ist der Text für Türchen 8."),
        9 to Pair("Türchen 9", "Dies ist der Text für Türchen 9."),
        10 to Pair("Türchen 10", "Dies ist der Text für Türchen 10."),
        11 to Pair("Türchen 11", "Dies ist der Text für Türchen 11."),
        12 to Pair("Türchen 12", "Dies ist der Text für Türchen 12."),
        13 to Pair("Türchen 13", "Dies ist der Text für Türchen 13."),
        14 to Pair("Türchen 14", "Dies ist der Text für Türchen 14."),
        15 to Pair("Türchen 15", "Dies ist der Text für Türchen 15."),
        16 to Pair("Türchen 16", "Dies ist der Text für Türchen 16."),
        17 to Pair("Türchen 17", "Dies ist der Text für Türchen 17."),
        18 to Pair("Türchen 18", "Dies ist der Text für Türchen 18."),
        19 to Pair("Türchen 19", "Dies ist der Text für Türchen 19."),
        20 to Pair("Türchen 20", "Dies ist der Text für Türchen 20."),
        21 to Pair("Türchen 21", "Dies ist der Text für Türchen 21."),
        22 to Pair("Türchen 22", "Dies ist der Text für Türchen 22."),
        23 to Pair("Türchen 23", "Dies ist der Text für Türchen 23."),
        24 to Pair("Türchen 24", "Dies ist der Text für Türchen 24.")
    )

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Heutiges Datum holen
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)

        // Schleife über die Türchen
        for (i in 1..24) {
            val starId = resources.getIdentifier("star_$i", "id", packageName)
            val starView = findViewById<TextView>(starId)

            starView?.let { star ->
                star.setOnClickListener {
                    Log.d("MainActivity", "Stern $i geklickt")
                    val daysRemaining = calculateRemainingDays(i, currentYear)

                    if (daysRemaining > 0) {
                        // Zeige verbleibende Tage
                        showDaysRemainingDialog(daysRemaining)
                    } else {
                        // Öffne das Türchen
                        openDoor(i)
                    }
                }
            } ?: Log.e("MainActivity", "Stern mit ID star_$i wurde nicht gefunden.")
        }
    }

    // Berechne die verbleibenden Tage
    private fun calculateRemainingDays(starDay: Int, currentYear: Int): Int {
        val targetDate = Calendar.getInstance().apply {
            set(currentYear, Calendar.DECEMBER, starDay + 1, 0, 0, 0)
        }
        val diffInMillis = targetDate.timeInMillis - System.currentTimeMillis()
        return (diffInMillis / (1000 * 60 * 60 * 24)).toInt()
    }

    // Zeigt verbleibende Tage in einem Toast an
    private fun showDaysRemainingDialog(daysRemaining: Int) {
        val dialogMessage = if (daysRemaining == 1) {
            "Es fehlt noch 1 Tag, bis du diesen Stern öffnen kannst."
        } else {
            "Es fehlen noch $daysRemaining Tage, bis du diesen Stern öffnen kannst."
        }
        Toast.makeText(this, dialogMessage, Toast.LENGTH_SHORT).show()
    }

    // Öffnet das Türchen-Layout
    @SuppressLint("DiscouragedApi")
    private fun openDoor(day: Int) {
        val content = doorContent[day] ?: Pair("Keine Headline", "Kein Text verfügbar")
        val starId = resources.getIdentifier("star_$day", "id", packageName)
        val star = findViewById<TextView>(starId)

        val intent = Intent(this, DoorOpener::class.java).apply {
            putExtra("starNumber", day.toString())
            putExtra("starColor", star.backgroundTintList?.defaultColor ?: Color.BLACK)
            putExtra("headline", content.first)
            putExtra("text", content.second)
        }
        startActivity(intent)
    }
}
