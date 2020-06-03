package non.shahad.heroesfandom.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import non.shahad.heroesfandom.data.models.*

@Parcelize
@Entity(tableName = "hero")
data class HeroEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")val id: Long?,
    @ColumnInfo(name = "name")val name: String?,
    @ColumnInfo(name = "slug")val slug: String?,
    @Embedded var powerstats: PowerStats? = null,
    @Embedded var appearance: Appearance? = null,
    @Embedded var biography: Biography? = null,
    @Embedded var work: Work? = null,
    @Embedded var connections: Connection? = null,
    @Embedded var images: Images? = null) : Parcelable

