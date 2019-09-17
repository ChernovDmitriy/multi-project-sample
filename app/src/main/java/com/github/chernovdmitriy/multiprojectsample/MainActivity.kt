package com.github.chernovdmitriy.multiprojectsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.coordinator.CoordinatorManager
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ComponentOwner<AppComponent> {

    @Inject
    internal lateinit var mainCoordinator: MainCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainCoordinator.startMainCoordinator(R.id.mainContainer, supportFragmentManager)
    }

    override fun provideComponent(): AppComponent =
        InjectionHolderX.instance.findComponent(AppComponent::class.java)

    override fun inject(t: AppComponent) = t.inject(this)

}
