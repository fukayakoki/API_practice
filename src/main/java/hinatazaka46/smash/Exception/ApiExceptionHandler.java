package hinatazaka46.smash.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * エラーハンドリングをするクラス
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    /**
     * ResourceNotFoundExceptionをハンドリングし、404エラーを返す
     *
     * @param exception 発生した例外
     * @param request   リクエストデータ
     * @return レスポンス(ボディにResource Not Found)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
        ResourceNotFoundException exception, WebRequest request) {
        
        return super.handleExceptionInternal(exception, "Resource Not Found", null,
            HttpStatus.NOT_FOUND, request);
    }
    
}
