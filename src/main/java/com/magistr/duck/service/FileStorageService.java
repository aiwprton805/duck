package com.magistr.duck.service;

import com.magistr.duck.common.enums.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {

    Path saveOnFS(List<MultipartFile> mfList, ContentType ct, Integer id) throws IOException;

    void removeFromFS(ContentType ct, Integer id) throws IOException;
}
