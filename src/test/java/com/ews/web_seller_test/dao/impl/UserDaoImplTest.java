package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDaoImplTest {
    @InjectMocks
    private UserDaoImpl userDao;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private RoleService mockRoleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDao.connection = mockConnection;
    }
    @Test
    void insertUser() {
    }

    @Test
    void editUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void testGetUser() {
    }

    @Test
    void getAllUser() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Mocking the result set
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("full_name")).thenReturn("John Doe");
        when(mockResultSet.getString("email")).thenReturn("john.doe@example.com");
        when(mockResultSet.getString("phone")).thenReturn("1234567890");
        when(mockResultSet.getString("address")).thenReturn("123 Street");
        when(mockResultSet.getString("username")).thenReturn("johndoe");
        when(mockResultSet.getString("password")).thenReturn("password");
        when(mockResultSet.getString("gender")).thenReturn("Male");
        when(mockResultSet.getString("avatar")).thenReturn("avatar.png");
        when(mockResultSet.getInt("role_id")).thenReturn(1);
        when(mockResultSet.getDate("created_at")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(mockResultSet.getDate("updated_at")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(mockRoleService.getRole(anyInt())).thenReturn(new Role());

        // Act
        List<User> users = userDao.getAllUser();

        // Assert
        assertNotNull(users);
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getFull_name());
        assertEquals("john.doe@example.com", user.getEmail());

        // Verify the interactions
        verify(mockConnection).prepareStatement(anyString());
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet, times(2)).next();
        verify(mockRoleService).getRole(anyInt());
    }

    @Test
    void searchUser() {
    }

    @Test
    void checkExistEmail() {
    }

    @Test
    void checkExistUsername() {
    }
}