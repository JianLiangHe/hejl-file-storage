package edu.storage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import edu.storage.service.RegistryService;

@Service(timeout = 6000) // service是dubbo
public class RegistryServiceImpl implements RegistryService {

	@Override
	public String registryTest() {
		// TODO Auto-generated method stub
		return "success";
	}

}
