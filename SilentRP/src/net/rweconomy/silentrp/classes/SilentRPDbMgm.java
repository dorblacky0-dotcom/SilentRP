package net.rweconomy.silentrp.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import net.risingworld.api.Plugin;
import net.risingworld.api.World;
import net.rweconomy.silentrp.SilentRP;
import net.rweconomy.silentrp.models.SilentRPApiBanlistResponse;
import net.rweconomy.silentrp.models.SilentRPConfigContent;

public class SilentRPDbMgm extends SilentRP {
	private final Plugin plugin;
	private final SilentRPConfigContent silentConfig;
	
	public SilentRPDbMgm(Plugin plugin, SilentRPConfigContent configContent) {
		this.plugin = plugin;
		this.silentConfig = configContent;
	}
	
	public void checkDatabaseStructure() {
		System.out.println("[SILENTRP-DBM] initialize SilentRP database management");
		String worldName = World.getName();
		String dbPath = this.plugin.getPath() + "/" + worldName + "/silentrp.db";
		
		silentDatabase = this.getSQLiteConnection(dbPath);
        silentDatabase.execute("CREATE TABLE IF NOT EXISTS `srp_players` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `player_money` INTEGER NOT NULL DEFAULT 0, `player_level` INTEGER NOT NULL DEFAULT 1, `player_xp` INTEGER NOT NULL default 0);");
        silentDatabase.execute("CREATE TABLE IF NOT EXISTS `srp_version` (`version` VARCHAR(50) NOT NULL DEFAULT '0.0.1', `version_date` DATETIME DEFAULT CURRENT_TIMESTAMP);");
        silentDatabase.execute("CREATE TABLE IF NOT EXISTS `srp_globalbans` (`player_uid` VARCHAR(36) PRIMARY KEY NOT NULL, `reason` TEXT NULL, `last_banaction` DATETIME NOT NULL, `ban_point` INTEGER NOT NULL DEFAULT 0);");

        try(ResultSet result = silentDatabase.executeQuery("SELECT `version` FROM srp_version")) {
        	if(!result.next()) {
        		System.out.println("[SILENTRP-DBM] generate database system");
        		silentDatabase.executeUpdate("INSERT INTO `srp_version` (`version`) VALUES ('" + SilentRP.silentVersion + "');");
        		if(silentConfig.isEnable_global_ban()) {
        			System.out.println("[SILENTRP-DBM] global ban is aktivated. Load banlist from hive..");
        			
        		} else {
        			System.out.println("[SILENTRP-DBM] global ban is not activated. Ignore banlist.");
        		}
        	} else {
        		
        	}
        } catch(SQLException ex) {
        	System.out.println("[SILENTRP-DBM] ERROR: " + ex.getMessage());
        }
	}

	public void addGlobalBann(String player_uid, String reason, Date last_action, int ban_point) {
		silentDatabase.executeUpdate("INSERT INTO `srp_globalbans` (`player_uid`, `reason`, `last_banaction`, `ban_point`) VALUES ('" + player_uid +"', '" + reason +"', "
				+ "'" + last_action + "', " + ban_point +")");
	}
}
