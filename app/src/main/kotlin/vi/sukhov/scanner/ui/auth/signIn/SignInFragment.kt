package vi.sukhov.scanner.ui.auth.signIn

import vi.sukhov.scanner.R
import vi.sukhov.scanner.ui.auth.BaseAuthFragment

class SignInFragment : BaseAuthFragment() {

    override val authType: AuthType = AuthType.SIGN_IN

    override fun initViews() {
        binding.titleText.text = getString(R.string.app_name)
        binding.actionButton.text = getString(R.string.sign_in)
        binding.prevScreen.text = getString(R.string.create_account)
    }

}