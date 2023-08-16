package io.github.maple8192.infstorage.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class InfStorageItem(private val storageItemKey: NamespacedKey) {
    fun create() : ItemStack {
        val chest = ItemStack(Material.CHEST).also {
            it.itemMeta = setupMeta(it.itemMeta)
        }

        return chest
    }

    private fun setupMeta(meta: ItemMeta) : ItemMeta {
        meta.displayName(Component.text("無限ストレージ").decoration(TextDecoration.ITALIC, false))
        val dataContainer = meta.persistentDataContainer
        dataContainer.set(storageItemKey, PersistentDataType.BOOLEAN, true)
        return meta
    }
}