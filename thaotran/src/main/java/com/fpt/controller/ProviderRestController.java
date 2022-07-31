package com.fpt.controller;

import com.fpt.convert.ProviderConverter;
import com.fpt.dto.ProviderDto;
import com.fpt.entities.Provider;
import com.fpt.services.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/provider")
public class ProviderRestController {
    @Autowired
    private ProviderService providerService;
    @Autowired
    private ProviderConverter providerConverter;

    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<List<ProviderDto>> getAll() {
        return new ResponseEntity<>(providerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/paging/")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProviderDto> list(@RequestParam(value = "page", required = false) Integer pageNum,
                                  @RequestParam(value = "size", required = false) Integer pageSize,
                                  @RequestParam(value = "title", required = false) String title
    ) {

        Page<Provider> pageEntity = providerService.findByNameProviderLike(pageNum, pageSize, title);
        Page<ProviderDto> pageDTO = pageEntity.map(provider -> providerConverter.convertToDTO(provider));
        return pageDTO;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ProviderDto> addProvider(@RequestBody @Validated ProviderDto providerDto) {
        return new ResponseEntity<>(providerService.save(providerDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<ProviderDto> editProvider(@RequestBody @Validated ProviderDto providerDto) {
        return new ResponseEntity<>(providerService.save(providerDto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(providerService.delete(id) ? "Delete Success" : "Delete Failed", HttpStatus.OK);
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<ProviderDto> detailProvider(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(providerService.findById(id), HttpStatus.OK);
    }

}
