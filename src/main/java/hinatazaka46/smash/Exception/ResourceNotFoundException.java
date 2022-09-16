package hinatazaka46.smash.Exception;

public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * DBにデータがなかった時のエラー
     *
     * @param message エラーメッセージ
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
