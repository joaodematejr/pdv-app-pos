package com.demate.apppos.presentation.screen.authEmailScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.demate.apppos.R
import com.demate.apppos.presentation.navigation.AppScreens

@Composable
fun AuthEmailScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var isCreatingAccount by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val companyCheckState by viewModel.companyCheckState.collectAsState()



    LaunchedEffect(uiState, companyCheckState) {
        when (uiState) {
            is AuthResult.Success -> {
                when (val currentCompanyState = companyCheckState) {
                    is CompanyCheckResult.HasCompany -> {
                        if (currentCompanyState.hasCompany) {
                            navController.navigate(AppScreens.HOME.name) {
                                popUpTo(AppScreens.AUTH_EMAIL.name) { inclusive = true }
                            }
                        } else {
                            navController.navigate(AppScreens.COMPANY_REGISTRATION.name) {
                                popUpTo(AppScreens.AUTH_EMAIL.name) { inclusive = true }
                            }
                        }
                    }

                    is CompanyCheckResult.Error -> {
                        Toast.makeText(context, currentCompanyState.message, Toast.LENGTH_LONG)
                            .show()
                    }

                    else -> Unit
                }
            }

            is AuthResult.Error -> {
                Toast.makeText(context, (uiState as AuthResult.Error).message, Toast.LENGTH_LONG)
                    .show()
            }

            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isCreatingAccount) stringResource(R.string.create_account) else stringResource(
                R.string.login
            ),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        if (isCreatingAccount) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.name_hint)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(stringResource(R.string.phone_hint)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email_hint)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password_hint)) },
            visualTransformation = if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        if (passwordVisible) Icons.Default.CheckCircle else Icons.Default.CheckCircle,
                        contentDescription = if (passwordVisible) stringResource(R.string.hide_password) else stringResource(
                            R.string.show_password
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        if (isCreatingAccount) {
            OutlinedTextField(
                value = passwordConfirmation,
                onValueChange = { passwordConfirmation = it },
                label = { Text(stringResource(R.string.confirm_password_hint)) },
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            if (passwordVisible) Icons.Default.CheckCircle else Icons.Default.CheckCircle,
                            contentDescription = if (passwordVisible) stringResource(R.string.hide_password) else stringResource(
                                R.string.show_password
                            )
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = {
                if (isCreatingAccount) {
                    if (!isValidEmail(email)) {
                        Toast.makeText(context, context.getString(R.string.invalid_email_format), Toast.LENGTH_SHORT).show()
                    } else if (password != passwordConfirmation) {
                        Toast.makeText(context, context.getString(R.string.passwords_dont_match), Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.createAccountWithEmail(name, phone, email, password)
                    }
                } else {
                    if (!isValidEmail(email)) {
                        Toast.makeText(context, context.getString(R.string.invalid_email_format), Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.signInWithEmail(email, password)
                    }
                }
            },
            enabled = email.isNotEmpty() && password.isNotEmpty() &&
                    (!isCreatingAccount || passwordConfirmation == password) &&
                    uiState !is AuthResult.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState is AuthResult.Loading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text(
                    if (isCreatingAccount) stringResource(R.string.create_account) else stringResource(
                        R.string.login
                    )
                )
            }
        }

        TextButton(
            onClick = { isCreatingAccount = !isCreatingAccount },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                if (isCreatingAccount)
                    stringResource(R.string.already_have_account)
                else
                    stringResource(R.string.create_account)
            )
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
}
