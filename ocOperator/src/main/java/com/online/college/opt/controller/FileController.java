package com.online.college.opt.controller;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.file.entity.FileParent;
import com.online.college.core.file.entity.FileReal;
import com.online.college.core.file.service.FileParentService;
import com.online.college.core.file.service.FileRealService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @ClassName FileController
 * @Description TODO
 * @Author like
 * @Data 2019/4/19 23:57
 * @Version 1.0
 **/
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileRealService fileRealService;

    @Autowired
    private FileParentService fileParentService;


    @RequestMapping("/list")
    public ModelAndView list(FileParent fileParent, TailPage<FileParent> page) {
        ModelAndView mv = new ModelAndView("cms/file/filePageList");
        mv.addObject("curNav", "fileManage");

        // 1. 查询目录
        page = fileParentService.findAllForPage(fileParent, page);
        mv.addObject("queryEntity", fileParent);
        mv.addObject("page", page);


        // 2. 查询文件
        FileReal fileReal = new FileReal();
        TailPage<FileReal> fileRealTailPage = new TailPage<>();
        fileRealTailPage = fileRealService.selectForPage(fileReal, fileRealTailPage);
        mv.addObject("fileReal", fileParent);
        mv.addObject("filePage", fileRealTailPage);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addNotice() {
        ModelAndView mv = new ModelAndView("cms/file/add");
        mv.addObject("curNav", "fileManage");
        return mv;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveFileParent(FileParent entity) {
        fileParentService.save(entity);
        return new JsonView(0).toString();
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(FileParent fileParent) {
        fileParentService.delDirectory(fileParent.getId());
        return new JsonView(0).toString();
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,FileParent fileParent) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
           return new JsonView(200,"Please select a file to upload!").toString();
        }

        FileParent fileParent1 = fileParentService.findById(fileParent.getId());
        String directoryName = fileParent1.getDirectoryName();

        Path path = null;
        try {
            byte[] bytes = file.getBytes();
             path = Paths.get("D:/data/upload/" + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        FileReal fileReal = new FileReal();
        fileReal.setFileName(file.getOriginalFilename());
        fileReal.setFileParentId(fileParent.getId());
        fileReal.setFilePath(path.toString());
        fileReal.setFileSize(String.valueOf(file.getSize()));
        fileRealService.save(fileReal);

       return new JsonView(0).toString();
    }

}
