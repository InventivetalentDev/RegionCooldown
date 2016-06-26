package org.inventivetalent.regioncooldown;

import com.sk89q.worldguard.protection.flags.DoubleFlag;

public class CooldownFlag extends DoubleFlag {

	public CooldownFlag() {
		super("attack-cooldown");
	}

}
