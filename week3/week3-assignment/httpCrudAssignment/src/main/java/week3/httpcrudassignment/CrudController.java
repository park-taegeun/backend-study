package week3.httpcrudassignment;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
/*
* 해당 클래스는 API 컨트롤러
* return 값이 그대로 HTTP 응답으로 나감
* */
@RequestMapping("/api/items") // 이 컨트롤러의 기본 주소
public class CrudController {
    private Map<Long, String> store = new HashMap<>();
    /*
    * Map은 키로 값을 찾아오는 자료구조
    * - key: Long 타입
    * - value: String 타입
    * */
    private Long idCounter = 1L;
    /*
    * 새로운 데이터를 만들 때, id를 자동으로 만들어주는 번호표
    * 처음 값이 1L인 이유 -> 첫 번째 데이터의 id를 1로 하기 위해서
    * */

    // Create
    @PostMapping // POST /api/items 요청 처리
    public String create(@RequestBody String content) {
        /*
        * @RequestBody String content
        * - 클라이언트가 Body에 보낸 데이터를 받는 부분
        * - 즉, 사용자가 보낸 데이터 -> content 변수에 저장
        * */
        store.put(idCounter, content);
        /*
        * 키-값 저장
        * idCounter에 의해 id가 그리고 사용자가 보낸 데이터는 content 변수로 저장
        * */
        return "Created item with id: " + idCounter++;
    }

    // Read All
    @GetMapping // GET /api/items 요청 처리
    public Map<Long, String> readAll() {
        /*
        * 반환 타입: Map<Long, String>
        * 즉, 키=id, 값=value을 그대로 내보내겠다
        * */
        return store;
        /*
        * store을 반환하는데 @RestController을 사용하고 있으니 스프링이 알아서 JSON으로 변환을 해
        * -> JSON 변환(직렬화)
        * */
    }

    // Read One
    @GetMapping("/{id}") // GET /api/items/{id} 요청 처리
    public String readOne(@PathVariable Long id) {
        /*
        * @PathVariable Long id
        * - URL에 있는 변수 {id}를 자바 변수 id에 담아라
        * */
        return store.get(id); // Map에서 id(키)에 해당하는 값을 가져와 반환함 == 해당 id 데이터 조회
    }

    // UPDATE (PUT - 전체 교체)
    @PutMapping("/{id}") // PUT /api/items/{id} 요청 처리
    public String update(@PathVariable Long id, @RequestBody String content) {
        /*
         * @PathVariable Long id: URL에 저장되어 있는 변수 {id}를 자바 변수 id로 가져옴
         * @RequestBody String content: 요청 바디에 들어온 데이터를 자바 변수 content로 가져옴
         * */
        store.put(id, content);
        /*
        * 이미 존재하는 값 -> 덮어쓰기
        * 존재하지 않는 값 -> 새로쓰기
        * */
        return "Updated item with id: " + id;
    }

    // PATCH (부분 수정)
    @PatchMapping("/{id}")// PATCH /api/items/{id} 요청 처리
    public String patch(@PathVariable Long id, @RequestBody String content) {
        /*
        * @PathVariable Long id: URL에 저장되어 있는 변수 {id}를 자바 변수 id로 가져옴
        * @RequestBody String content: 요청 바디에 들어온 데이터를 자바 변수 content로 가져옴
        * */
        String existing = store.get(id);// Map에서 키에 해당하는 값을 가져와서 existing 변수에 저장
        store.put(id, existing + " (patched: " + content + ")");
        /*
        * 기존 값에 덧붙이는 방식으로 수정
        * "공부하기 (patched: 운동 추가)" -> 기존 데이터를 유지하면서 일부만 변경함*/
        return "Patched item with id: " + id;
    }

    // DELETE
    @DeleteMapping("/{id}") // DELETE /api/items/{id} 요청 처리
    public String delete(@PathVariable Long id) {
        /*
        * @PathVariable Long id: URL에 저장되어 있는 변수 {id}를 자바 변수 id로 가져옴
        * */
        store.remove(id); // Map에서 키에 해당하는 값을 삭제함 === 리소스 삭제
        return "Deleted item with id: " + id;
    }
}
