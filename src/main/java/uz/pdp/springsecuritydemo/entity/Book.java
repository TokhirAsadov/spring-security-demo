package uz.pdp.springsecuritydemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springsecuritydemo.entity.temolate.AbsEntity;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends AbsEntity {
    private String title;
    private String author;
    private Double price;
}
