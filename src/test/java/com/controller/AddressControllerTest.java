package com.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressControllerTest {
/*
    @Mock
    private AddressService addressService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AddressController addressController;

    @Test
    void testSaveWithUserRoleAndNewAddress() {
        // 设置模拟的会话和请求
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("用户");
        when(session.getAttribute("userId")).thenReturn("1");

        // 创建一个新地址对象
        AddressEntity address = new AddressEntity();
        address.setAddressName("Sample Name");
        address.setAddressPhone("1234567890");
        address.setAddressDizhi("Sample Address");
        address.setIsdefaultTypes(1);

        // 模拟数据库操作
        when(addressService.selectOne(any())).thenReturn(null);
        when(addressService.insert(any())).thenReturn(true);

        R result = addressController.save(address, request);

        assertTrue(result.isSuccess());
        assertNull(result.getErrorMessage());
    }

    @Test
    void testSaveWithUserRoleAndExistingAddress() {
        // 设置模拟的会话和请求
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("用户");
        when(session.getAttribute("userId")).thenReturn("1");

        AddressEntity address = new AddressEntity();
        address.setAddressName("Sample Name");
        address.setAddressPhone("1234567890");
        address.setAddressDizhi("Sample Address");
        address.setIsdefaultTypes(1);

        // 模拟数据库中已存在的地址
        when(addressService.selectOne(any())).thenReturn(new AddressEntity());

        R result = addressController.save(address, request);

        assertEquals(511, result.getStatusCode());
        assertEquals("表中有相同数据", result.getErrorMessage());
    }

    @Test
    void testSaveWithDefaultAddress() {
        // 设置模拟的会话和请求
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("用户");
        when(session.getAttribute("userId")).thenReturn("1");

        AddressEntity address = new AddressEntity();
        address.setAddressName("Sample Name");
        address.setAddressPhone("1234567890");
        address.setAddressDizhi("Sample Address");
        address.setIsdefaultTypes(2);  // 默认地址

        List<AddressEntity> existingAddresses = new ArrayList<>();
        existingAddresses.add(address);

        // 模拟数据库操作
        when(addressService.selectOne(any())).thenReturn(null);
        when(addressService.selectList(any())).thenReturn(existingAddresses);
        when(addressService.updateBatchById(any())).thenReturn(true);
        when(addressService.insert(any())).thenReturn(true);

        R result = addressController.save(address, request);

        assertTrue(result.isSuccess());
        assertNull(result.getErrorMessage());
        verify(addressService, times(1)).updateBatchById(any(List.class));  // 确认是否调用了更新默认地址
    }

    void testUpdateWithUniqueAddress() {
        // 设置模拟的会话和请求
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("用户");
        when(session.getAttribute("userId")).thenReturn("1");

        AddressEntity address = new AddressEntity();
        address.setId(1);
        address.setYonghuId(1);
        address.setAddressName("Sample Name");
        address.setAddressPhone("1234567890");
        address.setAddressDizhi("Sample Address");
        address.setIsdefaultTypes(1);

        // 模拟数据库查询无相同数据
        when(addressService.selectOne(any())).thenReturn(null);
        when(addressService.updateById(any())).thenReturn(true);

        R result = addressController.update(address, request);

        assertTrue(result.isSuccess());
        assertNull(result.getErrorMessage());
    }

    @Test
    void testUpdateWithExistingAddress() {
        // 模拟会话
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("用户");
        when(session.getAttribute("userId")).thenReturn("1");

        AddressEntity address = new AddressEntity();
        address.setId(1);
        address.setYonghuId(1);
        address.setAddressName("Sample Name");
        address.setAddressPhone("1234567890");
        address.setAddressDizhi("Sample Address");
        address.setIsdefaultTypes(1);

        // 模拟数据库中已存在的地址
        when(addressService.selectOne(any())).thenReturn(new AddressEntity());

        R result = addressController.update(address, request);

        assertEquals(511, result.getStatusCode());
        assertEquals("表中有相同数据", result.getErrorMessage());
    }

    @Test
    void testUpdateDefaultAddress() {
        // 模拟会话和请求
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("用户");
        when(session.getAttribute("userId")).thenReturn("1");

        AddressEntity address = new AddressEntity();
        address.setId(1);
        address.setYonghuId(1);
        address.setAddressName("Sample Name");
        address.setAddressPhone("1234567890");
        address.setAddressDizhi("Sample Address");
        address.setIsdefaultTypes(2);  // 默认地址

        List<AddressEntity> existingAddresses = new ArrayList<>();
        existingAddresses.add(address);

        // 模拟数据库查询和更新操作
        when(addressService.selectOne(any())).thenReturn(null);
        when(addressService.selectList(any())).thenReturn(existingAddresses);
        when(addressService.updateBatchById(any())).thenReturn(true);
        when(addressService.updateById(any())).thenReturn(true);

        R result = addressController.update(address, request);

        assertTrue(result.isSuccess());
        assertNull(result.getErrorMessage());
        verify(addressService, times(1)).updateBatchById(any(List.class));  // 确认是否调用了更新默认地址
    }

    @Test
    void testDeleteSingleId() {
        Integer[] ids = new Integer[]{1};

        addressController.delete(ids);

        verify(addressService).deleteBatchIds(Arrays.asList(ids));
        assertTrue(true);  // 检查点，确保方法执行到此处
    }

    @Test
    void testDeleteMultipleIds() {
        Integer[] ids = new Integer[]{1, 2, 3};

        addressController.delete(ids);

        verify(addressService).deleteBatchIds(Arrays.asList(ids));
        assertTrue(true);  // 检查点，确保方法执行到此处
    }

    @Test
    void testDeleteNoIds() {
        Integer[] ids = new Integer[]{};

        addressController.delete(ids);

        verify(addressService).deleteBatchIds(Arrays.asList(ids));
        assertTrue(true);  // 检查点，确保方法执行到此处
    }

    @Test
    void testListWithDefaultOrder() {
        // 准备输入数据
        Map<String, Object> params = new HashMap<>();

        // 模拟页面返回数据
        PageUtils page = new PageUtils(new ArrayList<>(), 10, 10, 1);
        when(addressService.queryPage(any())).thenReturn(page);

        R result = addressController.list(params, request);

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
        when(addressService.queryPage(any())).thenReturn(page);

        R result = addressController.list(params, request);

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

        List<AddressView> mockList = new ArrayList<>();
        AddressView mockView = new AddressView();
        mockList.add(mockView);

        // 模拟页面返回数据
        PageUtils page = new PageUtils(mockList, 10, 10, 1);
        when(addressService.queryPage(any())).thenReturn(page);

        // 模拟字典表转换
        doNothing().when(dictionaryService).dictionaryConvert(any(AddressView.class), any(HttpServletRequest.class));

        R result = addressController.list(params, request);

        // 验证字典表转换是否被调用
        verify(dictionaryService, times(mockList.size())).dictionaryConvert(any(AddressView.class), any(HttpServletRequest.class));
        assertNotNull(result.getData());
        assertTrue(result.isSuccess());
    }

    @Test
    void testAddUniqueAddress() {
        AddressEntity address = new AddressEntity();
        address.setYonghuId(1);
        address.setAddressName("Sample Name");
        address.setAddressPhone("1234567890");
        address.setAddressDizhi("Sample Address");
        address.setIsdefaultTypes(1);

        // 模拟没有找到相同的地址
        when(addressService.selectOne(any())).thenReturn(null);
        when(addressService.insert(any())).thenReturn(true);

        R result = addressController.add(address, request);

        verify(addressService, never()).updateBatchById(anyList()); // 确认没有调用更新默认地址
        assertTrue(result.isSuccess());
    }

    @Test
    void testAddDuplicateAddress() {
        AddressEntity address = new AddressEntity();
        address.setYonghuId(1);
        address.setAddressName("Sample Name");
        address.setAddressPhone("1234567890");
        address.setAddressDizhi("Sample Address");
        address.setIsdefaultTypes(1);

        // 模拟数据库中已存在相同的地址
        when(addressService.selectOne(any())).thenReturn(new AddressEntity());

        R result = addressController.add(address, request);

        assertEquals(511, result.getStatusCode());
        assertEquals("表中有相同数据", result.getErrorMessage());
    }

    @Test
    void testAddDefaultAddress() {
        AddressEntity address = new AddressEntity();
        address.setYonghuId(1);
        address.setAddressName("Sample Name");
        address.setAddressPhone("1234567890");
        address.setAddressDizhi("Sample Address");
        address.setIsdefaultTypes(2); // 默认地址

        // 模拟没有找到相同的地址
        when(addressService.selectOne(any())).thenReturn(null);

        List<AddressEntity> existingAddresses = new ArrayList<>();
        existingAddresses.add(new AddressEntity());
        when(addressService.selectList(any())).thenReturn(existingAddresses);
        when(addressService.updateBatchById(anyList())).thenReturn(true);
        when(addressService.insert(any())).thenReturn(true);

        R result = addressController.add(address, request);

        verify(addressService).updateBatchById(anyList()); // 确认调用了更新默认地址
        assertTrue(result.isSuccess());
    }*/
}