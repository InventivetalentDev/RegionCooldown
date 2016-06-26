package org.inventivetalent.regioncooldown;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.apihelper.APIManager;
import org.inventivetalent.regionapi.RegionAPI;

public class RegionCooldown extends JavaPlugin implements Listener {

	FlagInjector flagInjector;

	@Override
	public void onLoad() {
		APIManager.require(RegionAPI.class, this);
	}

	@Override
	public void onEnable() {
		APIManager.initAPI(RegionAPI.class);
		Bukkit.getPluginManager().registerEvents(flagInjector = new FlagInjector(), this);
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void on(PlayerInteractEvent event) {
		double cooldown = flagInjector.getCooldown(event.getPlayer().getLocation());
		if (cooldown != -1) {
			event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(cooldown);
		}
	}

}
