package gumilar.unikom.formulirfgdapps.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Participant(
    val name: String,
    val phone: String,
    val email: String,
    val gender: String,
    val skillset: List<String>,
    val category: String
) : Parcelable
