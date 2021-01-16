package za.co.ashtech.booklog.controller;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.service.BookLogService;
import za.co.ashtech.booklog.util.BookLogApiException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-01-13T22:22:42.952Z[GMT]")
@RestController
public class BooklogApiController implements BooklogApi {

    private static final Logger log = LoggerFactory.getLogger(BooklogApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private BookLogService service;

    @org.springframework.beans.factory.annotation.Autowired
    public BooklogApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addInventory(@Parameter(in = ParameterIn.DEFAULT, description = "Create book in catalogue", schema=@Schema()) @Valid @RequestBody Book body) throws BookLogApiException{
        
        service.createBook(body);
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
