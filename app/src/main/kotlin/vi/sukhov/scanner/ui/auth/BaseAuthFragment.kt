package vi.sukhov.scanner.ui.auth

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.core.common.UiStates
import vi.sukhov.scanner.databinding.FragmentAuthBinding
import vi.sukhov.scanner.ui.home.HomeActivity
import vi.sukhov.scanner.util.extensions.hideSoftInput
import vi.sukhov.scanner.util.extensions.validateEmail
import vi.sukhov.scanner.util.extensions.validatePassword

@AndroidEntryPoint
abstract class BaseAuthFragment : BaseFragment(R.layout.fragment_auth),
    TextView.OnEditorActionListener {

    enum class AuthType { SIGN_IN, SIGN_UP }

    /** Установить свойства view-шек здесь */
    abstract fun initViews()
    abstract val authType: AuthType

    private val viewModel: AuthViewModel by viewModels()
    val binding: FragmentAuthBinding by viewBinding()

    private val errorToast: Toast by lazy {
        toast(requireContext().getText(R.string.wrong), show = false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Тут присваиваем полям значения
        initViews()

        // Вешаем обработчики
        binding.actionButton.setOnClickListener { action() }
        binding.prevScreen.setOnClickListener { moveToPrevScreen() }

        // Тут же обработаем экшены на клавеатуре
        binding.emailEdit.setOnEditorActionListener(this)
        binding.passwordEdit.setOnEditorActionListener(this)
    }


    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        return when (actionId) {

            EditorInfo.IME_ACTION_GO -> {
                action()
                true
            }
            else -> false
        }
    }

    // Действие на кнопке (Войти или создать аккаунт)
    private fun action() {
        lifecycleScope.launchWhenStarted {

            val editEmail = binding.emailInput.editText
            val editPassword = binding.passwordInput.editText

            val isValidEmail = editEmail?.validateEmail() ?: false
            val isValidPassword = editPassword?.validatePassword() ?: false

            if (isValidEmail && isValidPassword) {

                val email = editEmail?.text.toString().trim()
                val password = editPassword?.text.toString().trim()

                // Отправляем sing-action во viewModel
                if (authType == AuthType.SIGN_IN) {
                    viewModel.signIn(email, password)
                } else {
                    viewModel.signUp(email, password)
                }

                // Обозреваем состояние
                viewModel.uiStates.collect {
                    when (it) {
                        is UiStates.Loading -> blockUI(true)
                        is UiStates.Success -> actionButton()
                        is UiStates.Error -> showErrorMessage()
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun actionButton() {
        errorToast.cancel()
        blockUI(false)
        if (authType == AuthType.SIGN_IN) moveToHomeActivity() else moveToPrevScreen()
    }

    private fun showErrorMessage() {
        blockUI(false)
        errorToast.show()
    }

    private fun blockUI(isBlocked: Boolean) {

        val edits = listOf(
            binding.emailInput.editText,
            binding.passwordInput.editText,
            binding.actionButton
        )

        if (isBlocked) requireContext().hideSoftInput(binding.root)

        setVisibilityLoader(isBlocked)

        edits.forEach { it!!.isEnabled = !isBlocked }

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