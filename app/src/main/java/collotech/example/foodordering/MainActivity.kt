package collotech.example.foodordering

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    data class Food(val name: String, val price: Int, val imageRes: Int, var quantity: Int = 0)

    private val foodList = arrayListOf(
        Food("Burger", 65, R.drawable.buger),
        Food("Pizza", 75, R.drawable.pizaa),
        Food("Fries", 125, R.drawable.fries),
        Food("Sprite", 45, R.drawable.sprite),
        Food("smoocher", 20, R.drawable.smoocher),
        Food("Hot dogs", 14, R.drawable.hotdogs),
        Food("Ice Pop", 2, R.drawable.icepop),
        Food("Coke", 40, R.drawable.coke),
        Food("Fish", 60, R.drawable.fish),
        Food("Delamere", 10, R.drawable.delamere),
        Food("Fries Chicken", 100, R.drawable.friedchken),
        Food("County", 165, R.drawable.county)

    )
    private var total = 0

    private lateinit var totalText: TextView
    private lateinit var foodListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        totalText = findViewById(R.id.totalText)
        foodListView = findViewById(R.id.foodListView)

        val adapter = object : ArrayAdapter<Food>(this, R.layout.activity_list, R.id.food, foodList) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = layoutInflater.inflate(R.layout.activity_list, parent, false)
                val food = foodList[position]

                val image = view.findViewById<ImageView>(R.id.img)
                val name = view.findViewById<TextView>(R.id.food)
                val priceText = view.findViewById<TextView>(R.id.price)
                val qty = view.findViewById<TextView>(R.id.qty)
                val add = view.findViewById<Button>(R.id.add)
                val remove = view.findViewById<Button>(R.id.subtract)

                image.setImageResource(food.imageRes)
                name.text = food.name
                priceText.text = "Price: $${food.price}"
                qty.text = food.quantity.toString()

                add.setOnClickListener {
                    food.quantity++
                    total += food.price
                    qty.text = food.quantity.toString()
                    totalText.text = "Total: $$total"
                }

                remove.setOnClickListener {
                    if (food.quantity > 0) {
                        food.quantity--
                        total -= food.price
                        qty.text = food.quantity.toString()
                        totalText.text = "Total: $$total"
                    } else {
                        Toast.makeText(context, "Quantity is already 0", Toast.LENGTH_SHORT).show()
                    }
                }

                return view
            }
        }

        foodListView.adapter = adapter


    }
}