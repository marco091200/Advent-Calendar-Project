// DoorOpener.kt
package com.example.adventskalender

import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DoorOpener : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calender_door)

        // Hole die übergebenen Daten (Sternnummer, -farbe, headline und text)
        val starNumber = intent.getStringExtra("starNumber") ?: "?"
        val starColor = intent.getIntExtra("starColor", Color.WHITE)
        val headline = intent.getStringExtra("headline") ?: "Keine Headline"
        val text = intent.getStringExtra("text") ?: "Kein Text verfügbar"

        // Log-Ausgaben zur Bestätigung der erhaltenen Daten
        Log.d("DoorOpener", "Sternnummer: $starNumber, Farbe: $starColor, Headline: $headline, Text: $text")

        // Setze die Sternnummer und -farbe im DoorOpener Layout
        val starTextView = findViewById<TextView>(R.id.star)
        starTextView.text = starNumber
        starTextView.backgroundTintList = ColorStateList.valueOf(starColor)

        // Setze die Headline und den Text
        val headlineView = findViewById<TextView>(R.id.leer)
        val textView = findViewById<TextView>(R.id.leer2)
        headlineView.text = headline
        textView.text = text

        if (starNumber == "24") {
            playMusic()
        }
    }

    // Funktion zum Abspielen der Musik
    private fun playMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.christmas_song) // Ersetze "music" mit dem tatsächlichen Dateinamen
        mediaPlayer?.start()
    }

    // Stoppe die Musik, wenn die Aktivität pausiert oder gestoppt wird
    override fun onPause() {
        super.onPause()
        stopMusic()
    }

    // Stoppe die Musik, wenn die Aktivität gestoppt wird
    override fun onStop() {
        super.onStop()
        stopMusic()
    }

    // Funktion zum Stoppen der Musik
    private fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
