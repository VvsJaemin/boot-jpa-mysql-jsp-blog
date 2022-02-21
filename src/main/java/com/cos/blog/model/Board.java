package com.cos.blog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity  // User 클래스가 Mysql에 테이블이 생성
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Many = board, User = One
    @JoinColumn(name = "userId")
    private User user; //DB는 오브젝트를 저장할수 없음 FK, 자바는 오브젝트 저장 할 수 있음

    @CreationTimestamp
    private Timestamp createDate;

}
