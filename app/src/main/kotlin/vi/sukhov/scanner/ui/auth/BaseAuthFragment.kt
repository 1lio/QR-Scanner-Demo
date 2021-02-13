package vi.sukhov.scanner.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentAuthBinding
import vi.sukhov.scanner.ui.home.HomeActivity
import vi.sukhov.scanner.util.extensions.validateEmail
import vi.sukhov.scanner.util.extensions.validatePassword

@AndroidEntryPoint
abstract class BaseAuthFragment : BaseFragment(R.layout.fragment_auth) {

    enum class AuthType { SIGN_IN, SIGN_UP }

    /** Установить свойства view-шек здесь */
    abstract fun initViews()
    abstract val authType: AuthType

    private val viewModel: AuthViewModel by viewModels()
    val binding: FragmentAuthBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Тут присваиваем полям значения
        initViews()

        binding.actionButton.setOnClickListener { action() }
        binding.prevScreen.setOnClickListener { moveToPrevScreen() }
    }

    // Действие на кнопке (Войти или создать аккаунт)
    private fun action() {
        lifecycleScope.launchWhenStarted {

            val isValidEmail = binding.emailInput.editText?.validateEmail() ?: false
            val isValidPassword = binding.passwordInput.editText?.validatePassword() ?: false

            if (isValidEmail && isValidPassword) {

                val email = binding.emailInput.editText?.text.toString().trim()
                val password = binding.passwordInput.editText?.text.toString().trim()

                // Отправляем sing-action во viewModel
                if (authType == AuthType.SIGN_IN) {
                    viewModel.signIn(email, password)
                } else {
                    viewModel.signUp(email, password)
                }

                // Обозреваем состояние
                viewModel.uiStates.collect {
                    when (it) {
                        is AuthViewModel.UiStates.Loading -> {
                            setVisibilityLoader(true)
                        }
                        is AuthViewModel.UiStates.Success -> {
                            delay(2000)
                            setVisibilityLoader(false)
                            // Выполняем sing-action в UI
                            if (authType == AuthType.SIGN_IN) moveToHomeActivity() else moveToPrevScreen()

                        }
                        is AuthViewModel.UiStates.Error -> {
                            setVisibilityLoader(false)
                            toast(getString(R.string.wrong))
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun setVisibilityLoader(isVisible: Boolean) {
        binding.progress.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

    private fun moveToPrevScreen() {
        if (authType == AuthType.SIGN_IN) {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        } else {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun moveToHomeActivity() {
        startActivity(HomeActivity::class.java)
    }
}