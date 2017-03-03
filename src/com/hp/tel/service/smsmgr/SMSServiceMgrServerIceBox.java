package com.hp.tel.service.smsmgr;

import Ice.Communicator;
import Ice.ObjectAdapter;
import IceBox.Service;

public class SMSServiceMgrServerIceBox implements Service {

	private ObjectAdapter _adapter = null;
	
	@Override
	public void start(String name, Communicator communicator, String[] arg2) {
		_adapter = communicator.createObjectAdapter(name);
		_adapter.add(new SMSServiceMgrImpl(), communicator.stringToIdentity(name));
		System.out.println("SMSService start success ...");
		_adapter.activate();
	}

	@Override
	public void stop() {
		System.out.println("SMSService stop success ...");
		if(_adapter != null){
			_adapter.destroy();
		}
	}

}
