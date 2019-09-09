package com.github.chernovdmitriy.feature2_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.chernovdmitriy.feature2_api.Feature2Navigator
import com.github.chernovdmitriy.feature2_api.Feature2Object
import com.github.chernovdmitriy.feature2_impl.di.Feature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import kotlinx.android.synthetic.main.fmt_feature2.*
import javax.inject.Inject

class Feature2Fragment : Fragment(), ComponentOwner<Feature2Component> {

    companion object {
        @JvmStatic fun newInstance() = Feature2Fragment()
        @JvmStatic
        fun bundle() = Bundle()
    }

    @Inject
    lateinit var feature2Navigator: Feature2Navigator

    @Inject
    lateinit var feature2Object: Feature2Object

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fmt_feature2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "Feature2Object: $feature2Object"
    }

    override fun inject(t: Feature2Component) = t.inject(this)

    override fun provideComponent(): Feature2Component =
        Feature2ComponentProvider().feature2Component

}