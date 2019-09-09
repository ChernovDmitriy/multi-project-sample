package com.github.chernovdmitriy.feature1_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.chernovdmitriy.feature1_impl.di.Feature1Component
import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner

class Feature1Fragment : Fragment(), ComponentOwner<Feature1Component> {

    companion object {
        @JvmStatic fun newInstance() = Feature1Fragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fmt_feature1, container, false)

    override fun inject(t: Feature1Component) = t.inject(this)

    override fun provideComponent(): Feature1Component =
        Feature1ComponentProvider.instance.feature1Component

}