package ru.hse.project.mirfit.ui.profile.card

data class CardObject(
    val balance: Int,
    val cardNumber: String,
    var cardName: String,
    val id: Int?,
    val userId: String
) {
    companion object {
        const val CODE_ID: String = "id"
        const val CODE_NAME: String = "name"
        const val CODE_NUMBER: String = "number"
        const val CODE_COUNT_BONUSES: String = "numberOfBonuses"
        const val CODE_USER_ID: String = "userId"
    }
}



