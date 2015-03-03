package org.pxcbpmisframework.core.util;

import bpmis.pxc.system.pojo.TUser;
import java.io.PrintStream;
import java.util.UUID;

public class BpmisUtils {
	public static String getBpmisCode() {
		String str = UUID.randomUUID().toString();
		return "BPMIS_" + str.substring(9, 13) + str.substring(19, 21);
	}

	public static String getSerialNumber() {
		String str = UUID.randomUUID().toString();
		System.out.println(str.substring(0, 8) + str.substring(9, 13));
		return DataToolsUtils.SimpleFormatTime("yyyyMMddhhmmss")
				+ str.substring(0, 8) + str.substring(9, 13);
	}

	public static String getYearMounth(String day) {
		String[] days = day.split("-");
		return days[0] + "-" + days[1];
	}

	public static TUser InitUser() {
		TUser user = new TUser();
		user.setAccount("panxiaochao");
		user.setPwd(PasswordUtil.PasswordMd5("123456"));
		user.setLasttime(DataToolsUtils.SimpleFormatTime());
		user.setLogintime(DataToolsUtils.SimpleFormatTime());
		user.setUsername("潘骁超");
		return user;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getSerialNumber());
		}
	}
}
