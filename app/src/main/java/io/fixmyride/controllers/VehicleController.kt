package io.fixmyride.controllers

import com.google.firebase.firestore.FirebaseFirestore
import io.fixmyride.models.Vehicle

class VehicleController {
    companion object {
        /** Returns a list of all vehicles */
        fun getVehicles(): List<Vehicle> {
            val db = FirebaseFirestore.getInstance()
            val vehicles = ArrayList<Vehicle>()

            db.collection("vehicles").get().addOnSuccessListener { docs ->
                for (v in docs) {
                    // TODO add vehicle to *vehicles* list
                }
            }

            return vehicles.toList()
        }

    }
}