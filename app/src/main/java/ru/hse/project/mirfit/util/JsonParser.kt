package ru.hse.project.mirfit.util

import org.json.JSONArray
import ru.hse.project.mirfit.ui.profile.card.CardObject

class JsonParser {
    companion object {

        fun parseCards(json: JSONArray): ArrayList<CardObject> {
            val cards: ArrayList<CardObject> = arrayListOf()
            if (json.length() != 0) {

                for (i in 0 until json.length()) {
                    val obj = json.getJSONObject(i)
                    val id = obj.getInt(CardObject.CODE_ID)
                    val name = obj.getString(CardObject.CODE_NAME)
                    val number = obj.getString(CardObject.CODE_NUMBER)
                    val countBonuses = obj.getInt(CardObject.CODE_COUNT_BONUSES)
                    val userId = obj.getString(CardObject.CODE_USER_ID)
                    cards.add(CardObject(countBonuses, number, name, id, userId))
                }
            }
            return cards
        }
    }
}