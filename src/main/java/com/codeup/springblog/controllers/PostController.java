package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String showPosts(Model viewModel) {
        // seed posts in the DB
        // fetch all posts with postsDAO
        List<Post> posts = postsDao.findAll();
        // create posts index view
        // send list of post objects to the index view
        viewModel.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showSinglePost(@PathVariable long id) {
        return "Showing Post: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postForm() {
        return "Here is the form to create a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "Creating a post....";
    }

    @GetMapping("/posts/{id}/edit")
    public String returnEditView(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postsDao.getById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam(name="title") String title, @RequestParam String body) {
        // use the new form inputs to update the existing post in the DB
        // pull the existing post object from the DB
        Post post = postsDao.getById(id);
        post.setTitle(title);
        post.setBody(body);
        // set the and body to the request param values
        // persist the change in the DB with the postsDao
        postsDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postsDao.deleteById(id);
        return "redirect:/posts";
    }


}
