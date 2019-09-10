package com.github.chernovdmitriy.feature1_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.chernovdmitriy.feature1_api.Feature1Navigator
import com.github.chernovdmitriy.feature1_api.Feature1Object
import com.github.chernovdmitriy.feature1_impl.di.Feature1Component
import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import kotlinx.android.synthetic.main.fmt_feature1.*
import javax.inject.Inject

class Feature1Fragment : Fragment(), ComponentOwner<Feature1Component> {

    companion object {
        @JvmStatic fun newInstance() = Feature1Fragment()
        @JvmStatic
        fun bundle() = Bundle()
    }

    @Inject
    internal lateinit var feature1Navigator: Feature1Navigator

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
        button.setOnClickListener { feature1Navigator.moveToFeature2() }
    }

    override fun inject(t: Feature1Component) = t.inject(this)

    override fun provideComponent(): Feature1Component =
        Feature1ComponentProvider().feature1Component

}