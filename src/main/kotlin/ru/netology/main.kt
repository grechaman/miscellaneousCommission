package ru.netology

import java.util.*
import kotlin.math.roundToInt

val scanner = Scanner(System.`in`)
var amountThisMonth = 0.0
var amounthTransferVKpay = 0.0

fun conversionInRub(money: Double) = Math.round(money) / 100.0

fun conversion(rub: Int, kopeika: Int) = rub * 100 + kopeika

fun printTransferSuccess(money: Double, way: String = "VKpay") {
    when (way) {
        "VKpay" -> println("С вашего счета VK Pay списано ${conversionInRub(money)} рублей")
        "карта" -> println("С вашей карты списано ${conversionInRub(money)} рублей")
    }
}

fun vkPay(money: Int) {
    val maxSumTransferOneTimeVKpay = 1500000
    val maxSumTransferThisMonthVKpay = 4000000
    if (money <= maxSumTransferOneTimeVKpay) {
        if (amounthTransferVKpay < maxSumTransferThisMonthVKpay) {
            amountThisMonth += money
            amounthTransferVKpay += money
            printTransferSuccess(money.toDouble())
        } else println("Месячный лимит переводов исчерпан")
    } else println("Сумма перевода превышает допустимую")
}

fun maestroMasterCard(money: Int) {
    amountThisMonth += money
    val maxSumTransfer = 7500000
    var sumTransfer = 0.0
    val percentCommission = 1.006
    val commissionAdd = 2000
    if (money < maxSumTransfer) {
        sumTransfer += money
        printTransferSuccess(sumTransfer, "карта")
    } else {
        sumTransfer += money * percentCommission + commissionAdd
        printTransferSuccess(sumTransfer, "карта")
    }
}

fun visaMirCard(money: Int) {
    amountThisMonth += money
    val minimumCommission = 3500.0
    val percentCommission = 0.0075
    val commission = money * percentCommission
    val sumTransfer =
        if (commission > minimumCommission) money + commission else money + minimumCommission
    printTransferSuccess(sumTransfer, "карта")
}

fun selectionMethodPayment(method: Int = 1, amountMouth: Int = 0, money: Int) {
    when (method) {
        1 -> vkPay(money)
        2 -> maestroMasterCard(money)
        3 -> visaMirCard(money)
        else -> println("Выберите верный вариант")
    }
}

fun main() {
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
                    if (amountThisMonth > 0) "В этом месяце вы совершили переводы на сумму: " +
                            "${conversionInRub(amountThisMonth)
                    } рублей" else " "
        )
        val input = scanner.nextInt()
        val amount = conversion(rubley, kopeiki)
        selectionMethodPayment(input, amountThisMonth.roundToInt(), amount)

    }
}

//fun choiseMethodPaymeent(method: String = "VKpay", amountThisMonth: Int = 0, money: Int){
//    when (method){
//        "VKpay" -> vkPay(money,amountThisMonth)
//        "карта" -> Card(money,amountThisMonth)
//    }
//    choiseMethodPaymeent(money=1500)
}