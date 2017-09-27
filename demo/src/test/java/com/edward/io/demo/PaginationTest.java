package com.edward.io.demo;

import com.edward.io.demo.dao.WpPostsDao;
import com.edward.io.demo.domain.WpPosts;
import com.edward.io.demo.domain.WpPostsExample;
import com.edward.io.demo.domain.WpPostsKey;
import com.edward.io.demo.mapper.WpPostsMapper;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by edwardcheng on 2017/9/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PaginationTest {

    @Autowired
    private WpPostsDao postsDao;

    @Autowired
    private WpPostsMapper postsMapper;

    @Test
    public void get() throws Exception {

        WpPostsKey key = new WpPostsKey();
        key.setId(1L);
        WpPosts posts = postsDao.get(key);
        System.out.println(posts.getPostDate());
    }

    @Test
    public void getList() throws Exception {

        WpPostsExample example = new WpPostsExample();
        WpPostsExample.Criteria criteria = example.createCriteria();
        criteria.andPostAuthorEqualTo(1L);

        PageHelper.startPage(17, 10);
        List<WpPosts> list = postsMapper.selectByExample(example);
        System.out.println(list.size());
    }
}
