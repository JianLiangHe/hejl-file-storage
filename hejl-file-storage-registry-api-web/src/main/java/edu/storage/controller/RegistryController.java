package edu.storage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import edu.storage.service.RegistryService;
import edu.storage.util.ApiResponse;

@RestController
@RequestMapping("/registry/")
public class RegistryController {

	@Reference
	private RegistryService registryService;
	
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ApiResponse test() {
		String result = registryService.registryTest();
		return new ApiResponse(200, result, result);
	}
	
}
