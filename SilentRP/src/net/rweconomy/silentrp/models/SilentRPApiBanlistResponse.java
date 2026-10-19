package net.rweconomy.silentrp.models;

import java.sql.Date;

public class SilentRPApiBanlistResponse {
	private GlobalBanEntry[] entrys;

	public GlobalBanEntry[] getEntrys() {
		return entrys;
	}

	public void setEntrys(GlobalBanEntry[] entrys) {
		this.entrys = entrys;
	}
	
}
