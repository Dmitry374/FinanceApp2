package com.dima.financeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dima.financeapp.model.entity.BillEntity
import com.dima.financeapp.model.entity.RecordEntity
import com.dima.financeapp.model.entity.UserEntity

@Database(
    entities = [UserEntity::class, BillEntity::class, RecordEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FinanceDatabase : RoomDatabase() {

    abstract fun financeDao(): FinanceDao
}