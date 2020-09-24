package com.orcaolineapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value="orcaonline") 
public class OrcaOnlineApiProperty {

//	Produção
//	private String originPermitida = "http://127.0.0.1:8080";
	
//  Desenvolvimento	
	private String originPermitida = "http://localhost:4200";
	
	private final Seguranca seguranca = new Seguranca();
	
	public Seguranca getSeguranca() {
		return seguranca;
	}

	public String getOriginPermitida() {
		return originPermitida;
	}
	
	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}

	public static class Seguranca {
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
}
