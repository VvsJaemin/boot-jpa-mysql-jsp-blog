package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    private int count; // 조회수

    @ManyToOne // Many = board, User = One
    @JoinColumn(name = "userId")
    private User user; //DB는 오브젝트를 저장할수 없음 FK, 자바는 오브젝트 저장 할 수 있음

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다.
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replys;

    @CreationTimestamp
    private Timestamp createDate;

}
