package com.dima.financeapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dima.financeapp.model.entity.UserEntity
import io.reactivex.Single

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    fun getUserData(email: String): Single<UserEntity>

    @Query("DELETE FROM user")
    fun clearUserData()
}