package vi.sukhov.scanner.ui.auth.signIn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentSignInBinding
import vi.sukhov.scanner.ui.auth.AuthViewModel
import vi.sukhov.scanner.ui.home.HomeActivity
import vi.sukhov.scanner.util.extensions.validateEmail
import vi.sukhov.scanner.util.extensions.validatePassword

@AndroidEntryPoint
class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding()
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener(::login)
        binding.createAccount.setOnClickListener(::moveToSingUp)
    }

    private fun login(v: View) {
        lifecycleScope.launchWhenStarted {

            val isValidEmail = binding.emailInput.editText?.validateEmail() ?: false
            val isValidPassword = binding.passwordInput.editText?.validatePassword() ?: false

            if (isValidEmail && isValidPassword) {
                viewModel.signInUser(
                    binding.emailInput.editText?.text.toString().trim(),
                    binding.passwordInput.editText?.text.toString().trim()
                )

                viewModel.uiStates.collect {
                    when (it) {
                        is AuthViewModel.UiStates.Loading -> {
                            binding.progress.visibility = View.VISIBLE
                        }
                        is AuthViewModel.UiStates.Success -> {
                            delay(2000)
                            binding.progress.visibility = View.INVISIBLE
                            startHomeActivity()
                        }
                        is AuthViewModel.UiStates.Error -> {
                            binding.progress.visibility = View.INVISIBLE
                            Toast.makeText(
                                requireContext(),
                                "Something wrong happened",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun moveToSingUp(v: View) {
        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    private fun startHomeActivity() {
        Intent(requireContext(), HomeActivity::class.java).apply {
            startActivity(this)
            activity?.finish()
        }
    }

}