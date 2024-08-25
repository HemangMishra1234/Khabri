package com.project.khabri.domain.data

enum class Color(val hex: String) {
    ORANGE_RED("#fcba03"),
    GREEN("#96e339"),
    RED_ORANGE("#e85733"),
    LIGHT_GREEN("#2cdb9b"),
    BLUE("#2c66db"),
    PURPLE("#7e2cdb"),
    PINK("#db2cd5"),
    DARK_ORANGE("#db2c60"),
    DARK_RED("#0f0d0d");

    override fun toString(): String = hex
}