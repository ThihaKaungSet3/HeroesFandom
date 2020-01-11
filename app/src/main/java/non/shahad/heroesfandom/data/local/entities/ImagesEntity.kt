package non.shahad.heroesfandom.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "images")
data class ImagesEntity(
    @ColumnInfo(name = "xs")val xs: String?,
    @ColumnInfo(name = "sm")val sm: String?,
    @ColumnInfo(name = "md")val md: String?,
    @ColumnInfo(name = "lg")val lg: String?)