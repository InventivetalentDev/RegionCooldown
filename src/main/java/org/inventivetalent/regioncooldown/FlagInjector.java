package org.inventivetalent.regioncooldown;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.inventivetalent.regionapi.FlagLoadEvent;
import org.inventivetalent.regionapi.RegionAPI;

import java.util.ArrayList;
import java.util.List;

public class FlagInjector implements Listener {

	public CooldownFlag COOLDOWN_FLAG;

	@EventHandler
	public void on(FlagLoadEvent event) {
		event.addFlag(COOLDOWN_FLAG = new CooldownFlag());
	}

	double getCooldown(Location location) {
		List<ProtectedRegion> regions = new ArrayList<>(RegionAPI.getRegions(location));
		double cooldown = -1;
		ProtectedRegion globalRegion = RegionAPI.getGlobalRegion(location.getWorld());
		if (globalRegion != null) {
			regions.add(0, globalRegion);
		}
		for (ProtectedRegion region : regions) {
			Double regionCooldown = region.getFlag(COOLDOWN_FLAG);
			if (regionCooldown != null) {
				cooldown = regionCooldown;
			}
		}
		return cooldown;
	}

}
