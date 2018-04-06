package org.icar.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.icar.musa.runtime_entity.AbstractCapability;
import org.icar.specification.ACLanguage.CapabilityBuilder;
import org.icar.specification.ACLanguage.specmodel.Capability;

public class myCapabilityLanguageExample {

	public static void main(String[] args) throws IOException {
		File file = new File("./doc/check_user_capability.txt");
		FileInputStream fis = new FileInputStream(file);

		Capability cap = CapabilityBuilder.parse(fis);
		
		System.out.println(cap.toString());
		
		AbstractCapability abs_cap = CapabilityBuilder.convertToAbstract(cap);
		System.out.println(abs_cap.toString());
		
	}

}
