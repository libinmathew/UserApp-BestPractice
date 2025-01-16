package com.example.userapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.userapp.presentation.screens.MainViewModel
import com.example.userapp.presentation.screens.UserHomeScreen
import com.example.userapp.ui.theme.UserAppBestPracticeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserAppBestPracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = hiltViewModel<MainViewModel>()
                    LaunchedEffect(Unit) {
                        viewModel.getUserData()
                    }
                    UserHomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        userScreenState = viewModel.userScreenState.collectAsState().value
                    )
                }
            }
        }
    }
}




/*app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/myapp/
│   │   │       ├── di/                   // Dependency Injection (Hilt)
│   │   │       │   ├── AppModule.kt      // Application-level dependencies
│   │   │       │   ├── DataModule.kt     // Data layer dependencies
│   │   │       │   ├── DomainModule.kt   // Domain layer dependencies
│   │   │       │   └── PresentationModule.kt // Presentation layer dependencies
│   │   │       ├── data/
│   │   │       │   ├── api/
│   │   │       │   │   └── ApiService.kt
│   │   │       │   ├── source/
│   │   │       │   │   ├── UserDataSource.kt
│   │   │       │   │   ├── UserLocalDataSource.kt
│   │   │       │   │   └── UserRemoteDataSource.kt
│   │   │       │   ├── db/
│   │   │       │   │   ├── AppDatabase.kt
│   │   │       │   │   ├── UserDao.kt
│   │   │       │   │   └── UserEntity.kt
│   │   │       │   ├── model/
│   │   │       │   │   └── User.kt
│   │   │       │   └── repository/
│   │   │       │       └── UserRepositoryImpl.kt
│   │   │       ├── domain/
│   │   │       │   ├── model/
│   │   │       │   │   └── User.kt
│   │   │       │   ├── repository/
│   │   │       │   │   └── UserRepository.kt
│   │   │       │   └── usecase/
│   │   │       │       └── GetUserUseCase.kt
│   │   │       ├── presentation/
│   │   │       │   ├── view/
│   │   │       │   │   └── UserActivity.kt
│   │   │       │   └── viewmodel/
│   │   │       │       └── UserViewModel.kt
│   │   │       └── common/              // Common utilities and base classes
│   │   │           ├── utils/
│   │   │           │   └── PicassoUtils.kt // Example for Picasso
│   │   │           └── base/
│   │   │               └── BaseViewModel.kt // Example for BaseViewModel?*/