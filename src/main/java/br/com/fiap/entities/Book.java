package br.com.fiap.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "role", sequenceName = "SQ_TB_ROLE", allocationSize = 1)
public class Book {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role")
  private Long id;

  private String title;

  private String author;

  private String publisher;

  private LocalDate releaseDate;

  private Double price;

}
