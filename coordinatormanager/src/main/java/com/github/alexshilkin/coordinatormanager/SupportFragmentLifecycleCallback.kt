package com.github.alexshilkin.coordinatormanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


internal class SupportFragmentLifecycleCallback(
    private val coordinatorCallback: CoordinatorCallback,
    private val fragmentStateStore: FragmentStateStore
) : FragmentManager.FragmentLifecycleCallbacks() {

    private fun addInjectionIfNeed(fragment: Fragment?) {
        if (fragment is CoordinatorOwner<*>) {
            if (!isInSaveState(fragment)) {
                coordinatorCallback.removeInjection(fragment)
            }
            coordinatorCallback.addInjection(fragment)
        }
    }

    private fun removeInjectionIfNeed(fragment: Fragment?) {
        if (fragment !is CoordinatorOwner<*>) {
            return
        }

        if (fragment.activity?.isFinishing == true) {
            if (!isInSaveState(fragment)) {
                coordinatorCallback.removeInjection(fragment)
            }
            return
        }

        if (isInSaveState(fragment)) {
            return
        }

        var anyParentIsRemoving = false
        var parent = fragment.parentFragment
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving
            parent = parent.parentFragment
        }
        if (fragment.isRemoving || anyParentIsRemoving) {
            coordinatorCallback.removeInjection(fragment)
        }
    }


    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        addInjectionIfNeed(f)
        super.onFragmentAttached(fm, f, context)
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        super.onFragmentStarted(fm, f)
        setSaveState(f, false)
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        setSaveState(f, false)
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        super.onFragmentSaveInstanceState(fm, f, outState)
        setSaveState(f, true)
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        super.onFragmentDetached(fm, f)
        removeInjectionIfNeed(f)
    }

    private fun isInSaveState(fragment: Fragment) = fragmentStateStore.getSaveState(fragment)

    private fun setSaveState(fragment: Fragment, isInSaveState: Boolean) =
        fragmentStateStore.setSaveState(fragment, isInSaveState)

}