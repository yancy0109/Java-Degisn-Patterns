package com.yancy;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;


/**
 * CoinExtendFactory
 * 通过配置文件接触耦合
 * @author yancy0109
 */
public class CoinExtendFactory {

    // 存储对应Coin对象的容器
    private static HashMap<String, Coin> coinContainer = new HashMap<>();

    // 读取Properties，对coinContainer进行初始化
    static {
        Properties properties = new Properties();
        InputStream is = null;
        try {
            // 读取对应配置文件流
            is = CoinExtendFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            properties.load(is);
            Set<Object> classNames = properties.keySet();
            for (Object className : classNames) {
                String clasPath = (String) properties.get(className);
                // 获取配置文件中对应类 Class对象
                Class<?> aClass = Class.forName(clasPath);
                Coin newInstance = (Coin) aClass.newInstance();
                // 在容器中存入对应实例
                coinContainer.put((String) className, newInstance);
            }
        } catch (IOException e) {
            throw new RuntimeException("读取配置文件异常");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("不存在对应类");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭输入流异常");
                }
            }
        }
    }

    /**
     * 静态工厂方法，根据coinName返回对应的Coin实现类
     */
    public static Coin getCoin(String coinName) {
        return coinContainer.get(coinName);
    }
}
