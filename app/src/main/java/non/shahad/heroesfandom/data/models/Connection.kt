package non.shahad.heroesfandom.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Connection(
    val groupAffiliation: String?,
    val relatives: String?) : Parcelable