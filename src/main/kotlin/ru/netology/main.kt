package ru.netology

import java.util.*

fun main() {

    val scanner = Scanner(System.`in`)
    var amountThisMouth = 0.0

    fun conversionInRub(money: Double) = Math.round(money) / 100.0

    fun printTransferSuccess(money: Double, way: String) {
        when (way) {
            "VKpay" -> println("С вашего счета VK Pay списано ${conversionInRub(money)} рублей")
            "карта" -> println("С вашей карты списано ${conversionInRub(money)} рублей")
        }
    }

    fun vkPay(money: Int) {
        amountThisMouth += money
        printTransferSuccess(money.toDouble(), "VKpay")
    }

    fun maestroMasterCard(money: Int) {
        amountThisMouth += money
        val maxSumTransfer = 7500000
        var sumTransfer = 0.0
        if (money < maxSumTransfer) {
            sumTransfer += money
            printTransferSuccess(sumTransfer, "карта")
        } else {
            sumTransfer += money * 1.006 + 2000
            printTransferSuccess(sumTransfer, "карта")
        }
    }

    fun visaMirCard(money: Int) {
        amountThisMouth += money
        val minimumCommission = 3500.0
        val percentCommission = 0.0075
        val commission = money * percentCommission
        val sumTransfer =
            if (commission > minimumCommission) money + commission else money + minimumCommission
        printTransferSuccess(sumTransfer, "карта")
    }

    while (true) {
        println()
        print(
            "Введите сумму перевода:\n" +
                    "Рублей:"
        )
        val rubley = scanner.nextInt()
        print("Копеек:")
        val kopeiki = scanner.nextInt()
        println(
            "Выберите способ оплаты:\n" +
                    "1. VK Pay\n" +
                    "2. Mastercard или Maestro\n" +
                    "3. Visa или Мир\n" +
                    if (amountThisMouth > 0) "В этом месяце вы совершили переводы на сумму: ${conversionInRub(amountThisMouth)} рублей" else " "
        )
        val input = scanner.nextInt()
        val amount = rubley * 100 + kopeiki
        when (input) {
            1 -> vkPay(amount)
            2 -> maestroMasterCard(amount)
            3 -> visaMirCard(amount)
            else -> println("Выберите верный вариант")
        }
    }
}