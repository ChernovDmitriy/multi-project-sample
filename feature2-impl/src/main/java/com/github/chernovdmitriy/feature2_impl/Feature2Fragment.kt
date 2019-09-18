package com.github.chernovdmitriy.feature2_impl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.alexshilkin.coordinatormanager.CoordinatorOwner
import com.github.alexshilkin.coordinatormanager.InjectionCoordinatorHolder
import com.github.chernovdmitriy.feature2_api.Feature2Object
import com.github.chernovdmitriy.feature2_api.Feature2Output
import com.github.chernovdmitriy.feature2_impl.di.Feature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import kotlinx.android.synthetic.main.fmt_feature2.*
import javax.inject.Inject

class Feature2Fragment : Fragment(), ComponentOwner<Feature2Component>,
    CoordinatorOwner<Feature2Coordinator> {

    companion object {
        @JvmStatic
        fun newInstance() = Feature2Fragment()

        @JvmStatic
        fun bundle() = Bundle()
    }

    @Inject
    lateinit var feature2Output: Feature2Output

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
        textView.text = "feature2Object: $feature2Object"
        button.setOnClickListener { feature2Output.clickButton() }
    }

    override fun inject(t: Feature2Component) = t.inject(this)

    override fun provideComponent(): Feature2Component {
        Log.d("AppRestore", "provideComponent Feature2Component")

        return InjectionHolderX.instance.findComponent(
            componentClass = Feature2Component::class.java,
            componentBuilder = { Feature2ComponentProvider.getInstance().feature2Component }
        )
    }

    override fun startCoordinator(coordinator: Feature2Coordinator) {

    }

    override fun provideCoordinator(): Feature2Coordinator {
        return InjectionCoordinatorHolder.instance.findCoordinator(
            coordinatorClass = Feature2Coordinator::class.java,
            coordinatorBuilder = { feature2Output as Feature2Coordinator }
        )
    }
}