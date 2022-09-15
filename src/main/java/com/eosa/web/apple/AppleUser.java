package com.eosa.web.apple;

import lombok.Data;

@Data
public class AppleUser {
	private String email;
	private Name name;

	@Data
	public static class Name {
		private String firstName;
		private String middleName;
		private String lastName;
	}
}
