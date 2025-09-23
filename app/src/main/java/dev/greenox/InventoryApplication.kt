package dev.greenox

import android.app.Application
import dev.greenox.data.AppContainer
import dev.greenox.data.AppDataContainer

class InventoryApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(context = this)
    }
}