package week2.helloworld.assign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Week2_assignmentController {
    private final Week2_assignmentRepository week2_assignmentRepository;

    public Week2_assignmentController(Week2_assignmentRepository week2_assignmentRepository) {
        this.week2_assignmentRepository = week2_assignmentRepository;
    }

    @GetMapping("/week2_assign")
    public List<Week2_assignment> getWeek2_assign() {
        return week2_assignmentRepository.findAll();
    }
}
