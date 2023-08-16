package io.github.maple8192.infstorage.storage

import io.github.maple8192.infstorage.item.InfStorageItem
import org.bukkit.NamespacedKey
import org.bukkit.block.Block
import org.bukkit.block.Chest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType

class BlockListener(private val storageItemKey: NamespacedKey, private val materialKey: NamespacedKey, private val amountKey: NamespacedKey, private val storageItem: InfStorageItem) : Listener {
    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (!isStorageItem(event.itemInHand)) return

        val state = event.block.state as? Chest ?: return
        editDataContainer(state.persistentDataContainer)
        state.update(true)
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (!isStorageBlock(event.block)) return

        // ドロップアイテムをすり替え
        event.isDropItems = false
        event.player.world.dropItemNaturally(event.block.location, storageItem.create())
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

    private fun isStorageBlock(block: Block) : Boolean {
        val chest = block.state as? Chest ?: return false
        val pdc = chest.persistentDataContainer
        return pdc.has(materialKey) && pdc.has(amountKey)
    }
}