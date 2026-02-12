package week3.swaggerpractice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // 부모 end-point
public class HttpMethodController {

    @PostMapping("/create-test") // 자식 end-point
    public String createTest() {
        return "This is create test";
    }

    @GetMapping("/read-test")
    public String readTest() {
        return "This is read test";
    }

    @PatchMapping("/update-test")
    public String updatePatchTest() {
        return "This is update patch test";
    }

    @PutMapping("/update-test")
    public String updatePutTest() {
        return "This is update put test";
    }

    @DeleteMapping("/delete-test")
    public String deleteTest() {
        return "This is delete test";
    }
}