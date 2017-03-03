package com.hp.tel.service.bookmgr;

import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book.OnlineBookPrx;
import com.hp.tel.ice.book.OnlineBookPrxHelper;

// 模拟客户端
public class OnlineBookMgrClient {

	public static void main(String[] args) {
		int status = 0 ; 
		Ice.Communicator ic = null;
		try {
			String[] initParams = new String[]{ 
				"--Ice.Default.Locator=DemoGrid/Locator:tcp -h 127.0.0.1 -p 4061"
			};
			// 1、初始化通信器
			ic = Ice.Util.initialize(initParams);
			
			// 2、传入远程服务单元名称、网络协议、IP及端口，构造一个Proxy对象
			//Ice.ObjectPrx base = ic.stringToProxy("OnlineBookService:default -p 10000");
			
			// 2.1 使用registry获取代理对象
			Ice.ObjectPrx base = ic.stringToProxy("OnlineBookService@OnlilneBookAdapter");
			
			// 3、通过checkedCast向下转型，获取OnlineBookService接口的远程，并同时检测传入的名称
			OnlineBookPrx onlilneBookPrx = OnlineBookPrxHelper.checkedCast(base);
			
			if(onlilneBookPrx == null){
				throw new Error("Invalid proxy");
			}
			
			// 4、调用服务方法
			Message msg = onlilneBookPrx.bookTick(new Message());
			System.out.println(msg.content);
			
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		}finally {
			if(ic != null){
				ic.destroy();
			}
		}
		System.exit(status);
	}
}
