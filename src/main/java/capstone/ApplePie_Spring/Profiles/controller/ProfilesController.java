package capstone.ApplePie_Spring.Profiles.controller;

import capstone.ApplePie_Spring.Profiles.dto.LessonDto;
import capstone.ApplePie_Spring.Profiles.dto.OutsourcingDto;
import capstone.ApplePie_Spring.Profiles.dto.ProfilesFindDto;
import capstone.ApplePie_Spring.Profiles.dto.ProjectDto;
import capstone.ApplePie_Spring.Profiles.service.ProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/profiles")
@RestController
public class ProfilesController {

    private final ProfilesService profilesService;

    // 카테고리별 프로필

    @GetMapping("/project")
    public ResponseEntity<Object> findProject(@RequestBody ProfilesFindDto profilesFindDto) {
        return new ResponseEntity<>(profilesService.findProject(profilesFindDto), HttpStatus.OK);
    }

    @GetMapping("/lesson")
    public ResponseEntity<Object> findLesson(@RequestBody ProfilesFindDto profilesFindDto) {
        return new ResponseEntity<>(profilesService.findLesson(profilesFindDto), HttpStatus.OK);
    }

    @GetMapping("/outsourcing")
    public ResponseEntity<Object> findOutsourcing(@RequestBody ProfilesFindDto profilesFindDto) {
        return new ResponseEntity<>(profilesService.findOutsourcing(profilesFindDto), HttpStatus.OK);
    }

    @PostMapping("/project/{pid}")
    public ResponseEntity<Object> saveProject(@PathVariable Long pid, @RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(profilesService.saveProject(pid, projectDto), HttpStatus.OK);
    }

    @PutMapping("/project/{pid}")
    public ResponseEntity<Object> updateProject(@PathVariable Long pid, @RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(profilesService.updateProject(pid, projectDto), HttpStatus.OK);
    }

    @PostMapping("/lesson/{pid}")
    public ResponseEntity<Object> saveLesson(@PathVariable Long pid, @RequestBody LessonDto lessonDto) {
        return new ResponseEntity<>(profilesService.saveLesson(pid, lessonDto), HttpStatus.OK);
    }

    @PutMapping("/lesson/{pid}")
    public ResponseEntity<Object> updateLesson(@PathVariable Long pid, @RequestBody LessonDto lessonDto) {
        return new ResponseEntity<>(profilesService.updateLesson(pid, lessonDto), HttpStatus.OK);
    }

    @PostMapping("/outsourcing/{pid}")
    public ResponseEntity<Object> saveOutsourcing(@PathVariable Long pid, @RequestBody OutsourcingDto outsourcingDto) {
        return new ResponseEntity<>(profilesService.saveOutsourcing(pid, outsourcingDto), HttpStatus.OK);
    }

    @PutMapping("/outsourcing/{pid}")
    public ResponseEntity<Object> updateOutsourcing(@PathVariable Long pid, @RequestBody OutsourcingDto outsourcingDto) {
        return new ResponseEntity<>(profilesService.updateOutsourcing(pid, outsourcingDto), HttpStatus.OK);
    }
}
