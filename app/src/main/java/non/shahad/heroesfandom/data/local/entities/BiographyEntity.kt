package non.shahad.heroesfandom.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "biography")
data class BiographyEntity(
    @ColumnInfo(name = "fullName")val fullName: String?,
    @ColumnInfo(name = "alterEgos")val alterEgos: String?,
    @ColumnInfo(name = "aliases")val aliases: List<String>?,
    @ColumnInfo(name = "placeOfBirth")val placeOfBirth: String?,
    @ColumnInfo(name = "firstAppearance")val firstAppearance: String?,
    @ColumnInfo(name = "publisher")val publisher: String?,
    @ColumnInfo(name = "alignment")val alignment: String?)