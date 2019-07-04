package com.study.system;

import com.study.system.common.Constant;
import com.sun.org.apache.bcel.internal.classfile.ConstantValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SystemApplication {

	public static void main(String[] args) {
		System.setProperty("jasypt.encryptor.password", Constant.Password.JASYPT_ENCRYPTOR_PASSWORD);
		SpringApplication.run(SystemApplication.class, args);
	}

}
