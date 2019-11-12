package me.staartvin.plugins.pluginlibrary.hooks;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.staartvin.plugins.pluginlibrary.Library;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * WorldGuard library,
 * <a href="https://dev.bukkit.org/projects/worldguard">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class WorldGuardHook extends LibraryHook {

    private WorldGuardPlugin worldGuard;

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.LibraryHook#isAvailable()
     */
    @Override
    public boolean isAvailable() {
        // TODO Auto-generated method stub

        return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.WORLDGUARD
                .getInternalPluginName());
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        // TODO Auto-generated method stub

        if (!isAvailable())
            return false;

        worldGuard = (WorldGuardPlugin) this.getPlugin().getServer().getPluginManager()
                .getPlugin(Library.WORLDGUARD.getInternalPluginName());

        return worldGuard != null;
    }

    /**
     * Check to see if a player is in a specific WorldGuard region
     *
     * @param player
     *            Player that needs to be checked
     * @param regionName
     *            Name of the region to be checked
     * @return true if the player is in that region; false otherwise.
     */
    public boolean isInRegion(final Player player, final String regionName) {
        if (!isAvailable())
            return false;

        if (player == null || regionName == null)
            return false;

        return this.isInRegion(player.getLocation(), regionName);
    }

    /**
     * @see #isInRegion(Player, String)
     * @param location
     * @param regionName
     * @return
     */
    public boolean isInRegion(Location location, String regionName) {

        if (!this.isAvailable()) {
            return false;
        }

        if (location == null)
            return false;

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

        // Check all worlds (a region manager is applicable for a world)
        for (RegionManager regionManager : container.getLoaded()) {

            // Check if the region manager has the specified region.
            if (!regionManager.hasRegion(regionName)) {
                continue;
            }

            // Check all regions and see if the value is inside some region
            ProtectedRegion region = regionManager.getRegion(regionName);

            // We cannot find the region that was specified, so the player cannot be in it.
            if (region == null) {
                return false;
            }

            // Check whether the location is inside the region.
            return region.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        }

        // We couldn't find the region, so it means the player cannot be in it.
        return false;
    }
}
