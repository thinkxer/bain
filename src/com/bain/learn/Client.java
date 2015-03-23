package com.bain.learn;

public class Client {
	public static ServiceFactory sexServiceFactory = new ServiceFactory() {
		@Override
		public Service createService() {
			return new SexService();
		}
	};
	public static void main(String[] args) {
		Service service = sexServiceFactory.createService();
		service.supply();
	}
}
