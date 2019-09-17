package com.github.chernovdmitriy.feature1_impl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.chernovdmitriy.feature1_api.Feature1Output
import com.github.chernovdmitriy.feature1_api.Feature1Object
import com.github.chernovdmitriy.feature1_impl.di.Feature1Component
import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import kotlinx.android.synthetic.main.fmt_feature1.*
import javax.inject.Inject

class Feature1Fragment : Fragment(), ComponentOwner<Feature1Component> {

    companion object {
        @JvmStatic fun newInstance() = Feature1Fragment()
        @JvmStatic
        fun bundle() = Bundle()
    }

    @Inject
    internal lateinit var feature1Output: Feature1Output

    @Inject
    internal lateinit var feature1Object: Feature1Object

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fmt_feature1, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "feature1Object: $feature1Object"

        button1.setOnClickListener { feature1Output.clickBottom1() }
        button2.setOnClickListener { feature1Output.clickButton2() }
    }

    override fun inject(t: Feature1Component) = t.inject(this)

    override fun provideComponent(): Feature1Component {
        Log.d("AppRestore", "provideComponent Feature1Component")

        return InjectionHolderX.instance.findComponent(
            componentClass = Feature1Component::class.java,
            componentBuilder = { Feature1ComponentProvider.getInstance().feature1Component }
        )
    }

}