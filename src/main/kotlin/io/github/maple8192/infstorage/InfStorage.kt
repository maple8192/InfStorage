package io.github.maple8192.infstorage

import io.github.maple8192.infstorage.item.InfStorageItem
import io.github.maple8192.infstorage.recipe.CraftingListener
import io.github.maple8192.infstorage.storage.BlockListener
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class InfStorage : JavaPlugin() {
    private val storageItemKey = NamespacedKey(this, "storage-item")
    private val materialKey = NamespacedKey(this, "item-material")
    private val amountKey = NamespacedKey(this, "item-amount")

    private val storageItem = InfStorageItem(storageItemKey)

    override fun onEnable() {
        val pluginManager = server.pluginManager
        pluginManager.registerEvents(CraftingListener(storageItem), this)
        pluginManager.registerEvents(BlockListener(storageItemKey, materialKey, amountKey), this)
    }
}