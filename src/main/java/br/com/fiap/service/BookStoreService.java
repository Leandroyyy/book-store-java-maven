package br.com.fiap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.entities.Book;
import br.com.fiap.repositories.BookRepository;

@Service
public class BookStoreService {

  @Autowired
  private BookRepository bookRepository;

  public List<Book> list() {
    return this.bookRepository.findAll();
  }

}
