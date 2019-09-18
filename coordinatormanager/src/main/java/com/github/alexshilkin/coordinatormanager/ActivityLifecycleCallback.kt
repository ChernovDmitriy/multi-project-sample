package com.github.alexshilkin.coordinatormanager

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


internal class ActivityLifecycleCallback(
    private val coordinatorCallback: CoordinatorCallback
) : Application.ActivityLifecycleCallbacks {

    private companion object {
        const val IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH"
    }

    private fun isFirstLaunch(outState: Bundle?): Boolean =
        outState?.getBoolean(IS_FIRST_LAUNCH, true) ?: true

    private fun setFirstLaunch(outState: Bundle?, isFirstLaunch: Boolean) {
        outState?.putBoolean(IS_FIRST_LAUNCH, isFirstLaunch)
    }

    private fun addInjectionIfNeed(activity: Activity, outState: Bundle?) {
        if (activity is CoordinatorOwner<*>) {
            if (isFirstLaunch(outState)) {
                coordinatorCallback.removeInjection(activity)
            }
            coordinatorCallback.addInjection(activity)
        }

        (activity as? AppCompatActivity)
            ?.supportFragmentManager
            ?.registerFragmentLifecycleCallbacks(
                SupportFragmentLifecycleCallback(coordinatorCallback, FragmentStateStore.instance),
                true
            )
    }

    private fun removeInjectionIfNeed(activity: Activity) {
        if (activity is CoordinatorOwner<*> && activity.isFinishing) {
            coordinatorCallback.removeInjection(activity)
        }
    }

    override fun onActivityCreated(activity: Activity, outState: Bundle?) {
        addInjectionIfNeed(activity, outState)
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
        removeInjectionIfNeed(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        setFirstLaunch(outState, isFirstLaunch = false)
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

}