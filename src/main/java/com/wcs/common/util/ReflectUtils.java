package com.wcs.common.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 只对bean的set方法做反射操作
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-6-11
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
public class ReflectUtils {

    private Class c;
    private Object obj;

    public ReflectUtils(String className) {
        try {
            this.c = Class.forName(className);
            this.obj = c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public ReflectUtils(Object obj) {
        this.c = obj.getClass();
        this.obj = obj;
    }

    /**
     * 这里值针对javabean中set方法起作用，如果set方法不带参数，则不会执行
     *
     * @param fieldName
     * @param values    参数值
     */
    public void setFieldValue(String fieldName, Object[] values) {

        if (null == fieldName) {
            return;
        }

        try {
            String methodShortName = "set" + StringUtils.capitalize(fieldName);
            Class type = this.getParamType(methodShortName);

            if (null != type) {
                Method method = c.getDeclaredMethod(methodShortName, type);
                method.invoke(obj, this.getValue(values, type));
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Class getParamType(String methodShortName) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            String methodFullName = method.toString();

            if (methodFullName.contains(methodShortName)) {
                Class[] classes = method.getParameterTypes();
                if (classes.length == 1) {
                    return classes[0];
                }
                break;
            }
        }
        return null;
    }

    private Object getValue(Object[] values, Class type) {

        if (values.length != 1) {
            return null;
        }
        if (String.class == type) {
            return (String) values[0];
        } else if (int.class == type) {
            return Integer.parseInt((String) values[0]);
        } else if (long.class == type) {
            return Long.parseLong((String) values[0]);
        } else if (boolean.class == type) {
            return Boolean.parseBoolean((String) values[0]);
        } else if (float.class == type) {
            return Float.parseFloat((String) values[0]);
        } else if (short.class == type) {
            return Short.parseShort((String) values[0]);
        } else if (double.class == type) {
            return Double.parseDouble((String) values[0]);
        } else if (char.class == type) {
            char[] cs = values[0].toString().trim().toCharArray();
            return cs.length > 0 ? cs[0] : '0';
        } else if (Integer.class == type) {
            return Integer.parseInt((String) values[0]);
        } else if (Boolean.class == type) {
            return Boolean.parseBoolean((String) values[0]);
        } else if (Long.class == type) {
            return Long.parseLong((String) values[0]);
        } else if (Short.class == type) {
            return Short.parseShort((String) values[0]);
        } else if (Float.class == type) {
            return Float.parseFloat((String) values[0]);
        } else if (Double.class == type) {
            return Double.parseDouble((String) values[0]);
        }
        return null;
    }


    public Object getObj() {
        return this.obj;
    }
}
