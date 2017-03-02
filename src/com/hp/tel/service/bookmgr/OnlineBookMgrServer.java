package com.hp.tel.service.bookmgr;

//  ICE 服务入口
public class OnlineBookMgrServer {

	public static void main(String[] args) {
		
		int status = 0 ;
		// 定义通信器对象
		Ice.Communicator ic = null ; 
		try {
 			// 1、 初始化通信器，args 传递一些参数
			ic = Ice.Util.initialize(args);
			
			// 2、创建Object Adapter ，使用缺省通信协议，名称为：OnlineBookAdapter
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("OnlineBookAdapter", "default -p 10000");
			
			// 3、实例化一个OnlineBookMgrImpl 服务对象（servant）
			OnlineBookMgrImpl book = new OnlineBookMgrImpl();
			
			// 4、将servant增加到ObjectAdapter 中，并将servant 关联到ID为 OnlilneBookService 的Ice Object
			adapter.add(book, Ice.Util.stringToIdentity("OnlineBookService"));
			
			// 5、激活Adapter
			adapter.activate();
			
			// 让任务退出前，一直持续请求监听
			System.out.println("server start ...");
			
			ic.waitForShutdown();
			
		} catch (Exception e) {
				
			e.printStackTrace();
			status = 1;
		}finally{
			
			if(ic != null){
				ic.destroy();
			}
		}
		
		System.exit(status);
		
	}
}
