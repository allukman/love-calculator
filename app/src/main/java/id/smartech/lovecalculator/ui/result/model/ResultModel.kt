package id.smartech.lovecalculator.ui.result.model

import com.google.gson.annotations.SerializedName

data class ResultModel (
    @SerializedName("fname")val fName: String,
    @SerializedName("sname")val sName: String,
    @SerializedName("percentage")val percentage: String,
    @SerializedName("result")val result: String
)