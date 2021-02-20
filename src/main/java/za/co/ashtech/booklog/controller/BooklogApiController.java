package za.co.ashtech.booklog.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.model.Books;
import za.co.ashtech.booklog.model.Editing;
import za.co.ashtech.booklog.service.BookLogService;
import za.co.ashtech.booklog.util.BookLogApiException;
import za.co.ashtech.booklog.util.BookLogUtil;
import za.co.ashtech.booklog.util.CONSTANTS;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-01T20:11:16.872Z[GMT]")
@RestController
public class BooklogApiController implements BooklogApi {

    private static final Logger log = LoggerFactory.getLogger(BooklogApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private BookLogService bookLogService;
    
    @org.springframework.beans.factory.annotation.Autowired
    public BooklogApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createBook(@Parameter(in = ParameterIn.DEFAULT, description = "Create book in catalogue", schema=@Schema()) @Valid @RequestBody Book body) throws BookLogApiException{
    	/* Validate value format */
    	BookLogUtil.validateJsonField(CONSTANTS.ISBN_PATTERN, body.getISBN(), "ISBN");
        bookLogService.createBook(body);
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> editBook(@Parameter(in = ParameterIn.PATH, description = "Unique book identifier", required=true, schema=@Schema()) @PathVariable("isbn") String isbn,@Parameter(in = ParameterIn.DEFAULT, description = "Update book in catalogue", schema=@Schema()) @Valid @RequestBody Editing body) throws BookLogApiException{
    	/* Validate value format */
    	BookLogUtil.validateJsonField(CONSTANTS.ISBN_PATTERN, isbn, "ISBN");
    	
    	bookLogService.updateBook(body, isbn);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    public ResponseEntity<Void> deleteBook(@Parameter(in = ParameterIn.PATH, description = "Unique book identifier", required=true, schema=@Schema()) @PathVariable("isbn") String isbn) throws BookLogApiException {
    	/* Validate value format */
    	BookLogUtil.validateJsonField(CONSTANTS.ISBN_PATTERN, isbn, "ISBN");

    	bookLogService.deleteBook(isbn);
    	
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

	@Override
	public ResponseEntity<Book> getBook(String isbn) throws BookLogApiException {
    	/* Validate value format */
    	BookLogUtil.validateJsonField(CONSTANTS.ISBN_PATTERN, isbn, "ISBN");
    	
    	Book book = bookLogService.getBook(isbn);
    	
		return new ResponseEntity<Book>(book,HttpStatus.OK);
	}
	
	
    public ResponseEntity<Books> getBooks(@Parameter(in = ParameterIn.PATH, description = "Unique user identifier", required=true, schema=@Schema()) @PathVariable("username") String username) throws BookLogApiException {
    	Books books=null;
//    	BookLogUtil.validateJsonField(CONSTANTS.USERNAME, username, "Username");
    	
    	books=bookLogService.getBooks(username);

        return new ResponseEntity<Books>(books,HttpStatus.OK);
    }


}
