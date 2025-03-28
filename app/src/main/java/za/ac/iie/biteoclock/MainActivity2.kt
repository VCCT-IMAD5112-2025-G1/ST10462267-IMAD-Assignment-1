package za.ac.iie.biteoclock

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val buttonClear: Button = findViewById(R.id.clearButton)

        buttonClear.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val buttonDone: Button = findViewById(R.id.exitButton)

        // Set an OnClickListener to the exit button
        buttonDone.setOnClickListener {
            // Finish all activities and exit the app
            finishAffinity()
            exitProcess(0)
        }


        val mealSuggestionText = findViewById<TextView>(R.id.mealSuggestionText)

        // Get the time of day from the intent
        val timeOfDay = intent.getStringExtra("TIME_OF_DAY")

        // Show meal suggestions based on time of day
        val mealSuggestion = getMealSuggestion(timeOfDay)
        mealSuggestionText.text = mealSuggestion.toString()
    }

    private fun getMealSuggestion(timeOfDay: String?): Any {
        return when (timeOfDay) {
            "Morning" -> "Breakfast: Eggs, Toast, and Coffee, Muesli and yoghurt, Greek yoghurt, Oatmeal, Breakfast Burritos"
            "Mid-Morning" -> "Snack: Yoghurt with Fruit, Berries, Chia seeds, Blueberry and banana smoothie "
            "Afternoon" -> "Lunch: Sandwich and Salad, Turkey zucchini boats, Blueberry sweet potato and grains bowl "
            "Mid-Afternoon" -> "Snack: Smoothie or Granola Bar"
            "Evening" -> "Dinner: Grilled Chicken with Veggies, Creamy garlic pasta, Easy chicken curry "


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }.toString(),
            -> {
            }

            else -> "Invalid time input"

        }
    }
}