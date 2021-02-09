package vi.sukhov.scanner.ui.auth.signUp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentSignUpBinding
import vi.sukhov.scanner.ui.auth.AuthViewModel
import vi.sukhov.scanner.util.extensions.validateEmail
import vi.sukhov.scanner.util.extensions.validatePassword

@AndroidEntryPoint
class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val binding: FragmentSignUpBinding by viewBinding()
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.register.setOnClickListener(::signUp)
        binding.account.setOnClickListener(::moveToSignIn)
    }

    private fun signUp(view: View) {

        lifecycleScope.launchWhenStarted {

            val isValidEmail = binding.emailInput.editText?.validateEmail() ?: false
            val isValidPassword = binding.passwordInput.editText?.validatePassword() ?: false

            if (isValidEmail and isValidPassword) {

                viewModel.signUpUser(
                    binding.emailInput.editText?.text.toString().trim(),
                    binding.passwordInput.editText?.text.toString().trim()
                )

                viewModel.uiStates.collect {

                    when (it) {
                        is AuthViewModel.UiStates.Loading -> {
                            binding.progress.visibility = View.VISIBLE
                        }
                        is AuthViewModel.UiStates.Success -> {
                            binding.progress.visibility = View.INVISIBLE
                            moveToSignIn(view)
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

    private fun moveToSignIn(view: View) {
        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
    }
}