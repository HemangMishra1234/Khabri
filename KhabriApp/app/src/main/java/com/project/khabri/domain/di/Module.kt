package com.project.khabri.domain.di

import com.google.firebase.auth.FirebaseAuth
import com.project.khabri.domain.repositories.AuthenticationService
import com.project.khabri.domain.repositories.AuthenticationServiceImpl
import com.project.pattagobhi.ui.authentication.createAccount.CreateAccountViewModel
import com.project.pattagobhi.ui.authentication.emailVerification.EmailVerificationViewModel
import com.project.khabri.ui.authentication.login.LoginViewModel
import org.koin.dsl.module

val pattagobhiModules = module {
    single {
        FirebaseAuth.getInstance()
    }
    single<AuthenticationService> {
        AuthenticationServiceImpl()
    }


    factory {
        CreateAccountViewModel(get(), get())
    }
    factory {
        EmailVerificationViewModel(get(), get())
    }
    factory {
        LoginViewModel(get(), get())
    }
}