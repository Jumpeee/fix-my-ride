package io.fixmyride.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import io.fixmyride.models.Notification
import io.fixmyride.models.Vehicle

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM vehicle")
    suspend fun getData(): List<Vehicle>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVehicle(vehicle: Vehicle)

    @Query("DELETE FROM vehicle WHERE id = :id")
    suspend fun deleteVehicleById(id: Int)

    @Upsert
    suspend fun updateVehicle(vehicle: Vehicle)

    @Query("SELECT * FROM vehicle")
    suspend fun getVehicles(): List<Vehicle>

    @Query("SELECT * FROM vehicle ORDER BY model DESC")
    suspend fun getVehiclesOrderedByModel(): List<Vehicle>

    @Query("SELECT * FROM vehicle ORDER BY registration DESC")
    suspend fun getVehiclesOrderedByRegistration(): List<Vehicle>

    @Query("SELECT * FROM vehicle ORDER BY tplInsuranceExpiry DESC")
    suspend fun getVehiclesOrderedByTPLInsurance(): List<Vehicle>

    @Query("SELECT * FROM vehicle ORDER BY collisionInsuranceExpiry DESC")
    suspend fun getVehiclesOrderedByCIInsurance(): List<Vehicle>

    @Query("SELECT * FROM vehicle ORDER BY nextInspectionDate DESC")
    suspend fun getVehiclesOrderedByNextInspectionDate(): List<Vehicle>

    @Query("SELECT * FROM vehicle WHERE id = :id")
    suspend fun getVehicleById(id: Int): Vehicle

    @Query("SELECT * FROM notification")
    suspend fun getNotifications(): List<Notification>

    @Query("DELETE FROM notification")
    suspend fun deleteNotifications()
}