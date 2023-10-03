package sakigake.mzaziconnect.mzaziconnectapplication.ui.parent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sakigake.mzaziconnect.mzaziconnectapplication.database.ShopsDataclass
import sakigake.mzaziconnect.mzaziconnectapplication.databinding.ActivityShopsBinding
import sakigake.mzaziconnect.mzaziconnectapplication.ui.teacher.NavActivity

class Shops : AppCompatActivity() {
    lateinit var binding: ActivityShopsBinding
    lateinit var shopsAdapter: ShopsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.rvshops
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val shops = listOf(
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695639596/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695639596/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695633304/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695633304/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695633304/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695633304/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695633304/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695633304/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695633304/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695633304/Frame_208_tdmxjf.png"),
            ShopsDataclass("Utawala New", "Kinca", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695633304/Frame_208_tdmxjf.png")

        )

        shopsAdapter = ShopsAdapter(shops) { selectedShop ->
//            val intent = Intent(this, SubjectChoosenAssignments::class.java)
//            intent.putExtra(this, SubjectChoosenAssignments::class.java)
//            intent.putExtra("TopicName", selectedShop.shopName)
//            intent.putExtra("AssignmentDetails", selectedShop.shoplocation)
//            intent.putExtra("DueDate", selectedShop.shopImage)
//            startActivity(intent)
        }
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvshops.layoutManager = layoutManager
        recyclerView.adapter = shopsAdapter


    }

    override fun onResume() {
        super.onResume()
        binding.imgBack.setOnClickListener {
            val intent = Intent(this@Shops, AssignmentView::class.java)
            startActivity(intent)
        }
        binding.ivHome.setOnClickListener {
            val intent = Intent(this@Shops, NavActivity::class.java)
            startActivity(intent)
        }
    }
}