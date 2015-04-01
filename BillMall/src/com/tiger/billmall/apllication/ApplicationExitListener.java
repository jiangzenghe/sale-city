package com.tiger.billmall.apllication;

public interface ApplicationExitListener {

	/**
	 * Shutdown.
	 * app退出时，关闭资源
	 * @param app the app
	 * @return true, if successful
	 */
	public boolean shutdown(Application app);
}
