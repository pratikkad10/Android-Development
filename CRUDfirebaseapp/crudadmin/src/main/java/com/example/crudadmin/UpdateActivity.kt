package com.example.crudadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudadmin.databinding.ActivityMainBinding
import com.example.crudadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {
            val ownerName = binding.updateOwnerName.text.toString()
            val vehicleBrand = binding.updateVehicleBrand.text.toString()
            val vehicleNumber = binding.updateVehicleNumber.text.toString()
            val vehicleRTO = binding.updateVehicleRTO.text.toString()

            if(ownerName.isNotEmpty() && vehicleNumber.isNotEmpty() && vehicleRTO.isNotEmpty() && vehicleBrand.isNotEmpty()){
                updateVehicle(vehicleNumber, ownerName, vehicleBrand, vehicleRTO)
            }else{
                Toast.makeText(this, "Enter valid Input!", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateVehicle(
        vehicleNumber: String,
        ownerName: String,
        vehicleBrand: String,
        vehicleRTO: String
    ) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        val vehicleData = mapOf<String, String>("ownerName" to ownerName, "vehicleBrand" to vehicleBrand, "vehicleRTO" to vehicleRTO)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData)
            .addOnSuccessListener {
                binding.updateVehicleRTO.text?.clear()
                binding.updateVehicleNumber.text?.clear()
                binding.updateVehicleBrand.text?.clear()
                binding.updateOwnerName.text?.clear()
                Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
            }
    }
}