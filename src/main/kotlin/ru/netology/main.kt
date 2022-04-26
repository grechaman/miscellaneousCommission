package ru.netology

import kotlin.math.roundToInt

const val maxSumTransferMonthCard = 600_000_00

fun conversionInRub(money: Double) = Math.round(money) / 100.0

fun conversion(rub: Int) = rub * 100

fun vkPay(money: Int, amountMounthTransfers: Int = 0): Boolean {
    val maxSumTransferOneTimeVKpay = 1500000
    val maxSumTransferThisMonthVKpay = 4000000
    return if (money <= maxSumTransferOneTimeVKpay) {
        amountMounthTransfers < maxSumTransferThisMonthVKpay
    } else false
}

fun checkMouthLimitCard(amountMounthTransfers: Int): Boolean = amountMounthTransfers*100 < maxSumTransferMonthCard

fun maestroMasterCard(money: Int): Int {
    val maxSumTransfer = 7500000
    val percentCommission = 0.006
    val commissionAdd = 2000
    return when {
        money < maxSumTransfer -> 0
        else -> (money * percentCommission + commissionAdd).roundToInt()
    }
}

fun visaMirCard(money: Int): Int {
    val minimumCommission = 3500.0
    val percentCommission = 0.0075
    val commission = money * percentCommission
    return if (commission > minimumCommission) commission.roundToInt() else minimumCommission.roundToInt()
}

fun printConsoleTransferSuccessful(money: Int): String =
    "Операция выполнена успешно, комиссия составила ${conversionInRub(money.toDouble())} рублей."

fun printConsoleTransferSuccessfulNonCommission(): String = "Операция выполнена успешно."

fun printConsoleTransferFail(): String = "Операция не выполнена."

fun operationCalculateCommission(
    methodPayment: String = "vkPay",
    amountMounthTransfers: Int = 0,
    money: Int
): String {
    val sumTransfer = conversion(money)
    val commission = when (methodPayment) {
        "vkPay" -> if (vkPay(money, amountMounthTransfers)) sumTransfer else false
        "maestroMasterCard" -> if (checkMouthLimitCard(amountMounthTransfers)) maestroMasterCard(sumTransfer) else false
        "visaMirCard" -> if (checkMouthLimitCard(amountMounthTransfers)) visaMirCard(sumTransfer) else false
        else -> throw IllegalArgumentException("Недопустимое значение")
    }
    return when (methodPayment) {
        "vkPay" -> if (commission is Int) printConsoleTransferSuccessfulNonCommission() else printConsoleTransferFail()
        "maestroMasterCard" -> if (commission is Int) {
            if (commission == 0) printConsoleTransferSuccessfulNonCommission() else
                printConsoleTransferSuccessful(commission)
        } else printConsoleTransferFail()
        "visaMirCard" -> if (commission is Int) printConsoleTransferSuccessful(commission) else printConsoleTransferFail()
        else -> throw IllegalArgumentException("Недопустимое значение")
    }
}

fun main() {
//    println(operationCalculateCommission("visaMirCar", money = 300))
    println(operationCalculateCommission(money = 1500))
    println(operationCalculateCommission("maestroMasterCard", 700000, 700))
    println(operationCalculateCommission("visaMirCard", money = 300))
    println(operationCalculateCommission("maestroMasterCard", 700000, 76000))
}
