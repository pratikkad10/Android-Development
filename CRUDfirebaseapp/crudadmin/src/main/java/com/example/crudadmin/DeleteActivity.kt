package com.example.crudadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding : ActivityDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.deleteButton.setOnClickListener {
           val vehicleNumber = binding.inputVehicleNumber.text.toString()

           if(vehicleNumber.isNotEmpty()){
               deleteVehicle(vehicleNumber)
           }else{
               Toast.makeText(this, "Enter vehicle number!" , Toast.LENGTH_SHORT).show()
           }
       }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun deleteVehicle(vehicleNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).removeValue()
            .addOnSuccessListener {
                binding.inputVehicleNumber.text?.clear()
                Toast.makeText(this, "Deleted successfully!" , Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to delete!", Toast.LENGTH_SHORT).show()

            }
    }
}