package com.fupadeb.celtas.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import com.fupadeb.celtas.logger.LoggerUtility;

class Secure {
	private LoggerUtility logger;

	public Secure() {
		logger = new LoggerUtility(0);
		logger.log(4, this.getClass().getName(), "constructor", "Initializating");
	}

	private byte[] getKey() {
		String encodedPrivateKey = "";
		byte[] decodedKey;
		try {
			FileReader input = new FileReader("Configuration/Key.txt");
			BufferedReader buffer = new BufferedReader(input);
			encodedPrivateKey = buffer.readLine();
			buffer.close();
		} catch (Exception e) {
			logger.log(3, this.getClass().getName(), "getKey", e.getMessage());
		}
		decodedKey = Base64.decodeBase64(encodedPrivateKey);
		return decodedKey;
	}

	public String encode(String message)
	{		
		String encodedString = "";
		try
		{
			MessageDigest messageToDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
			messageToDigest.update(message.getBytes("UTF-8"));
			byte[] digestBytes = messageToDigest.digest();						
			return Base64.encodeBase64String(digestBytes);
		}
		catch (Exception e) {
			logger.log(3, this.getClass().getName(), "encode", e.getMessage());
		}
		return encodedString;
	}
}
