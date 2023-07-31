package com.api.app.online.common.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.app.online.common.vo.DefaultResponse;
import org.springframework.http.MediaType;

import io.swagger.annotations.Api;

@Api(tags = "공통")
@RestController
public class CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    /* 파일 등록 */
    @PostMapping(value = "/fileUpload", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public DefaultResponse uploadFile(HttpServletRequest req,
            @RequestParam(required = false, defaultValue = "notice") String directoryType,
            @RequestPart MultipartFile[] uploadFiles) {
        DefaultResponse defaultResponse = new DefaultResponse();
        List<Map<String, String>> fileResults = new ArrayList<>();

        try {
            for (MultipartFile uploadFile : uploadFiles) {
                /* 업로드 된 파일의 이름이 없거나, 용량이 0인 등 제대로 업로드가 되었는지 확인 */
                String originalName = uploadFile.getOriginalFilename();
                long fileSize = uploadFile.getSize();
                if (originalName == null || originalName.equals("") || fileSize <= 0) {
                    return DefaultResponse.builder().code(402).msg("You check the file (name or size)").result("F")
                            .build();
                }

                // 월별 디렉토리 날짜 생성
                LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String directoryName = directoryType + "/" + now.format(formatter) + "/";

                // 파일 저장 위치 설정
                URI uriDirectory = new URI("file:/fileData/" + directoryName);
                Path resourceDirectory = Paths.get(uriDirectory);

                // 디렉토리 확인
                if (!Files.exists(resourceDirectory)) {
                    File file = new File(String.valueOf(resourceDirectory));
                    file.mkdirs();
                }

                // 파일 경로
                URI uri = new URI(uriDirectory + originalName.replaceAll("[\\s'><]", "_"));
                String uploadPath = directoryName + originalName.replaceAll("[\\s'><]", "_");

                // 파일 저장
                Path resourcePaths = Paths.get(uri);
                uploadFile.transferTo(resourcePaths);
                Map<String, String> fileResult = Map.of("fileName", originalName, "uploadPath", uploadPath);

                fileResults.add(fileResult);
            }

            defaultResponse = DefaultResponse.builder().code(200).msg("success").result(fileResults).build();
        } catch (Exception e) {
            LOGGER.error("uploadFile = ", e.getCause());
            defaultResponse = DefaultResponse.builder().code(401).msg("You entered incorrect values").result("F")
                    .build();
        }
        return defaultResponse;
    }

}
