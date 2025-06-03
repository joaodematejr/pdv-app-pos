package com.demate.apppos.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demate.apppos.presentation.screen.authEmailScreen.AuthEmailScreen
import com.demate.apppos.presentation.screen.companyRegistrationScreen.CompanyRegistrationScreen
import com.demate.apppos.presentation.screen.homeScreen.HomeScreen
import com.demate.apppos.presentation.screen.splashScreen.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = AppScreens.SPLASH_SCREEN.name) {
        composable(AppScreens.SPLASH_SCREEN.name) {
            SplashScreen(navController = navController)
        }
        composable(AppScreens.AUTH_EMAIL.name) {
            AuthEmailScreen(navController = navController)
        }
        composable(AppScreens.COMPANY_REGISTRATION.name) {
            CompanyRegistrationScreen(navController = navController)
        }
        composable(AppScreens.HOME.name) {
            HomeScreen(navController = navController)
        }
    }
}