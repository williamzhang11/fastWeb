package com.xiu.fastWeb.util;

public class EncryptKey implements java.security.Key {

	@Override
	public String getAlgorithm() {

		return "DES";
	}

	@Override
	public byte[] getEncoded() {
		byte[] bb = new byte[8];
		bb[0] = 55;
		bb[1] = -62;
		bb[2] = 55;
		bb[3] = -8;
		bb[4] = 14;
		bb[5] = 93;
		bb[6] = -51;
		bb[7] = -22;

		return bb;
	}

	@Override
	public String getFormat() {
		return "RAW";
	}

}