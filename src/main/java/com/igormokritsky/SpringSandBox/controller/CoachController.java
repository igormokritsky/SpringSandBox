package com.igormokritsky.SpringSandBox.controller;



import java.util.List;
import com.igormokritsky.SpringSandBox.entity.Coach;
import com.igormokritsky.SpringSandBox.exception.ResourceNotFoundException;
import com.igormokritsky.SpringSandBox.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CoachController {

    @Autowired
    private CoachRepository coachRepository;

    @GetMapping("/coaches")
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    @GetMapping("/coaches/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable(value = "id") Long coachId) throws ResourceNotFoundException {
        Coach coach = coachRepository.findById(coachId).orElseThrow(() ->
                new ResourceNotFoundException("Coach not found for this id :: "+ coachId));
        return ResponseEntity.ok().body(coach);
    }

    @PostMapping("/coaches")
    public Coach createCoach(@RequestBody Coach coach) {
        return coachRepository.save(coach);
    }

    @PutMapping("coaches/{id}")
    public Coach updateCoach(@RequestBody Coach newCoach, @PathVariable Long id) {

        return coachRepository.findById(id).map(coach -> {
            coach.setName(newCoach.getName());
            coach.setAwards(newCoach.getAwards());
            coach.setCountryId(newCoach.getCountryId());
            coach.setUserId(newCoach.getCountryId());
            return coachRepository.save(coach);
        }).orElseGet(() -> {
            newCoach.setId(id);
            return coachRepository.save(newCoach);
        });
    }

    @DeleteMapping("/coaches/{id}")
    void deleteCoach(@PathVariable Long id) {
        coachRepository.deleteById(id);
    }

}
