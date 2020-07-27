package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    AuthorRepository authorRepository;


    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("authors", authorRepository.findAll());
        return "index";
    }
    @RequestMapping("/addAuthor")
    public String addAuthor(Model model) {
        model.addAttribute("author",new Author());
        return "addAuthor";
    }
    @RequestMapping("/processAuthor")
    public String proccessAuthor(@ModelAttribute Author author ){
        authorRepository.save(author);
        return "redirect:/";
    }

    @RequestMapping("/addJobs")
    public String addJob(Model model){
        model.addAttribute("job", new Job());
        model.addAttribute("authors", authorRepository.findAllByIdGreaterThanOrderByName(0));
        return "addJobs";
    }
    @PostMapping("/processJobs")
    public String processJobs(@ModelAttribute Job job, @RequestParam("authorid")long authorid){
        Author author= authorRepository.findById(authorid).get();
        job.setAuthor(author);
        jobRepository.save(job);
        return "redirect:/";
    }
    @RequestMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable("id")long id)
    {
        authorRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/updateAuthor/{id}")
    public  String updateAuthor(Model model , @PathVariable("id") long id){
        model.addAttribute("author" ,authorRepository.findById(id).get());
        return "addAuthor";
    }
    @RequestMapping("/deleteJob/{id}")
    public String deleteJob(@PathVariable("id")long id){
        jobRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/updateJob/{id}")
    public String updateJob (@PathVariable("id") long id,Model model){
        model.addAttribute("job",jobRepository.findById(id).get());
        model.addAttribute("authors", authorRepository.findAll());
        return "addJobs";
    }


    @RequestMapping("/detailsJobs/{id}")
    public String detailsMovies(Model model,@PathVariable("id")long id) {
        model.addAttribute("job",jobRepository.findById(id).get());
        model.addAttribute("author",authorRepository.findAll());
        return "detailsJobs";

    }
    @RequestMapping  ("/allJobs")
    public String allJobs (Model model){
        model.addAttribute("jobs",jobRepository.findAll());
        model.addAttribute("authors",authorRepository.findAll());
        return "alljobs";
    }

    @RequestMapping("/jobsByAuthor/{id}")
    public String jobsByAuthor(@PathVariable("id") long id,Model model){
        model.addAttribute("authors",authorRepository.findAll());
        model.addAttribute("author",authorRepository.findById(id).get());
      return "JobsByAuthor";
    }


}
