package com.demate.apppos.presentation.screen.cashierScreen

import com.demate.apppos.domain.model.Cashier

sealed class CashierCheckResult {
    data object Initial : CashierCheckResult()
    data object Loading : CashierCheckResult()
    data object NoCashierFound : CashierCheckResult()
    data class CashierFound(val cashier: Cashier) : CashierCheckResult()
    data class Error(val message: String) : CashierCheckResult()
}