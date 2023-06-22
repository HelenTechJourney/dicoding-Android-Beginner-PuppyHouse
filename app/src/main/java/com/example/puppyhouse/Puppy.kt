package com.example.puppyhouse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Puppy(
    var name: String = "",
    var description: String = "",
    var photo: Int = 0,
    var puppies_height : String = "",
    var puppies_weight : String = "",
    var life_expectancy : String = ""
 ) : Parcelable