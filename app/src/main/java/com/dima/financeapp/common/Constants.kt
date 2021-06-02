package com.dima.financeapp.common

import com.dima.financeapp.R
import com.dima.financeapp.model.domain.Category

object Constants {

    const val BASE_URL = "http://10.0.2.2:8080/"

    const val DATABASE_NAME = "finance_database.db"

    /**
     * SharedPreferences constants
     */
    const val IS_SIGN = "is_sign"
    const val USER_EMAIL = "user_email"

    const val RECORD_TYPE_IMAGE_SIZE = 50

    const val RECORD_TYPE_INCOME = "Доход"
    const val RECORD_TYPE_CONSUMPTION = "Расход"

    const val LAST_RECORDS_COUNT = 5

    private const val CATEGORY_BUY = "Покупки"
    private const val CATEGORY_FOOD_AND_DRINK = "Еда и напитки"
    private const val CATEGORY_HOUSE = "Жилье"
    private const val CATEGORY_TRANSPORT = "Транспорт"
    private const val CATEGORY_VEHICLE = "Транспортное средство"
    private const val CATEGORY_GAMES = "Игры"
    private const val CATEGORY_LIFE = "Жизненные потребности"
    private const val CATEGORY_INTERNET = "Интернет"
    private const val CATEGORY_PHONE = "Телефон"
    private const val CATEGORY_COMMUNICATION = "Связь, ПК - Прочее"
    private const val CATEGORY_FINANCE_EXPENSES = "Финансовые расходы"
    private const val CATEGORY_INVESTMENTS = "Инвестиции"
    private const val CATEGORY_INCOME = "Доходы"
    private const val CATEGORY_OTHER = "Прочее"

    val CATEGORIES = listOf(
        Category(CATEGORY_BUY, R.drawable.img1),
        Category(CATEGORY_FOOD_AND_DRINK, R.drawable.img2),
        Category(CATEGORY_HOUSE, R.drawable.img3),
        Category(CATEGORY_TRANSPORT, R.drawable.img4),
        Category(CATEGORY_VEHICLE, R.drawable.img5),
        Category(CATEGORY_GAMES, R.drawable.img6),
        Category(CATEGORY_LIFE, R.drawable.img7),
        Category(CATEGORY_INTERNET, R.drawable.img8),
        Category(CATEGORY_PHONE, R.drawable.img9),
        Category(CATEGORY_COMMUNICATION, R.drawable.img10),
        Category(CATEGORY_FINANCE_EXPENSES, R.drawable.img11),
        Category(CATEGORY_INVESTMENTS, R.drawable.img12),
        Category(CATEGORY_INCOME, R.drawable.img13),
        Category(CATEGORY_OTHER, R.drawable.img14)
    )
}