package com.github.chernovdmitriy.feature3_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.chernovdmitriy.feature3_api.Feature3Object
import com.github.chernovdmitriy.feature3_impl.di.Feature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import kotlinx.android.synthetic.main.fmt_feature3.*
import javax.inject.Inject

class Feature3Fragment : Fragment(), ComponentOwner<Feature3Component> {

    companion object {
        @JvmStatic
        fun newInstance() = Feature3Fragment()

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

    override fun provideComponent(): Feature3Component =
        Feature3ComponentProvider().feature3Component
}