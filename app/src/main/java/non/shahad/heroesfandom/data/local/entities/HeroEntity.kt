package non.shahad.heroesfandom.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero")
data class HeroEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")val id: Long?,
    @ColumnInfo(name = "name")val name: String?,
    @ColumnInfo(name = "slug")val slug: String?,
    @Embedded val powerstats: PowerStatsEntity?,
    @Embedded val appearance: AppearanceEntity?,
    @Embedded val biography: BiographyEntity?,
    @Embedded val work: WorkEntity?,
    @Embedded val connections: ConnectionEntity?,
    @Embedded val images: ImagesEntity?)

