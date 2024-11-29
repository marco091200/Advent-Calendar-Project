package com.example.adventskalender

import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Diese Aktivität zeigt den Inhalt eines Adventskalender-Türchens.
 * Sie zeigt die Sternnummer, -farbe, Headline und Text an.
 * Bei Tür 24 wird ein Weihnachtslied abgespielt.
 *
 * @author Marco Martins
 * @created 10.11.2024
 */

class DoorOpener : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    /**
     * Initialisiert die Aktivität und zeigt die übergebenen Daten an.
     *
     * @param savedInstanceState Enthält gespeicherte Instanzdaten, wenn vorhanden.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calender_door)

        val starNumber = intent.getStringExtra("starNumber") ?: "?"
        val starColor = intent.getIntExtra("starColor", Color.WHITE)
        val headline = intent.getStringExtra("headline") ?: "Keine Headline"
        val text = intent.getStringExtra("text") ?: "Kein Text verfügbar"

        Log.d(
            "DoorOpener",
            "Sternnummer: $starNumber, Farbe: $starColor, Headline: $headline, Text: $text"
        )

        val starTextView = findViewById<TextView>(R.id.star)
        starTextView.text = starNumber
        starTextView.backgroundTintList = ColorStateList.valueOf(starColor)

        val headlineView = findViewById<TextView>(R.id.leer)
        val textView = findViewById<TextView>(R.id.leer2)
        headlineView.text = headline
        textView.text = text

        if (starNumber == "24") {
            playMusic()
        }
    }

    /**
     * Spielt ein Weihnachtslied ab.
     */
    private fun playMusic() {
        mediaPlayer = MediaPlayer.create(
            this,
            R.raw.christmas_song
        ) // Ersetze "music" mit dem tatsächlichen Dateinamen
        mediaPlayer?.start()
    }

    /**
     * Stoppt die Musik, wenn die Aktivität pausiert wird.
     */
    override fun onPause() {
        super.onPause()
        stopMusic()
    }

    /**
     * Stoppt die Musik, wenn die Aktivität gestoppt wird.
     */
    override fun onStop() {
        super.onStop()
        stopMusic()
    }

    /**
     * Stoppt den MediaPlayer und gibt Ressourcen frei.
     */
    private fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
