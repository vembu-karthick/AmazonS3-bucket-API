package com.S3Bucket.S3BucketAPI;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @GetMapping("/search")
    public List<String> searchFiles(@RequestParam("userName") String userName,
                                    @RequestParam("searchTerm") String searchTerm) {
        System.out.println("Hi, Hello from controller");
        return fileService.searchFiles(userName, searchTerm);
    }

    // @Mapping("/download")
    // public ResponseEntity<Resource> downloadFile(@RequestParam("userName") String userName,
    //                                               @RequestParam("fileName") String fileName) {
    //     return fileService.downloadFile(userName, fileName);
    // }
}


