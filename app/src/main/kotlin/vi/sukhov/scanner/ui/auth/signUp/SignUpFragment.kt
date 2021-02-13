package vi.sukhov.scanner.ui.auth.signUp

import vi.sukhov.scanner.R
import vi.sukhov.scanner.ui.auth.BaseAuthFragment

class SignUpFragment : BaseAuthFragment() {

    override val authType: AuthType = AuthType.SIGN_UP

    override fun initViews() {
        binding.titleText.text = getString(R.string.sign_up)
        binding.actionButton.text = getString(R.string.create_account)
        binding.prevScreen.text = getString(R.string.accounted)
    }

}