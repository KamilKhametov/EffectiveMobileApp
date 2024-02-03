package com.khametov.effectivemobileapp.presentation.auth.ui

import androidx.core.widget.doAfterTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import com.khametov.R
import com.khametov.databinding.FragmentAuthBinding
import com.khametov.effectivemobileapp.base.BaseMviFragment
import com.khametov.effectivemobileapp.common.extension.setPhoneMask
import com.khametov.effectivemobileapp.common.extension.viewModels
import com.khametov.effectivemobileapp.presentation.auth.AuthFeature
import com.khametov.effectivemobileapp.presentation.auth.intents.AuthViewEvent
import com.khametov.effectivemobileapp.presentation.auth.intents.AuthViewState
import com.khametov.effectivemobileapp.presentation.auth.vm.AuthViewModel
import java.util.regex.Pattern
import javax.inject.Inject

class AuthFragment: BaseMviFragment<AuthViewState, AuthViewEvent, AuthViewModel>(
    R.layout.fragment_auth
) {

    companion object {
        fun newInstance() = AuthFragment()
    }

    @Inject
    lateinit var viewModelAssistedFactory: AuthViewModel.Factory

    override val viewModel: AuthViewModel by viewModels {
        viewModelAssistedFactory.create(tabRouter)
    }

    private val viewBinding by viewBinding<FragmentAuthBinding>()

    private var nameText = ""
    private var surnameText = ""
    private var phoneText = ""

    override fun setupInjection() {
        AuthFeature.getComponent().inject(this)
    }

    override fun setupUi() {

        validateFields()

        viewBinding.logInButton.setOnClickListener {

            viewModel.perform(
                AuthViewEvent.CheckIsAuth(
                    nameText,
                    surnameText,
                    phoneText
                )
            )
        }
    }

    private fun validateFields() {

        with(viewBinding) {

            inputPhoneEditText.setPhoneMask()

            inputNameEditText.doAfterTextChanged { input ->

                nameText = input.toString()

                changeButtonState(
                    input.toString(),
                    surnameText,
                    phoneText
                )
            }

            inputSurnameEditText.doAfterTextChanged { input ->

                surnameText = input.toString()

                changeButtonState(
                    nameText,
                    input.toString(),
                    phoneText
                )
            }

            inputPhoneEditText.doAfterTextChanged { input ->

                phoneText = input.toString()

                changeButtonState(
                    nameText,
                    surnameText,
                    input.toString()
                )
            }
        }
    }

    private fun changeButtonState(
        name: String,
        surname: String,
        phone: String
    ) {

        with(viewBinding) {

            if (userNameFieldIsValid(name) && userSurnameFieldIsValid(surname) && phoneIsValid(phone)) {
                logInButton.isEnabled = true
                logInButton.setBackgroundResource(R.drawable.background_pink_round_8)
            } else {
                logInButton.isEnabled = false
                logInButton.setBackgroundResource(R.drawable.background_light_pink_round_8)
            }
        }
    }

    private fun userNameFieldIsValid(input: String): Boolean {

        with(viewBinding) {
            if (Pattern.matches(".*[a-zA-Z].*", input) && input.isNotEmpty()) {
                inputNameContainer.setBackgroundResource(R.drawable.background_light_gray_round_8_error)
                return false
            } else {
                inputNameContainer.setBackgroundResource(R.drawable.background_light_gray_round_8)
                return true
            }
        }
    }

    private fun userSurnameFieldIsValid(input: String): Boolean {

        with(viewBinding) {
            if (Pattern.matches(".*[a-zA-Z].*", input) && input.isNotEmpty()) {
                inputSurnameContainer.setBackgroundResource(R.drawable.background_light_gray_round_8_error)
                return false
            } else {
                inputSurnameContainer.setBackgroundResource(R.drawable.background_light_gray_round_8)
                return true
            }
        }
    }

    private fun phoneIsValid(input: String): Boolean {
        return input.length == 18
    }

    override fun render(state: AuthViewState) {

    }

    override fun onBackPressed(): Boolean {
        return true
    }
}