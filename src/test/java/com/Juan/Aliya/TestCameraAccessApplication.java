package com.Juan.Aliya;

import org.springframework.boot.SpringApplication;

public class TestCameraAccessApplication {

	public static void main(String[] args) {
		SpringApplication.from(CameraAccessApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
