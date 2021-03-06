package non.shahad.heroesfandom.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PowerStats(
    val intelligence: Int?,
    val strength: Int?,
    val speed: Int?,
    val durability: Int?,
    val power: Int?,
    val combat: Int?) : Parcelable