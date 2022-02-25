package com.getui.push.v2.sdk.core.registry;

import com.getui.push.v2.sdk.anno.method.GtDelete;
import com.getui.push.v2.sdk.anno.method.GtGet;
import com.getui.push.v2.sdk.anno.method.GtPost;
import com.getui.push.v2.sdk.anno.method.GtPut;
import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.common.type.ParameterizedTypeImpl;
import com.getui.push.v2.sdk.common.type.TypeReference;
import com.getui.push.v2.sdk.common.util.Utils;
import com.getui.push.v2.sdk.core.factory.GtApiProxyFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by getui on 2020/6/8
 *
 * @author getui
 */
public class DefaultGtApiRegistry implements GtApiRegistry {

    private Map<String, GtApiProxyFactory.BaseParam> cache = new ConcurrentHashMap<String, GtApiProxyFactory.BaseParam>();

    @Override
    public void register(Method method) {
        get(method);
    }

    @Override
    public GtApiProxyFactory.BaseParam get(Method method) {
        GtApiProxyFactory.BaseParam param = cache.get(method.toString());
        if (param != null) {
            return param;
        }
        synchronized (cache) {
            param = cache.get(method.toString());
            if (param != null) {
                return param;
            }
            param = doAnalise(method);
            cache.put(method.toString(), param);
            return param;
        }
    }

    private GtApiProxyFactory.BaseParam doAnalise(Method method) {
        GtApiProxyFactory.BaseParam param = new GtApiProxyFactory.BaseParam();
        // 解析方法注解 -> HTTP请求方式和uri
        handleAnnotation(method.getAnnotations(), param);
        // 获取泛型类型，用于反序列化使用
        Type[] types = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments();
        // 设置返回值类型，用于反序列化
        param.setReturnType(new GtTypeHelper(method.getReturnType(), types).getType());
        return param;
    }

    /**
     * 这个类主要目的: 构造泛型类型，方便反序列化(参考jackson)
     * 不一定是最好的方式，如果有更好的方式，可以替换
     */
    class GtTypeHelper extends TypeReference<Void> {
        final Class<?> aClass;
        final Type[] types;

        public GtTypeHelper(Class<?> aClass, Type[] types) {
            this.aClass = aClass;
            this.types = types;
        }

        @Override
        public Type getType() {
            return ParameterizedTypeImpl.make(aClass, types, null);
        }
    }

    /**
     * 处理注解，解析uri和method
     *
     * @param annotations
     * @param apiParam    notnull
     */
    private GtApiProxyFactory.BaseParam handleAnnotation(Annotation[] annotations, GtApiProxyFactory.BaseParam apiParam) {
        if (apiParam == null) {
            throw new ApiException("apiParam cannot be null.");
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof GtGet) {
                apiParam.setMethod("GET");
                apiParam.setUri(((GtGet) annotation).uri());
                apiParam.setNeedToken(((GtGet) annotation).needToken());
            } else if (annotation instanceof GtPost) {
                apiParam.setMethod("POST");
                apiParam.setUri(((GtPost) annotation).uri());
                apiParam.setNeedToken(((GtPost) annotation).needToken());
            } else if (annotation instanceof GtPut) {
                apiParam.setMethod("PUT");
                apiParam.setUri(((GtPut) annotation).uri());
                apiParam.setNeedToken(((GtPut) annotation).needToken());
            } else if (annotation instanceof GtDelete) {
                apiParam.setMethod("DELETE");
                apiParam.setUri(((GtDelete) annotation).uri());
                apiParam.setNeedToken(((GtDelete) annotation).needToken());
            } else {
                throw new ApiException("请添加请求注解 GtGet/GtPost/GtPut/GtDelete");
            }
        }
        if (Utils.isEmpty(apiParam.getMethod())) {
            throw new UnsupportedOperationException();
        }
        return apiParam;
    }

}
