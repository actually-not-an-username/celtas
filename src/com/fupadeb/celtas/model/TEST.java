package com.fupadeb.celtas.model;

import java.util.ArrayList;
import java.util.List;

import com.fupadeb.celtas.controller.PostgresControl;

public class TEST {

	public static void main(String[] args) {
		Secure security = new Secure();
		PostgresControl dbControl = new PostgresControl();
		List<Role> roleList = new ArrayList<Role>();
		Role role = dbControl.getRole("Administrator");
		roleList.add(role);
		dbControl.createUser("Usuario", "Apellido", "email@fakedomain.com", security.encode("Contraseña"), roleList);
		dbControl.createUser("Apellido", "FAKEEEE", "email2@fakedomain.com", security.encode("Contraseña"), roleList);

		// demo of some users
		System.out.println("Queriying all users with name or surname = \"Apellido\"");
		List<User> myUsers = dbControl.getUserByName("Apellido");
		for (int i = 0; i < myUsers.size(); i++) {
			User tempUser = myUsers.get(i);
			System.out.println("UserId: " + tempUser.getUid().toString());
			System.out.println("Name: " + tempUser.getName());
			System.out.println("SurName: " + tempUser.getSurName());
			System.out.println("Email: " + tempUser.getEmail());
			System.out.println("Password: " + tempUser.getPassword());
			System.out.println("The associated roles are: ");
			for (int j = 0; j < tempUser.getRoles().size(); i++) {
				System.out.println(">> " + tempUser.getRoles().get(j));
			}
			System.out.println("The user is active?: " + tempUser.getActive().toString());
		}
	}

}
