package io.fixmyride.data.repositories

import io.fixmyride.data.models.Vehicle

interface VehicleRepository {
    suspend fun addVehicle(vehicle: Vehicle)

    suspend fun deleteVehicleById(id: Int)

    suspend fun getVehicleById(id: Int): Vehicle

    suspend fun updateVehicle(vehicle: Vehicle)

    suspend fun getVehicles(): List<Vehicle>

    suspend fun getVehiclesOrderedByModel(): List<Vehicle>

    suspend fun getVehiclesOrderedByRegistration(): List<Vehicle>

    suspend fun getVehiclesOrderedByTPLInsurance(): List<Vehicle>

    suspend fun getVehiclesOrderedByCIInsurance(): List<Vehicle>

    suspend fun getVehiclesOrderedByNextInspectionDate(): List<Vehicle>
}