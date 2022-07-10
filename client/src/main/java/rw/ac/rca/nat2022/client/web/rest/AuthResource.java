package rw.ac.rca.nat2022.client.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import rw.ac.rca.nat2022.client.dao.School;
import rw.ac.rca.nat2022.client.dao.Student;
import rw.ac.rca.nat2022.client.utils.ApiResponse;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static rw.ac.rca.nat2022.client.utils.Utility.formatURL;

@Controller
@RequestMapping("auth")
public class AuthResource {

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("register")
    public String index() {
        return "auth/register";
    }

    @PostMapping("register")
    public String storeUser(HttpServletRequest request, Model model) throws JsonProcessingException {
        try {
            RestTemplate restTemplate = new RestTemplate();

            Map<String, String> requestBody = new HashMap<>();

            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                requestBody.put(entry.getKey(), entry.getValue()[0]);
            }

            ResponseEntity<ApiResponse> res = restTemplate.postForEntity(formatURL("/api/auth/register"), requestBody, ApiResponse.class);


            return "redirect:/auth/login?registered=true";
        } catch (Exception e) {
            ApiResponse response = new ObjectMapper().readValue(e.getMessage().substring(7, e.getMessage().length() - 1), ApiResponse.class);
            model.addAttribute("error", response.getMessage());

            return "auth/register";
        }
    }

    @PostMapping("login")
    public String loginUser(HttpServletRequest request, Model model) throws JsonProcessingException {
        try {
            RestTemplate restTemplate = new RestTemplate();

            Map<String, String> requestBody = new HashMap<>();

            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                requestBody.put(entry.getKey(), entry.getValue()[0]);
            }

            ResponseEntity<ApiResponse> res = restTemplate.postForEntity(formatURL("/api/auth/signin"), requestBody, ApiResponse.class);

            request.getSession().setAttribute("token", Objects.requireNonNull(res.getBody()).getMessage());

            return "redirect:/auth/dashboard";

        } catch (Exception e) {
            ApiResponse response = new ObjectMapper().readValue(e.getMessage().substring(7, e.getMessage().length() - 1), ApiResponse.class);

            model.addAttribute("error", response.getMessage());

            return "auth/login";
        }
    }

    @GetMapping("/dashboard")
    public String profile(HttpServletRequest request, Model model) {

        if(request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String token = request.getSession().getAttribute("token").toString();

        headers.setBearerAuth(token);

        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ApiResponse schoolsResponse = restTemplate.exchange(formatURL("/api/schools"), HttpMethod.GET, entity, ApiResponse.class).getBody();
        ApiResponse studentsResponse = restTemplate.exchange(formatURL("/api/students"), HttpMethod.GET, entity, ApiResponse.class).getBody();

        List<School> schools = (List<School>) schoolsResponse.getData();
        List<Student> students = (List<Student>) studentsResponse.getData();

        model.addAttribute("totalSchools", schools.size());
        model.addAttribute("totalStudents", students.size());

        return "auth/dashboard";
    }
}
