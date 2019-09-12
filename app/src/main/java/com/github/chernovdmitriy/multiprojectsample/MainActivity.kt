package com.github.chernovdmitriy.multiprojectsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import ru.mosparking.injectionholder.ComponentOwner
import ru.mosparking.injectionholder.InjectionHolder
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ComponentOwner<AppComponent> {

    @Inject
    internal lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigator.bind(findNavController(R.id.nav_host_fragment))
    }

    override fun onPause() {
        super.onPause()
        navigator.unbind()
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.nav_host_fragment).navigateUp()

    override fun provideComponent(): AppComponent =
        InjectionHolder.instance.findComponent(AppComponent::class.java)

    override fun inject(t: AppComponent) = t.inject(this)

}
