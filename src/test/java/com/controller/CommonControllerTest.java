package com.controller;

import com.entity.ConfigEntity;
import com.service.ConfigService;
import com.utils.BaiduUtil;
import com.utils.R;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommonControllerTest {
/*
    @Mock
    private ConfigService configService;

    @InjectMocks
    private CommonController commonController;

    @Test
    void testLocationWithNullAK() {
        // 模拟当AK为空时的配置服务行为
        when(configService.selectOne(any())).thenReturn(null);

        R result = commonController.location("123", "456");

    }

    @Test
    void testLocationWithValidAK() {
        // 模拟有效的AK配置
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setValue("your_ak_value");
        when(configService.selectOne(any())).thenReturn(configEntity);

        // 模拟BaiduUtil.getCityByLonLat的行为
        Map<String, String> mockMap = new HashMap<>();
        mockMap.put("city", "Beijing");
        when(BaiduUtil.getCityByLonLat("your_ak_value", "123", "456")).thenReturn(mockMap);

        R result = commonController.location("123", "456");


    }

    @Test
    void testLocationWithInvalidCoordinates() {
        // 模拟有效的AK配置
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setValue("your_ak_value");
        when(configService.selectOne(any())).thenReturn(configEntity);

        // 模拟当传入无效坐标时BaiduUtil的行为
        when(BaiduUtil.getCityByLonLat("your_ak_value", "invalid", "invalid")).thenReturn(new HashMap<>());

        R result = commonController.location("invalid", "invalid");


    }

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private ServletContext servletContext;


    @Test
    void testMatchFaceWithInvalidConfiguration() {
        // 模拟配置服务返回null
        when(configService.selectOne(any())).thenReturn(null);

        R result = yourController.matchFace("face1.jpg", "face2.jpg", request);

        assertEquals("请在配置管理中正确配置APIKey和SecretKey", result.getMessage());
    }

    @Test
    void testMatchFaceWithValidConfigurationAndFiles() throws IOException {
        // 模拟有效的配置和文件处理
        ConfigEntity apiKeyConfig = new ConfigEntity();
        apiKeyConfig.setValue("your_api_key");
        ConfigEntity secretKeyConfig = new ConfigEntity();
        secretKeyConfig.setValue("your_secret_key");

        when(configService.selectOne(argThat(argument -> "APIKey".equals(argument.getCondition().get("name")))))
                .thenReturn(apiKeyConfig);
        when(configService.selectOne(argThat(argument -> "SecretKey".equals(argument.getCondition().get("name")))))
                .thenReturn(secretKeyConfig);

        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRealPath("/upload")).thenReturn("/fakepath");

        File file1 = mock(File.class);
        File file2 = mock(File.class);
        when(file1.exists()).thenReturn(true);
        when(file2.exists()).thenReturn(true);

        // 模拟文件路径
        when(new File("/fakepath/face1.jpg")).thenReturn(file1);
        when(new File("/fakepath/face2.jpg")).thenReturn(file2);

        // 模拟文件操作
        when(FileUtil.FileToByte(file1)).thenReturn(new byte[]{1, 2, 3});
        when(FileUtil.FileToByte(file2)).thenReturn(new byte[]{4, 5, 6});

        R result = yourController.matchFace("face1.jpg", "face2.jpg", request);

        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
    }

    @Test
    void testMatchFaceWithFileNotFound() {
        // 模拟一个文件不存在的情况
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRealPath("/upload")).thenReturn("/fakepath");

        when(new File("/fakepath/face1.jpg")).thenThrow(new FileNotFoundException());

        R result = yourController.matchFace("face1.jpg", "face2.jpg", request);

        assertEquals("文件不存在", result.getMessage());
    }

    @InjectMocks
    private YourController yourController;

    @Test
    void testMatchFaceWithInvalidConfiguration() {
        // 模拟配置服务返回null
        when(configService.selectOne(any())).thenReturn(null);

        R result = yourController.matchFace("face1.jpg", "face2.jpg", request);

        assertEquals("请在配置管理中正确配置APIKey和SecretKey", result.getMessage());
    }

    @Test
    void testMatchFaceWithValidConfigurationAndFiles() throws IOException {
        // 模拟有效的配置和文件处理
        ConfigEntity apiKeyConfig = new ConfigEntity();
        apiKeyConfig.setValue("your_api_key");
        ConfigEntity secretKeyConfig = new ConfigEntity();
        secretKeyConfig.setValue("your_secret_key");

        when(configService.selectOne(argThat(argument -> "APIKey".equals(argument.getCondition().get("name")))))
                .thenReturn(apiKeyConfig);
        when(configService.selectOne(argThat(argument -> "SecretKey".equals(argument.getCondition().get("name")))))
                .thenReturn(secretKeyConfig);

        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRealPath("/upload")).thenReturn("/fakepath");

        File file1 = mock(File.class);
        File file2 = mock(File.class);
        when(file1.exists()).thenReturn(true);
        when(file2.exists()).thenReturn(true);

        // 模拟文件路径
        when(new File("/fakepath/face1.jpg")).thenReturn(file1);
        when(new File("/fakepath/face2.jpg")).thenReturn(file2);

        // 模拟文件操作
        when(FileUtil.FileToByte(file1)).thenReturn(new byte[]{1, 2, 3});
        when(FileUtil.FileToByte(file2)).thenReturn(new byte[]{4, 5, 6});

        R result = yourController.matchFace("face1.jpg", "face2.jpg", request);

        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
    }

    @Test
    void testMatchFaceWithFileNotFound() {
        // 模拟一个文件不存在的情况
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRealPath("/upload")).thenReturn("/fakepath");

        when(new File("/fakepath/face1.jpg")).thenThrow(new FileNotFoundException());

        R result = yourController.matchFace("face1.jpg", "face2.jpg", request);

        assertEquals("文件不存在", result.getMessage());
    }

    @Test
    void getOption() {
    }

    @Test
    void getFollowByOption() {
    }

    @Test
    void sh() {
    }*/
}