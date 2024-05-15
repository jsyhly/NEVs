package com.controller;

import com.entity.NewsEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class NewsControllerTest {
/*

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private NewsController newsController;

    @Test
    void testSaveNewNewsItem() {
        NewsEntity news = new NewsEntity();
        news.setNewsName("Breaking News");
        news.setNewsTypes("Type1");

        // 模拟没有找到相同的新闻条目
        when(newsService.selectOne(any())).thenReturn(null);
        when(newsService.insert(any())).thenReturn(true);

        R result = newsController.save(news, request);

        assertTrue(result.isSuccess());
        assertNull(result.getErrorMessage());
    }

    @Test
    void testSaveExistingNewsItem() {
        NewsEntity news = new NewsEntity();
        news.setNewsName("Breaking News");
        news.setNewsTypes("Type1");

        // 模拟数据库中已存在相同的新闻条目
        when(newsService.selectOne(any())).thenReturn(new NewsEntity());

        R result = newsController.save(news, request);

        assertEquals(511, result.getStatusCode());
        assertEquals("表中有相同数据", result.getErrorMessage());
    }

    @Test
    void testUpdateNewsItemWithNoConflicts() {
        NewsEntity news = new NewsEntity();
        news.setId(1);
        news.setNewsName("Updated News");
        news.setNewsTypes("Type1");

        // 模拟没有找到相同的新闻条目
        when(newsService.selectOne(any())).thenReturn(null);
        when(newsService.updateById(any())).thenReturn(true);

        R result = newsController.update(news, request);

        assertTrue(result.isSuccess());
        assertNull(result.getErrorMessage());
    }

    @Test
    void testUpdateNewsItemWithConflicts() {
        NewsEntity news = new NewsEntity();
        news.setId(1);
        news.setNewsName("Updated News");
        news.setNewsTypes("Type1");

        // 模拟数据库中已存在相同的新闻条目
        when(newsService.selectOne(any())).thenReturn(new NewsEntity());

        R result = newsController.update(news, request);

        assertEquals(511, result.getStatusCode());
        assertEquals("表中有相同数据", result.getErrorMessage());
    }

    @Test
    void testUpdateNewsItemWithNullPhoto() {
        NewsEntity news = new NewsEntity();
        news.setId(1);
        news.setNewsName("Updated News");
        news.setNewsTypes("Type1");
        news.setNewsPhoto("null");

        // 模拟没有找到相同的新闻条目
        when(newsService.selectOne(any())).thenReturn(null);
        when(newsService.updateById(any())).thenReturn(true);

        R result = newsController.update(news, request);

        assertNull(news.getNewsPhoto(), "News photo should be set to null");
        assertTrue(result.isSuccess());
    }

    @Test
    void testDeleteSingleId() {
        Integer[] ids = {1};

        newsController.delete(ids);

        verify(newsService).deleteBatchIds(Arrays.asList(ids)); // 验证是否调用了deleteBatchIds方法
        assertTrue(true);  // 确认方法已执行到这一点
    }

    @Test
    void testDeleteMultipleIds() {
        Integer[] ids = {1, 2, 3};

        newsController.delete(ids);

        verify(newsService).deleteBatchIds(Arrays.asList(ids)); // 验证是否调用了deleteBatchIds方法
        assertTrue(true);  // 确认方法已执行到这一点
    }

    @Test
    void testDeleteNoIds() {
        Integer[] ids = new Integer[0]; // 传入一个空数组

        newsController.delete(ids);

        verify(newsService).deleteBatchIds(Arrays.asList(ids)); // 验证是否调用了deleteBatchIds方法
        assertTrue(true);  // 确认方法已执行到这一点
    }

    @Test
    void testListWithDefaultOrder() {
        // 准备输入数据
        Map<String, Object> params = new HashMap<>();

        // 模拟页面返回数据
        PageUtils page = new PageUtils(new ArrayList<>(), 10, 10, 1);
        when(newsService.queryPage(any())).thenReturn(page);

        R result = newsController.list(params, request);

        // 验证是否默认添加了排序字段
        assertEquals("id", params.get("orderBy"));
        assertNotNull(result.getData());
        assertTrue(result.isSuccess());
    }

    @Test
    void testListWithSpecificOrder() {
        // 准备输入数据，指定排序
        Map<String, Object> params = new HashMap<>();
        params.put("orderBy", "name");

        // 模拟页面返回数据
        PageUtils page = new PageUtils(new ArrayList<>(), 10, 10, 1);
        when(newsService.queryPage(any())).thenReturn(page);

        R result = newsController.list(params, request);

        // 验证指定的排序字段是否被正确处理
        assertEquals("name", params.get("orderBy"));
        assertNotNull(result.getData());
        assertTrue(result.isSuccess());
    }

    @Test
    void testDictionaryConversion() {
        // 准备输入数据
        Map<String, Object> params = new HashMap<>();
        params.put("orderBy", "id");

        List<NewsView> mockList = new ArrayList<>();
        NewsView mockView = new NewsView();
        mockList.add(mockView);

        // 模拟页面返回数据
        PageUtils page = new PageUtils(mockList, 10, 10, 1);
        when(newsService.queryPage(any())).thenReturn(page);

        // 模拟字典表转换
        doNothing().when(dictionaryService).dictionaryConvert(any(NewsView.class), any(HttpServletRequest.class));

        R result = newsController.list(params, request);

        // 验证字典表转换是否被调用
        verify(dictionaryService, times(mockList.size())).dictionaryConvert(any(NewsView.class), any(HttpServletRequest.class));
        assertNotNull(result.getData());
        assertTrue(result.isSuccess());
    }

    @Test
    void testAddNewNewsItem() {
        NewsEntity news = new NewsEntity();
        news.setNewsName("Breaking News");
        news.setNewsTypes("Type1");

        // 模拟没有找到相同的新闻条目
        when(newsService.selectOne(any())).thenReturn(null);
        when(newsService.insert(any())).thenReturn(true);

        R result = newsController.add(news, request);

        assertTrue(result.isSuccess());
        assertNull(result.getErrorMessage());
    }

    @Test
    void testAddExistingNewsItem() {
        NewsEntity news = new NewsEntity();
        news.setNewsName("Breaking News");
        news.setNewsTypes("Type1");

        // 模拟数据库中已存在相同的新闻条目
        when(newsService.selectOne(any())).thenReturn(new NewsEntity());

        R result = newsController.add(news, request);

        assertEquals(511, result.getStatusCode());
        assertEquals("表中有相同数据", result.getErrorMessage());
    }*/
}