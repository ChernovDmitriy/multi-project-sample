package com.github.alexshilkin.coordinatormanager

class CoordinatorNotFoundException(key: String) :
    Throwable("Component of the $key type was not found")