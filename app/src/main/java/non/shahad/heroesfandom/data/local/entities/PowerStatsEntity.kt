package non.shahad.heroesfandom.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "powerstats")
data class PowerStatsEntity(
    @ColumnInfo(name = "intelligence")val intelligence: Number?,
    @ColumnInfo(name = "strength")val strength: Int?,
    @ColumnInfo(name = "speed")val speed: Int?,
    @ColumnInfo(name = "durability")val durability: Number?,
    @ColumnInfo(name = "power")val power: Int?,
    @ColumnInfo(name = "combat")val combat: Int?)