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

@AndroidEntryPoint
class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding()
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                if (validateEmail() and validatePassword()) {
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
                                Intent(requireContext(), HomeActivity::class.java).apply {
                                    startActivity(this)
                                    activity?.finish()
                                }
                            }
                            is AuthViewModel.UiStates.Error -> {
                                binding.progress.visibility = View.INVISIBLE
                                Toast.makeText(
                                    requireContext(),
                                    "Something wrong happened",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                            }
                        }
                    }
                }
            }
        }

        binding.noaccount.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

    }

    private fun validateEmail(): Boolean {
        val email = binding.emailInput.editText?.text.toString().trim()
        return if (email.isEmpty()) {
            binding.emailInput.error = "Insert Something..."
            false
        } else {
            binding.emailInput.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.passwordInput.editText?.text.toString().trim()

        return if (password.isEmpty()) {
            binding.passwordInput.error = "Insert Something..."
            false
        } else {
            binding.passwordInput.error = null
            true
        }
    }

}