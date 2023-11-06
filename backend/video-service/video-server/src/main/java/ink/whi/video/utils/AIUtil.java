package ink.whi.video.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


public class AIUtil {

    public static String getCategoryByTitle(String str) throws JSONException {

        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求体
        Map<String, Object> requestBody = new HashMap<>();
        List<String> data = List.of(str);
        requestBody.put("data", data);
        // 创建请求实体
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // 发送HTTP请求
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://10.60.99.144:7675/run/predict", HttpMethod.POST, requestEntity, String.class);

        // 获取响应
        JSONObject responseJson = new JSONObject(responseEntity.getBody());
        JSONArray responseBody = responseJson.getJSONArray("data");
        String category = (String) responseBody.get(0);

        return category;
    }

    public static List<Long> getVideoRecommendResults(Long userId, Long categoryId) throws JSONException {
        List<Long> result = new ArrayList<>(10);

        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("data", List.of(userId, categoryId));
        // 创建请求实体
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // 发送HTTP请求
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7676/run/predict", HttpMethod.POST, requestEntity, String.class);

        // 获取响应
        JSONObject responseJson = new JSONObject(responseEntity.getBody());
        String data = (String) responseJson.getJSONArray("data").get(0);

        Arrays.stream(data.split(",")).map(Long::parseLong).forEach(result::add);

        return result;
    }
}