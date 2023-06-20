package com.nicky.shoppingmall.config.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHelper {
     /**
     * 파라미터 타입 예외처리
     * @param ex
     * @return
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), message));
    }

    /**
     * Valid 어노테이션 -> not valid 예외처리
     * @param ex
     * @return
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), message));
    }

    /**
     * RequestParam 파라미터 매핑 예외처리
     * @param ex
     * @return
     */
    @ExceptionHandler({ MissingServletRequestParameterException.class })
    public ResponseEntity<Object> handleMissingServletRequestPart (MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(),ex.getParameterName().concat(" is required.")));
    }

    /**
     * api 호출 시, 필요로 하는 헤더 값이 missing 되었을 경우 예외처리
     * @param ex
     * @return
     */
    @ExceptionHandler({ MissingRequestHeaderException.class })
    public ResponseEntity<Object> handleMissingRequestHeader (MissingRequestHeaderException ex) {
        return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    /**
     * 사용자 exception 예외처리
     * @param ex
     * @return
     */
    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<Object> handleCommonException(BusinessException ex) {
        return ResponseEntity.badRequest().body(new Response(ex.getCode(),ex.getMessage()));
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDenied (AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response(ErrorInfo.ACCESS_DENIED.getCode(), ex.getMessage()));
    }
    
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex) {
        // log.info(ex.getCause().getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(ErrorInfo.SYSTEM_ERROR));
    }
}
