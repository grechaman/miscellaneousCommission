package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun conversionInRub() {
        val money = 1000.0
        val result = ru.netology.conversionInRub(money)
        val expected = 10.0
        assertEquals(expected, result, 0.001)
    }

    @Test
    fun conversion() {
        val rub = 1
        val result = conversion(rub)
        val expected = 100
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultVkpayTrueAmountMouthZeroDefault() {
        val money = 500
        val result = ru.netology.vkPay(money)
        assert(result)
    }

    @Test
    fun shouldResultVkpayFalseAmountMouthZeroDefault() {
        val money = 15001_00
        val result = ru.netology.vkPay(money)
        val expected = false
        assertFalse(result)
    }

    @Test
    fun shouldResultVkpayTrueAmountMouthZero() {
        val money = 500
        val amountMouth = 15
        val result = ru.netology.vkPay(money, amountMouth)
        assert(result)
    }

    @Test
    fun shouldResultVkpayFalseAmountMouthZero() {
        val money = 15001_00
        val amountMouth = 15
        val result = ru.netology.vkPay(money, amountMouth)
        assertFalse(result)
    }

    @Test
    fun shouldResultVkpayFalseAmountMouthExceeded() {
        val money = 500
        val amountMouth = 4000001
        val result = ru.netology.vkPay(money, amountMouth)
        assertFalse(result)
    }

    @Test
    fun shouldResultTrueCheckMouthLimitCard() {
        val money = 1
        val result = ru.netology.checkMouthLimitCard(money)
        assert(result)
    }

    @Test
    fun shouldResultFalseCheckMouthLimitCard() {
        val money = 600_001_00
        val result = ru.netology.checkMouthLimitCard(money)
        assertFalse(result)
    }

    @Test
    fun shouldResultZeroMaestroMasterCard() {
        val money = 500
        val result = ru.netology.maestroMasterCard(money)
        val expected = 0
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultCommissionMaestroMasterCard() {
        val money = 76000_00
        val result = ru.netology.maestroMasterCard(money)
        val expected = 476_00
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultThirtyFiveVisaMirCard() {
        val money = 5_00
        val result = ru.netology.visaMirCard(money)
        val expected = 35_00
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultNotEqualThirtyFiveVisaMirCard() {
        val money = 5000_00
        val result = ru.netology.visaMirCard(money)
        val expected = 37_50
        assertEquals(expected, result)
    }

    @Test
    fun printConsoleTransferSuccessful() {
        val money = 5_00
        val result = ru.netology.printConsoleTransferSuccessful(money)
        val expected = "Операция выполнена успешно, комиссия составила 5.0 рублей."
        assertEquals(expected, result)
    }

    @Test
    fun printConsoleTransferSuccessfulNonCommission() {
        val result = ru.netology.printConsoleTransferSuccessfulNonCommission()
        val expected = "Операция выполнена успешно."
        assertEquals(expected, result)
    }

    @Test
    fun printConsoleTransferFail() {
        val result = ru.netology.printConsoleTransferFail()
        val expected = "Операция не выполнена."
        assertEquals(expected, result)
    }

    @Test
    fun operationCalculateCommission() {
        val money = 3500
        val result = ru.netology.operationCalculateCommission(money = money)
        val expected = "Операция выполнена успешно."
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultOperationSuccessOperationCalculateCommissionMethodDefaultAmountMouthNonDefault() {
        val money = 3500
        val amountMounthTransfers = 50000
        val result =
            ru.netology.operationCalculateCommission(amountMounthTransfers = amountMounthTransfers, money = money)
        val expected = "Операция выполнена успешно."
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultOperationSuccessPrintCommissionOperationCalculateCommissionMethodVisaMirAmountNonMouthDefault() {
        val money = 500
        val amountMounthTransfers = 50000
        val method = "visaMirCard"
        val result = ru.netology.operationCalculateCommission(
            methodPayment = method,
            amountMounthTransfers = amountMounthTransfers,
            money = money
        )
        val expected = "Операция выполнена успешно, комиссия составила 35.0 рублей."
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultOperationSuccessPrintCommissionOperationCalculateCommissionMethodMasterCardAmountMouthNonDefault() {
        val money = 76000
        val amountMounthTransfers = 50000
        val method = "maestroMasterCard"
        val result = ru.netology.operationCalculateCommission(
            methodPayment = method,
            amountMounthTransfers = amountMounthTransfers,
            money = money
        )
        val expected = "Операция выполнена успешно, комиссия составила 476.0 рублей."
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultOperationSuccessPrintCommissionOperationCalculateCommissionMethodMasterCardAmountMouthDefault() {
        val money = 76000
        val method = "maestroMasterCard"
        val result = ru.netology.operationCalculateCommission(methodPayment = method, money = money)
        val expected = "Операция выполнена успешно, комиссия составила 476.0 рублей."
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultOperationSuccessOperationCalculateCommissionMethodMasterCardAmountMouthNonDefault() {
        val money = 300
        val amountMounthTransfers = 7000
        val method = "maestroMasterCard"
        val result = ru.netology.operationCalculateCommission(
            methodPayment = method,
            amountMounthTransfers = amountMounthTransfers,
            money = money
        )
        val expected = "Операция выполнена успешно."
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultOperationSuccessOperationCalculateCommissionMethodMasterCardAmountMouthDefault() {
        val money = 300
        val method = "maestroMasterCard"
        val result = ru.netology.operationCalculateCommission(methodPayment = method, money = money)
        val expected = "Операция выполнена успешно."
        assertEquals(expected, result)
    }

    @Test
    fun shouldResultOperationFailOperationCalculateCommissionMethodMasterCardAmountMouthNonDefault() {
        val money = 300
        val amountMounthTransfers = 600001
        val method = "maestroMasterCard"
        val result = ru.netology.operationCalculateCommission(
            methodPayment = method,
            amountMounthTransfers = amountMounthTransfers,
            money = money
        )
        val expected = "Операция не выполнена."
        assertEquals(expected, result)
    }
}