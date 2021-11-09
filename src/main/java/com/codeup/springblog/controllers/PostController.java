package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private PostRepository postsDao;
    private UserRepository userDao;
    private EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository userDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String showPosts(Model viewModel) {
        // seed posts in the DB
        // fetch all posts with postsDAO
        List<Post> posts = postsDao.findAll();
        // create posts index view
        // send list of post objects to the index view
//        viewModel.addAttribute("posts", posts);
//        return "posts/index";

        viewModel.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showSinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.getById(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String create(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String insert(@ModelAttribute Post post, @RequestParam List<String> urls) {
        List<com.codeup.dracospringblog.models.PostImage> images = new ArrayList<>();
        User author = userDao.getById(1L);


        // create list of post image objects to pass to the new post constructor
        for (String url : urls) {
            com.codeup.dracospringblog.models.PostImage postImage = new com.codeup.dracospringblog.models.PostImage(url);
            postImage.setPost(post);
            images.add(postImage);
        }

        post.setImages(images);

        post.setUser(author);

        // save a post object with images

        postsDao.save(post);

        emailService.prepareAndSend(post, "You submitted: " + post.getTitle(), post.getBody());
        // modify the post index view to display post images

        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String returnEditView(@PathVariable long id, Model viewModel) {
//        Post post = postsDao.getById(id);
        viewModel.addAttribute("post", postsDao.getById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post post) {
        // use the new form inputs to update the existing post in the DB
        // pull the existing post object from the DB
        Post editedPost = postsDao.getById(post.getId());
//        post.setTitle(title);
//        post.setBody(body);
        // set the title and body to the request param values
        editedPost.setTitle(post.getTitle());
        editedPost.setBody(post.getBody());
        // persist the change in the DB with the postsDao
//        postsDao.save(post);

        postsDao.save(editedPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postsDao.delete(postsDao.getById(id));
        return "redirect:/posts";
    }


}
