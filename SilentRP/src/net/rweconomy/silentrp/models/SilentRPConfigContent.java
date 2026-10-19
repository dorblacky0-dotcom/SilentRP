package net.rweconomy.silentrp.models;

public class SilentRPConfigContent {
	private String apikey;
    private String watermark;
    private boolean enable_global_ban;
    private int global_ban_atpoints;
    private boolean enable_levelsystem;
    private boolean enable_robberysystem;
    private String hive_url;
    
    public String getApiKey() {
        return apikey;
    }

    public boolean isEnable_global_ban() {
        return enable_global_ban;
    }

    public void setEnable_global_ban(boolean enable_global_ban) {
        this.enable_global_ban = enable_global_ban;
    }

    public int getGlobal_ban_atpoints() {
        return global_ban_atpoints;
    }

    public void setGlobal_ban_atpoints(int global_ban_atpoints) {
        this.global_ban_atpoints = global_ban_atpoints;
    }

    public boolean isEnable_levelsystem() {
        return enable_levelsystem;
    }

    public void setEnable_levelsystem(boolean enable_levelsystem) {
        this.enable_levelsystem = enable_levelsystem;
    }

    public boolean isEnable_robberysystem() {
        return enable_robberysystem;
    }

    public void setEnable_robberysystem(boolean enable_robberysystem) {
        this.enable_robberysystem = enable_robberysystem;
    }

    public void setApiKey(String apiKey) {
        this.apikey = apiKey;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

	public String getHive_url() {
		return hive_url;
	}

	public void setHive_url(String hive_url) {
		this.hive_url = hive_url;
	}
    
}
