package com.example.app3.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.app3.R
import com.example.app3.databinding.FragmentLoginBinding
import com.example.app3.utils.handleError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {
    lateinit var binding: FragmentLoginBinding
    private val viewModel by navGraphViewModels<LoginViewModel>(R.id.login_navigation) { defaultViewModelProviderFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        setupObserververs()

    }
    private fun setupObserververs() {
        with(binding) {
            usernameTextInput.editText?.doAfterTextChanged {
                viewModel.setUsername(it.toString())
            }
            passwordLayout.editText?.doAfterTextChanged {
                viewModel.setPassword(it.toString())
            }
            viewModel.error.observe(viewLifecycleOwner,requireActivity().handleError())


            loginButton.setOnClickListener {
                viewModel.auth()
                viewModel.isLoggedIn()
            }

        }
        viewModel.isLoggedIn.observe(viewLifecycleOwner){
            if(it){
                findNavController().setGraph(R.navigation.mobile_navigation)
            } else {
                    Toast.makeText(
                        context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
            }

        }

    }


}