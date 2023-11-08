package io.fixmyride.data.repositories

import io.fixmyride.data.database.DatabaseDao
import io.fixmyride.data.models.Vehicle
import io.fixmyride.data.repositories.VehicleRepository

class VehicleRepositoryImpl(
    private val dao: DatabaseDao
) : VehicleRepository {
    override suspend fun addVehicle(vehicle: Vehicle) {
        dao.addVehicle(vehicle)
    }

    override suspend fun deleteVehicleById(id: Int) {
        dao.deleteVehicleById(id)
    }

    override suspend fun getVehicleById(id: Int): Vehicle {
        return dao.getVehicleById(id)
    }

    override suspend fun updateVehicle(vehicle: Vehicle) {
        dao.updateVehicle(vehicle)
    }

    override suspend fun getVehicles(): List<Vehicle> {
        return dao.getVehicles()
    }

    override suspend fun getVehiclesOrderedByModel(): List<Vehicle> {
        return dao.getVehiclesOrderedByModel()
    }

    override suspend fun getVehiclesOrderedByRegistration(): List<Vehicle> {
        return dao.getVehiclesOrderedByRegistration()
    }

    override suspend fun getVehiclesOrderedByTPLInsurance(): List<Vehicle> {
        return dao.getVehiclesOrderedByTPLInsurance()
    }

    override suspend fun getVehiclesOrderedByCIInsurance(): List<Vehicle> {
        return dao.getVehiclesOrderedByCIInsurance()
    }

    override suspend fun getVehiclesOrderedByNextInspectionDate(): List<Vehicle> {
        return dao.getVehiclesOrderedByNextInspectionDate()
    }
}