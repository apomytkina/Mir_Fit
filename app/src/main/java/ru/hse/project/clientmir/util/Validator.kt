package ru.hse.project.clientmir.util

class Validator {
    companion object {

        private val notValidateChars: List<Char> = listOf<Char>(
            ' ', ',', '.', '/', ';',
            ':', '\'', '"', '[', ']',
            '!', '>', '<', '|', '#', '@',
            '$', '%', '^', '&', '*', '(', ')'
        )


        fun validateString(str: String): Boolean {
            if (str.isEmpty()) {
                return false
            }

            if (str.length < 2) {
                return false
            }

            if (str.any { x -> notValidateChars.contains(x) }) {
                return false
            }
            return true
        }


        fun validateNumber(str: String): Boolean {

            if (str.isEmpty()) {
                return false
            }

            if (str.length < 13) {
                return false
            }
            return true
        }

        fun validateLogin(str: String): Boolean {
            if (str.isEmpty()) {
                return false
            }

            if (str.length < 2) {
                return false
            }

            return true
        }

        fun validatePassword(str: String): Boolean {
            if (str.isEmpty()) {
                return false
            }

            if (str.length < 6) {
                return false
            }

            if (str.any { x -> x.isUpperCase() }) {
                return false
            }

            return true;

        }

    }
}