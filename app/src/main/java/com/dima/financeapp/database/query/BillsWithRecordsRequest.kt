package com.dima.financeapp.database.query

import androidx.room.Embedded
import androidx.room.Relation
import com.dima.financeapp.model.entity.BillEntity
import com.dima.financeapp.model.entity.RecordEntity

data class BillsWithRecordsRequest(
    @Embedded
    val billEntity: BillEntity,
    @Relation(parentColumn = "id", entityColumn = "bill_id", entity = RecordEntity::class)
    val records: List<RecordEntity>
)