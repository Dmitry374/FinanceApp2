package com.dima.financeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dima.financeapp.model.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FinanceDatabase : RoomDatabase() {

    abstract fun financeDao(): FinanceDao
}