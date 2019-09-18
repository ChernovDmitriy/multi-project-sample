package com.github.chernovdmitriy.feature3_impl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.alexshilkin.coordinatormanager.CoordinatorOwner
import com.github.alexshilkin.coordinatormanager.InjectionCoordinatorHolder
import com.github.chernovdmitriy.feature3_api.Feature3Object
import com.github.chernovdmitriy.feature3_api.Feature3Output
import com.github.chernovdmitriy.feature3_impl.di.Feature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import kotlinx.android.synthetic.main.fmt_feature3.*
import javax.inject.Inject

class Feature3Fragment : Fragment(), ComponentOwner<Feature3Component>, CoordinatorOwner<Feature3Coordinator> {

    companion object {
        val VRP = "VRP"

        @JvmStatic
        fun newInstance(vrp: String): Feature3Fragment {
            val fragment = Feature3Fragment()
            val bundle = Bundle()
            bundle.putString(VRP, vrp)
            fragment.arguments = bundle
            return fragment
        }

        @JvmStatic
        fun bundle() = Bundle()
    }

    @Inject
    lateinit var feature3Object: Feature3Object

    @Inject
    lateinit var feature3Output: Feature3Output

    @Inject
    lateinit var vrp: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fmt_feature3, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView1.text = "feature3Object: $feature3Object"
        textView2.text = "VRP = $vrp"
    }

    override fun inject(t: Feature3Component) = t.inject(this)

    override fun provideComponent(): Feature3Component {
        Log.d("AppRestore", "provideComponent Feature3Component")
        val arg = arguments?.get(VRP)

        return InjectionHolderX.instance.findComponent(
            componentClass = Feature3Component::class.java,
            componentBuilder = {
                Feature3ComponentProvider.getInstance(arg.toString()).feature3Component
            }
        )
    }

    override fun startCoordinator(coordinator: Feature3Coordinator) {

    }

    override fun provideCoordinator(): Feature3Coordinator {
        return InjectionCoordinatorHolder.instance.findCoordinator(
            coordinatorClass = Feature3Coordinator::class.java,
            coordinatorBuilder = { feature3Output as Feature3Coordinator }
        )
    }
}