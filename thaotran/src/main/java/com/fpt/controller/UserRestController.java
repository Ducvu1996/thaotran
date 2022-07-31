package com.fpt.controller;

import com.fpt.convert.UserConverter;
import com.fpt.dto.UserDto;
import com.fpt.entities.User;
import com.fpt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/paging/")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDto> list(@RequestParam(value = "page", required = false) Integer pageNum,
                              @RequestParam(value = "size", required = false) Integer pageSize,
                              @RequestParam(value = "title", required = false) String title
    ) {

        Page<User> pageEntity = userService.findByUserNameLike(pageNum, pageSize, title);
        Page<UserDto> pageDTO = pageEntity.map(user -> userConverter.convertToDTO(user));
        return pageDTO;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<UserDto> addCategory(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<UserDto> editCategory(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.delete(id) ? "Delete Success" : "Delete Failed", HttpStatus.OK);
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<UserDto> detailProvider(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

}
