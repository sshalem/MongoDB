package com.example.facade;

import com.example.common.ClientType;

public interface CouponClientFacade {
	
	CouponClientFacade login(String name, String password, ClientType clientType);

}
