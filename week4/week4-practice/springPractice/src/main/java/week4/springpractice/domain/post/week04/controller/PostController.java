package week4.springpractice.domain.post.week04.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import week4.springpractice.domain.post.week05.dto.request.CreatePostRequest;
import week4.springpractice.domain.post.week05.dto.request.UpdatePostRequest;

@RestController
// 이 클래스가 REST API 컨트롤러임을 의미.
// 반환값을 View가 아니라 HTTP Response Body(JSON, String 등)로 바로 내려보냄.
// 내부적으로 @Controller + @ResponseBody가 합쳐진 것.

@RequiredArgsConstructor
// final 필드에 대해 생성자를 자동 생성.
// 보통 Service를 주입받을 때 사용됨.
// 현재는 필드가 없어서 의미는 없지만,
// Service 추가되면 자동으로 생성자 주입이 가능.

@RequestMapping("/api/v1")
// 이 컨트롤러의 공통 URL prefix.
// 모든 메서드는 "/api/v1"이 기본 경로가 됨.
// 예: @GetMapping("/posts") → 실제 경로는 "/api/v1/posts"

@Tag(name = "Post", description = "게시글 관련 API")
// Swagger 문서 그룹 이름과 설명 지정.
// Swagger UI에서 "Post" 그룹으로 묶여서 표시됨.

public class PostController {

    @Operation(
            summary = "게시글 생성",
            description = "게시판 페이지에서 게시글 작성 후 생성 버튼을 눌렀을 때 요청되는 API"
    )
    // Swagger에 표시될 API 설명.
    // 문서 자동화를 위한 메타데이터.

    @PostMapping("/posts")
    // HTTP POST 요청을 처리.
    // URL: /api/v1/posts
    // 의미: 리소스 생성(Create)

    public String createPost(
            @Parameter(description = "게시글 작성 내용")
            // Swagger에서 이 파라미터 설명을 표시.

            @RequestBody CreatePostRequest createPostRequest
            // HTTP Body에 담겨오는 JSON을
            // CreatePostRequest 객체로 변환해줌.
            // 즉, 클라이언트가 보낸 JSON → 자바 객체 매핑.
    ) {
        return "게시글 생성";
        // 지금은 실제 DB 저장이 아니라 문자열만 반환.
        // 나중에는 Service 호출해서 저장 로직 수행.
    }


    @Operation(
            summary = "게시글 전체 조회",
            description = "게시판 페이지로 이동될 때 요청되는 API"
    )
    @GetMapping("/posts")
    // HTTP GET 요청 처리.
    // URL: /api/v1/posts
    // 의미: 모든 게시글 조회(Read - List)

    public String getAllPosts() {
        return "게시글 전체 조회";
        // 실제로는 List<Post> 또는 DTO 리스트 반환해야 함.
    }


    @Operation(
            summary = "게시글 단일 조회",
            description = "게시판 페이지에서 특정 게시글에 접근할 때 요청되는 API"
    )
    @GetMapping("/posts/{id}")
    // URL에 포함된 id 값을 받아 처리.
    // 예: /api/v1/posts/3
    public String getPostById(
            @Parameter(description = "특정 게시글 ID")
            @PathVariable Long id
            // URL 경로 변수 {id}를 Long 타입으로 받아옴.
            // 즉, 3번 게시글이면 id = 3
    ) {
        return id + "번 게시글 조회";
    }


    @Operation(
            summary = "게시글 수정",
            description = "게시판 페이지에서 게시글 수정 후 수정 완료 버튼을 눌렀을 때 요청되는 API"
    )
    @PutMapping("/posts/{id}")
    // HTTP PUT 요청 처리.
    // URL: /api/v1/posts/{id}
    // 의미: 특정 리소스 전체 수정(Update)

    public String updatePost(
            @Parameter(description = "게시글 수정 내용")
            @RequestBody UpdatePostRequest updatePostRequest,
            // HTTP Body(JSON)에 들어있는 데이터를 UpdatePostRequest 객체로 변환해줘

            @Parameter(description = "특정 게시글 ID")
            @PathVariable Long id
            // URL에 있는 {id} 값을 꺼내서 id 변수에 넣어줘
    ) {
        return id + "번 게시글 수정";
    }


    @Operation(
            summary = "게시글 삭제",
            description = "게시판 페이지에서 게시글 삭제 버튼을 눌렀을 때 요청되는 API"
    )
    @DeleteMapping("/posts/{id}")
    // HTTP DELETE 요청 처리.
    // URL: /api/v1/posts/{id}
    // 의미: 특정 리소스 삭제(Delete)

    public String deletePost(
            @Parameter(description = "특정 게시글 ID")
            @PathVariable Long id
            // 삭제할 게시글 ID를 URL에서 받음.
    ) {
        return id + "번 게시글 삭제";
    }
}