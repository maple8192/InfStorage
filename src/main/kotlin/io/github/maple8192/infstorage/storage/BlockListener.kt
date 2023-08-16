package io.github.maple8192.infstorage.storage

import org.bukkit.NamespacedKey
import org.bukkit.block.Chest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType

class BlockListener(private val storageItemKey: NamespacedKey, private val materialKey: NamespacedKey, private val amountKey: NamespacedKey) : Listener {
    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (!isStorageItem(event.itemInHand)) return

        val state = event.block.state as? Chest ?: return
        editDataContainer(state.persistentDataContainer)
        state.update(true)
    }

    private fun isStorageItem(item: ItemStack) : Boolean {
        val meta = item.itemMeta
        val pdc = meta.persistentDataContainer
        return pdc.has(storageItemKey)
    }

    private fun editDataContainer(pdc: PersistentDataContainer) {
        pdc.set(materialKey, PersistentDataType.STRING, "")
        pdc.set(amountKey, PersistentDataType.LONG, -1)
    }
}