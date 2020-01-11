package non.shahad.heroesfandom.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "work")
data class WorkEntity(
    @ColumnInfo(name = "occupation")val occupation: String?,
    @ColumnInfo(name = "base")val base: String?)