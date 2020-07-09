package com.fromthebasement.githubrepos.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Base class for Fragments that make use of Databinding
 */
abstract class BaseBindingFragment<DB : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutResource: Int

    open lateinit var binding: DB

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

}