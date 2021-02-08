package vi.sukhov.scanner.ui.auth.signUp

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentSignUpBinding

@AndroidEntryPoint
class SignUpFragment : BaseFragment(R.layout.fragment_sign_up){

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}