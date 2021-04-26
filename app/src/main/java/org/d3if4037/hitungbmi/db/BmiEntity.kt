package org.d3if4037.hitungbmi.db

import androidx.room.Entity
import androidx.room.PrimaryKey

class BmiEntity {
    @Entity(tableName = "bmi")
    data class BmiEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0L,
        var tanggal: Long = System.currentTimeMillis(),
        var berat: Float,
        var tingg: Float,
        var isMale: Boolean
    )
}