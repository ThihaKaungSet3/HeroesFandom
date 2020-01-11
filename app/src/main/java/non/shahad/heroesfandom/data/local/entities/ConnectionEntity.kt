package non.shahad.heroesfandom.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "connection")
data class ConnectionEntity(
    @ColumnInfo(name = "groupAffiliation")val groupAffiliation: String?,
    @ColumnInfo(name = "relatives")val relatives: String?)