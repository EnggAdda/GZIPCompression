package org.example.gzipcompression;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SampleController {

    @GetMapping("/get")
    public ResponseEntity<Map<String,Object>> getDetails(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
  HttpHeaders  headers = new HttpHeaders();
  headers.add("abc","def");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(Map.of("data",map));

    }

    @PostMapping("/compressedData")
    public ResponseEntity<Map<String,Object>> postData(@RequestBody Map<String,Object> requestBody){
      Map<String,Object> originalRequestBody = requestBody;
        System.out.println("originalRequestBody"+originalRequestBody);
        HttpHeaders  headers = new HttpHeaders();
        headers.add("abc","def");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(Map.of("data",originalRequestBody));

    }
}
