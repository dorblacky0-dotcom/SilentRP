package net.rweconomy.silentrp.classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.gson.Gson;

import net.risingworld.api.Server;
import net.rweconomy.silentrp.SilentRP;
import net.rweconomy.silentrp.models.SilentRPApiConnectRequest;

public class SilentRPHive extends SilentRP {
	
	public void connect() {
		String apiUrl = this.silentConfigData.getHive_url() + "plugin_connect";
		try {
			configureTrustAllSSL();
			URL url = new URL(apiUrl);
			
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setDoOutput(true);
	        
	        var requestData = new SilentRPApiConnectRequest();
	        requestData.setApi_key(this.silentConfigData.getApiKey());
	        
	        String serverIP = Server.getOption("Server_IP"); 
	        if(serverIP.isEmpty()) {
	        	serverIP = InetAddress.getLocalHost().getHostAddress();
	        }
	        int serverPort = 4255;
	        if(!Server.getOption("Server_Port").isEmpty()) {
	        	serverPort = Integer.parseInt(Server.getOption("Server_Port"));
	        }
	        requestData.setServer_ip(serverIP);
	        requestData.setServer_port(serverPort);
	        
	        Gson json = new Gson();
	        String jsonString = json.toJson(requestData);
	        try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
	        int status = conn.getResponseCode();
	        switch(status) {
	        	default:
	        	case 400:{
	        		
	        	}break;
	        	case 200: {
	        		try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
	        			StringBuilder response = new StringBuilder();
	        			String responseLine = null;
	        			while((responseLine = br.readLine()) != null) {
	        				response.append(responseLine.trim());
	        			}
	        		}
	        	}; break;
	        }
	        conn.disconnect();	        
		} 
		catch(MalformedURLException ex) {
			throw new RuntimeException(ex);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void configureTrustAllSSL() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing - trust all clients
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing - trust all servers
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
    }
}
