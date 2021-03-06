package com.mi.bookvillage.common.domain.book;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
public class BookVO {

    private int bookSeq;
    private String bookTitle;
    private String bookPrice;
    private String bookRentalFee;
    private String bookMemo;
    private String bookRegDt;
    private String bookRentalStatus;


}
