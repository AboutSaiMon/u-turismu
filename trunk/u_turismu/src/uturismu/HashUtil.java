/*
 * This file is part of "U Turismu" project. 
 * 
 * U Turismu is an enterprise application in support of calabrian tour operators.
 * This system aims to promote tourist services provided by the operators
 * and to develop and improve tourism in Calabria.
 *
 * Copyright (C) 2012 "LagrecaSpaccarotella" team.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uturismu;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Random;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class HashUtil {

	/*
	 * generates a 128 characters code
	 */
	private static final String SHA_512 = "SHA-512";

	/**
	 * Generates a random string
	 */
	public static String generateSalt() {
		return Long.toHexString(new Random(System.nanoTime()).nextLong());
	}

	/**
	 * Gets a salted hash code.
	 */
	public static String getHash(String password, String salt) {
		try {
			// a message digest object is created;
			MessageDigest messageDigest = null;
			messageDigest = MessageDigest.getInstance(SHA_512);
			messageDigest.reset();
			messageDigest.update(salt.getBytes("UTF-8"));
			messageDigest.update(password.getBytes("UTF-8"));
			// a message digest is generated
			byte[] input = messageDigest.digest();
			// the message digest is hashed 1000 times
			for (int i = 0; i < 1000; i++) {
				messageDigest.reset();
				input = messageDigest.digest(input);
			}
			// the message digest is formatted in hexadecimal format
			Formatter formatter = new Formatter();
			for (byte i : input) {
				formatter.format("%02x", i);
			}
			return formatter.toString();
		} catch (NoSuchAlgorithmException e) {
			return "";
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}