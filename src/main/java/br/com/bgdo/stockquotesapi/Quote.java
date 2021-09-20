package br.com.bgdo.stockquotesapi;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "quote")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private Double openValue;
  private Double closeValue;
  private Date timestamp;
}
