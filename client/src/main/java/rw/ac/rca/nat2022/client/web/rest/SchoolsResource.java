package rw.ac.rca.nat2022.client.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("schools")
public class SchoolsResource {

    @GetMapping("list")
    public String list(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String token = request.getSession().getAttribute("token").toString();

        headers.setBearerAuth(token);

        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ApiResponse schoolsResponse = restTemplate.exchange(formatURL("/api/schools"), HttpMethod.GET, entity, ApiResponse.class).getBody();

        List<School> schools = (List<School>) schoolsResponse.getData();

        model.addAttribute("schools", schools);
        return "schools/list";
    }

    @GetMapping("create")
    public String create(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        return "schools/create";
    }

    @GetMapping("edit")
    public String edit(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("location", location);
        return "schools/edit";
    }

    @GetMapping("delete")
    public String delete(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String token = request.getSession().getAttribute("token").toString();

        headers.setBearerAuth(token);

        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        Long id = Long.parseLong(request.getParameter("id"));

        ApiResponse schoolsResponse = restTemplate.exchange(formatURL("/api/schools/" + id), HttpMethod.DELETE, entity, ApiResponse.class).getBody();

        return "redirect:/schools/list";
    }

    @PostMapping("create")
    public String createSchool(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String token = request.getSession().getAttribute("token").toString();

        headers.setBearerAuth(token);

        Map<String, String> requestBody = new HashMap<>();

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            requestBody.put(entry.getKey(), entry.getValue()[0]);
        }

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ApiResponse response = restTemplate.exchange(formatURL("/api/schools"), HttpMethod.POST, entity, ApiResponse.class).getBody();

        if(response.getStatus() == HttpStatus.OK){
            return "redirect:/schools/list";
        }
        model.addAttribute("error", response.getMessage());
        return "schools/create";
    }

    @PostMapping("edit")
    public String editSchool(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String token = request.getSession().getAttribute("token").toString();

        headers.setBearerAuth(token);

        Map<String, String> requestBody = new HashMap<>();

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            requestBody.put(entry.getKey(), entry.getValue()[0]);
        }
        Long id = Long.parseLong(request.getParameter("id"));
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
        ApiResponse response = restTemplate.exchange(formatURL("/api/schools/"+id), HttpMethod.PUT, entity, ApiResponse.class).getBody();
        if(response.getStatus() == HttpStatus.OK){
            return "redirect:/schools/list";
        }
        model.addAttribute("error", response.getMessage());
        model.addAttribute("id", id);
        return "schools/edit";
    }
}
