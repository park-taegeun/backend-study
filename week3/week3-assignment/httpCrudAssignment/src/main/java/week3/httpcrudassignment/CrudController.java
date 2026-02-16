package week3.httpcrudassignment;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
public class CrudController {
    private Map<Long, String> store = new HashMap<>();
    private Long idCounter = 1L;

    // Create
    @PostMapping
    public String create(@RequestBody String content) {
        store.put(idCounter, content);
        return "Created item with id: " + idCounter++;
    }

    // Read All
    @GetMapping
    public Map<Long, String> readAll() {
        return store;
    }

    // Read One
    @GetMapping("/{id}")
    public String readOne(@PathVariable Long id) {
        return store.get(id);
    }

    // UPDATE (PUT - 전체 교체)
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody String content) {
        store.put(id, content);
        return "Updated item with id: " + id;
    }

    // PATCH (부분 수정)
    @PatchMapping("/{id}")
    public String patch(@PathVariable Long id, @RequestBody String content) {
        String existing = store.get(id);
        store.put(id, existing + " (patched: " + content + ")");
        return "Patched item with id: " + id;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        store.remove(id);
        return "Deleted item with id: " + id;
    }
}
