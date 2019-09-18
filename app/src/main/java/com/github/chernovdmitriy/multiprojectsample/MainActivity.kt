package com.github.chernovdmitriy.multiprojectsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.alexshilkin.coordinatormanager.CoordinatorOwner
import com.github.alexshilkin.coordinatormanager.InjectionCoordinatorHolder
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ComponentOwner<AppComponent>,
    CoordinatorOwner<MainCoordinator> {

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

    override fun startCoordinator(coordinator: MainCoordinator) {
    }

    override fun provideCoordinator(): MainCoordinator {
        return InjectionCoordinatorHolder.instance.findCoordinator(MainCoordinator::class.java)
    }
}
