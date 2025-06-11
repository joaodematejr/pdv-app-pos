package com.demate.apppos.domain.model

data class Cashier(
    val id: String = "",
    val openingDate: Long = 0,
    val closingDate: Long? = null,
    val initialAmount: Double = 0.0,
    val finalAmount: Double? = null,
    val posId: String = "",
    val userId: String = "",
    val totalSales: Double = 0.0,
    val totalCash: Double = 0.0,
    val totalCard: Double = 0.0,
    val totalCredit: Double = 0.0,
    val totalDebit: Double = 0.0,
    val totalPix: Double = 0.0,
    val totalVouchers: Double = 0.0,
    val totalCashless: Double = 0.0,
    val totalRefunds: Double = 0.0,
    val totalCashlessTransactions: Double = 0.0,
    val totalCashlessTransactionsCount: Int = 0,
    val totalCashlessTransactionsRefunds: Double = 0.0,
    val totalCashlessTransactionsRefundsCount: Int = 0,
    val totalCashlessTransactionsSales: Double = 0.0,
    val totalCashlessTransactionsSalesCount: Int = 0,
    val totalCashlessTransactionsSalesRefunds: Double = 0.0,
    val totalCashlessTransactionsSalesRefundsCount: Int = 0,
    val status: CashierStatus = CashierStatus.CLOSED
)

enum class CashierStatus {
    OPEN, CLOSED
}