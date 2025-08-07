package com.example.crudfirebaseapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudfirebaseapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchButton.setOnClickListener {
            val vehicleNumber = binding.inputVehicleNumber.text.toString()
            if(vehicleNumber.isNotEmpty()){
                readData(vehicleNumber)
            }else{
                Toast.makeText(this, "Enter vehicle number!", Toast.LENGTH_SHORT).show()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun readData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).get()
            .addOnSuccessListener {
                if(it.exists()){
                    val ownerName = it.child("ownerName").value
                    val vehicleRTO = it.child("vehicleRTO").value
                    val vehicleBrand = it.child("vehicleBrand").value
                    Toast.makeText(this, "Vehicle Found!", Toast.LENGTH_SHORT).show()
                    binding.ownerNameField.text = ownerName.toString()
                    binding.vehicleBrandField.text = vehicleBrand.toString()
                    binding.vehicleRTOFeild.text = vehicleRTO.toString()
                }else{
                    Toast.makeText(this, "Vehicle does not exist!", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()

            }
    }
}