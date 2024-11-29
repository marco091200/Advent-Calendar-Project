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

class MainActivity : AppCompatActivity() {

    // Inhalte für die Türchen
    private val doorContent = mapOf(
        1 to Pair(
            "Fußmassage",
            "Heute beginnt die Adventszeit!\n " +
                    "Du bekommst eine 30-minütige Fußmassage, um entspannt reinzustarten. " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        2 to Pair(
            "Spültag",
            "Heute übernehme ich den Abwasch! \n" +
                    "Lass alles einfach stehen, ich kümmere mich darum. " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        3 to Pair(
            "Kleine Botschaft",
            "Du bist der hellste Stern in meinem Leben. \n " +
                    "Ich bin so froh, dass es dich gibt! " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        4 to Pair(
            "Minecraft Abend",
            "Heute spielen wir Minecraft, " +
                    "aber auf eine ganz neue, kreative Art! " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        5 to Pair(
            "Kleine Botschaft",
            "Denke daran: Jeder Tag mit dir ist ein Geschenk. \n " +
                    "Danke, dass du immer für mich da bist. " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        6 to Pair(
            "Türchenjagd",
            "In deiner Wohnung ist ein kleines Adventstürchen versteckt. \n" +
                    "Tipp: Schau unter deinem Bett! " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        7 to Pair(
            "Spaziergang",
            "Heute machen wir gemeinsam einen gemütlichen Spaziergang. " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        8 to Pair(
            "Backtag",
            "Was gibt es Schöneres in der Weihnachtszeit als Plätzchen zu backen?\n " +
                    "Lass uns zusammen in der Küche kreativ werden! " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        9 to Pair(
            "Spültag",
            "Heute übernehme ich erneut den Abwasch für dich. \n" +
                    "Lehn dich zurück und entspann dich! " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        10 to Pair(
            "Filmabend",
            "Heute schauen wir gemeinsam einen Film auf der Couch! " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        11 to Pair(
            "Flachwitz",
            "Heute war Schrottwichteln im Kindergarten.\n " +
                    "Wir sind die neuen Eltern von Kevin." +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        12 to Pair(
            "Kinogutschein",
            "Hier ist ein Gutschein für unseren heutigen Kinoabend!\n " +
                    "Code: CZ202401803345 " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        13 to Pair(
            "Türchenjagd",
            "Gehe in die Küche – dort findest du etwas, um deine selbst gebackenen Plätzchen sicher aufzubewahren! " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        14 to Pair(
            "Flachwitz",
            "Wie nennt man jemanden, der so tut, als würde er etwas werfen? " +
                    "\nScheinwerfer." +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        15 to Pair(
            "Flachwitz",
            "Wie nennt man einen Bumerang, der nicht zurückkommt? " +
                    "\nStock." +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        16 to Pair(
            "Kleine Botschaft",
            "Ich bin so dankbar, dich in meinem Leben zu haben.\n " +
                    "Du bringst so viel Freude und Liebe in meine Welt. " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        17 to Pair(
            "Türchenjagd",
            "Etwas hat sich in der Dusche verändert.\n " +
                    "Kannst du entdecken, was es ist? " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        18 to Pair(
            "Flachwitz",
            "Ich hab einem Hipster ins Bein geschossen. " +
                    "\nJetzt hoppst er." +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        19 to Pair(
            "Kleine Botschaft",
            "Du bist etwas ganz Besonderes für mich.\n " +
                    "Jeder Moment mit dir ist ein wahrer Schatz! " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        20 to Pair(
            "Abendessen",
            "Mach dich schick!\n " +
                    "Ich hole dich heute Abend ab, und wir genießen ein schönes gemeinsames Essen. " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        21 to Pair(
            "Flachwitz",
            "\"Anton, findest du, dass ich eine schlechte Mutter bin?\" \n \"Ich heiße Paul.\"" +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        22 to Pair(
            "Wellness Abend",
            "Heute machen wir deine Wohnung zu einem Spa!" +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        23 to Pair(
            "Türchenjagd",
            "In deiner Wohnung ist ein kleines Adventstürchen versteckt. \n" +
                    "Tipp: Schau in deinem Abstellraum! " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        ),
        24 to Pair(
            "Weihnachten!",
            "Frohe Weihnachten, mein kleiner Engel!\n " +
                    "Mache den Handyton an! \n Eine letzte Überraschung wartet versteckt auf dich. " +
                    "\n\nVergiss nicht, in deinen Adventskalender zu schauen."
        )
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
