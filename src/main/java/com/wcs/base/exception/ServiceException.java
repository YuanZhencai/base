package com.wcs.base.exception;

/**
 * Created by IntelliJ IDEA.
 * User: Chris
 * Date: 11-7-8 
 * Time: 上午10:14 
 * To change this template use File | Settings | File Templates.
 */
public class ServiceException extends RuntimeException {

        private static final long serialVersionUID = 8773735629925897379L;


        public ServiceException() {
                super();
        }


        public ServiceException(String message, Throwable cause) {
                super(message, cause);
        }


        public ServiceException(String message) {
                super(message);
        }


        public ServiceException(Throwable cause) {
                super(cause);
        }
}