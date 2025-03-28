package za.ac.iie.biteoclock

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val timeString = findViewById<EditText>(R.id.TimeOfTheMeal)
        val enterButton = findViewById<Button>(R.id.btnEnter)

        enterButton.setOnClickListener {
            // Get the text entered in the EditText
            val inputTime = timeString.text.toString()

            Log.d("MainActivity", "Input time1 $inputTime")

            // Make sure the input has exactly 4 digits
            if (inputTime.length == 4) {
                Log.d("MainActivity", "Input time2 $inputTime")
                // Extract the hour and minute from the 4-digit input.
                val hour = inputTime.substring(0, 2).toIntOrNull() ?: 0
                val minute = inputTime.substring(2, 4).toIntOrNull() ?: 0

                // Classify the time based on the hour
                val timeOfDay = classifyTime(hour)

                // Go to the meal suggestion page according to the classification.
                val intent = Intent(this, MainActivity2::class.java)  // create the explicit intent
                intent.putExtra("TIME_OF_DAY", timeOfDay)
                startActivity(intent)
                Log.d("MainActivity", "Input time3 $inputTime")
            } else {
                Log.d("MainActivity", "Input time4 $inputTime")
                // Handle invalid time format (e.g., not a 4-digit time)
                timeString.error = "Please enter a valid time in HHMM format"
            }
        }
    }

    // Function to classify time of the day based on the hour of the day
    private fun classifyTime(hour: Int): String {
        Log.d("MainActivity", "Input time4 $hour")
        return when (hour) {
            in 5..9 -> "Morning"
            in 9..11 -> "Mid-Morning"
            in 12..16 -> "Afternoon"
            in 16..18 -> "Mid-Afternoon"
            in 18..23 -> "Evening"
            else -> "Invalid Time"  // Prints invalid time input when the user puts in the wrong time
        }

    }
}