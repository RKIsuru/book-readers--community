package com.rkisuru.book.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rkisuru.book.common.BaseEntity;
import com.rkisuru.book.feedback.Feedback;
import com.rkisuru.book.history.BookTransactionHistory;
import com.rkisuru.book.favourite.MyFavourite;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn;

    @Length(min = 5, max = 5000)
    private String synopsis;

    private String bookCover;
    private boolean archived;
    private boolean shareable;

    private String owner;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<MyFavourite> myWishlists;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> transactionHistories;

    @Transient
    public double getRate(){
        if (feedbacks == null || feedbacks.isEmpty()){
            return 0.0;
        }
        var rate = this.feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        double roundedRate = Math.round(rate*10.0)/10.0;
        return roundedRate;
    }

}
