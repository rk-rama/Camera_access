package com.Camera.access;

import org.springframework.boot.SpringApplication;

public class TestCameraAccessApplication {

	public static void main(String[] args) {
		SpringApplication.from(CameraAccessApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
