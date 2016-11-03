package utils;

import java.util.UUID;

/**
 * Created by JerryMouse on 2016/11/3.
 */
public class UuidUtils {

    //唯一标示符    获得去除“-”的UUID  未去除前：fd61384b-05a5-4030-885e-a6bd3b60ef1a
    public static String getUuid(){
        String uid = UUID.randomUUID().toString();
        return uid.replace("-","");
    }

}
