package com.khametov.effectivemobileapp.presentation.profile.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.khametov.R
import com.khametov.databinding.FragmentProfileBinding
import com.khametov.effectivemobileapp.base.BaseMviFragment
import com.khametov.effectivemobileapp.common.extension.viewModels
import com.khametov.effectivemobileapp.presentation.profile.ProfileFeature
import com.khametov.effectivemobileapp.presentation.profile.intents.ProfileViewEvent
import com.khametov.effectivemobileapp.presentation.profile.intents.ProfileViewState
import com.khametov.effectivemobileapp.presentation.profile.vm.ProfileViewModel
import javax.inject.Inject

class ProfileFragment:
    BaseMviFragment<ProfileViewState, ProfileViewEvent, ProfileViewModel>(R.layout.fragment_profile) {

    @Inject
    lateinit var viewModelAssistedFactory: ProfileViewModel.Factory

    override val viewModel: ProfileViewModel by viewModels {
        viewModelAssistedFactory.create(tabRouter)
    }

    private val viewBinding by viewBinding<FragmentProfileBinding>()

    override fun setupInjection() {
        ProfileFeature.getComponent().inject(this)
    }

    override fun setupUi() {

        with(viewBinding) {

            favoritesContainer.setOnClickListener {

                viewModel.perform(ProfileViewEvent.NavigateToFavoritesScreen)
            }
        }
    }

    override fun render(state: ProfileViewState) {

    }

    override fun onBackPressed(): Boolean {
        return true
    }
}