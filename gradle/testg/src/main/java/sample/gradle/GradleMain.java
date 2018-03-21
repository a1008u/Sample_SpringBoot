package sample.gradle;

import net.arnx.jsonic.JSON;

import java.util.HashMap;
import java.util.Map;

public class GradleMain {
    public static void main(String[] args) {

        System.out.println("Hello Gradle!!");

        // Seasorが提供したJSONをエンコーダを利用する
        Map<String, Object> map = new HashMap<>();

        map.put("hoge", "HOGE");
        map.put("fuga", "FUGA");
        map.put("piyo", "PIYO");

        System.out.println(JSON.encode(map));

    }

    public String method() {
        return "original";
    }
}