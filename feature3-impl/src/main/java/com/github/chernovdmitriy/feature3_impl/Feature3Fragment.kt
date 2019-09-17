package com.github.chernovdmitriy.feature3_impl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.chernovdmitriy.feature3_api.Feature3Object
import com.github.chernovdmitriy.feature3_impl.di.Feature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import kotlinx.android.synthetic.main.fmt_feature3.*
import javax.inject.Inject

class Feature3Fragment : Fragment(), ComponentOwner<Feature3Component> {

    companion object {
        val VRP = "VRP"

        @JvmStatic
        fun newInstance(vrp: String): Feature3Fragment {
            val fragment = Feature3Fragment()
            fragment.arguments?.putString(VRP, vrp)
            return fragment
        }

        @JvmStatic
        fun bundle() = Bundle()
    }

    @Inject
    lateinit var feature3Object: Feature3Object

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fmt_feature3, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "feature3Object: $feature3Object"
    }

    override fun inject(t: Feature3Component) = t.inject(this)

    override fun provideComponent(): Feature3Component {
        Log.d("AppRestore", "provideComponent Feature3Component")
        val arg = arguments?.get(VRP)

        return InjectionHolderX.instance.findComponent(
            componentClass = Feature3Component::class.java,
            componentBuilder = { Feature3ComponentProvider.getInstance(arg.toString()).feature3Component }
        )
    }
}