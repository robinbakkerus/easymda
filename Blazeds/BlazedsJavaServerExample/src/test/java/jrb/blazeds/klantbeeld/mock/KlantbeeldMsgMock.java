package jrb.blazeds.klantbeeld.mock;

import flca.mda.api.util.MockDataUtil;
import jrb.grpc.blazeds.KlantbeeldMsg;

public class KlantbeeldMsgMock {
	
	private static final MockDataUtil mu = new MockDataUtil();
	
	public static KlantbeeldMsg buildMock() { 
		final KlantbeeldMsg.Builder builder = KlantbeeldMsg.newBuilder();
		builder.setAchternaam(mu.randomString());
		return builder.build();
	}
}
