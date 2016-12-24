package generation.service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 *Class created the service
 * @author Bogdan
 *
 */
public class ServiceFactory {
	/**
	 * @param realService
	 * @return
	 */
	public static Object createService(Object realService) {
		return Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(),realService.getClass().getInterfaces(),
				new ServiceInvocationHandler(realService));
	}

	/**Class expands inteface InvocationHandler
	 * @author Bogdan
	 *
	 */
	private static class ServiceInvocationHandler implements InvocationHandler {
		private final Object realService;

		/**
		 * @param realService
		 * 
		 */
		public ServiceInvocationHandler(Object realService) {
			super();
			this.realService = realService;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			return method.invoke(realService, args);
		}
	}
}
