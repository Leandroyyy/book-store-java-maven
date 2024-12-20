package br.com.fiap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.entities.Book;
import br.com.fiap.service.BookStoreService;

@RestController
@RequestMapping("/books")
public class BookStoreController {

  @Autowired
  private BookStoreService bookStoreService;

  @GetMapping
  public ResponseEntity<List<Book>> find() {
    List<Book> books = this.bookStoreService.list();

    return ResponseEntity.status(HttpStatus.OK).body(books);
  }

}
