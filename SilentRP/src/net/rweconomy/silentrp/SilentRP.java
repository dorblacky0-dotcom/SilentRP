package net.rweconomy.silentrp;

import java.util.Objects;

import net.risingworld.api.Plugin;
import net.risingworld.api.database.Database;
import net.risingworld.api.events.Listener;
import net.rweconomy.silentrp.classes.SilentRPConfig;
import net.rweconomy.silentrp.classes.SilentRPHive;
import net.rweconomy.silentrp.models.SilentRPConfigContent;

public class SilentRP extends Plugin implements Listener {
	public final String anonymousApiKey = "9d448347-ab70-4036-9a1a-680b987df301";
	
	protected static Database silentDatabase;
	protected static String silentVersion = "0.0.3";
	protected String silentPath = this.getPath(); 
	public SilentRPConfigContent silentConfigData;
	private SilentRPConfig silentConfig;	
	
	@Override
	public void onEnable() {
		try {
			silentConfig = new SilentRPConfig(this);
			this.silentConfigData = silentConfig.readConfig();
			if(Objects.equals(this.silentConfigData.getApiKey(), this.anonymousApiKey)) {
				System.out.println("[SILENTRP-MAIN] Please register this Plugin under https://silentrp.de or entry your ApiKey in configfile!");
			}
			
			var connectResponse = new SilentRPHive(silentConfigData).connect();
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}				
	}

	@Override
	public void onDisable() {
	
	}

}
