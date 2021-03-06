/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package dev.mmccall.farmdisp;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.papermc.paper.event.block.BlockPreDispenseEvent;

public class FarmDispensers extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DispenseHandler(), this);
    }

    @Override
    public void onDisable() {
        // TODO Auto-generated method stub
    }

}

/**
 * DispenseHandler
 */
class DispenseHandler implements Listener {
    @EventHandler
    public void onDispense(BlockPreDispenseEvent e) {
        Block block = e.getBlock();
        ItemStack item = e.getItemStack();

        if (block.getType() != Material.DISPENSER) {
            return;
        }

        Dispenser dispenserData = (Dispenser) block.getBlockData();
        Block facingBlock = block.getRelative(dispenserData.getFacing());

        if (!facingBlock.getBlockData().getMaterial().isAir()) {
            return;
        }

        Block blockUnderFacingBlock = facingBlock.getRelative(BlockFace.DOWN);

        if (blockUnderFacingBlock.getBlockData().getMaterial() != Material.FARMLAND) {
            return;
        }

        switch (item.getType()) {
            case WHEAT_SEEDS:
                facingBlock.setType(Material.WHEAT);
                break;
            case CARROT:
                facingBlock.setType(Material.CARROTS);
                break;
            case POTATO:
                facingBlock.setType(Material.POTATOES);
                break;
            case BEETROOT_SEEDS:
                facingBlock.setType(Material.BEETROOTS);
                break;
            case MELON_SEEDS:
                facingBlock.setType(Material.MELON_STEM);
                break;
            case PUMPKIN_SEEDS:
                facingBlock.setType(Material.PUMPKIN_STEM);
                break;
            default:
                return;
        }

        item.subtract();
        e.setCancelled(true);
    }

}