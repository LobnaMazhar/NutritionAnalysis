package lobna.asset.nutrition.analysis.data

data class TotalNutrition(
    val calories: Int,
    val totalNutrients: TotalNutrient
) {
    data class TotalNutrient(
        val FAT: TotalNutrientModel = TotalNutrientModel(),
        val CHOLE: TotalNutrientModel = TotalNutrientModel(),
        val NA: TotalNutrientModel = TotalNutrientModel(),
        val CHOCDF: TotalNutrientModel = TotalNutrientModel(),
        val PROCNT: TotalNutrientModel = TotalNutrientModel(),
        val VITD: TotalNutrientModel = TotalNutrientModel(),
        val CA: TotalNutrientModel = TotalNutrientModel(),
        val FE: TotalNutrientModel = TotalNutrientModel(),
        val K: TotalNutrientModel = TotalNutrientModel()
    )

    data class TotalNutrientModel(
        val label: String = "",
        val quantity: Double = 0.0,
        val unit: String = ""
    )
}