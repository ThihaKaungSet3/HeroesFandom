package non.shahad.heroesfandom.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "appearance")
data class AppearanceEntity(
    @ColumnInfo(name = "gender")val gender: String?,
    @ColumnInfo(name = "race")val race: String?,
    @ColumnInfo(name = "height")val height: List<String>?,
    @ColumnInfo(name = "weight")val weight: List<String>?,
    @ColumnInfo(name = "eyeColor")val eyeColor: String?,
    @ColumnInfo(name = "hairColor") val hairColor: String?)