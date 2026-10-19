package net.rweconomy.silentrp.models;

public class SilentRPApiConnectResponse {
	public String owner;
	public String aktual_plugin_version;
	public String database_update;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAktual_plugin_version() {
		return aktual_plugin_version;
	}
	public void setAktual_plugin_version(String aktual_plugin_version) {
		this.aktual_plugin_version = aktual_plugin_version;
	}
	public String getDatabase_update() {
		return database_update;
	}
	public void setDatabase_update(String database_update) {
		this.database_update = database_update;
	}	
}
