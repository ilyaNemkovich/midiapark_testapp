package com.midiapark.newsapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.reflect.KClass
import kotlin.reflect.full.staticFunctions

abstract class ViewBindingBottomSheetFragment<VB : ViewBinding>(
    private val viewBindingClass: KClass<VB>
) : BottomSheetDialogFragment() {

    val binding: VB get() = requireNotNull(_binding)
    private var _binding: VB? = null

    @Suppress("UNCHECKED_CAST")
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB =
        { inflater, container, _ ->
            viewBindingClass.staticFunctions.single { it.name == "inflate" && it.parameters.size > 1 }
                .call(inflater, container, false) as VB
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}