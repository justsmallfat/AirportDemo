package com.smallfat5566.airportdemo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class APISampleResponse (
    val data: Map<String, Double>
): Parcelable
