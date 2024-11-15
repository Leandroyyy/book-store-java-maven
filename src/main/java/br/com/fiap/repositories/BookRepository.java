package br.com.fiap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
