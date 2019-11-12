package me.staartvin.plugins.pluginlibrary.hooks;

import me.staartvin.plugins.pluginlibrary.Library;

import java.util.UUID;

/**
 * UltimateCore library,
 * <a href="http://dev.bukkit.org/bukkit-plugins/ultimatecore/">link</a>.
 * <p>
 * Date created: 17:30:19 14 aug. 2015
 * 
 * @author Staartvin
 *
 */
public class UltimateCoreHook extends LibraryHook {

//	private UltimateCore api;

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
        return this.getPlugin().getServer().getPluginManager().isPluginEnabled(Library.ULTIMATECORE.getInternalPluginName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
	 */
	@Override
	public boolean hook() {
		if (!isAvailable())
			return false;

//		api = (UltimateCore) this.getPlugin().getServer().getPluginManager()
//                .getPlugin(Library.ULTIMATECORE.getInternalPluginName());

		return false;
	}

    /**
     * Check whether a player is AFK.
     * @param uuid UUID of the player to check.
     * @return true if the player is AFK, false otherwise.
     */
	public boolean isAFK(UUID uuid) {

		return false;
//	    if (!isAvailable()) {
//	        return false;
//        }
//
//        UPlayer player = UC.getPlayer(uuid);
//
//        if (player == null) {
//            return false;
//        }
//
//        return player.isAfk();
    }
}
