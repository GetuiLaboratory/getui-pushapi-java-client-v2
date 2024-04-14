package com.getui.push.v2.sdk.dto;

/**
 * create by getui on 2020/6/3
 *
 * @author getui
 */
public interface CommonEnum {

    enum ClickTypeEnum {
        TYPE_INTENT("intent", "打开应用内特定页面"),
        TYPE_URL("url", "打开网页地址"),
        TYPE_PAYLOAD("payload", "自定义消息内容启动应用"),
        TYPE_PAYLOAD_CUSTOM("payload_custom", "自定义消息内容不启动应用"),
        TYPE_STARTAPP("startapp", "打开应用首页"),
        TYPE_NONE("none", "纯通知，无后续动作"),
        ;
        public final String type;
        public final String msg;

        ClickTypeEnum(String type, String msg) {
            this.type = type;
            this.msg = msg;
        }
    }

    enum HarmonyClickTypeEnum {
        TYPE_WANT("want", "打开应用内特定页面"),
        TYPE_STARTAPP("startapp", "打开应用首页")
        ;
        public final String type;
        public final String msg;

        HarmonyClickTypeEnum(String type, String msg) {
            this.type = type;
            this.msg = msg;
        }
    }

    /**
     * 通知渠道重要性
     */
    enum ChannelLevelEnum implements IEnum<Integer> {
        LEVEL_ZERO(0, "<8.0, 无声音，无振动，不浮动; >8.0 无声音，无振动，不显示;"),
        LEVEL_ONE(1, "<8.0, 无声音，无振动，不浮动; >8.0 无声音，无振动，锁屏不显示，通知栏中被折叠显示，导航栏无logo;"),
        LEVEL_TWO(2, "<8.0, 无声音，无振动，不浮动; >8.0 无声音，无振动，锁屏和通知栏中都显示，通知不唤醒屏幕;"),
        LEVEL_THREE(3, "<8.0, 有声音，无振动，不浮动; >8.0 有声音，无振动，锁屏和通知栏中都显示，通知唤醒屏幕;"),
        LEVEL_FOUR(4, "<8.0, 有声音，有振动，有浮动; >8.0 有声音，有振动，亮屏下通知悬浮展示，锁屏通知以默认形式展示且唤醒屏幕;"),
        ;

        public final int level;
        public final String msg;

        @Override
        public boolean is(Integer integer) {
            return get().equals(integer);
        }

        @Override
        public Integer get() {
            return level;
        }

        ChannelLevelEnum(int level, String msg) {
            this.level = level;
            this.msg = msg;
        }
    }

    /**
     * 推送消息使用网络类型
     */
    enum NetworkTypeEnum implements IEnum<Integer> {
        TYPE_ALL(0, "推送时不限制联网方式"),
        TYPE_WIFI(1, "仅wifi推送"),
        ;
        public final int type;
        public final String msg;

        @Override
        public boolean is(Integer integer) {
            return get().equals(integer);
        }

        @Override
        public Integer get() {
            return type;
        }

        NetworkTypeEnum(int type, String msg) {
            this.type = type;
            this.msg = msg;
        }
    }

    enum MethodEnum {
        METHOD_GET("GET"),
        METHOD_POST("POST"),
        METHOD_PUT("PUT"),
        METHOD_DELETE("DELETE"),
        METHOD_PATCH("PATCH"),
        METHOD_TRACE("TRACE"),
        METHOD_HEAD("HEAD"),
        METHOD_OPTIONS("OPTIONS"),
        ;
        public final String method;

        MethodEnum(String method) {
            this.method = method;
        }

        public boolean is(String method) {
            return this.method.equalsIgnoreCase(method);
        }
    }

    /**
     * 条件关联方式
     */
    enum OptTypeEnum implements IEnum<String> {
        TYPE_AND("and"),
        TYPE_OR("or"),
        TYPE_NOT("not"),
        ;
        public final String type;

        @Override
        public boolean is(String s) {
            return get().equalsIgnoreCase(s);
        }

        @Override
        public String get() {
            return type;
        }

        OptTypeEnum(String type) {
            this.type = type;
        }
    }

    interface IEnum<T> {
        /**
         * 判断当前值和枚举值是否相同
         *
         * @param t
         * @return
         */
        boolean is(T t);

        /**
         * 获取枚举的值
         *
         * @return
         */
        T get();
    }

}
