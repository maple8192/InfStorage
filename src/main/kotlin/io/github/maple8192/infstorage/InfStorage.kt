package io.github.maple8192.infstorage

import io.github.maple8192.infstorage.item.InfStorageItem
import io.github.maple8192.infstorage.recipe.CraftingListener
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class InfStorage : JavaPlugin() {
    private val storageItem = InfStorageItem(this)
    private val storageItemKey = NamespacedKey(this, "storage-item")

    val storageItem = InfStorageItem(storageItemKey)

    override fun onEnable() {
        val pluginManager = server.pluginManager
        pluginManager.registerEvents(CraftingListener(storageItem), this)
    }
}