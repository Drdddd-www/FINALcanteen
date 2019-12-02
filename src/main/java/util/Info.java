package util;


import com.alibaba.fastjson.annotation.JSONField;


    public class Info {
        /**
         * code 请求状态码 0表示无错误, 小于0代表错误, 大于0代表非错误性提示
         */
        @JSONField(ordinal = 1)
        int code = -1;

        @JSONField(ordinal = 2)
        String msg = null;

        @JSONField(ordinal = 3)
        Object data = null;


        public Info(int code, String msg, Object data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }


        public Info(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }


        public Info(Object data) {
            code = 0;
            msg = "success";
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public Object getData() {
            return data;
        }
    }

