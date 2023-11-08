package io.fixmyride.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import io.fixmyride.data.models.Vehicle

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVehicle(vehicle: Vehicle)

    @Query("DELETE FROM vehicles WHERE id = :id")
    suspend fun deleteVehicleById(id: Int)

    @Query("SELECT * FROM vehicles WHERE id = :id")
    suspend fun getVehicleById(id: Int): Vehicle

    @Upsert
    suspend fun updateVehicle(vehicle: Vehicle)

    @Query("SELECT * FROM vehicles")
    suspend fun getVehicles(): List<Vehicle>

    @Query("SELECT * FROM vehicles ORDER BY model DESC")
    suspend fun getVehiclesOrderedByModel(): List<Vehicle>

    @Query("SELECT * FROM vehicles ORDER BY registration DESC")
    suspend fun getVehiclesOrderedByRegistration(): List<Vehicle>

    @Query("SELECT * FROM vehicles ORDER BY tplInsuranceExpiry ASC")
    suspend fun getVehiclesOrderedByTPLInsurance(): List<Vehicle>

    @Query("SELECT * FROM vehicles ORDER BY collisionInsuranceExpiry ASC")
    suspend fun getVehiclesOrderedByCIInsurance(): List<Vehicle>

    @Query("SELECT * FROM vehicles ORDER BY nextInspectionDate ASC")
    suspend fun getVehiclesOrderedByNextInspectionDate(): List<Vehicle>
}