package com.github.chernovdmitriy.feature2_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.chernovdmitriy.feature2_impl.di.Feature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner

class Feature2Fragment : Fragment(), ComponentOwner<Feature2Component> {

    companion object {
        @JvmStatic fun newInstance() = Feature2Fragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fmt_feature2, container, false)

    override fun inject(t: Feature2Component) = t.inject(this)

    override fun provideComponent(): Feature2Component =
        Feature2ComponentProvider.instance.feature2Component

}