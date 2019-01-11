package com.suye.personalblog.service;

import com.suye.personalblog.mapping.BlogMapping;
import com.suye.personalblog.model.Blog;
import com.suye.personalblog.redisrepository.BlogRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 16:21
 */
@Service
public class BlogService {

    private static int TIME_UNIT=10000;
    @Autowired
    private BlogMapping blogMapping;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogRedisRepository blogRedisRepository;

    /**
     * 返回最受欢迎的5篇博客
     * @return
     */
    public List<Blog> mostPopularBlog(){
        return blogMapping.mostPopularBlg();
    }

    /**
     * 返回博客的总数
     * @return
     */
    public int blogTotal(){
        return blogMapping.blogTotal();
    }

    /**
     * 返回最近发表的7篇博客
     * @return
     */
    public List<Blog> recentBlogs(){
        return blogMapping.recentBlogs();
    }

    /**
     * 返回最近发表的不是说说的博客
     * @return
     */
    public List<Blog> recentBlogsNotShuoShuo(){
        return blogMapping.recentBlogNotShuoShuo();
    }

    /**
     * 返回最近发表的是说说的7篇博客
     * @return
     */
    public List<Blog> recentBlogsIsShuoShuo(){
        return blogMapping.recentBlogIsShuoShuo();
    }

    /**
     * 加载更多的博客
     * @param offset
     * @return
     */
    public List<Blog> loadMoreRecentBlogs(int offset){
        return blogMapping.loadMoreBlogs(offset);
    }

    /**
     * 返回更多的已发布的博客
     * @param offset
     * @return
     */
    public List<Blog> loadMoreBlogsHadPublish(int offset){
        return blogMapping.loadMoreBlogsHadPublish(offset);
    }

    /**
     * 加载更多的不是说说得到博客
     * @param offset
     * @return
     */
    public List<Blog> loadMoreRecentNotShuoShuo(int offset){
        return blogMapping.loadMoreBlogsNotShuoShuo(offset);
    }

    /**
     * 加载更多的说说
     * @param offset
     * @return
     */
    public List<Blog> loadMoreRecentIsShuoShuo(int offset){
        return blogMapping.loadMoreBlogsIsShuoShuo(offset);
    }

    public int increaseVotenum(int blogId){
        return blogMapping.increaseVotenum(blogId);
    }

    public int increaseCainum(int blogId){
        return blogMapping.increaseCainum(blogId);
    }

    public List<Blog> findAllBlogTimeDesc(){
        return blogMapping.findAllBlogTimeDesc();
    }

    /**
     * 返回归档信息
     * @return
     */
    public Blog findArchive(){
       return blogMapping.findArchive();
    }

    /**
     * 返回友链信息
     * @return
     */
    public Blog findFirends(){
        return blogMapping.findFriends();
    }

    /**
     * 返回关于我的信息
     * @return
     */
    public Blog findAboutMe(){
        return blogMapping.findAboutMe();
    }

    public List<Blog> searchContent(String content){
        return blogMapping.searchContent(content);
    }

    public List<Blog> loadMoreSearch(String content,int offset){
        return blogMapping.loadMoreSearch(content,offset);
    }

    public int increaseReadNum(int blogId){
        return blogMapping.increaseReadNum(blogId);
    }

    public int increaseConmentNum(int blogId){
        return blogMapping.increaseConmentNum(blogId);
    }

    /**
     * 通过专栏id寻找该专栏的博客
     * @param columnId
     * @param offset
     * @return
     */
    public List<Blog> findBlogsByColumnId(int columnId,int offset){
        List<Integer> blogIdList=columnService.findBlogIdsByColumnId(columnId,offset);
        List<Blog> blogList=new ArrayList<>();
        for (int i=0;i<blogIdList.size();i++){
            blogList.add(findOneById(blogIdList.get(i)));
        }
        System.out.println("dsadas  "+blogIdList.size());
        return blogList;
    }

    /**
     * 通过标签id返回含有该标签的博客
     * @param labelId
     * @param offset
     * @return
     */
    public List<Blog> findBlogsByLabelId(int labelId,int offset){
        List<Integer> blogIdList=labelService.findBlogIdsByLabelId(labelId,offset);
        List<Blog> blogList=new ArrayList<>();
        for (int i=0;i<blogIdList.size();i++){
            blogList.add(findOneById(blogIdList.get(i)));
        }
        return blogList;
    }

    /**
     * 通过分类id返回已发布且不为说说的博客
     * @param categoryId
     * @param offset
     * @return
     */
    public List<Blog> findBlogsByCategoryId(int categoryId,int offset){
        List<Integer> blogIdList=categoryService.findBlogIdsByCategoryId(categoryId,offset);
        List<Blog> blogList=new ArrayList<>();
        for (int i=0;i<blogIdList.size();i++){
            blogList.add(findOneById(blogIdList.get(i)));
        }
        System.out.println("category"+blogList.size());
        return blogList;
    }


    /**
     * 新增一个博客
     * @param title
     * @param imgurl
     * @param describ
     * @param content
     * @param istalk
     * @param iscomment
     * @param ispublish
     * @return 该新增博客的id
     */
    public int addBlog(String title,String imgurl,String describ,String content,int istalk,int iscomment,int ispublish,String htmlcontent){
         blogMapping.addBlog(title,imgurl,describ,content,istalk,iscomment,ispublish,htmlcontent);
         return blogMapping.lastBlogID();
    }

    /**
     * 修改一篇博客
     * @param title
     * @param imgurl
     * @param describ
     * @param content
     * @param istalk
     * @param iscomment
     * @param ispublish
     * @param blogId
     * @param htmlcontent
     * @return
     */
    public int modifyBlog(String title,String imgurl,String describ,String content,int istalk,int iscomment,int ispublish,int blogId,String htmlcontent){
        return blogMapping.modifyBlog(title,imgurl,describ,content,istalk,iscomment,ispublish,blogId,htmlcontent);
    }


    @Transient
    public int deleteBlog(int id){
        deleteBlogAndCategoryByBlogId(id);
        deleteBlogAndLabelByBlogId(id);
        return blogMapping.deleteBlog(id);
    }

    public int deleteBlogAndLabelByBlogId(int blogId){
        return blogMapping.deleteBlogAndLabelByBlogId(blogId);
    }

    public int deleteBlogAndCategoryByBlogId(int blogId){
        return blogMapping.deleteBlogAndCategoryByBlogId(blogId);
    }

    /**
     * 有缓存的保存博客
     * @param blog
     * @return
     */
    public Blog saveBlog(Blog blog){
        blogMapping.saveBlog(blog);
        blog=findOneById(blog.getId());
        blogRedisRepository.addBlog(blog,TIME_UNIT);
        return blog;
    }

    /**
     * 有缓存的更新博客
     * @param blog
     * @return
     */
    public Blog updateBlog(Blog blog){
        if (blog.getId()==0 || blog==null)
            return null;
        int blogId=blog.getId();
        modifyBlog(blog.getTitle(),blog.getImgUrl(),blog.getDescrib(),blog.getContent(),blog.getIsTalk(),
                blog.getIsComment(),blog.getIsPublish(),blogId,blog.getHtmlcontent());
        blog=findOneById(blogId);
        blogRedisRepository.addBlog(blog,blogId);
        return blog;
    }

    /**
     * 根据博客的id查询博客信息
     * @param id
     * @return
     */
    public Blog findOneById(int id){
        Blog blog=blogRedisRepository.getBlog(id);
        if (blog!=null){
            return blog;
        }
        return blogMapping.findOneBlog(id);
    }


}
