package lobna.asset.nutrition.analysis.data

import android.os.Parcel
import android.os.Parcelable

data class IngredientAnalysis(
    var qty: Int,
    var unit: String,
    var food: String,
    val calories: Double,
    val totalWeight: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readFloat()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(qty)
        dest?.writeString(unit)
        dest?.writeString(food)
        dest?.writeDouble(calories)
        dest?.writeFloat(totalWeight)
    }

    companion object CREATOR : Parcelable.Creator<IngredientAnalysis> {
        override fun createFromParcel(parcel: Parcel): IngredientAnalysis {
            return IngredientAnalysis(parcel)
        }

        override fun newArray(size: Int): Array<IngredientAnalysis?> {
            return arrayOfNulls(size)
        }
    }
}