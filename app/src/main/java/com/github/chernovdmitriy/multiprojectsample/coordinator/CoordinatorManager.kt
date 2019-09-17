package com.github.chernovdmitriy.multiprojectsample.coordinator

class CoordinatorManager {

    private val coordinators: MutableMap<String, Coordinator> = HashMap()

    fun addCoordinator(key: String, coordinator: Coordinator) {
        coordinators.put(key, coordinator)
    }

    fun removeCoordinator(key: String) {
        coordinators.remove(key)
    }

    fun getCoordinator(key: String): Coordinator? {
        return coordinators[key]
    }

    fun isContains(key: String): Boolean {
        return coordinators.contains(key)
    }
}