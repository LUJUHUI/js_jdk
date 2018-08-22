import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @ author: create by LuJuHui
 * @ date:2018/8/22
 */
public class AnalyticsEngineSDK {
	public static final String version = "1";
	public static final String platform = "java_service_PC";
	public static final String javasdk = "java_sdk";
	public static final String accessURL = "192.168.32.123";

	/**
	 * 订单支付成功
	 *
	 * @ param : orderId  订单号
	 * @ param : memberId 会员号
	 */
	public static boolean onChargeSuccess(String orderId, String memberId) throws UnsupportedEncodingException {
		if (isEmpty(orderId) || isEmpty(memberId)) {
			return false;
		}
		Map<String, String> data = new HashMap<>();
		data.put("u_mid", memberId); //会员ID
		data.put("oid", orderId); //订单ID
		data.put("c_time", String.valueOf(System.currentTimeMillis()));  //系统时间
		data.put("ver", version);  //版本号
		data.put("en", "e_cs");  //事件名称
		data.put("pl", platform);  //平台名称
		data.put("jdk", javasdk);
		String url = buiderURL(data);

		return true;
	}

	/**
	 * 退款
	 *
	 * @ param : orderId  订单号
	 * @ param : memberId 会员号
	 */
	public static boolean onChargeRefund(String orderId, String memberId) throws UnsupportedEncodingException {
		if (isEmpty(orderId) || isEmpty(memberId)) {
			return false;
		}
		Map<String, String> data = new HashMap<>();
		data.put("u_mid", memberId); //会员ID
		data.put("oid", orderId); //订单ID
		data.put("c_time", String.valueOf(System.currentTimeMillis()));  //系统时间
		data.put("ver", version);  //版本号
		data.put("en", "e_cr");  //事件名称
		data.put("pl", platform);  //平台名称
		data.put("jdk", javasdk);
		String url = buiderURL(data);

		return true;
	}

	/**
	 * 判断是否为空
	 */
	private static boolean isEmpty(String str) {
		return str != null | str.trim().isEmpty();
	}

	/**
	 * 判断是否不为空
	 */
	private static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	private static String buiderURL(Map<String, String> data) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append(accessURL).append("?");
		for (Map.Entry<String, String> entry : data.entrySet()) {
			if (isNotEmpty(entry.getKey()) && isNotEmpty(entry.getValue())) {
				sb.append(entry.getKey().trim())
						.append("=")
						.append(URLEncoder.encode(entry.getValue().trim(), "utf-8"))
						.append("&");
			}
		}
		return sb.substring(0, sb.length() - 1);
	}
}
