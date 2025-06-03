package com.demate.apppos.presentation.navigation

enum class AppScreens {
    SPLASH_SCREEN,
    HOME,
    AUTH_EMAIL,
    COMPANY_REGISTRATION,
    REGISTER,
    FORGOT_PASSWORD,
    PROFILE,
    SETTINGS,
    PRODUCTS,
    CART,
    CHECKOUT,
    ORDER_HISTORY,
    ORDER_DETAILS,
    PAYMENT_METHODS,
    NOTIFICATIONS,
    HELP_CENTER;

    companion object {
        fun fromRoute(route: String?): AppScreens = when (route?.substringBefore("/")) {
            SPLASH_SCREEN.name -> SPLASH_SCREEN
            AUTH_EMAIL.name -> AUTH_EMAIL
            HOME.name -> HOME
            COMPANY_REGISTRATION.name -> COMPANY_REGISTRATION
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }

    }
}