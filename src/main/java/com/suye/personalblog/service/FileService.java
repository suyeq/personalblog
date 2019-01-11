package com.suye.personalblog.service;

import com.suye.personalblog.mapping.FileMapping;
import com.suye.personalblog.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-04
 * Time: 12:33
 */
@Service
public class FileService {

    @Autowired
    private FileMapping fileMapping;

    /**
     * 根据索引返回指定位置数据
     * @param offset
     * @return
     */
    public List<File> findFileByOffset(int offset){
        return fileMapping.findFileByOffset(offset);
    }

    /**
     * 根据id删除文件数据
     * @param id
     * @return
     */
    public int deleteFileById(int id){
        return fileMapping.deleteFileById(id);
    }

    /**
     * 新增文件信息
     * @param name
     * @param url
     * @return
     */
    public int addFile(String name,String url){
        return fileMapping.addFile(name,url);
    }

    /**
     * 修改地址信息
     * @param url
     * @return
     */
    public int updateFileUrl(int id,String url){
        return fileMapping.updateFileUrl(id,url);
    }

    /**
     * 返回自增后的主键id
     * @return
     */
    public int lastFile(){
        return fileMapping.lastFile();
    }

    /**
     * 返回文件总的数量
     * @return
     */
    public int fileTotal(){
        return fileMapping.fileTotal();
    }

    /**
     * 根据名字查询id
     * @param fileName
     * @return
     */
    public int findFileIdByFileName(String fileName){
        return fileMapping.findFileIdByFileName(fileName);
    }

    /**
     * 根据id查找file
     * @param id
     * @return
     */
    public File findOneById(int id){
        return fileMapping.findOneById(id);
    }
}
