package peaksoft.api;

import peaksoft.model.Instructor;
import peaksoft.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class InstructorApi {

    private final InstructorService instructorService;

    @Autowired
    public InstructorApi(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/getAllInstructor/{courseId}")
    public String getAllCourse(@PathVariable Long courseId, Model model) {
        model.addAttribute("getAllInstructor", instructorService.getAllInstructor());
        model.addAttribute("courseId", courseId);
        return "/instructro/instructors";
    }

    @GetMapping("/getAllInstructorByCourseId/{courseId}")
    public String getAllInstructorByCourseId(@PathVariable Long courseId, Model model) {
        model.addAttribute("getAllInstructorByCourseId", instructorService.getAllInstructor(courseId));
        model.addAttribute("courseId", courseId);
        return "/instructro/getAllInstructor";
    }

    @GetMapping("/getInstructorById/{id}")
    public String getInstructorById(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", instructorService.getInstructorById(id));
        return "redirect:/getAllInstructorByCourseId";
    }

    @GetMapping("/getAllInstructorByCourseId/{courseId}/new")
    public String newInstructor(@PathVariable Long courseId, Model model) {
        model.addAttribute("newInstructor", new Instructor());
        model.addAttribute("courseId", courseId);
        return "/instructro/addInstructor";
    }

    @PostMapping("/{courseId}/saveInstructor")
    public String saveInstructor(@PathVariable Long courseId, @ModelAttribute("newInstructor") Instructor instructor) throws IOException {
        instructorService.saveInstructor(courseId, instructor);
        return "redirect:/getAllInstructorByCourseId/" + courseId;
    }

    @GetMapping("/updateInstructor/{id}")
    public String updateInstructor(@PathVariable Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("updateInstructor", instructor);
        model.addAttribute("courseId", instructor.getCourse().getId());
        return "/instructro/updateInstructor";
    }

    @PostMapping("/{courseId}/{id}/saveUpdateInstructor")
    public String saveUpdateInstructor(@PathVariable Long courseId,
                                       @PathVariable Long id,
                                       @ModelAttribute("updateInstructor") Instructor instructor) throws IOException{
        instructorService.updateInstructor(id, instructor);
        return "redirect:/getAllInstructorByCourseId/" + courseId;
    }

    @GetMapping("/{courseId}/{id}/deleteInstructorById")
    public String deleteInstructorById(@PathVariable Long courseId, @PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return "redirect:/getAllInstructorByCourseId/" + courseId;
    }

}