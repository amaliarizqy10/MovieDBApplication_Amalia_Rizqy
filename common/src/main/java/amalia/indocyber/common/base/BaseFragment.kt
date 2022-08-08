package amalia.indocyber.common.base

import amalia.indocyber.common.BR
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM: BaseViewModel, Binding:ViewDataBinding> :Fragment() {
    abstract val vm:VM
    lateinit var binding: Binding
    abstract val layoutResourceId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<Binding>(inflater, layoutResourceId, container, false)
        binding.setVariable(BR.vm,vm)
        binding.lifecycleOwner = this
        initBinding(binding)
        return binding.root
    }

    open fun initBinding(binding: Binding){}
}