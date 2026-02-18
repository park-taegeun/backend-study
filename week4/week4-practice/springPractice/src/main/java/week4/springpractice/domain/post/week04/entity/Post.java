package week4.springpractice.domain.post.week04.entity;

import jakarta.persistence.*;
import lombok.*;
import week4.springpractice.global.BaseTimeEntity;

@Entity
// 이 클래스가 JPA 엔티티임을 의미.
// 즉, DB 테이블과 1:1로 매핑되는 객체라는 뜻.
// 이 어노테이션이 있어야 Hibernate가 테이블로 인식함.

@Getter
// Lombok이 모든 필드에 대한 getter 메서드를 자동 생성.
// 예: getId(), getTitle(), getContent()

@Builder
// Builder 패턴 자동 생성.
// Post.builder().title("제목").content("내용").build() 같은 방식으로 객체 생성 가능.

@NoArgsConstructor
// 파라미터 없는 기본 생성자 생성.
// JPA는 리플렉션으로 객체를 생성하기 때문에 기본 생성자가 반드시 필요함.

@AllArgsConstructor
// 모든 필드를 매개변수로 받는 생성자 생성.
// new Post(id, title, content) 형태의 생성자 자동 생성.

@Table(name = "post")
// 이 엔티티가 매핑될 테이블 이름을 "post"로 지정.
// 지정하지 않으면 클래스명을 기반으로 자동 생성됨.

public class Post extends BaseTimeEntity {
// BaseTimeEntity를 상속받음.
// createdAt, modifiedAt 같은 공통 시간 필드를 물려받음.
// 결과적으로 post 테이블에도 생성/수정 시간이 포함됨.

    @Id
    // 이 필드가 테이블의 기본키(Primary Key)임을 의미.
    // 한 행을 유일하게 식별하는 값.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본키 값을 DB가 자동으로 생성하도록 설정.
    // MySQL의 AUTO_INCREMENT 방식과 동일.
    // INSERT 시 id를 비워두면 DB가 자동으로 숫자를 증가시켜 넣어줌.
    private Long id;

    @Column(nullable = false)
    // DB 컬럼으로 매핑됨.
    // nullable = false → NOT NULL 제약조건.
    // 즉, title은 반드시 값이 있어야 함.

    private String title; // 게시글 제목

    @Column(nullable = false)
    // DB 컬럼으로 매핑됨.
    // nullable = false → NULL 값 저장 불가.
    // 내용이 없으면 DB에서 에러 발생.

    private String content; // 게시글 내용
}