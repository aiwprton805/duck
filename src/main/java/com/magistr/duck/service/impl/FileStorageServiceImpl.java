package com.magistr.duck.service.impl;

import com.magistr.duck.common.enums.ContentType;
import com.magistr.duck.common.utils.Transform;
import com.magistr.duck.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final static String TERM_IMG_PATH = "G:\\Duck Temp Storage\\term-image";
    private final static String PROPOSAL_IMG_PATH = "G:\\Duck Temp Storage\\proposal-image";
    private final static String PROPOSAL_CONTEXT_PATH = "G:\\Duck Temp Storage\\proposal-context";
    private final static String PROPOSAL_OTHER_DOCS_PATH = "G:\\Duck Temp Storage\\proposal-other-docs";

    @Override
    public Path saveOnFS(List<MultipartFile> mfList, ContentType ct, Integer id) throws IOException {
        final String prefix = toPrefix(ct);
        final Path path = Paths.get(prefix, Transform.IdToSubDirPath(id));
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        for(final MultipartFile mf: mfList){
            final Path filePath = Paths.get(path.toString(), mf.getOriginalFilename());
            mf.transferTo(filePath);
        }
        return path;
    }

    @Override
    public void removeFromFS(ContentType ct, Integer id) throws IOException {
        final String prefix = toPrefix(ct);
        final Path dirPath = Paths.get(prefix, Transform.IdToSubDirPath(id));
        final Path[] filePaths = Files.list(dirPath).toArray(Path[]::new);
        for(final Path filePath: filePaths){
            Files.delete(filePath);
        }
        Files.deleteIfExists(dirPath);
    }

    private String toPrefix(final ContentType ct){
        switch (ct){
            case TERM_IMG:{
                return TERM_IMG_PATH;
            }
            case PROPOSAL_IMG:{
                return PROPOSAL_IMG_PATH;
            }
            case PROPOSAL_CONTEXT:{
                return PROPOSAL_CONTEXT_PATH;
            }
            case PROPOSAL_OTHER_DOCS:{
                return PROPOSAL_OTHER_DOCS_PATH;
            }
        }
        return "";
    }
}
//class
