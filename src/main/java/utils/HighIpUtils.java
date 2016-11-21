package utils;

import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by JerryMouse on 2016/11/19.
 */
public class HighIPUtils {

    private static String SREVICE_AK = "rfstt4ws6sVtVKt0NSXHHl8dWOy9GEyl";

    private static String HIGH_IP_ADDRESS = "http://api.map.baidu.com/highacciploc/v1?qcip=TEST_IP&qterm=pc&ak=TEST_AK&coord=bd09ll&extensions=1";

    private static Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 获取高精度IP定位的结果(String)
     * @param ip
     * @return
     */
    public static String getStrHighAddress(String ip){

        String url = HIGH_IP_ADDRESS.replace("TEST_IP",ip).replace("TEST_AK",SREVICE_AK);
        String result = HttpUtils.httpGetRequest(url);
        return result;
    }

    /**
     * 获取高精度IP定位的结果(Map)
     * @param ip
     * @return
     */
    public static Map<String,Object> getMapHighAdress(String ip){
        JSONObject jsonObject = JSONObject.fromObject(getStrHighAddress(ip));
        return toMap(jsonObject);
    }

    /**
     * 利用递归，将多层的JSON转换成Map
     * @param jsonObject
     * @return
     */
    private static Map<String, Object> toMap(JSONObject jsonObject){
        Iterator it = jsonObject.keys();
        while (it.hasNext()){
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            if(value instanceof JSONObject){
                toMap((JSONObject)value);
            }else {
                data.put(key, value);
            }
        }
        return data;
    }

    public static void main(String[] args){
        String ip = "115.193.172.44";
        String result = getStrHighAddress(ip);
        JSONObject jsonObject = JSONObject.fromObject(result);
        String error = jsonObject.getJSONObject("result").getString("error");
        System.out.println(error);

        Map<String ,Object> map = toMap(jsonObject);
        System.out.println("map error ====="+map.get("error")+" admin_area_code=="+map.get("admin_area_code"));
    }

}
