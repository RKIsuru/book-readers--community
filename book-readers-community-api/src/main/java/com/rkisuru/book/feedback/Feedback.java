package com.rkisuru.book.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rkisuru.book.book.Book;
import com.rkisuru.book.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends BaseEntity {

    @Column
    private Double note;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    private String own;
}
