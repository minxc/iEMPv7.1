package org.minxc.emp.bpm.plugin.task.reminders.def;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ReminderDef {
	private static char[] I = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '+', '/'};
	private static byte[] J = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1,
			63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
			11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31,
			32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

	public static void main(String[] args) {
		System.out.println(new String(ReminderDef.decode("bWlhb2ppZmFuZw==")));
	}

	public static byte[] decode(String str) {
		byte[] data = str.getBytes();
		int len = data.length;
		ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
		int i = 0;
		while (i < len) {
			byte b2;
			byte b4;
			byte b3;
			byte b1;
			do {
				b1 = J[data[i++]];
			} while (i < len && b1 == -1);
			if (b1 == -1)
				break;
			do {
				b2 = J[data[i++]];
			} while (i < len && b2 == -1);
			if (b2 == -1)
				break;
			buf.write(b1 << 2 | (b2 & 48) >>> 4);
			do {
				if ((b3 = data[i++]) == 61) {
					return buf.toByteArray();
				}
				b3 = J[b3];
			} while (i < len && b3 == -1);
			if (b3 == -1)
				break;
			buf.write((b2 & 15) << 4 | (b3 & 60) >>> 2);
			do {
				if ((b4 = data[i++]) == 61) {
					return buf.toByteArray();
				}
				b4 = J[b4];
			} while (i < len && b4 == -1);
			if (b4 == -1)
				break;
			buf.write((b3 & 3) << 6 | b4);
		}
		return buf.toByteArray();
	}
}