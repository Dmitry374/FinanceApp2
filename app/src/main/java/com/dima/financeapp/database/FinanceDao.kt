package com.dima.financeapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dima.financeapp.database.query.BillsWithRecordsRequest
import com.dima.financeapp.model.entity.BillEntity
import com.dima.financeapp.model.entity.RecordEntity
import com.dima.financeapp.model.entity.UserEntity
import io.reactivex.Single

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    fun getUser(email: String): Single<UserEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(userEntity: UserEntity)

    @Query("DELETE FROM user")
    fun clearUserData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBills(bills: List<BillEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(bill: BillEntity)

    @Query("SELECT * FROM bill")
    fun getBills(): Single<List<BillsWithRecordsRequest>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBill(billEntity: BillEntity)

    @Delete
    fun deleteBill(billEntity: BillEntity)

    @Query("DELETE FROM bill")
    fun clearBillsData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(recordEntity: RecordEntity)

    @Query("SELECT * FROM record ORDER BY id DESC LIMIT :count")
    fun getLastRecords(count: Int): Single<List<RecordEntity>>

    @Query("DELETE FROM record")
    fun clearRecordsData()
}