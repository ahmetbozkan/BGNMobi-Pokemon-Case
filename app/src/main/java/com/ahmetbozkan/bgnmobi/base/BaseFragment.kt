package com.ahmetbozkan.bgnmobi.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.core.Failure
import com.ahmetbozkan.bgnmobi.util.extensions.showToast

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: DB? = null
    protected val binding: DB get() = _binding!!

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract val viewModel: VM

    abstract fun initialize(savedInstanceState: Bundle?)

    private val navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()

    protected lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize(savedInstanceState)

        subscribeToViewModel()

        initNavigation()
    }

    private fun subscribeToViewModel() {
        viewModel.error.observe(viewLifecycleOwner, ::observeError)
    }

    private fun observeError(exception: Failure?) {
        when (exception) {
            is Failure.EmptyResponse -> {
                requireContext().showToast(exception.message ?: getString(R.string.error_general))
            }
            is Failure.GeneralError -> {
                requireContext().showToast(exception.message ?: getString(R.string.error_general))
            }
            is Failure.HttpError -> {
                requireContext().showToast(exception.message)
            }
            is Failure.NoInternetError -> {
                requireContext().showToast(exception.message ?: getString(R.string.error_general))
            }
            is Failure.TimeOutError -> {
                requireContext().showToast(exception.message ?: getString(R.string.error_timeout))
            }
        }
    }

    private fun initNavigation() {
        navController = findNavController()
    }

    protected fun navigate(directions: NavDirections) {
        val currentDestination =
            (navController.currentDestination as? FragmentNavigator.Destination)?.className
                ?: (navController.currentDestination as? DialogFragmentNavigator.Destination)?.className

        if (currentDestination == this.javaClass.name)
            navController.navigate(directions)
    }

    protected fun popBackStack() {
        navController.popBackStack()
    }

    protected fun navigateUp() {
        navController.navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}