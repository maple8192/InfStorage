package io.github.maple8192.infstorage.recipe

import io.github.maple8192.infstorage.item.InfStorageItem
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.inventory.ItemStack

class CraftingListener(private val storageItem: InfStorageItem) : Listener {
    @EventHandler
    fun onPrepareCraftItem(event: PrepareItemCraftEvent) {
        val inventory = event.inventory
        inventory.result = getResult(inventory.matrix) ?: return
    }

    private fun getResult(craftingMatrix: Array<ItemStack?>) : ItemStack? {
        /*
           AAA
           ABA     A: Chest
           AAA     B: Diamond
         */
        val expected = listOf(
            Material.CHEST, Material.CHEST, Material.CHEST,
            Material.CHEST, Material.DIAMOND, Material.CHEST,
            Material.CHEST, Material.CHEST, Material.CHEST)

        val result = craftingMatrix.withIndex().all { craftingMatrix[it.index]?.type == expected[it.index] }
        return if (result) storageItem.create() else null
    }
}