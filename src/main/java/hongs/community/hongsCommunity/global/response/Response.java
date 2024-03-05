package hongs.community.hongsCommunity.global.response;

import hongs.community.hongsCommunity.global.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response {

    private String date = TimeUtil.nowDate();
    private Integer httpStatus;
    private String httpReason;
    private Object message;

    public Response(Object message, StatusCode statusCode) {
        this.message = message;
        this.httpStatus = statusCode.value;
        this.httpReason = statusCode.reason;
    }

    public static Response ok(Object message) {
        return new Response(message, StatusCode.OK);
    }

    public static Response created(Object message){
        return new Response(message, StatusCode.CREATED);
    }

    public static Response noContent(Object message){
        return new Response(message, StatusCode.NO_CONTENT);
    }

    public static Response badRequest(Object message){
        return new Response(message, StatusCode.BAD_REQUEST);
    }

    public static Response forBidden(Object message){
        return new Response(message, StatusCode.FORBIDDEN);
    }

    public static Response notFound(Object message){
        return new Response(message, StatusCode.NOT_FOUND);
    }
}
