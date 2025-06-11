package com.demate.apppos.presentation.screen.cashierScreen

import com.demate.apppos.domain.model.CashierModel

sealed class CashierCheckResult {
    object Initial : CashierCheckResult()
    object Loading : CashierCheckResult()
    object NoCashierFound : CashierCheckResult()
    data class CashierFound(val cashier: CashierModel) : CashierCheckResult()
    data class Error(val message: String) : CashierCheckResult()
}