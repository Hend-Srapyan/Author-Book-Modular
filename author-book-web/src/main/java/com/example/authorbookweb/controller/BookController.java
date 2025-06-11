package com.example.authorbookweb.controller;

import com.example.authorbookcommon.entity.Author;
import com.example.authorbookcommon.entity.Book;
import com.example.authorbookweb.service.AuthorServiceWeb;
import com.example.authorbookweb.service.BookServiceWeb;
import com.example.authorbookweb.specification.BookSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceWeb bookServiceWeb;
    private final AuthorServiceWeb authorServiceWeb;

    @GetMapping
    public String booksPage(ModelMap modelMap) {
        List<Book> all = bookServiceWeb.findAll();
        modelMap.put("books", all);
        return "book/books";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam("keyword") String keyword, ModelMap modelMap) {
        List<Book> searchResult = bookServiceWeb.search(keyword);
        modelMap.addAttribute("books", searchResult);
        return "book/booksSearch";
    }

    @GetMapping("/filter")
    public String filterBook(@ModelAttribute BookSearchCriteria criteria, ModelMap modelMap) {
        modelMap.put("authors", authorServiceWeb.findAll());
        List<Book> searchResult = bookServiceWeb.filter(criteria);
        modelMap.addAttribute("books", searchResult);
        modelMap.addAttribute("title", criteria.getTitle());
        modelMap.addAttribute("price", criteria.getPrice());
        modelMap.addAttribute("qty", criteria.getQty());
        modelMap.addAttribute("author", criteria.getAuthor());

        return "book/booksSearch";
    }

    @GetMapping("/add")
    public String addBookPage(ModelMap modelMap) {
        List<Author> all = authorServiceWeb.findAll();
        modelMap.put("authors", all);
        return "book/addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookServiceWeb.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookServiceWeb.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") int id, ModelMap modelMap) {
        Book book = bookServiceWeb.findById(id);
        List<Author> all = authorServiceWeb.findAll();
        modelMap.put("authors", all);
        if (book != null) {
            modelMap.put("book", book);
            return "book/editBook";
        }
        return "redirect:/books";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book){
        bookServiceWeb.update(book);
        return "redirect:/books";
    }
}
