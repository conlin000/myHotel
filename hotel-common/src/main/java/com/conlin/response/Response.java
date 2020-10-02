package com.conlin.response;

import java.io.Serializable;

public class Response<T> implements Serializable {

    /**
     * 状态码
     */
   private int code;

    /**
     * 返回消息
     */
   private String msg;

    /**
     * 返回内容
     */
    private T data;

    /**
     * getter
     */
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    /**
     * construct
     */
    public Response() {
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 操作成功
     */
    public static Response success(){
        return builder().code(ResponseStatus.SUCCESS).build();
    }

    public static Response success(String msg){
        return  builder().code(ResponseStatus.SUCCESS).msg(msg).build();
    }

    public static <T> Response success(String msg, T data){
        return  builder().code(ResponseStatus.SUCCESS).msg(msg).data(data).build();
    }

    /**
     * 操作失误
     */
    public static Response error(){
        return builder().code(ResponseStatus.ERROR).build();
    }

    public static Response error(String msg){
        return builder().code(ResponseStatus.ERROR).msg(msg).build();
    }

    /**
     * 构建返回体
     */
    public static <T>ResponseBuilder builder(){
        return new ResponseBuilder<T>();
    }

    /**
     * 返回体构建类
     *
     */
    public static class ResponseBuilder<T>{

        /**
         * 状态码
         */
        private int code;

        /**
         * 返回消息
         */
        private String msg;

        /**
         * 返回内容
         */
        private T data;

        /**
         * 数据装填
         */
        public ResponseBuilder code(ResponseStatus status){
            this.code = status.code;
            return this;
        }

        public ResponseBuilder msg(String msg){
            this.msg = msg;
            return this;
        }

        public ResponseBuilder<T> data(T data){
            this.data = data;
            return this;
        }

        /**
         * 创建成功
         */
        public Response build(){
            return new Response<>(this.code, this.msg, this.data);
        }

    }
}
