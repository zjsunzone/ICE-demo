[[ "java:package:com.hp.tel.ice" ]]
module book {

	// 定义书籍实体
	struct Message {
		string name;		// 图书名称
		int type; 			// 图书类型
		bool valid; 		// 是否存在库存
		double price;		// 图书价格
		string content;		// 图书内容
	};
	
	// 定义服务接口（属于book模块），该服务提供一个订票方法
	interface OnlineBook {
		Message bookTick(Message msg);
	};
	
	
};