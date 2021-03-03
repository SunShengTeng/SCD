package cn.sst.scd.util;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author shengtengsun
 * @Description 动态物料信息
 * @Date 2020/11/23 上午11:16
 * @Version 1.1.0
 **/
public class DynamicMaterial {
    public static void main(String[] args) {
        try {
            generateBean();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateBean() throws Exception {
        HashMap<String, Class> properties = new HashMap<>(16);
        properties.put("partNum", Class.forName("java.lang.String"));
        properties.put("2020-11-12", Class.forName("java.math.BigDecimal"));


        BeanGenerator generator = new BeanGenerator();
        Iterator<String> iterator = properties.keySet().iterator();
        while (iterator.hasNext()) {
            String propertyName = iterator.next();
            generator.addProperty(propertyName, properties.get(propertyName));
        }
        Object o = generator.create();
        System.out.println(o);
    }

    //动态生成的类
    private Object object = null;
    //存放属性名称以及属性的类型
    private BeanMap beanMap = null;

    public DynamicMaterial() {
        super();
    }

    public DynamicMaterial(Map propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    /**
     * 生成Bean
     *
     * @param propertyMap:
     * @return java.lang.Object
     * @author shengtengsun
     * @date 2020/11/23 上午11:25
     **/
    private Object generateBean(Map<String, String> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Iterator i = keySet.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            try {
                generator.addProperty(key, Class.forName(propertyMap.get(key)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return generator.create();
    }

    /**
     * 给bean属性赋值
     *
     * @param property 属性名
     * @param value    值
     */
    public void setValue(Object property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 通过属性名得到属性值
     *
     * @param property 属性名
     * @return 值
     */
    public Object getValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 得到该实体bean对象
     *
     * @return
     */
    public Object getObject() {
        return this.object;
    }
}
