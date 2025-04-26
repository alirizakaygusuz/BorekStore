package com.alirizakaygusuz.handler;

import java.util.List;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.starter.BorekStoreApplicationStarter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final BorekStoreApplicationStarter borekStoreApplicationStarter;

    GlobalExceptionHandler(BorekStoreApplicationStarter borekStoreApplicationStarter) {
        this.borekStoreApplicationStarter = borekStoreApplicationStarter;
    }
	
	@ExceptionHandler(value = {BaseException.class})
	public ResponseEntity<ApiError<?>> handleBaseException(BaseException exception,WebRequest webRequest) {
		
		return ResponseEntity.badRequest().body(creatApiError(exception.getMessage(), webRequest));
	}
	
	
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<ApiError<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception , WebRequest webRequest) {
		Map<String, List<String>> map= new HashMap<>();
		
		for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError)objectError).getField();
			
			if(map.containsKey(fieldName)) {
				map.put(fieldName, addValue(map.get(fieldName), objectError.getDefaultMessage()));
			}else {
				map.put(fieldName, addValue(new ArrayList<>(),objectError.getDefaultMessage()));
			}
			
		}
		
		return	ResponseEntity.badRequest().body(creatApiError(map, webRequest));
		
	}
	
	
	private List<String> addValue(List<String> list , String newValue){
		list.add(newValue);
		return list;
	}
	
	private String getHostName() {
		try {
			return	Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		return "";
	}
	
	
	public<E>  ApiError<E> creatApiError(E message,WebRequest webRequest){
		ApiError<E> apiError = new ApiError<>();
		apiError.setStatus(HttpStatus.BAD_REQUEST.value());
		
		
		ExceptionDetails<E> exception = new ExceptionDetails<>();
		exception.setPath(webRequest.getDescription(false).substring(4));
		exception.setCreateTime(new Date());
		exception.setMessage(message);
		exception.setHostName(getHostName());
		
		
		apiError.setExceptionDetails(exception);
		
		return apiError;
	}

}
