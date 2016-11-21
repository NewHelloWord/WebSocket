package utils;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JerryMouse on 2016/10/23.
 */
public class IpUtils {

    private static final String FIND_IP_ADDRESS = "http://ip.taobao.com/service/getIpInfo.php";
    // 从http://whois.pconline.com.cn取得IP所在的省市区信息

    //获取真实的ip
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    public static String getAddress(String ip) throws URISyntaxException {
        String result = "";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ip", ip);
        String address = HttpUtils.httpGetRequest(FIND_IP_ADDRESS, map);
        if (address != null || !address.equals("")) {
            System.out.println(address);
            String[] temp = address.split(",");
            if (temp.length < 3) {
                return result;//无效IP，局域网测试
            }
            String country = "";
            String area = "";
            String region = "";
            String city = "";
            String county = "";
            String isp = "";
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                    case 1:
                        country =(temp[i].split(":"))[2].replaceAll("\"", "");
                        country =decodeUnicode(country);//国家
                        break;
                    case 3:
                        area = (temp[i].split(":"))[1].replaceAll("\"", "");
                        area =decodeUnicode(area);//地区
                        break;
                    case 5:
                        region =(temp[i].split(":"))[1].replaceAll("\"", "");
                        region =decodeUnicode(region);//省份
                        break;
                    case 7:
                        city = (temp[i].split(":"))[1].replaceAll("\"", "");
                        city =decodeUnicode(city);//市区
                        break;
                    case 9:
                        county =(temp[i].split(":"))[1].replaceAll("\"", "");
                        county =decodeUnicode(county);//地区
                        break;
                    case 11:
                        isp =(temp[i].split(":"))[1].replaceAll("\"", "");
                        isp =decodeUnicode(isp);//ISP公司
                        break;
                }
            }
            //中国=华东=浙江省=杭州市==电信
            result = country + "-" + area + "-" + region + "-" + city + "-" + isp;
        }
        return result;
    }


}
