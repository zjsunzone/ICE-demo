package com.hp.tel.service.bookmgr;

import Ice.Communicator;
import Ice.Object;
import Ice.ObjectAdapter;
import IceBox.Service;

// 使用IceBox 完成服务启动
public class OnlineBookMgrServiceIceBox implements Service {

	// 定义ObjectAdapter
	private ObjectAdapter _adapter ; 
	public static final String objectAdapterStr = "OnlineBookAdapter";
	
	/**
	 * IceBox 服务启动初始化
	 * @param name 服务名称
	 * @param args 服务配置信息
	 */
	@Override
	public void start(String name, Communicator communicator, String[] args) {
		
		System.out.println("start args[name] -> "+name);
		System.out.println("args -> " + args[0]);
		
		String objectStr = "OnlineBookService";
		
		// 1、创建objectAdapter，name 为定义IceBox中定义的service名称
		_adapter = communicator.createObjectAdapter(objectAdapterStr);
		System.out.println("--------- step02 ------------");
		
		// 2、创建servant并激活
		_adapter.add(new OnlineBookMgrImpl(), communicator.stringToIdentity(name));
		
		// 3、激活
		_adapter.activate();
		System.out.println("start ....");
	}

	@Override
	public void stop() {
		System.out.println(this._adapter.getName() + " stop...");
		_adapter.destroy();
	}

}
