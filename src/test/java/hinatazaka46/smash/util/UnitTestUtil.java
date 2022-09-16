package hinatazaka46.smash.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UnitTestUtil {
    
    /**
     * エンティティをjson形式のStringの文字列に変換
     */
    public static String entityToJsonText(Object entity) throws Exception {
        return new ObjectMapper().writeValueAsString(entity);
    }
}
