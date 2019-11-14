package com.report.service.impl;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.report.dao.AbstractBaseDao;
import com.report.dao.UserDao;
import com.report.entity.ResultBean;
import com.report.entity.User;
import com.report.entity.UserRole;
import com.report.entity.response.LeaderResponse;
import com.report.entity.response.UserPageResponse;
import com.report.entity.response.UserResponse;
import com.report.entity.response.search.UserSearchListRequest;
import com.report.service.UserService;
import com.report.utils.Constants;
import com.report.utils.DataUtil;
import com.report.utils.PasswordGenerator;
import com.report.utils.SendMail;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl extends AbstractBaseDao implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    public SendMail sendMail;

    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public ResultBean getLeaders() throws Exception {
        List<LeaderResponse> report = userDao.getLeaders();
        return new ResultBean(report, "200", "OK");
    }

    @Override
    public ResultBean getUsersAddTeam() throws Exception {
        List<LeaderResponse> users = userDao.getUsers();
        List<LeaderResponse> usersInTeam = userDao.getUsersInTeam();
        for (LeaderResponse user : usersInTeam) {
            users.removeIf(e -> e.getUserId() == user.getUserId());
        }
        return new ResultBean(users, "200", "OK");
    }

    @Override
    public User findOne(String email) {
        User user = userDao.findOne(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        //        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        //        List<Role> roles = userDao.getRoles(user.getUserId());
        //        for (Role role : roles) {
        //            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        //        }
        return user;
    }

    @Override
    public ResultBean add(HttpServletRequest request, String json) throws Exception {
        User entity = new User();
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        if (DataUtil.hasMember(jsonObject, "email")) {
            String email = DataUtil.getJsonString(jsonObject, "email");
            if (userDao.checkMailExits(email)) {
                return new ResultBean(entity, "403", "Email da ton tai");
            }
            entity.setEmail(email);
        }
        entity.setCreated(DataUtil.getLocalDateTime());
        entity.setDelFlg(Constants.DEL_FLG_0);
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setCreatedbyUsername(email);
        String password = PasswordGenerator.generatePassword(8, Constants.ALPHA_CAPS + Constants.ALPHA);
        entity.setPassword(encoder().encode(password));
        this.getUserEntity(json, entity);
        sendMail.send(entity, password);
        userDao.add(entity);
        UserRole userRole = new UserRole();
        userRole.setUserId(entity.getUserId());
        if (DataUtil.hasMember(jsonObject, "role_id")) {
            userRole.setRoleId(DataUtil.getJsonInteger(jsonObject, "role_id"));
        }
        userRole.setCreated(DataUtil.getLocalDateTime());
        userRole.setCreatedbyUsername(email);
        userRole.setDelFlg(Constants.DEL_FLG_0);
        userDao.addUserRole(userRole);
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean update(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        User entity = null;
        if (DataUtil.hasMember(jsonObject, "email")) {
            String email = DataUtil.getJsonString(jsonObject, "email");
            entity = userDao.findOne(email);
        }
        entity.setLastmodified(DataUtil.getLocalDateTime());
        String delFlg = null;
        if (DataUtil.hasMember(jsonObject, "del_flg")) {
            delFlg = DataUtil.getJsonString(jsonObject, "del_flg");
        }
        String email = String.valueOf(DataUtil.getUserNameByToken(request));
        entity.setLastmodifiedbyUsername(email);
        UserRole userRole = userDao.getUserRole(entity.getUserId());
        if (delFlg.equals(Constants.DEL_FLG_1)) {
            entity.setDelFlg(Constants.DEL_FLG_1);
            userRole.setDelFlg(Constants.DEL_FLG_1);
        } else {
            this.getUserEntity(json, entity);
            userRole.setUserId(entity.getUserId());
            if (DataUtil.hasMember(jsonObject, "role_id")) {
                userRole.setRoleId(DataUtil.getJsonInteger(jsonObject, "role_id"));
            }
            userRole.setLastmodified(DataUtil.getLocalDateTime());
            userRole.setLastmodifiedbyUsername(email);
            userRole.setDelFlg(Constants.DEL_FLG_0);
        }
        userDao.updateUserRole(userRole);
        userDao.update(entity);

        return new ResultBean(entity, "200", "OK");
    }

    public void getUserEntity(String json, User entity) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        if (DataUtil.hasMember(jsonObject, "fullname")) {
            entity.setFullname(DataUtil.getJsonString(jsonObject, "fullname"));
        }
    }

    @Override
    public ResultBean reset(String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        User entity = null;
        String password = PasswordGenerator.generatePassword(8, Constants.ALPHA_CAPS + Constants.ALPHA);
        if (DataUtil.hasMember(jsonObject, "email")) {
            String email = DataUtil.getJsonString(jsonObject, "email");
            entity = userDao.findOne(email);
        }
        entity.setPassword(encoder().encode(password));
        sendMail.send(entity, password);
        userDao.update(entity);
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean getUsers(String fullname, String date, Integer page, Integer size) {
        UserSearchListRequest searchListRequest = new UserSearchListRequest();
        UserPageResponse response = null;
        searchListRequest.currentPage(page);
        searchListRequest.noRecordInPage(size);
        searchListRequest.addSearchField("fullname", fullname, false);
        searchListRequest.addSearchField("created", date, true);
        searchListRequest.addsSortField("userId DESC");
        if (!DataUtil.isEmpty(date) && Objects.isNull(DataUtil.convertStringToDate(date, "yyyy/MM/dd"))) {
            return new ResultBean(response, "200", "SAI DINH DANG");
        } 
        searchListRequest.addSearchField("delFlg", Constants.DEL_FLG_0, true);
        response = (UserPageResponse) userDao.findAll(searchListRequest);
        return new ResultBean(response, "200", "OK");
    }

    @Override
    public ResultBean changePass(HttpServletRequest request, String json) throws Exception {
        JsonObject jsonObject = DataUtil.getJsonObject(json);
        User entity = null;
        if (DataUtil.hasMember(jsonObject, "email")) {
            String email = DataUtil.getJsonString(jsonObject, "email");
            entity = userDao.findOne(email);
        }
        String password = null;
        if (DataUtil.hasMember(jsonObject, "password")) {
            password = DataUtil.getJsonString(jsonObject, "password");
        }
        String passwordOld = null;
        if (DataUtil.hasMember(jsonObject, "password_old")) {
            passwordOld = DataUtil.getJsonString(jsonObject, "password_old");
        }
        if (!Objects.isNull(passwordOld)) {
            if (!encoder().matches(passwordOld, entity.getPassword())) {
                return new ResultBean("404", "Sai mật khẩu cũ");
            }
        }
        if (!Objects.isNull(password)) {
            entity.setPassword(encoder().encode(password));
        }
        userDao.update(entity);
        return new ResultBean(entity, "200", "OK");
    }

    @Override
    public ResultBean getUsersByTeam(Integer team_id) {
        List<UserResponse> usersInTeam = userDao.getUsersByTeam(team_id);
        return new ResultBean(usersInTeam, "200", "OK");
    }
}
