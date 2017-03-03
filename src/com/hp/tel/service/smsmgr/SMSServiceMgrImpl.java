package com.hp.tel.service.smsmgr;

import Ice.Current;

import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book.OnlineBook;
import com.hp.tel.ice.book.OnlineBookPrx;
import com.hp.tel.ice.book.OnlineBookPrxHelper;
import com.hp.tel.ice.message._SMSServiceDisp;

public class SMSServiceMgrImpl extends _SMSServiceDisp {

	@Override
	public void sendSMS(String msg, Current __current) {
		// TODO Auto-generated method stub
		System.out.println("send msg ,content : "  + msg);
		
		// 本地服务互相调用
		if(msg.startsWith("book")){
			try {
				System.out.println(__current.adapter.getName());
				Ice.ObjectPrx proxy = __current.adapter.getCommunicator().stringToProxy("OnlineBookService@OnlilneBookAdapter");
				OnlineBookPrx onlineBook = OnlineBookPrxHelper.checkedCast(proxy);
				Message message = new Message();
				message.content = "成功书名" ;
				onlineBook.bookTick(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
