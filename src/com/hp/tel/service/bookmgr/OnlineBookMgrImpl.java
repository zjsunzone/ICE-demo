package com.hp.tel.service.bookmgr;

import Ice.Current;

import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book._OnlineBookDisp;

public class OnlineBookMgrImpl extends _OnlineBookDisp {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Message bookTick(Message msg, Current __current) {
		Message message = new Message("唐诗300收",1,true,1000,"窗前明月官");
		return message;
	}

}
