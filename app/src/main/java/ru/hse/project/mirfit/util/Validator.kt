package ru.hse.project.mirfit.util

import java.util.regex.Pattern

class Validator {
    companion object {

        private val AUTH_PATTERN = Pattern.compile("""^[_A-z0-9]*([_A-z0-9])*${'$'}""")
        private val UPPER_CASE_PATTERN = Pattern.compile("""[_A-Z]*${'$'}""")
        private val USERNAME_PATTERN = Pattern.compile("""^[_A-z]*((-)*[_A-z])*${'$'}""")
        private val NUMBER_CODE_MIR_PAY = listOf("2200", "2201", "2202", "0987")

        fun validateLogin(str: String): ValidatorResult {
            if (str.isBlank()) {
                return ValidatorResult(validateError = "Логин не может быть пустым!")
            }

            if (str.length < 5) {
                return ValidatorResult(validateError = "Логин не может содержать менее 5 символов")
            }

            if (!AUTH_PATTERN.matcher(str).matches()) {
                return ValidatorResult(validateError = "Логин может содержать только символы латиницы и цифры")
            }

            return ValidatorResult(isValidate = true)
        }


        fun validatePassword(str: String): ValidatorResult {
            if (str.isBlank()) {
                return ValidatorResult(validateError = "Пароль не может быть пустым!")
            }

            if (str.length < 5) {
                return ValidatorResult(validateError = "Пароль не может содержать менее 5 символов")
            }

            if (!AUTH_PATTERN.matcher(str).matches()) {
                return ValidatorResult(validateError = "Пароль может содержать только символы латиницы и цифры")
            }


            if (!str.isOneUpperLetter()) {
                return ValidatorResult(validateError = "Пароль Должен содержать хотя бы одину заглавную букву")
            }

            return ValidatorResult(isValidate = true)
        }

        fun validateUserName(str: String): ValidatorResult {
            if (str.isBlank()) {
                return ValidatorResult(validateError = "Поле не может быть пустым!")
            }

            if (str.length < 2) {
                return ValidatorResult(validateError = "Поле не может содержать менее 2 символов")
            }

            if (!USERNAME_PATTERN.matcher(str).matches()) {
                return ValidatorResult(validateError = "Поле может содержать только символы латиницы")
            }


            if (!str.isTitleCase()) {
                return ValidatorResult(validateError = "Поле должно содержать первую заглавную букву и остальные строчные")
            }

            return ValidatorResult(isValidate = true)
        }


        fun validateNumberOfCard(str: String): ValidatorResult {

            if (str.length < 13) {
                return ValidatorResult(validateError = "Номер карты не может быть короче 13 цифр")
            }


            if (!NUMBER_CODE_MIR_PAY.any { str.startsWith(it) }) {
                return ValidatorResult(validateError = "Карта должна быть с платежной системой MIR")
            }

            return ValidatorResult(isValidate = true)
        }

        private fun String.isTitleCase() = this[0].isUpperCase() && drop(1).all { it.isLowerCase() }

        private fun String.isOneUpperLetter(): Boolean {
            for (s in this) {
                if (s.isUpperCase()) {
                    return true
                }
            }
            return false
        }
    }
}