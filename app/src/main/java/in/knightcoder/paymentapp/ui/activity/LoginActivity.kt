package `in`.knightcoder.paymentapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import `in`.knightcoder.paymentapp.R
import `in`.knightcoder.paymentapp.ui.viewmodel.LoginViewModel
import `in`.knightcoder.paymentapp.utils.SharePrefManager
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var sharePrefManager: SharePrefManager

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (sharePrefManager.getString("TOKEN_KEY") != null) {
            navigateToMainActivity()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views (e.g., EditText for username and password, Button for login)

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val username = findViewById<EditText>(R.id.usernameEditText).text.toString()
            val password = findViewById<EditText>(R.id.passwordEditText).text.toString()
            loginViewModel.login(username, password)
        }

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        lifecycleScope.launch {
            loginViewModel.loginState.collect { loginState ->
                when (loginState) {
                    is LoginViewModel.LoginUiState.Empty -> {
                        progressBar.visibility = ProgressBar.GONE
                    }
                    is LoginViewModel.LoginUiState.Loading -> {
                        progressBar.visibility = ProgressBar.VISIBLE
                    }
                    is LoginViewModel.LoginUiState.Success -> {
                        progressBar.visibility = ProgressBar.GONE
                        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                    }
                    is LoginViewModel.LoginUiState.Error -> {
                        progressBar.visibility = ProgressBar.GONE
                        Toast.makeText(this@LoginActivity, "Login Failed: ${loginState.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
