package peaksoft.api;

import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.service.GroupService;
import peaksoft.service.InstructorService;

import java.io.IOException;


@Controller
public class CourseApi {

    private final CourseService courseService;
    private final GroupService groupService;

    private final InstructorService instructorService;

    @Autowired
    public CourseApi(CourseService courseService, GroupService groupService ,InstructorService instructorService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.instructorService=  instructorService;
    }

    @GetMapping("/getAllCourse/{companyId}")
    public String getAllCourse(@PathVariable Long companyId, Model model) {
        model.addAttribute("getAllCourse", courseService.getAllCourse());
        model.addAttribute("company_Id", companyId);
        return "/course/courses";
    }

    @GetMapping("/getAllCourseByCompanyId/{companyId}")
    public String getAllCourseByCompanyId(@PathVariable Long companyId, Model model,@ModelAttribute ("instructor") Instructor instructor, @ModelAttribute ("group") Group group) {
        model.addAttribute("getAllCourseByCompanyId", courseService.getAllCourse(companyId));
        model.addAttribute("companyId", companyId);
        model.addAttribute("instructors",instructorService.getAllInstructor());
        model.addAttribute("groups",groupService.getAllGroup());
        return "/course/getAllCoourses";
    }

    @GetMapping("/getCourseById/{id}")
    public String getCourseById(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "redirect:/getAllCourseByCompanyId";
    }

    @GetMapping("/getAllCourseByCompanyId/{companyId}/new")
    public String newCourse(@PathVariable Long companyId, Model model) {
        model.addAttribute("newCourse", new Course());
        model.addAttribute("companyId", companyId);
        return "/course/addCourse";
    }

    @PostMapping("/{companyId}/save")
    public String saveCourse(@PathVariable Long companyId, @ModelAttribute("newCourse") Course course) throws IOException{
        courseService.saveCourse(companyId, course);
        return "redirect:/getAllCourseByCompanyId/" + companyId;
    }

    @GetMapping("/updateCourse/{id}")
    public String updateCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("updateCourse", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "/course/updateCourse";
    }

    @PostMapping("/{companyId}/{id}/saveUpdateCourse")
    public String saveUpdateCourse(@PathVariable("companyId") Long companyId,
                                   @PathVariable("id") Long id, @ModelAttribute("updateCourse") Course course) throws IOException {
        courseService.updateCourse(id, course);
        return "redirect:/getAllCourse/{companyId}" + companyId;
    }

    @GetMapping("/{companyId}/{id}/deleteCourseById")
    public String deleteCourseById(@PathVariable("companyId") Long companyId, @PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/getAllCourseByCompanyId/{companyId}" + companyId;
    }

    @PostMapping("/{courseId}/assignGroup")
    private String assignGroup(@PathVariable Long courseId, @ModelAttribute("group") Group group) throws IOException {
        System.out.println(group);
        Long id = group.getId();
        groupService.assignGroup(courseId, id);
        return "redirect:/getAllGroupByCourseId/"+courseId;
    }

    @PostMapping("/{courseId}/assignInstructor")
    private String assignInstructorToCourse(@PathVariable("courseId") Long courseId,
                                            @ModelAttribute("instructor") Instructor instructor) throws IOException {
        instructorService.assignInstructorToCourse(courseId, instructor.getId());
        return "redirect:/getAllInstructorByCourseId/" + courseId;
    }
}